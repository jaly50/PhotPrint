
package employeeFormbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class ChangePwdForm extends FormBean {
	private String confirmPassword;
	private String newPassword;

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setConfirmPassword(String s) {
		confirmPassword = s.trim();
	}

	public void setNewPassword(String s) {
		newPassword = s.trim();
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (newPassword == null || newPassword.length() == 0) {
			errors.add("New Password is required");
		}

		if (confirmPassword == null || confirmPassword.length() == 0) {
			errors.add("Confirm Pwd is required");
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

		

		if (!newPassword.equals(confirmPassword)) {
			errors.add("Passwords do not match");
		}

		return errors;
	}
}
