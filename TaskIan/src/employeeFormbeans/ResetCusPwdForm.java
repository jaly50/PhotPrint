package employeeFormbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class ResetCusPwdForm  extends FormBean  {
	private String username;
	private String newPassword;
	private String confirm;
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (username == null || username.length() == 0) {
			errors.add("Customer Username is required");
			return errors;
		}

		if (newPassword == null || newPassword.length() == 0) {
			errors.add("New Password is required");
			return errors;
		}
		if (confirm == null || confirm.length() == 0) {
			errors.add("The confirm password is required");
			return errors;
		}

        if (!confirm.equals(newPassword)) {
           errors.add("Confirm and New Password are different. Please try again.");
           return errors;
        }
		
		if (errors.size() > 0) {
			return errors;
		}

		if(newPassword.length()<6)
		{
			errors.add("Password is too short! it must be between 6 and 15 characters");
		}
		if(newPassword.length()>15)
		{
			errors.add("Password can't be more than 15 characters");
		}


		return errors;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	
}
