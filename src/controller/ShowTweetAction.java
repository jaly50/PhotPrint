package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import databeans.User;
import model.Model;
import model.UserDAO;

public class ShowTweetAction extends Action {

		private UserDAO userDAO;

	public ShowTweetAction(Model model) {
		userDAO = model.getUserDAO();
	}

	public String getName() { return "showTweet.do"; }
    
    public String perform(HttpServletRequest request) {
    	User user = (User) request.getSession(false).getAttribute("user");
		if (user==null)
			return "login.do";
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        HttpSession session = request.getSession();
        String twitter="https://twitter.com/"+user;
        request.setAttribute("twitter", twitter);
	        return "showTweet.jsp";
       
    }


}
