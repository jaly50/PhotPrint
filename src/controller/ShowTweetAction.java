package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.UserDAO;

public class ShowTweetAction extends Action {

		private UserDAO userDAO;

	public ShowTweetAction(Model model) {
		userDAO = model.getUserDAO();
	}

	public String getName() { return "showTweet.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        HttpSession session = request.getSession();
        String twitter="https://twitter.com/"+session.getAttribute("user");
        request.setAttribute("twitter", twitter);
	        return "showTweet.jsp";
       
    }


}
