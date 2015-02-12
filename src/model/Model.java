package model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;

public class Model {
	private PhotoDAO photoDAO;
	private UserDAO  userDAO;
	private Photo_FavorDAO photo_FavorDAO;
	private TweetlistDAO tweetlistDAO;

	public Model(ServletConfig config) throws ServletException {
		try {
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL    = config.getInitParameter("jdbcURL");
			
			ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL);
			userDAO  = new UserDAO("user", pool);
			photoDAO = new PhotoDAO("photo", pool);
			tweetlistDAO = new TweetlistDAO("tweetlist", pool);
			photo_FavorDAO = new Photo_FavorDAO("photoFavor",pool);
		} catch (DAOException e) {
			throw new ServletException(e);
		}
	}
	
	public PhotoDAO getPhotoDAO() { return photoDAO; }
	public UserDAO  getUserDAO()  { return userDAO;  }
	public Photo_FavorDAO getPhoto_FavorDAO() {
		return photo_FavorDAO;
	}

	public TweetlistDAO getTweetlistDAO() {
		return tweetlistDAO;
		
	}
}
