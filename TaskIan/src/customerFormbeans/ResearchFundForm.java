/*
 * Name: Charlotte Lin
 * Date: 01/17/2015
 */

package customerFormbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class ResearchFundForm extends FormBean   {
	private String fund_id;
		
	// get
	public String getId() {
		return fund_id;
	}
	public int getIdAsInt() {
		try {
			return Integer.parseInt(fund_id);
		} catch (NumberFormatException e) {
			// call getValidationErrors() to detect this
			return -1;
		}
	}
		
	// set
	public void setId(String s) {
		fund_id = s;
	}
		
	@Override
	// error
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
			
		if (fund_id == null || fund_id.length() == 0) {
			errors.add("Fund id is required");
		}			
		return errors;	
	}	
}