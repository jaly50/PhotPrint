package employeeFormbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CreateFundForm  extends FormBean  {
	private String name;
	private String symbol;
	static String pattern= "^[a-zA-Z0-9]*$";
	static String symbolPattern = "^[a-zA-Z0-9]*$";
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name.trim();
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol.trim();
	}
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (name == null || name.length() == 0) {
			errors.add("Fund Name is required");
		}
		if (name.length() >30) {
			errors.add("Please input a shorter fund name(with 30 characters)");
		}
		if (!name.matches(pattern)) {
			errors.add("Only characters, numbers are allowed for fund name.");
			return errors;
		}

		if (symbol == null || symbol.length() == 0) {
			errors.add("Fund ticker is required");
			return errors;
		}
		if (symbol.length() >5 || symbol.length() <1) {
			errors.add("Fund ticker should be a short one to five character identifier.");
			return errors;
		}
		if (!symbol.matches(pattern)) {
			errors.add("Only chracters allowed for fund symbol.");
			return errors;
		}

		if (errors.size() > 0) {
			return errors;
		}


		return errors;
	}
	
}
