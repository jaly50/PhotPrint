package customerFormbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;


public class SellFundForm extends FormBean {

	private String shares;
	private String action;
	private String select;
	private int    fund_id;
	private double realShares;
	private long databaseShares;

	public void setShares(String shares) {
		this.shares = shares;
	}
	public String getShares() {
		return shares;
	}


	public void setAction(String action) {
		this.action = action;
	}


	
	public String getAction() {
		return action;
	}


	public int getFun_id() {
		return fund_id;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		 if (shares ==null || shares.length()==0) {
		    	errors.add("Shares is required");
		    	return errors;
		    }
		 if (select ==null || select.length()<1) {
			 errors.add("Please select one fund.");
			 return errors;
		 }
		 try {
		 fund_id = Integer.valueOf(select);
		 //Someone change Jsp sneakingly
		  } catch (NumberFormatException e) {
				errors.add("Please don't change element value.");
				return errors;
			}
		 
		 
		    try {
		    	realShares = Double.parseDouble(shares);
		    } catch (NumberFormatException e) {
				errors.add("Shares should be a number");
				return errors;
			}
		    if (realShares < 1) {
		  	  errors.add("The minimum shares to sell is 1");
		    }
		    if (realShares > 1000000) {
		    	errors.add("The maximum shares to sell is one million");
		    }
		  // shares get into database 
		    databaseShares = (long) (realShares * 1000);
		    if (databaseShares - realShares*1000 !=0) {
		   	 errors.add("Request amount should be x.xxx(tracked to three decimal places)");
		    }
		   
		return errors;
		
	}
	public double getRealShares() {
		return realShares;
	}
	public void setRealShares(double realShares) {
		this.realShares = realShares;
	}
	public long getDatabaseShares() {
		return databaseShares;
	}
	public void setDatabaseShares(long databaseShares) {
		this.databaseShares = databaseShares;
	}
	public String getSelect() {
		return select;
	}
	public void setSelect(String select) {
		this.select = select;
	}

	
}
