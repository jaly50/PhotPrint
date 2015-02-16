package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.PhotoDAO;
import model.Photo_FavorDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Photo_Favor;
import databeans.WrapperTable;
import formbeans.LikeForm;
import formbeans.TagsForm;

public class showTagsAction extends Action {

	private FormBeanFactory<TagsForm> formBeanFactory = FormBeanFactory
			.getInstance(TagsForm.class);
	private PhotoDAO photoDAO;
	private Photo_FavorDAO photo_FavorDAO;

	// constructor
	public showTagsAction(Model model) {
		photoDAO = model.getPhotoDAO();
		photo_FavorDAO = model.getPhoto_FavorDAO();
	}

	// get action name
	public String getName() {
		return "showTags.do";
	}

	public String perform(HttpServletRequest request) {
		// get session
		HttpSession session = request.getSession(false);
		String location = (String) session.getAttribute("location");
		request.setAttribute("location", location);
		TagsForm form;
		
		// set error attribute
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		// get form, set form attribute
		try {
			form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			if (!form.isPresent()) {
				return "showTags.jsp";

			}
			
			String tag = form.getTag();
			//System.out.println("Tag Action location " + location);
			//System.out.println("Tag Action tag " + tag);
			Photo_Favor[] photo_favor = photo_FavorDAO.getWrappers(location.trim(),tag.trim());
			if (photo_favor==null || photo_favor.length == 0) {
				//System.out.println("Location 64");
				return "showTags.do";
			}
			System.out.println(66 + photo_favor[0].getPhoto());
			List<WrapperTable> WrapperTable = new ArrayList<WrapperTable>();
			for (Photo_Favor p : photo_favor) {
				WrapperTable wTableRow = new WrapperTable();
				wTableRow.setPhoto(p.getPhoto());
				wTableRow.setUrl(p.getUrl());
				wTableRow.setTitle(p.getTitle());
				String tags[] = new String[1];
				tags[0] = p.getTag();
				wTableRow.setTags(tags);
				wTableRow.setCount_like(p.getCount_Like());
				wTableRow.setCount_dislike(p.getCount_Dislike());
				WrapperTable.add(wTableRow);
				
			}
			request.setAttribute("WrapperTable", WrapperTable);
			if (!form.isPresent()) {
				return "showTags.jsp";
			}
			
			

		} catch (FormBeanException e) {
			errors.add("Formbean Exception, please contact the administrator.");
			return "showTags.jsp";

		} catch (RollbackException e1) {
			errors.add(e1.getMessage());
			return "showTags.jsp";
		}
		return "showTags.jsp";
	}

}
