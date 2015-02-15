package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import databeans.Location;
import databeans.Photo;
import databeans.User;
import model.Model;
import model.PhotoDAO;
import model.Photo_FavorDAO;
import model.UserDAO;

public class IndexAction extends Action {
	private PhotoDAO photoDAO;
	private UserDAO userDAO;
	private Photo_FavorDAO photo_FavorDAO;
	
	// constructor
	public IndexAction(Model model) {
		userDAO = model.getUserDAO();
		photoDAO = model.getPhotoDAO();
		photo_FavorDAO = model.getPhoto_FavorDAO();
	}
	public String getName() {
		return "index.do"; 
	}
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		// get session
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null)	return "login.jsp";
		try {
			Photo[] photos = photoDAO.getPhotos(user.getUserName());
			
			request.setAttribute("photos", photos);
			return "index.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "index.jsp";
		}
	}
}
