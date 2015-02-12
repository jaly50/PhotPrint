/*
 * Jiali Chen
 * andrewID: jialic
 * 
 */
package model;

import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.RollbackException;





import com.mysql.jdbc.Connection;

import databeans.Customer;
import databeans.Employee;
import databeans.Fund;


public class Model {
	private CustomerDAO customerDAO;
	private EmployeeDAO employeeDAO;
	private FundDAO fundDAO;
	private Fund_Price_HistoryDAO fundPriceHistoryDAO;
	private PositionDAO positionDAO;
	private TransactionDAO transactionDAO;
	private Photo_FavorDAO photo_FavorDAO;
	// constructor
	public Model(ServletConfig config) throws ServletException {
		try {
			String jdbcDriver = config.getInitParameter("jdbcDriverName");

			String jdbcURL    = config.getInitParameter("jdbcURL");		
			//ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL);
			ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL,"root","");
			
			customerDAO  = new CustomerDAO("Customer", pool);
			employeeDAO = new EmployeeDAO("Employee", pool);
			fundDAO = new FundDAO("Fund", pool);
			positionDAO = new PositionDAO("position", pool);
			fundPriceHistoryDAO = new Fund_Price_HistoryDAO("Fund_Price_History", pool);
			transactionDAO = new TransactionDAO("transaction", pool);
			photo_FavorDAO = new Photo_FavorDAO("photoFavor",pool);
              initialization();
		} catch (DAOException e) {
			throw new ServletException(e);
		} catch (RollbackException e) {
			e.printStackTrace();
		}

	}
	private void initialization() throws RollbackException {
		   if(employeeDAO.getCount()==0)
		   {
			   //String employee[]=new String[]{"adminj","jiali","chen","jiali"};
			   Employee e=new Employee();
			   e.setUsername("admin");
			   e.setFirstname("Jeffrey");
			   e.setLastname("Eppinger");
			   e.setPassword("admin");
		       employeeDAO.create(e);
		   }
		   

		}
	
	// get 
	public CustomerDAO getCustomerDAO() {
		return customerDAO;
	}
	public EmployeeDAO getEmployeeDAO() {
		return employeeDAO;
	}
	public FundDAO getFundDAO(){
		return fundDAO;
	}
	public Fund_Price_HistoryDAO getFund_Price_HistoryDAO() {
		return fundPriceHistoryDAO;
	}
	public PositionDAO getPositionDAO() {
		return positionDAO;
	}
	public TransactionDAO getTransactionDAO() {
		return transactionDAO;
	}
	
	public Photo_FavorDAO getPhoto_FavorDAO() {
		return photo_FavorDAO;
	}


}
