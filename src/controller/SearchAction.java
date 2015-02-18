package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.PhotoDAO;
import model.Photo_FavorDAO;
import model.UserDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Photo_Favor;
import databeans.User;
import formbeans.SearchForm;
import formbeans.ViewTagsForm;

public class SearchAction extends Action {
	private FormBeanFactory<SearchForm> formBeanFactory = FormBeanFactory
			.getInstance(SearchForm.class);

	private PhotoDAO photoDAO;
	private UserDAO userDAO;
	private Photo_FavorDAO photo_FavorDAO;

	// constructor
	public SearchAction(Model model) {
		userDAO = model.getUserDAO();
		photoDAO = model.getPhotoDAO();
		photo_FavorDAO = model.getPhoto_FavorDAO();
	}

	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

	// get action name
	public String getName() {
		return "search.do";
	}

	// return next page name
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		// get session
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null)
			return "login.jsp";

		// Set up HashSet for likes and dislikes;
		HashSet<String> hsl = new HashSet<String>();
		HashSet<String> hsd = new HashSet<String>();
		session.setAttribute("hsl", hsl);
		session.setAttribute("hsd", hsd);

		// get form, set form attribute
		try {
			SearchForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);

			// if no parameters passed in
			if (!form.isPresent()) {
				return "search.jsp";
			}

			// check error, if has
			errors.addAll(form.getValidationErrors());
			if (errors.size() > 0) {

				return "search.jsp";
			}

			String location = form.getLocation();
			String description = form.getDescription();
			session.setAttribute("location", location);
			session.setAttribute("description", description);

			return "showWrapper.do";

		} catch (FormBeanException e) {
			return "search.jsp";
		}
	}
}