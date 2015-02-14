package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databeans.Photo;
import databeans.Photo_Favor;

public class Photo_FavorDAO extends GenericDAO<Photo_Favor> {

	public Photo_FavorDAO(String tableName, ConnectionPool pool)
			throws DAOException {
		super(Photo_Favor.class, tableName, pool);

	}

	void createPhotoFavor(Photo_Favor photo_Favor) {
		try {
			create(photo_Favor);
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void updatePhotoFavor(Photo_Favor photo_Favor) {
		try {
			update(photo_Favor);
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public int getCount_Like(String photo) throws RollbackException {
		Photo_Favor[] list = match(MatchArg.equals("photo", photo));
		if (list == null || list.length < 1) {
			return 0;
		}
		int count_like = list[list.length-1].getCount_Like();
		
		return count_like;
	}
	
	
	public int getCount_Dislike(String photo) throws RollbackException {
		Photo_Favor[] list = match(MatchArg.equals("photo", photo));
		if (list == null || list.length < 1) {
			return 0;
		}
		int count_Dislike = list[list.length-1].getCount_Dislike();
		
		return count_Dislike;
	}
	
	public Photo_Favor[] getPhotos(String tag, String location)
			throws RollbackException {
		MatchArg matchArg1 = MatchArg.equals("tag", tag);
		MatchArg matchArg2 = MatchArg.equals("location", location);
		Photo_Favor[] photos = match(MatchArg.and(matchArg1, matchArg2));
		if (photos == null) {
			return null;
		}
		return photos;
	}
}
