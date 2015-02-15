package controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Photo_Favor;
import formbeans.IdForm;
import formbeans.LikeForm;
import model.Model;
import model.PhotoDAO;
import model.Photo_FavorDAO;

public class LikeAction extends Action {
	private FormBeanFactory<LikeForm> formBeanFactory = FormBeanFactory
			.getInstance(LikeForm.class);
	private PhotoDAO photoDAO;
	private Photo_FavorDAO photo_FavorDAO;

	// constructor
	public LikeAction(Model model) {
		photoDAO = model.getPhotoDAO();
		photo_FavorDAO = model.getPhoto_FavorDAO();
	}

	// get action name
	public String getName() {
		return "like.do";
	}

	public String perform(HttpServletRequest request) {
		// get session
		HttpSession session = request.getSession(false);
		LikeForm form;
		// set error attribute
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		// get form, set form attribute
		try {
			form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			if (!form.isPresent()) {
				return "showWrapper.jsp";

			}
			Photo_Favor photo_favor = photo_FavorDAO.read(form.getPhoto());
			HashSet hsl = (HashSet) session.getAttribute("hsl");
			if (!hsl.contains(form.getPhoto()))
				photo_FavorDAO.updateLike(photo_favor);
			
			
			

		} catch (FormBeanException e) {
			errors.add("Formbean Exception, please contact the administrator.");
			return "showWrapper.jsp";

		} catch (RollbackException e1) {
			errors.add(e1.getMessage());
			return "showWrapper.jsp";
		}
		return "showWrapper.do";
	}
}
