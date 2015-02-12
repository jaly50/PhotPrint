package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import model.Model;
import model.PhotoDAO;
import model.UserDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FileProperty;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
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
	private FormBeanFactory<UploadPhotoForm> formBeanFactory = FormBeanFactory.getInstance(UploadPhotoForm.class);

	private PhotoDAO photoDAO;
	private UserDAO  userDAO;
	
	public UploadAction(Model model) {
    	photoDAO = model.getPhotoDAO();
    	userDAO  = model.getUserDAO();
	}

	public String getName() { return "upload.do"; }

    public String perform(HttpServletRequest request)  {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {
            // Set up user list for nav bar
			request.setAttribute("userList",userDAO.getUsers());

			User user = (User) request.getSession(false).getAttribute("user");
        	Photo[] photoList = photoDAO.getPhotos(user.getUserName());
	        request.setAttribute("photoList",photoList);

			UploadPhotoForm form = formBeanFactory.create(request);
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() > 0) return "error.jsp";
            
//	        String button = form.getButton();
	//        if (button!=null && button.equals("Upload To Twitter")) {
	        //Upload to twitter
	        	update(request,form);
	  //      	return "success.jsp";
	   //     }
	        
	        
	        FileProperty fileProp = form.getFile();
	        

	        
	    	byte[] bFile = fileProp.getBytes();
	    	File yourFile = new File("task8/"+fixBadChars(form.getCaption()));
	    	if(!yourFile.exists()) {
	    	    yourFile.createNewFile();
	    	} 
	    	System.out.println(yourFile.getAbsolutePath());
			FileOutputStream fileOuputStream = 
	                  new FileOutputStream(yourFile,false); 
			
		    fileOuputStream.write(bFile);
		    fileOuputStream.close();
	        
	        
	        
			Photo photo = new Photo();  // id & position will be set when created
			photo.setBytes(fileProp.getBytes());
			
			
			if (form.getCaption().length() > 0) {
				photo.setDescription(fixBadChars(form.getCaption()));
			} else {
				photo.setDescription(fixBadChars(fileProp.getFileName()));
			}
			photo.setContentType(fileProp.getContentType());
			photo.setOwner(user.getUserName());
			photo.setLocation(form.getLocation());
			photoDAO.create(photo);
			
		

			// Update photoList (there's now one more on the list)
        	Photo[] newPhotoList = photoDAO.getPhotos(user.getUserName());
	        request.setAttribute("photoList",newPhotoList);
	        return "manage.jsp";
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
			return "manage.jsp";
		}
    }
    
    private void update(HttpServletRequest request, UploadPhotoForm form) throws IOException, TwitterException {
    	 request.setCharacterEncoding("UTF-8");
         String text = form.getCaption();
         if (form.getTag()!=null && form.getTag().length()>0) {
        	 text +=" #"+form.getTag();
         }
         Twitter twitter = (Twitter)request.getSession().getAttribute("twitter");
    	
    	// Create the File 
    	FileProperty fileProp = form.getFile(); 
	    	byte[] bFile = fileProp.getBytes();
	    	File yourFile = new File(fixBadChars(form.getCaption()));
	    	if(!yourFile.exists()) {
	    	    yourFile.createNewFile();
	    	} 
	    	System.out.println(yourFile.getAbsolutePath());
			FileOutputStream fileOuputStream = 
	                  new FileOutputStream(yourFile,false); 
			
		    fileOuputStream.write(bFile);
		    fileOuputStream.close();
  
	             StatusUpdate sta = new StatusUpdate(text);
	             sta.setMedia(yourFile);
	            Status status = twitter.updateStatus(sta);  
	            System.out.println("Successfully updated the status to [" + status.getText() + "].");  
	           
	     
	}

	private String fixBadChars(String s) {
		if (s == null || s.length() == 0) return s;
		
		Pattern p = Pattern.compile("[<>\"&]");
        Matcher m = p.matcher(s);
        StringBuffer b = null;
        while (m.find()) {
            if (b == null) b = new StringBuffer();
            switch (s.charAt(m.start())) {
                case '<':  m.appendReplacement(b,"&lt;");
                           break;
                case '>':  m.appendReplacement(b,"&gt;");
                           break;
                case '&':  m.appendReplacement(b,"&amp;");
                		   break;
                case '"':  m.appendReplacement(b,"&quot;");
                           break;
                default:   m.appendReplacement(b,"&#"+((int)s.charAt(m.start()))+';');
            }
        }
        
        if (b == null) return s;
        m.appendTail(b);
        return b.toString();
    }
}
