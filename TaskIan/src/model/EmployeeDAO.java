
package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.*;

public class EmployeeDAO extends GenericDAO<Employee> {

	public EmployeeDAO(String tableName, ConnectionPool pool) throws DAOException,
			RollbackException {
		super(Employee.class, tableName, pool);

	}
	public boolean checkPassword(String username,String oldpass)
	{    
		Employee emp=null;
		try {
			emp = read(username);
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       	
	    	    if(emp.getHashedPassword().equals(oldpass))
	    	return true;
			else 
	    	return false;
	}
	public Employee getEmployee(String username) throws RollbackException {
		Employee[] emp = match(MatchArg.equals("username", username));
		if (emp.length == 0)
			return null;
		return emp[0];
	}

	public void createEmployee(Employee newEmp) throws RollbackException {
		create(newEmp);
	}

	public void setPassword(String username,String newpassword) throws RollbackException {
		 try {
	        	Transaction.begin();
				Employee dbUser = read(username);
				
				if (dbUser == null) {
					throw new RollbackException("User "+username+" no longer exists");
				}
				
				dbUser.setPassword(newpassword);
				
				update(dbUser);
				Transaction.commit();
			} finally {
				if (Transaction.isActive()) Transaction.rollback();
			}
		
	}
}
