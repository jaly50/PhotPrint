/*
 * Name: Charlotte Lin
 * Date: 01/17/2015
 */

package employeeFormbeans;
import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;
public class ViewCusAccForm extends FormBean   {
	private String  customer_id;
	
	public int getCustomer_idAsInt() {
		try {
			return Integer.parseInt(customer_id);
		} catch (NumberFormatException e) {
			// call getValidationErrors() to detect this
			return -1;
		}
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		if (customer_id == null || customer_id.length()==0)
			errors.add("Customer id is required");
       
		if (errors.size() > 0) {
			return errors;
		}
		return errors;
	}
}
