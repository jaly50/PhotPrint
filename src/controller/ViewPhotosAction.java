package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.PhotoDAO;
import model.UserDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Photo;
import databeans.User;
import formbeans.ViewPhotosForm;

public class ViewPhotosAction extends Action {
	private FormBeanFactory<ViewPhotosForm> formBeanFactory = 
			FormBeanFactory.getInstance(ViewPhotosForm.class);
	
	private PhotoDAO photoDAO;
	private UserDAO userDAO;
	
	// constructor
	public ViewPhotosAction(Model model) {
		userDAO = model.getUserDAO();
		photoDAO = model.getPhotoDAO();
	}
	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	
	// get action name
	public String getName() {
		return "viewPhotos.do"; 
	}
	
	// return next page name
	public String perform(HttpServletRequest request) {
		System.out.println("here");
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		// get session
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null)	return "login.jsp";
		
		
		// get form, set form attribute
		try {
			ViewPhotosForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
							
							
			// check error, if has
			errors.addAll(form.getValidationErrors());
			if (errors.size() > 0) {
				return "view-photprint.jsp";
			}
							
			// if no error, get the clicked fund
			String location = form.getLocation();
			Photo[] photos = 
					photoDAO.getPhotoWithOwnerAndLocation(user.getUserName(), location);
			
			System.out.println(photos);
			if (photos == null || photos.length == 0) {
				errors.add("Photo item does not exist");
				return "view-photprint.jsp";
			}
							
			// set photos attribute
			request.setAttribute("photos", photos);
			request.setAttribute("description", photos[0].getDescription());
			request.setAttribute("location", location);
			return "view-photos.jsp";
					
		} catch (FormBeanException e) {
			return "view-photprint.jsp";
		} catch (RollbackException e) {
			return "view-photprint.jsp";
		} 		
	}
}


