package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class ViewPhotosForm extends FormBean{
	private String location;
	
	// get
	public String getLocation() {
		return location;
	}
		
	// set
	public void setLocation(String s) {
		location = s;
	}
		
	@Override
	// error
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
			
		if (location == null) {
			errors.add("Location is required");
		}			
		return errors;	
	}	
}