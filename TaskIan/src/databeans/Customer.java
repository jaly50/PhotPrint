package databeans;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.genericdao.PrimaryKey;

@PrimaryKey("customer_id")
public class Customer extends User {
	private int customer_id;
	private String username;
	private String password;
	private String firstname;

	private String hashedPassword = "*";
	private int salt = 0;

	private String lastname;
	private String addr_line1;
	private String addr_line2;
	private String state;
	private String city;
	private String zip;
	private long availablebalance;
	private long totalbalance;

	public boolean checkPassword(String password) {
		System.out.println("System password check"
				+ hashedPassword.equals(hash(password)));
		return hashedPassword.equals(hash(password));
	}

	// public String getPassword() { return password; }
	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getAddr_line1() {
		return addr_line1;
	}

	public String getAddr_line2() {
		return addr_line2;
	}

	public String getState() {
		return state;
	}

	public String getCity() {
		return city;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public long getAvailablebalance() {
		return availablebalance;
	}

	public long getTotalbalance() {
		return totalbalance;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public int getSalt() {
		return salt;
	}

	public int hashCode() {
		return username.hashCode();
	}

	public void setHashedPassword(String x) {
		hashedPassword = x;
	}

	public void setPassword(String s) {
		salt = newSalt();
		hashedPassword = hash(s);
	}

	public void setSalt(int x) {
		salt = x;
	}

	// public void setPassword(String s) { password = s; }
	public void setFirstname(String s) {
		firstname = s;
	}

	public void setLastname(String s) {
		lastname = s;
	}

	public void setAddr_line1(String s) {
		addr_line1 = s;
	}

	public void setAddr_line2(String s) {
		addr_line2 = s;
	}

	public void setState(String s) {
		state = s;
	}

	public void setCity(String s) {
		city = s;
	}

	public void setCustomer_id(int s) {
		customer_id = s;
	}

	public void setAvailablebalance(long s) {
		availablebalance = s;
	}

	public void setTotalbalance(long s) {
		totalbalance = s;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	private String hash(String clearPassword) {
		if (salt == 0)
			return null;

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA1");
		} catch (NoSuchAlgorithmException e) {
			throw new AssertionError(
					"Can't find the SHA1 algorithm in the java.security package");
		}

		String saltString = String.valueOf(salt);

		md.update(saltString.getBytes());
		md.update(clearPassword.getBytes());
		byte[] digestBytes = md.digest();

		// Format the digest as a String
		StringBuffer digestSB = new StringBuffer();
		for (int i = 0; i < digestBytes.length; i++) {
			int lowNibble = digestBytes[i] & 0x0f;
			int highNibble = (digestBytes[i] >> 4) & 0x0f;
			digestSB.append(Integer.toHexString(highNibble));
			digestSB.append(Integer.toHexString(lowNibble));
		}
		String digestStr = digestSB.toString();

		return digestStr;
	}

	private int newSalt() {
		Random random = new Random();
		return random.nextInt(8192) + 1; // salt cannot be zero
	}

}