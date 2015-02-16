package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.PhotoDAO;
import model.UserDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FileProperty;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import com.hmkcode.FileUploadServlet;
import com.hmkcode.vo.FileMeta;

import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.UploadedMedia;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import databeans.Photo;
import databeans.User;
import formbeans.UploadPhotoForm;

/*
 * Uploads a file from the user.  If successful, sets the "userList"
 * and "photoList" request attributes, creates a new Photo bean with the
 * image, and forward (back to) manage.jsp.
 * 
 * Note that to upload a file, the multipart encoding type is used
 * in the HTML form.  This needs to be specially parsed.  The FormBeanFactory
 * can do this, but to do it, the FormBeanFactory uses the Jakarta Commons FileUpload
 * package (org.apache.commons.fileupload).
 * These classes are in the commons-fileupload-x.x.jar file in the webapp's
 * WEB-INF/lib directory.  See the User Guide on
 * http://jakarta.apache.org/commons/fileupload for details.
 */
public class UploadAction extends Action {
	private FormBeanFactory<UploadPhotoForm> formBeanFactory = FormBeanFactory
			.getInstance(UploadPhotoForm.class);

	private PhotoDAO photoDAO;
	private UserDAO userDAO;

	public UploadAction(Model model) {
		photoDAO = model.getPhotoDAO();
		userDAO = model.getUserDAO();
	}

	public String getName() {
		return "upload.do";
	}

	public String perform(HttpServletRequest request) {
		List<FileMeta> files = FileUploadServlet.files;
		System.out.println("In upload action, we receive files: "
				+ files.size());
		HttpSession session = request.getSession();
		;

		// Set up HashSet for likes and dislikes;
		HashSet<String> hsl = new HashSet<String>();
		HashSet<String> hsd = new HashSet<String>();
		session.setAttribute("hsl", hsl);
		session.setAttribute("hsd", hsd);

		// Set up the errors list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			User user = (User) request.getSession(false).getAttribute("user");

			UploadPhotoForm form = formBeanFactory.create(request);
			errors.addAll(form.getValidationErrors());
			if (errors.size() > 0)
				return "error.jsp";

			// Upload to twitter
			update(request, form);

			session.setAttribute("location", form.getLocation());
			// Add those photo to database
			for (int i = 0; i < files.size(); i++) {
				FileMeta temp = files.get(i);
				byte[] bFile = temp.getBytes();
				Photo photo = new Photo();
				photo.setBytes(bFile);

				if (form.getDescription().length() > 0) {
					photo.setDescription(fixBadChars(form.getDescription()));
				} else {
					photo.setDescription(fixBadChars(temp.getFileName()));
				}

				photo.setContentType(temp.getFileType());
				photo.setOwner(user.getUserName());
				photo.setLocation(form.getLocation());
				photoDAO.create(photo);
				request.setAttribute("description", form.getDescription());
				session.setAttribute("description", form.getDescription());

			}

			// Clean the cached files
			FileUploadServlet.files = new LinkedList<FileMeta>();
			
			//Set successfuly message
			String success ="You successfully posted tweet with photos to Twitter. View more similar pictures, Please click <a href=\"showWrapper.do\">here</a>.";
			request.setAttribute("success", success);

			return "showTweet.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "manage.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "manage.jsp";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "manage.jsp";
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Upload 130");
		return "showWrapper.do";
	}

	private void update(HttpServletRequest request, UploadPhotoForm form)
			throws IOException, TwitterException {
		request.setCharacterEncoding("UTF-8");

		// Get files --pictures
		List<FileMeta> files = FileUploadServlet.files;
		String text = form.getDescription();

		Twitter twitter = (Twitter) request.getSession()
				.getAttribute("twitter");

		// Turn fileMeta to files
		File[] fs = new File[files.size()];
		for (int i = 0; i < files.size(); i++) {
			FileMeta temp = files.get(i);
			byte[] bFile = temp.getBytes();
			File yourFile = new File(fixBadChars(form.getDescription()) + i);
			if (!yourFile.exists()) {
				yourFile.createNewFile();
			}
			System.out.println(yourFile.getAbsolutePath());
			FileOutputStream fileOuputStream = new FileOutputStream(yourFile,
					false);

			fileOuputStream.write(bFile);
			fileOuputStream.close();
			fs[i] = yourFile;
		}

		// Get thier media ID
		long[] mediaIds = new long[files.size()];
		for (int i = 0; i < files.size(); i++) {
			UploadedMedia media = twitter.uploadMedia(fs[i]);
			System.out.println("Uploaded: id=" + media.getMediaId() + ", w="
					+ media.getImageWidth() + ", h=" + media.getImageHeight()
					+ ", type=" + media.getImageType() + ", size="
					+ media.getSize());
			mediaIds[i] = media.getMediaId();
		}
		StatusUpdate update = new StatusUpdate(text);
		update.setMediaIds(mediaIds);
		Status status = twitter.updateStatus(update);
		System.out.println("Successfully updated the status to ["
				+ status.getText() + "][" + status.getId() + "].");

	}

	private String fixBadChars(String s) {
		if (s == null || s.length() == 0)
			return s;

		Pattern p = Pattern.compile("[<>\"&]");
		Matcher m = p.matcher(s);
		StringBuffer b = null;
		while (m.find()) {
			if (b == null)
				b = new StringBuffer();
			switch (s.charAt(m.start())) {
			case '<':
				m.appendReplacement(b, "&lt;");
				break;
			case '>':
				m.appendReplacement(b, "&gt;");
				break;
			case '&':
				m.appendReplacement(b, "&amp;");
				break;
			case '"':
				m.appendReplacement(b, "&quot;");
				break;
			default:
				m.appendReplacement(b, "&#" + ((int) s.charAt(m.start())) + ';');
			}
		}

		if (b == null)
			return s;
		m.appendTail(b);
		return b.toString();
	}
}
