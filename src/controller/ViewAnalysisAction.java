package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;

import databeans.Photo;
import databeans.User;
import formbeans.ViewPhotosForm;
import model.Model;
import model.PhotoDAO;
import model.UserDAO;

public class ViewAnalysisAction extends Action {
	private PhotoDAO photoDAO;
	private UserDAO userDAO;
	
	// constructor
	public ViewAnalysisAction(Model model) {
		userDAO = model.getUserDAO();
		photoDAO = model.getPhotoDAO();
	}
	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		
	// get action name
	public String getName() {
		return "viewAnalysis.do"; 
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
		try {
			Photo[] photos = photoDAO.getPhotos(user.getUserName());
			if (photos == null || photos.length == 0) errors.add("No photos");
			request.setAttribute("photos", photos);
			return "view-analysis.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "view-analysis.jsp";
		}
	}
}

