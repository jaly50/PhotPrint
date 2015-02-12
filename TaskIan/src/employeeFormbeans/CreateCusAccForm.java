package employeeFormbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CreateCusAccForm extends FormBean {
	private String username;
	private String firstName;
	private String lastName;
	private String password;
	private String confirm;
	private String addr_line1;
	private String addr_line2;
	private String state;
	private String city;
	private String zip;
	private String cash;
	//private String totalbalance;
	
	public String getUsername() {
		return username;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPassword() {
		return password;
	}

	public String getConfirm() {
		return confirm;
	}

	public String getAddr_line1() {
		return addr_line1;
	}

	public String getAddr_line2() {
		return addr_line2;
	}

	public String getState() {
		return state;
	}

	public String getCity() {
		return city;
	}
	public String getZip()
	{
		return zip;
	}

	public void setUsername(String s) {
		username = trimAndConvert(s, "<>\"");
	}

	public void setFirstName(String s) {
		firstName = trimAndConvert(s, "<>\"");
	}

	public void setLastName(String s) {
		lastName = trimAndConvert(s, "<>\"");
	}
	public void setState(String s) {
		state = trimAndConvert(s, "<>\"");
	}

	
	public void setZip(String s) {
		zip = trimAndConvert(s, "<>\"");
	}

	public void setCity(String s) {
		city = trimAndConvert(s, "<>\"");
	}

	public void setAddr_line1(String s) {
		addr_line1 = trimAndConvert(s, "<>\"");
	}

	public void setAddr_line2(String s) {
		addr_line2 = trimAndConvert(s, "<>\"");
	}

	public void setPassword(String s) {
		password = s.trim();
	}

	public void setConfirm(String s) {
		confirm = s.trim();
	}


	public String getCash() {
		return cash;
	}
	public long getCashAsLong() {

		long cashLong = (long) (100*(Double.parseDouble(cash)));

		return cashLong;
		
	}

	public void setcash(String cash) {
		this.cash = cash;
	}


	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (username == null || username.length() == 0) {
			errors.add("Username is required");
			//return errors;
		}

		if (firstName == null || firstName.length() == 0) {
			errors.add("First Name is required");
		}

		if (lastName == null || lastName.length() == 0) {
			errors.add("Last Name is required");
		}
		if (addr_line1 == null || addr_line1.length() == 0) {
			errors.add("Address Line 1 is required");
		}
		if (city == null || city.length() == 0) {
			errors.add("City is required");
		}

		if (state == null || state.length() == 0) {
			errors.add("State is required");
		}
		if (cash == null || cash.length() == 0) {
			errors.add("Cash is required");
		}

		if (password == null || password.length() == 0) {
			errors.add("Password is required");
		}


		if (confirm == null || confirm.length() == 0) {
			errors.add("Confirm Password is required");
		}

		if (errors.size() > 0) {
			return errors;
		}

		if(username.substring(0, 1).matches("[0-9]"))
		{
			errors.add("The first character of the username can't be a number");
		}
		if(!(username.matches("^\\d*[a-zA-Z][a-zA-Z\\d]*$")))
		{
			errors.add("Characters other than alphabets and numbers are not allowed in username");
		}
        if(addr_line1.contains("!")||addr_line1.contains("?")||addr_line1.contains("*")||addr_line1.contains("&lt;")||addr_line1.contains("&gt;")||addr_line1.contains("&amp;")||addr_line1.contains("\""))
        {    //System.out.println("In error address thing");
        	errors.add("addr_line1 cannot contain <>,?,!,* or quotes");
        }	
          
        if(addr_line2.contains("!")||addr_line2.contains("?")||addr_line2.contains("*")||addr_line2.contains("&lt;")||addr_line2.contains("&gt;")||addr_line2.contains("&amp;")||addr_line2.contains("\""))
        {    //System.out.println("In error address thing");
        	errors.add("addr_line2 cannot contain <>,?,!,* or quotes");
        }	
		if(username.length()>50)
		{
			errors.add("The length of username can't be more than 50");
		}
		if(firstName.length()>50)
		{
			errors.add("The length of first name can't be more than 50");
		}
		if(lastName.length()>50)
		{
			errors.add("The length of last name can't be more than 50");
		}
		if(addr_line1.length()>100)
		{
			errors.add("The length of address line 1 cannot be more than 100");
		}
		if(addr_line2.length()>100)
		{
			errors.add("The length of address line 2 cannot be more than 100");
		}
		if(city.length()>50)
		{
			errors.add("The length of city cannot be more than 50");
		}

		if(state.length()>50)
		{
			errors.add("The length of state cannot be more than 50");
		}
		
		if(password.length()<6)
		{
			errors.add("Password is too short! it must be between 6 and 15 characters");
		}
		if(password.length()>15)
		{
			errors.add("Password can't be more than 15 characters");
		}


		if (!password.equals(confirm)) {
			errors.add("Passwords are not the same");
		}
		if(!(firstName.matches("[a-zA-Z]+")))
		{
			errors.add("There should be no other characters than alphabets in first name");
		}
		if(!(lastName.matches("[a-zA-Z]+")))
		{
			errors.add("There should be no other characters than alphabets in last name");
		}
		if(!(city.matches("[a-zA-Z]+")))
		{
			errors.add("There should be no other characters than alphabets in city");
		}
		if(!(state.matches("[a-zA-Z]+")))
		{
			errors.add("There should be no other characters than alphabets in state");
		}
		if(!(zip.matches("[0-9]+")))
		{
			errors.add("Zip should only contain numbers");
		}
		if(cash.matches("^\\d+(\\.\\d{1,2})?$"))
				{
		 if(Double.parseDouble(cash)<0)
		{
			errors.add("Cash value cannot be negative");
		}
		if(Double.parseDouble(cash)>10000000 || Double.parseDouble(cash)<0.01)
		{
			errors.add("Cash out of range! Contact admin for assistance");
		}
				}
		if(!(cash.matches("^\\d+(\\.\\d{1,2})?$")))
		{
			errors.add("Should be a number with upto two decimal places");
		}
		
			if(zip.length()>5)
		{
			errors.add("Zip can't ba more than 5 characters");
		}
	
		return errors;
	}
    
    
}