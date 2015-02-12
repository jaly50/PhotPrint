package customerFormbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class LoginForm extends FormBean {
	private String username;
	private String password;
	private String userType;
	
	public String getPassword(){ 
		return password; 
	}
	
	
	public void setPassword(String s) {	
		password = trimAndConvert(s, "<>\"");              
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
	if (username == null || username.length() == 0) {
			errors.add("User Name is required");
		}
		
		if (password == null || password.length() == 0) {
			errors.add("Password is required");
		}
		return errors;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = trimAndConvert(username, "<>\"");
	}
}