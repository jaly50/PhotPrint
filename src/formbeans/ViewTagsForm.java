package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class ViewTagsForm extends FormBean{
	private String tag;
	private String location;
	
	// get
	public String getTag() {
		return tag;
	}
		
	// set
	public void setTag(String s) {
		tag = s;
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
			
		if (tag == null) {
			errors.add("Tag is required");
		}			
		return errors;	
	}	
}