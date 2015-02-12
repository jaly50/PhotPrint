package controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.PhotoDAO;
import model.UserDAO;
import databeans.Photo;
import databeans.User;

import org.genericdao.RollbackException;

public class ViewPhotPrintAction extends Action {
	private PhotoDAO photoDAO;
	private UserDAO userDAO;
	
	// constructor
	public ViewPhotPrintAction(Model model) {
		userDAO = model.getUserDAO();
		photoDAO = model.getPhotoDAO();
	}
	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	
	// get action name
	public String getName() {
		return "viewPhotPrint.do";
	}
	
	// return next page name
	public String perform(HttpServletRequest request) {
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
			return "view-photprint.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "view-photprint.jsp";
		}
	}
}

