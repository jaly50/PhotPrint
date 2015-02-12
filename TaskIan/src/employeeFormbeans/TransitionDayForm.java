package employeeFormbeans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;

import org.mybeans.form.FormBean;

public class TransitionDayForm extends FormBean {
	private String price;
	private String name;
	private String symbol;
	private String newDate;
	private Date latestDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getNewDate() {
		return newDate;
	}

	public void get(String newDate) {
		this.newDate = newDate;
	}
	
	public void setLatestDate(Date latestDate) {
		this.latestDate = latestDate;
	}
	
	public Date getLatestDate() {
		return latestDate;
	}

	public List<String> validateFundPrice(String[] prices) {
		List<String> errors = new ArrayList<String>();
		if (prices == null || prices.length < 1) {
			errors.add("No price available");
			return errors;
		}
		double realAmount;
		long databaseAmount;
		for (String amount : prices) {
			amount = amount.trim();
			if (amount == null || amount.length() == 0) {
				errors.add("Please fill in price of every fund.");
				return errors;
			}
			try {
				realAmount = Double.parseDouble(amount);
			} catch (NumberFormatException e) {
				errors.add("Fund price should be a number");
				return errors;
			}
			if (realAmount == 0) {
				errors.add("The price cannot be zero");
				return errors;
			}
				
			
			if (realAmount < 1) {
				errors.add("The minimum fund price is $1");
				return errors;
			}
			
			if (realAmount > 10000) {
				errors.add("The maximum fund price is $10,000");
				return errors;
			}
				
			
			databaseAmount = (long) (realAmount * 100);
			if (databaseAmount - realAmount * 100 != 0) {
				errors.add("Fund price should be x.xx(tracked to two decimal places)");
				return errors;
			}

		}

		return errors;

	}

	public List<String> validateDate(String sdate) {
		List<String> errors = new ArrayList<String>();
		System.out.println(sdate+"  get the date");
		if (sdate.isEmpty() || sdate.length() == 0) {
			errors.add("Please add a date");
			return errors;
		}
		if (sdate.length() < 8) {
			errors.add("Please input correct date format. It should be like mm/dd/yyyy");
			return errors;
		}

		for (int i = 0; i < sdate.length(); i++) {
			char c = sdate.charAt(i);
			if((i == 2 || i ==5) && c !='/') {
				errors.add("Please choose a date");
				return errors;
			}
			System.out.println("index " + i + "is" + c);
			if ((i != 2 && i !=5) && !(c >= '0' && c <= '9')) {
				errors.add("Please input number");
				return errors;
			}
		}

		int month = Integer.parseInt(sdate.substring(0,2));
		System.out.println("Month is : " + month);
		if (month <= 0 || month > 12) {
			errors.add("Please input the right month number. It should be like mm/dd/yyyy");
			return errors;
		}
		int day = Integer.parseInt(sdate.substring(3,5));
		if (day <= 0 || day > 31) {
			errors.add("Please input the right day number. It should be like mm/dd/yyyy");
			return errors;
		}
		
		return errors;
	}

}
