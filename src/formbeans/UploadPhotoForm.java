package formbeans;

import java.util.ArrayList;

import org.mybeans.form.FileProperty;
import org.mybeans.form.FormBean;

public class UploadPhotoForm extends FormBean {
	private String button     = "";
	private String description    = "";
	private String location = "Pittsburgh";
	
	public static int FILE_MAX_LENGTH = 1024 * 1024;
	
	public String       getButton()         { return button;         }


	public void setButton(String s)         { button      = s;        }
	public void setCaption(String s)        { setDescription(trimAndConvert(s,"<>\"")); }
	
	public ArrayList<String> getValidationErrors() {
		ArrayList<String> errors = new ArrayList<String>();
		

		
		return errors;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = trimAndConvert(description,"<>\"");;
		
	}

}
