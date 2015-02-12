package model;

import java.util.Date;
import java.util.HashMap;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.Customer;
import databeans.Fund_Price_History;
import databeans.Position;

public class PositionDAO extends GenericDAO<Position> {
	public PositionDAO(String tableName, ConnectionPool pool)
			throws DAOException {
		super(Position.class, tableName, pool);
	}

	/*
	 * Given a customer id, return the positions he have
	 */
	public Position[] getPositions(int customer_id) throws RollbackException {
		Position[] list = match(MatchArg.equals("customer_id", customer_id));
		return list;
	}

	public Position getPosition(int customer_id, int fund_id)
			throws RollbackException {
		MatchArg matchArg1 = MatchArg.equals("customer_id", customer_id);
		MatchArg matchArg2 = MatchArg.equals("fund_id", fund_id);
		Position[] position = match(MatchArg.and(matchArg1, matchArg2));
		if (position == null || position.length == 0)
			return null;
		return position[0];
	}

	public void updatePosition(int customer_id) throws RollbackException {
		MatchArg matchArg1 = MatchArg.equals("customer_id", customer_id);
		Position[] pos = match(MatchArg.and(matchArg1));
		for (int i = 0; i < pos.length; i++) {
			try {

				Transaction.begin();
				pos[i].setShares(pos[i].getAvailableShares());
				update(pos[i]);
				Transaction.commit();
			}

			finally {
				if (Transaction.isActive())
					Transaction.rollback();
			}
		}
	}

	
	public void update(long value, Position pos) throws RollbackException {
		// TODO Auto-generated method stub
		try {

			if (pos.getAvailableShares() >= value) {
				Transaction.begin();
				update(pos);
				Transaction.commit();
			} else {
				throw new RollbackException(
						"Available share less than shares to be sold");
			}

		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}

	public void transiUpdate(Position pos, long shareIncre)
			throws RollbackException {
		// TODO Auto-generated method stub
		try {

			Transaction.begin();
			pos.setAvailableShares(pos.getAvailableShares() + shareIncre);
			update(pos);
			Transaction.commit();
		}

		finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}

	public void totalShareUpdate(Position pos) throws RollbackException {
		// TODO Auto-generated method stub
		try {

			Transaction.begin();
			pos.setShares(pos.getAvailableShares());
			update(pos);
			Transaction.commit();
		}

		finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}

	public Position getCustomerShares(int customer_id, int fund_id)
			throws RollbackException {
		Position[] position = match(MatchArg.and(
				MatchArg.equals("customer_id", customer_id),
				MatchArg.equals("fund_id", fund_id)));
		if (position.length == 0)
			return null;
		return position[0];
	}

}
