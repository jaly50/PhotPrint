package databeans;

public abstract class User {
	private String firstname = null;
	private String lastname = null;
	// private String password = null;
	private String username = null;

	private String hashedPassword = "*";
	private int salt = 0;

	public abstract boolean checkPassword(String password);

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
