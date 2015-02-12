package customerFormbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class RequestCheckForm   extends FormBean   {
	private String amount;
	private double realAmount;
	private long databaseAmount;
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount.trim();
	}

	public double getAmountAsDouble() {
		try {
			return Double.parseDouble(amount);
		} catch (NumberFormatException e) {
			// call getValidationErrors() to detect this
			return -1;
		}
	}
	public List<String> getValidationErrors() {
	List<String> errors = new ArrayList<String>();
	
    if (amount ==null || amount.length()==0) {
    	errors.add("Amount is required");
    	return errors;
    }
    try {
    	realAmount = Double.parseDouble(amount);
    } catch (NumberFormatException e) {
		errors.add("Request amount should be a number");
		return errors;
	}
    if (realAmount < 1) {
  	  errors.add("The minimum request amount is $1");
    }
    if (realAmount > 1000000) {
    	errors.add("Money more than 1 million is not allowed, please contact the CFS administrator.");
    }
  //Cash and fund share prices are tracked to two decimal places and also stored as (long) integers in the database.
   databaseAmount = (long) (realAmount * 100);
   if (databaseAmount - realAmount*100 !=0) {
  	 errors.add("Request amount should be x.xx(tracked to two decimal places)");
   }
  

	if (errors.size() > 0) {
		return errors;
	}


	return errors;
}

	public double getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(double realAmount) {
		this.realAmount = realAmount;
	}

	public long getDatabaseAmount() {
		return databaseAmount;
	}

	public void setDatabaseAmount(long databaseAmount) {
		this.databaseAmount = databaseAmount;
	}



}
