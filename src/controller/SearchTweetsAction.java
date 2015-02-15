package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Model;
import model.PhotoDAO;
import model.TweetlistDAO;
import model.UserDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import databeans.Tweetlist;
import databeans.User;
import formbeans.UploadPhotoForm;
import formbeans.UserForm;

/*
 * Looks up the photos for a given "user".
 * 
 * If successful:
 *   (1) Sets the "userList" request attribute in order to display
 *       the list of users on the navbar.
 *   (2) Sets the "photoList" request attribute in order to display
 *       the list of given user's photos for selection.
 *   (3) Forwards to list.jsp.
 */
public class SearchTweetsAction extends Action {
	private FormBeanFactory<UploadPhotoForm> formBeanFactory = FormBeanFactory.getInstance(UploadPhotoForm.class);

	private PhotoDAO photoDAO;
	private UserDAO  userDAO;
    private TweetlistDAO tweetlistDAO;
    public SearchTweetsAction(Model model) {
    	photoDAO = model.getPhotoDAO();
    	userDAO  = model.getUserDAO();
    	tweetlistDAO=model.getTweetlistDAO();
    }

    public String getName() { return "searchtweets.do"; }

    public String perform(HttpServletRequest request) {
        // Set up the request attributes (the errors list and the form bean so
        // we can just return to the jsp with the form if the request isn't correct)
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        Tweetlist[] tl=null;
		Tweetlist ll=new Tweetlist();
        try {
            // Set up user list for nav bar
			request.setAttribute("userList",userDAO.getUsers());

		//	UploadPhotoForm form = formBeanFactory.create(request);

	        ConfigurationBuilder cb = new ConfigurationBuilder();
	        cb.setDebugEnabled(true)
	          .setOAuthConsumerKey("xMVoz5JYv3EZU7mpf6oDIYnry")
	          .setOAuthConsumerSecret("shjy1fS7pRX1qZkjO3qzpYmnxYYajD4CfvLAZfoLvWV6dxuDy4")
	          .setOAuthAccessToken("3023934688-ieFb0F0iPvy32B5hbva6irYC6yKWd8PnKbkgdll")
	          .setOAuthAccessTokenSecret("H3ViKZSHYOE4kjUuIXIVHxbN4qKlxZlK1wgJQzvt0dfd1");
	        TwitterFactory tf = new TwitterFactory(cb.build());
	        Twitter twitter = tf.getInstance();
	        List<Status> tweets = null;
	        try {   
	        	    //String s[]=form.getCaption().split("#");
	        	String k="place#food#spinach";
	        	String s[]=k.split("#");    
	        	String c="";
	        		for(String b: s)
	                	  c=c+" "+b;
	            Query query = new Query(c);
	            query.count(100);
	            QueryResult result;
	           
	            do {
	                result = twitter.search(query);
	                tweets = result.getTweets();
	               
	                for (Status tweet : tweets) {
	                     ll.setUserScreenName(tweet.getUser().getScreenName());
	                     ll.setUserTweet(tweet.getText());
	                     tweetlistDAO.create(ll);
	                	 tl=tweetlistDAO.getTweets();
	                    // System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
	                }

                	System.out.println("Checking tweets fetch");
	                for(Tweetlist t:tl)
	                	System.out.println(t.getUserScreenName()+"--"+t.getUserTweet());
	            } while ((query = result.nextQuery()) != null);
	            System.exit(0);
	        } catch (TwitterException te) {
	            te.printStackTrace();
	            System.out.println("Failed to search tweets: " + te.getMessage());
	            System.exit(-1);
	        }
	        request.setAttribute("tweetslist",tl);
	        return "list.jsp";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        } //catch (FormBeanException e) {
        //	errors.add(e.getMessage());
        //	return "error.jsp";
        //}
    }
}
