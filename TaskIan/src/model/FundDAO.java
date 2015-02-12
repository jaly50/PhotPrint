package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databeans.Fund;

public class FundDAO extends GenericDAO<Fund> {
	public FundDAO(String tableName, ConnectionPool pool)
			throws DAOException {
		super(Fund.class, tableName, pool);

	}

	public void create(Fund newFund) throws RollbackException {
		createAutoIncrement(newFund);
	}


	public Fund[] getFunds() throws RollbackException {
		Fund[] funds = match();

		if(funds==null || funds.length == 0) 
			return null;
		return funds;
	}
	
	public Fund getFund(int fund_id) throws RollbackException {
		return read(fund_id);
	}
	
	public Fund getFund(String symbol) throws RollbackException {
		Fund[] fund = match(MatchArg.equals("symbol", symbol));
		if (fund ==null || fund.length<1) {
			return null;
		}
		return fund[0];
	}
	public Fund getFundFromName(String name) throws RollbackException {
		Fund[] fund = match(MatchArg.equals("name", name));
		if (fund ==null || fund.length<1) {
			return null;
		}
		return fund[0];	
	}
	
	public int getFund_ID(String symbol) throws RollbackException {
		Fund[] fund = match(MatchArg.equals("symbol", symbol));
		return fund[0].getFund_id();
	}

}
