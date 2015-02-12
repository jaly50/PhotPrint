package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.*;

public class CustomerDAO extends GenericDAO<Customer> {

	public CustomerDAO(String tableName, ConnectionPool pool)
			throws DAOException, RollbackException {
		super(Customer.class, tableName, pool);

	}

	public boolean checkPassword(String username, String oldpass) {
		Customer emp = null;
		try {
			emp = read(username);
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (emp.getHashedPassword().equals(oldpass))
			return true;
		else
			return false;
	}

	public void createCustomer(Customer newCust) throws RollbackException {
		createAutoIncrement(newCust);
	}

	public Customer viewCustomer(int cust_id) throws RollbackException {
		return read(cust_id);
	}

	public Customer getCustomer(int cust_id) throws RollbackException {
		return read(cust_id);
	}

	public Customer[] getCustomers() throws RollbackException {
		Customer[] customers = match();
		return customers;
	}

	public Customer getCustomer(String username) throws RollbackException {
		Customer[] customer = match(MatchArg.equals("username", username));
		if (customer == null || customer.length < 1)
			return null;
		return customer[0];
	}

	public void setPassword(String username, String newpassword)
			throws RollbackException {
		try {
			Transaction.begin();
			Customer[] dbUser = match(MatchArg.equals("username", username));
			// Customer dbUser = read(username);

			if (dbUser == null) {
				throw new RollbackException("User " + username
						+ " no longer exists");
			}

			dbUser[0].setPassword(newpassword);

			update(dbUser[0]);
			Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}

	public void update(long value, Customer cus) throws RollbackException {
		// TODO Auto-generated method stub
		try {

			if (cus.getAvailablebalance() >= value) {
				Transaction.begin();
				cus.setAvailablebalance(cus.getAvailablebalance() - value);
				update(cus);
				Transaction.commit();
			} else {
				throw new RollbackException(
						"Available amount less than mount to buy");
			}

		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}

	public void transiUpdate(long balanceIncre, Customer cus)
			throws RollbackException {
		// TODO Auto-generated method stub
		try {

			Transaction.begin();
			cus.setAvailablebalance(cus.getAvailablebalance() + balanceIncre);
			update(cus);
			Transaction.commit();
		}

		finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}
	
	public void totalBalanceUpdate(Customer cus)
			throws RollbackException {
		// TODO Auto-generated method stub
		try {

			Transaction.begin();
			cus.setTotalbalance(cus.getAvailablebalance());
			update(cus);
			Transaction.commit();
		}

		finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}
	
	

}
