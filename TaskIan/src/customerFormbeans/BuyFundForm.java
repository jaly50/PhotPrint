package customerFormbeans;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;

import org.mybeans.form.FormBean;

public class BuyFundForm extends FormBean {

	private String amount;
	private double realAmount;
	private long databaseAmount;
	private String chooseFund;
	private int    fund_id;
	private String select;

	public String getBuyAmount() {
		return amount;
	}
	
	public long getAmountAsLong() {
		try {
			Double tempDouble = Double.parseDouble(amount);
			Long amountLong = (long)(tempDouble * 100);
			return amountLong;
		} catch (NumberFormatException e) {
			// call getValidationErrors() to detect this
			return -1;
		}
	}

	public void setBuyAmount(String buyAmount) {
		this.amount = buyAmount;
	}
	public String getChooseFund() {
		return chooseFund;
	}

	public void setChooseFund(String chooseFund) {
		this.chooseFund = chooseFund;
	}
	
	public int getFund_id() {
		return fund_id;
	}
	

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		if (amount == null || amount.length() == 0) {
			errors.add("Please enter an amount.");
		}
		if (select == null || select.length() == 0) {
			errors.add("Please choose a fund");
			return errors;
		}
		  try {
		    	realAmount = Double.parseDouble(amount);
		    } catch (NumberFormatException e) {
				errors.add("Buy amount should be a number");
				return errors;
			}
		    if (realAmount < 1) {
		  	  errors.add("The minimum buy amount is $1");
		  	  return errors;
		    }
		    if (realAmount > 1000000) {
		    	errors.add("Money more than 1 million is not allowed, please contact the CFS administrator.");
		    	return errors;
		    }
		    //Cash and fund share prices are tracked to two decimal places and also stored as (long) integers in the database.
		    databaseAmount = (long) (realAmount * 100);
		    if (databaseAmount - realAmount*100 !=0) {
		   	 errors.add("Buy amount should be x.xx(tracked to two decimal places)");
		    }
		   

		 try {
		 fund_id = Integer.valueOf(select);
		 //Someone change Jsp sneakingly
		  } catch (NumberFormatException e) {
				errors.add("Please don't change element value.");
				return errors;
			}
		
		if (errors.size() > 0)
			return errors;
		try {
			double amt = Double.parseDouble(amount);
			amt = Math.round(amt * 100);
			amt = amt / 100;
			if (amt < 10) {
				errors.add("Please enter an amount that is greater than $10");
			} else if (amt > 1000000000) {
				errors.add("Please enter an amount that is lesser than $1000000000");
			}
		} catch (NumberFormatException nfe) {
			errors.add("Please enter amount in digits. Do not use letters");
		}

		return errors;
	}

	public boolean checkDecimal(String input) {
		Pattern p = Pattern.compile("[+-]?[0-9]+.{0,1}[0-9]{0,2}");
		return p.matcher(input).matches();
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

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

}
