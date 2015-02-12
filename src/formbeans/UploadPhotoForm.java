package formbeans;

import java.io.File;
import java.util.ArrayList;

import org.mybeans.form.FileProperty;
import org.mybeans.form.FormBean;

public class UploadPhotoForm extends FormBean {
	private String button     = "";
	private String caption    = "";
	private String location = "Pittsburgh";
	private String tag ="";
	private FileProperty file = null;
	
	public static int FILE_MAX_LENGTH = 1024 * 1024;
	
	public String       getButton()         { return button;         }
	public FileProperty getFile()           { 
		return file;   
	}
	public String       getCaption()        { return caption;        }

	public void setButton(String s)         { button      = s;        }
	public void setCaption(String s)        { caption     = trimAndConvert(s,"<>\""); }
	public void setFile(FileProperty file)  { this.file   = file;     }
	
	public ArrayList<String> getValidationErrors() {
		ArrayList<String> errors = new ArrayList<String>();
		
		if (file == null || file.getFileName().length() == 0) {
			errors.add("You must provide a file");
			return errors;
		}
		System.out.println(file.toString());
		System.out.println(file.getFileName());
		

		if (file.getBytes().length == 0) {
			errors.add("Zero length file");
		}
		
		return errors;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
}
