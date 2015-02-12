package employeeFormbeans;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBean;

public class CustomerListForm extends FormBean  {
	private static Set<String> operationList = new HashSet<String>();
	static {
		operationList.add("View Account");
		operationList.add("Deposit Check");
		operationList.add("Reset Password");
		operationList.add("View Transaction History");
	}
	private String operation;
	private String select;
	private int customer_id;
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getSelect() {
		return select;
	}
	public void setSelect(String select) {
		this.select = select;
	}
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (select ==null || select.length()==0) {
			errors.add("Please select at least one customer");
			return errors;
		}
		customer_id = Integer.parseInt(select);
		if (operation == null || operation.length() == 0) {
			errors.add("Please click at least one button.");
			return errors;
		}
		if (!operationList.contains(operation)) {
			errors.add("We don't have such operation. Please choose one of view account, reset password, view transaction history and deposit check");
	        return errors;	
		}

		return errors;
		
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

}
