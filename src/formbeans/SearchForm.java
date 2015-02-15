package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class SearchForm extends FormBean{
	private String location;
	private String description;
	
	// get
	public String getDescription() {
		return description;
	}
		
	// set
	public void setDescription(String s) {
		description = s;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String l) {
		location = l;
	}
	
	@Override
	// error
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
			
		if (location == null) {
			errors.add("Location is required");
		}		
		if (description == null) {
			errors.add("Description is required");
		}
		return errors;	
	}	
}