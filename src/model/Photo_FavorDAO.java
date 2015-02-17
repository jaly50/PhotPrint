package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databeans.Photo;
import databeans.Photo_Favor;
import databeans.WrapperTable; 


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

	public void updatePhotoFavor(Photo_Favor photo_Favor) {
		try {
			Photo_Favor[] list = match(MatchArg.equals("photo",
					photo_Favor.getPhoto()));
			
			if (list == null || list.length < 1) {
				//System.out.println(list[0].getPhoto());
				//System.out.println("PHDAO36 Create" + photo_Favor.getPhoto());
				createPhotoFavor(photo_Favor);
			} else {
				//System.out.println("PHDAO39 Update" + photo_Favor.getPhoto());
				update(photo_Favor);
			}
				
		} catch (RollbackException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	public void updateLike(Photo_Favor photo_Favor) throws RollbackException {
		try {
			org.genericdao.Transaction.begin();
			int i = photo_Favor.getCount_Like();
			photo_Favor.setCount_Like(++i);
			System.out.println("PhotoFavDAO + 49 "
					+ photo_Favor.getCount_Like());
			update(photo_Favor);
			org.genericdao.Transaction.commit();
			
		} finally {
			if (org.genericdao.Transaction.isActive())
				org.genericdao.Transaction.rollback();
		}

	}

	public void updateDislike(Photo_Favor photo_Favor) throws RollbackException {
		try {
			org.genericdao.Transaction.begin();
			int dis = photo_Favor.getCount_Dislike();
			photo_Favor.setCount_Dislike(++dis);
			System.out.println("PhotoFavDAO + 49 "
					+ photo_Favor.getCount_Dislike());
			update(photo_Favor);
			org.genericdao.Transaction.commit();
		} finally {
			if (org.genericdao.Transaction.isActive())
				org.genericdao.Transaction.rollback();
		}

	}
	
	
	public int getCount_Like(String photo) throws RollbackException {
		Photo_Favor[] list = match(MatchArg.equals("photo", photo));
		if (list == null || list.length < 1) {
			return (int) (Math.random() * 100);
		}
		int count_like = list[list.length - 1].getCount_Like();
		return count_like;
	}

	public int getCount_Dislike(String photo) throws RollbackException {
		Photo_Favor[] list = match(MatchArg.equals("photo", photo));
		if (list == null || list.length < 1) {
			return (int) (Math.random() * 50);
		}
		int count_Dislike = list[list.length - 1].getCount_Dislike();

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
	
	public Photo_Favor[] getWrappers(String location, String tag)
			throws RollbackException {
		System.out.println("DAO location: " + location);
		System.out.println("DAO tag: " + tag);
		MatchArg matchArg1 = MatchArg.equals("tag", tag);
		MatchArg matchArg2 = MatchArg.equals("location", location);
		Photo_Favor[] photos = match(MatchArg.and(matchArg1, matchArg2));
		if (photos == null) {
			System.out.println("Wrapper + 114");
			return null;
		}
		return photos;
	}
	
	
	
	
}
