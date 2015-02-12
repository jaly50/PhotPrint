package model;

import java.util.Arrays;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.Favorite;
import databeans.Tweetlist;

public class TweetlistDAO extends GenericDAO<Tweetlist> {
	public TweetlistDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(Tweetlist.class, tableName, pool);
	}

	public void create(Tweetlist l) throws RollbackException {
		try {
			Transaction.begin();
			
			
			createAutoIncrement(l);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}

	public Tweetlist[] getTweets() throws RollbackException {
		Tweetlist[] list = match();
		//Arrays.sort(list);
		return list;
	}
	
}
