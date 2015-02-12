package model;

import java.util.Date;
import java.util.HashMap;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databeans.Customer;
import databeans.Fund_Price_History;
import databeans.Position;
import databeans.Transaction;

public class Fund_Price_HistoryDAO extends GenericDAO<Fund_Price_History> {
	public Fund_Price_HistoryDAO(String tableName, ConnectionPool pool)
			throws DAOException, RollbackException {
		super(Fund_Price_History.class, tableName, pool);
	}


	/*
	 * Given a date, return all fund price history in that day
	 */
	public Fund_Price_History[] getFundPrice(Date price_date)
			throws RollbackException {
		Fund_Price_History[] Fund_Price_Historys = match(MatchArg.equals(
				"price_date", price_date));
		return Fund_Price_Historys;
	}

	public Fund_Price_History getFundPrice(Date price_date, int fund_id)
			throws RollbackException {
		MatchArg matchArg1 = MatchArg.equals("price_date", price_date);
		MatchArg matchArg2 = MatchArg.equals("fund_id", fund_id);
		Fund_Price_History Fund_Price_History[] = match(MatchArg.and(matchArg1,
				matchArg2));
		return Fund_Price_History[0];
	}

	public Fund_Price_History[] getFundPrice(String symbol)
			throws RollbackException {
		Fund_Price_History[] fund_Price_Historys = match(MatchArg.equals(
				"symbol", symbol));
		if (fund_Price_Historys.length == 0)
			return null;
		return fund_Price_Historys;

	}

	public long getCurrentPrice(String Symbol) throws RollbackException {

		long prices = 0;
		Fund_Price_History[] fundPriceHistorys = this.getFundPrice(Symbol);

		if (fundPriceHistorys == null || fundPriceHistorys.length < 1) {
			return -1;
		}
		prices = fundPriceHistorys[fundPriceHistorys.length - 1].getPrice();
		return prices;
	}

	public long getCurrentPrice(int fund_id) throws RollbackException {

		long prices = 0;
		Fund_Price_History[] fundPriceHistorys = this.getFundPrice(fund_id);

		if (fundPriceHistorys == null || fundPriceHistorys.length < 1) {
			return -1;
		}
		prices = fundPriceHistorys[fundPriceHistorys.length - 1].getPrice();
		return prices;
	}

	public Fund_Price_History getLatestFundPrice(String symbol)
			throws RollbackException {

		Fund_Price_History[] fund_Price_Historys = match(MatchArg.equals(
				"symbol", symbol));
		if (fund_Price_Historys == null || fund_Price_Historys.length == 0)
			return null;
		return fund_Price_Historys[fund_Price_Historys.length - 1];
	}

	public Fund_Price_History getLatestFundPrice(int fund_id)
			throws RollbackException {

		Fund_Price_History[] fund_Price_Historys = match(MatchArg.equals(
				"fund_id", fund_id));
		if (fund_Price_Historys == null || fund_Price_Historys.length == 0)
			return null;
		return fund_Price_Historys[fund_Price_Historys.length - 1];
	}

	public Fund_Price_History[] getFundPrice(int fund_id)
			throws RollbackException {
		Fund_Price_History[] fund_Price_Historys = match(MatchArg.equals(
				"fund_id", fund_id));
		if (fund_Price_Historys == null || fund_Price_Historys.length == 0)
			return null;

		return fund_Price_Historys;

	}

	public Date getLatestDate() throws RollbackException {
		Fund_Price_History[] fundPriceHistorys = match();
		if (fundPriceHistorys==null || fundPriceHistorys.length == 0)
			return null;
		return fundPriceHistorys[fundPriceHistorys.length - 1]
				.getPrice_date();
	}

	public void createFund_Price_History(Fund_Price_History fund_Price_History)
			throws RollbackException {
		try {
			org.genericdao.Transaction.begin();
			int i = fund_Price_History.getFund_id();
			System.out.println("create: " + i);
			Fund_Price_History fph = new Fund_Price_History();
			
			int id = 10;
			long price = 10;
			Date now = new Date();

			fph.setFund_id(i);
			fph.setPrice(price);
			fph.setPrice_date(now);
			
			
			create(fph);
			
			//create(fund_Price_History);
			org.genericdao.Transaction.commit();
		}

		finally {
			if (org.genericdao.Transaction.isActive())
				org.genericdao.Transaction.rollback();
		}
	}

	public void updateFund_Price_History(Fund_Price_History fund_Price_History)
			throws RollbackException {
		try {
			org.genericdao.Transaction.begin();
			int i = fund_Price_History.getFund_id();
			System.out.println("update: " + i);
			Fund_Price_History fph = new Fund_Price_History();
			fph.setFund_id(1);
			fph.setPrice(100);
			fph.setPrice_date(fund_Price_History.getPrice_date());
			update(fph);
			update(fund_Price_History);
			org.genericdao.Transaction.commit();
		} finally {
			if (org.genericdao.Transaction.isActive())
				org.genericdao.Transaction.rollback();
		}

	}
}
