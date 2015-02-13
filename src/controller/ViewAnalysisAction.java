package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;

import databeans.Location;
import databeans.LocationData;
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
			String[] locations = new String[photos.length];
			for (int i = 0; i < photos.length; i++) {
				locations[i] = photos[i].getLocation();
			}
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			for (int i = 0; i < locations.length; i++) {
				if (!map.containsKey(locations[i])) {
					map.put(locations[i], 1);
				} else {
					map.put(locations[i], map.get(locations[i]) + 1);
				}
			}
			Set<String> keySet = map.keySet();
			ArrayList<LocationData> locationsData = new ArrayList<LocationData>();
			for (String s : keySet) {
				LocationData locationData = new LocationData(s, map.get(s));
				locationsData.add(locationData);
			}
			request.setAttribute("locationsData", locationsData);
			return "view-analysis.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "view-analysis.jsp";
		}
	}
}

