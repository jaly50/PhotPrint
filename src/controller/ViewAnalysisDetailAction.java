package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Location;
import databeans.LocationData;
import databeans.Photo;
import databeans.Photo_Favor;
import databeans.User;
import formbeans.ViewPhotosForm;
import formbeans.ViewTagsForm;
import model.Model;
import model.PhotoDAO;
import model.Photo_FavorDAO;
import model.UserDAO;

public class ViewAnalysisDetailAction extends Action {
	private FormBeanFactory<ViewTagsForm> formBeanFactory = 
			FormBeanFactory.getInstance(ViewTagsForm.class);
	
	private PhotoDAO photoDAO;
	private UserDAO userDAO;
	private Photo_FavorDAO photo_FavorDAO;
	
	// constructor
	public ViewAnalysisDetailAction(Model model) {
		userDAO = model.getUserDAO();
		photoDAO = model.getPhotoDAO();
		photo_FavorDAO = model.getPhoto_FavorDAO();
	}
	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		
	// get action name
	public String getName() {
		return "viewAnalysisDetail.do"; 
	}
		
	// return next page name
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
			
		// get session
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null)	return "login.jsp";
		// get form, set form attribute
		try {
			ViewTagsForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
									
			// if no parameters passed in
			if (!form.isPresent()) {
				return "view-analysis.jsp";
			}
									
			// check error, if has
			errors.addAll(form.getValidationErrors());
			if (errors.size() > 0) {
				
				return "view-analysis-detail.jsp";
			}
			
			String tag = form.getTag();
			System.out.println("TTTTTTTTTTTTT" + tag);
			String location = "Pittsburgh, PA, United States";
//			String location = form.getLocation();
			System.out.println("LLLLLLLLLLLLLL" + location);
			request.setAttribute("tag", tag);
			request.setAttribute("location", location);
			
			// if no error, get the clicked fund
			Photo_Favor[] photoFavors = 
							photo_FavorDAO.getPhotos(tag, location);
									
			if (photoFavors == null || photoFavors.length == 0) {
				errors.add("Photo item does not exist");
				return "view-analysis-detail.jsp";
			}
			
			Comparator<Photo_Favor> comparator = new Comparator<Photo_Favor>() {
				public int compare(Photo_Favor p1, Photo_Favor p2) {
					return p2.getCount_Like() - p1.getCount_Like();
				}
			};
			Arrays.sort(photoFavors, comparator);
			
			if (photoFavors.length > 5) {
				Photo_Favor[] less = Arrays.copyOf(photoFavors, 5);
				for (int i = 0; i < less.length; i++) {
					System.out.println(less[i].getUrl());
				}				
				request.setAttribute("photoFavors", less);
			} else {
				for (int i = 0; i < photoFavors.length; i++) {
					System.out.println(photoFavors[i].getUrl());
				}	
				request.setAttribute("photoFavors", photoFavors);
			}
			return "view-analysis-detail.jsp";
							
		} catch (FormBeanException e) {
			return "view-analysis.jsp";
		} catch (RollbackException e) {
			return "view-analysis.jsp";
		} 		
	}
}


