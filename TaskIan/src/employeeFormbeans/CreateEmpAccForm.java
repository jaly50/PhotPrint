package employeeFormbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CreateEmpAccForm extends FormBean {
	private String username;
	private String firstName;
	private String lastName;
	private String password;
	private String confirm;

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

	public void setUsername(String s) {
		username = trimAndConvert(s, "<>\"");
	}

	public void setFirstName(String s) {
		firstName = trimAndConvert(s, "<>\"");
	}

	public void setLastName(String s) {
		lastName = trimAndConvert(s, "<>\"");
	}

	public void setPassword(String s) {
		password = s.trim();
	}

	public void setConfirm(String s) {
		confirm = s.trim();
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (username == null || username.length() == 0) {
			errors.add("Username is required");
		//	return errors;
		}
		if (firstName == null || firstName.length() == 0) {
			errors.add("First Name is required");
		}

		if (lastName == null || lastName.length() == 0) {
			errors.add("Last Name is required");
		}

		if (password == null || password.length() == 0) {
			errors.add("Password is required");
		}

		if (errors.size() > 0) {
			return errors;
		}
		if(username.length()>50)
		{
			errors.add("The length of username can't be more than 50");
		}
		if(username.substring(0, 1).matches("[0-9]"))
		{
			errors.add("The first character of the username can't be a number");
		}
		
		if(!(username.matches("^\\d*[a-zA-Z][a-zA-Z\\d]*$")))
		{
			errors.add("Characters other than alphabets and numbers are not allowed");
		}
		
		if(firstName.length()>50)
		{
			errors.add("The length of first name can't be more than 50");
		}
		if(lastName.length()>50)
		{
			errors.add("The length of last name can't be more than 50");
		}
		
		if( password.length()<6)
		{
			errors.add("Password is too short!it must be between 6 and 15 characters");
		}
		if(password.length()>15)
		{
			errors.add("Password can't be more than 15 characters");
		}

		if (confirm == null || confirm.length() == 0) {
			errors.add("Confirm Password is required");
		}
		if(!(firstName.matches("[a-zA-Z]+")))
		{
			errors.add("There should be no other characters than alphabets in first name");
		}
		if(!(lastName.matches("[a-zA-Z]+")))
		{
			errors.add("There should be no other characters than alphabets in last name");
		}


		if (!password.equals(confirm)) {
			errors.add("Passwords are not the same");
		}
         return errors;
	}
}
