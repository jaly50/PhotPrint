package controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import databeans.Location;
import model.Model;
import model.PhotoDAO;
import model.UserDAO;
import databeans.Photo;
import databeans.User;

import org.genericdao.RollbackException;

import utils.GeoInfo;

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
			
			// set location
			ArrayList<Location> myLocations = new ArrayList<Location>();
			Comparator<Photo> comparator = new Comparator<Photo>() {
				public int compare(Photo p1, Photo p2) {
					return p1.getLocation().compareTo(p2.getLocation());
				}
			};
			Arrays.sort(photos, comparator);
			for (int i = 0; i < photos.length; i++) {
				if (i > 0 && photos[i].getLocation().equals(photos[i - 1].getLocation())) {
					continue;
				}
				myLocations.add(new Location(photos[i]));
			}
			int length = myLocations.size();
			for (Location location : myLocations) {
				System.out.println(location.getLocation());
			}
			
			request.setAttribute("myLocations", myLocations);
			request.setAttribute("length", length);
			return "view-photprint.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "view-photprint.jsp";
		}
	}
}

