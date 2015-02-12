package model;

import java.util.Date;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databeans.Transaction;
import databeans.Position;

public class TransactionDAO  extends GenericDAO<Transaction>  {
	public TransactionDAO(String tableName, ConnectionPool pool)
			throws DAOException {
		super(Transaction.class, tableName, pool);

	}

	public void create(Transaction newTransaction) throws RollbackException {
		createAutoIncrement(newTransaction);
	}
	/*
	 * Given a type of transaction and a customer id
	 * return an array within the range of that type and the specific customer
	 * types includes: Fund, Check
	 */
	public Transaction[] getTransactions(int customer_id, String transaction_type) throws RollbackException {
		MatchArg matchArg1 = MatchArg.equals("customer_id", customer_id);
		MatchArg matchArg2 = MatchArg.equals("transaction_type", transaction_type);
		Transaction[] list =  match(MatchArg.and(matchArg1,matchArg2));
		return list;
	}
	
	public Transaction[] getTransactions(int customer_id) throws RollbackException {
		MatchArg matchArg = MatchArg.equals("customer_id", customer_id);
		Transaction[] list =  match(MatchArg.and(matchArg));
		return list;
	}
	
	public Transaction[] getPendingTransactions() throws RollbackException {
		MatchArg matchArg = MatchArg.equals("execute_date", null);
		Transaction[] list =  match(MatchArg.and(matchArg));
		return list;
	}
	
	public Transaction[] getWorkedTransactions(Date execute_date) throws RollbackException {
		MatchArg matchArg = MatchArg.equals("execute_date", execute_date);
		Transaction[] list =  match(MatchArg.and(matchArg));
		return list;
	}
	
	
	
	public Transaction[] getPendingBuyTransacs() throws RollbackException {
		Transaction[] transaction = match(MatchArg.and(MatchArg.equals("transaction_type", "buy"),MatchArg.equals("execute_date", null)));
		return transaction;
	}
	
	public Transaction[] getPendingSellTransacs() throws RollbackException {
		Transaction[] transaction = match(MatchArg.and(MatchArg.equals("transaction_type", "sell"),MatchArg.equals("execute_date", null)));
		return transaction;
	}
	
	public Transaction[] getPendingDepositTransacs() throws RollbackException {
		Transaction[] transaction = match(MatchArg.and(MatchArg.equals("transaction_type", "deposit"),MatchArg.equals("transaction_type", null)));
		return transaction;
	}
	
	public Transaction[] getPendingCheckTransacs() throws RollbackException {
		Transaction[] transaction = match(MatchArg.and(MatchArg.equals("transaction_type", "request"),MatchArg.equals("transaction_type", null)));
		return transaction;
	}
	
	public void transactionBuyUpdate(Date execute_date, long shares,Transaction transaction) throws RollbackException {
		// TODO Auto-generated method stub
		try {
				org.genericdao.Transaction.begin();
				transaction.setExecute_date(execute_date);
				transaction.setShares(shares);
				update(transaction);
				org.genericdao.Transaction.commit();

		} finally {
			if (org.genericdao.Transaction.isActive())
				org.genericdao.Transaction.rollback();
		}
	}
	
	public void transactionSellUpdate(Date execute_date, long amount,Transaction transaction) throws RollbackException {
		// TODO Auto-generated method stub
		try {
				org.genericdao.Transaction.begin();
				transaction.setExecute_date(execute_date);
				transaction.setAmount(amount);
				update(transaction);
				org.genericdao.Transaction.commit();

		} finally {
			if (org.genericdao.Transaction.isActive())
				org.genericdao.Transaction.rollback();
		}
	}
	
	public void transactionDepositUpdate(Date execute_date,Transaction transaction) throws RollbackException {
		// TODO Auto-generated method stub
		try {
				org.genericdao.Transaction.begin();
				transaction.setExecute_date(execute_date);
				update(transaction);
				org.genericdao.Transaction.commit();

		} finally {
			if (org.genericdao.Transaction.isActive())
				org.genericdao.Transaction.rollback();
		}
	}
	
	public void transactionCheckUpdate(Date execute_date,Transaction transaction) throws RollbackException {
		// TODO Auto-generated method stub
		try {
				org.genericdao.Transaction.begin();
				transaction.setExecute_date(execute_date);
				update(transaction);
				org.genericdao.Transaction.commit();

		} finally {
			if (org.genericdao.Transaction.isActive())
				org.genericdao.Transaction.rollback();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
