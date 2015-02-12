/*
 * Task7 -team 12
 * Edited by Scarlett chen
 * Jan 22 Thu 10:26 PM
 */
package employeeFormbeans;
import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class DepositCheckForm   extends FormBean   {
	private String  username;
	private String amount;
	private double realAmount;
	private long databaseAmount;

   public long getDatabaseAmount() {
	   return databaseAmount;
   }

	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount.trim();
	}
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		if (username == null || username.length()==0)
			errors.add("Customer Username is required");
        if (amount ==null || amount.length()==0) {
        	errors.add("Amount is required");
	        return errors;        
        }
	
        try {
        	realAmount = Double.parseDouble(amount);
        } catch (NumberFormatException e) {
			errors.add("Deposit amount should be a number");
			return errors;
		}
          if (realAmount < 1) {
        	  errors.add("The minimum deposit amount is $1");
          }
          if (realAmount > 1000000) {
        	  errors.add("Money more than 1 million is not allowed, please contact administrator to deposit it.");
          }
        //Cash and fund share prices are tracked to two decimal places and also stored as (long) integers in the database.
         databaseAmount = (long) (realAmount * 100);
         if (databaseAmount - realAmount*100 !=0) {
        	 errors.add("Deposit amount should be x.xx(tracked to two decimal places)");
         }
        
        
		if (errors.size() > 0) {
			return errors;
		}


		return errors;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username.trim();
	}

	public double getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(double realAmount) {
		this.realAmount = realAmount;
	}

}
