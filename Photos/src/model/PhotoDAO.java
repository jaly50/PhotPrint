package model;

import java.util.Arrays;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.Favorite;

public class PhotoDAO extends GenericDAO<Favorite> {
	public PhotoDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(Favorite.class, tableName, pool);
	}

	public void create(Favorite newPhoto) throws RollbackException {
		try {
			Transaction.begin();
			Favorite[] oldList = match(MatchArg.equals("owner",newPhoto.getOwner()));
			int maxPos = 0;
			for (Favorite p : oldList) {
				if (p.getPosition() > maxPos) maxPos = p.getPosition();
			}
			
			newPhoto.setPosition(maxPos + 1);
			createAutoIncrement(newPhoto);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}

	public void delete(int id, String owner) throws RollbackException {
		try {
			Transaction.begin();
    		Favorite p = read(id);

    		if (p == null) {
				throw new RollbackException("Photo does not exist: id="+id);
    		}

    		if (!owner.equals(p.getOwner())) {
				throw new RollbackException("Photo not owned by "+owner);
    		}

			delete(id);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public Favorite[] getPhotos(String owner) throws RollbackException {
		Favorite[] list = match(MatchArg.equals("owner",owner));
		Arrays.sort(list);
		return list;
	}
	
	public Favorite[] moveDown(int id, String owner) throws RollbackException {
		try {
			Transaction.begin();
			Favorite[] list = match(MatchArg.equals("owner",owner));
			Arrays.sort(list);
			
			int index = -1;
			for (int i=0; i<list.length; i++) {
				if (list[i].getId() == id) index = i;
			}
			
			if (index == -1) throw new RollbackException("Photo not owned by "+owner);
			if (index == list.length-1) throw new RollbackException("Photo already at bottom of list");

			swapPositions(list[index],list[index+1]);
			update(list[index]);
			update(list[index+1]);
			
			Transaction.commit();
			
			// Resort and return
			Arrays.sort(list);
			return list;
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public Favorite[] moveUp(int id, String owner) throws RollbackException {
		try {
			Transaction.begin();
			Favorite[] list = match(MatchArg.equals("owner",owner));
			Arrays.sort(list);
			
			int index = -1;
			for (int i=0; i<list.length; i++) {
				if (list[i].getId() == id) index = i;
			}
			
			if (index == -1) throw new RollbackException("Photo not owned by "+owner);
			if (index == 0) throw new RollbackException("Photo already at top of list");

			swapPositions(list[index],list[index-1]);
			update(list[index-1]);
			update(list[index]);
			
			Transaction.commit();
			
			// Resort and return
			Arrays.sort(list);
			return list;
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	private void swapPositions(Favorite p1, Favorite p2) {
		int temp = p1.getPosition();
		p1.setPosition(p2.getPosition());
		p2.setPosition(temp);
	}
}
