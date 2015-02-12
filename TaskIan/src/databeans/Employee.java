package databeans;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.genericdao.PrimaryKey;

@PrimaryKey("username")
public class Employee extends User {
    private String username;
    //private String password;

	private String  hashedPassword = "*";
	private int     salt           = 0;

    private String firstname;
    private String lastname;
	public boolean checkPassword(String password) {
		System.out.println("System password check"+hashedPassword.equals(hash(password)));
			return hashedPassword.equals(hash(password));
		}
    //public String getPassword()        { return password; }
    public String getUsername()        { return username; }
    public String  getHashedPassword() { return hashedPassword; }
	public int     getSalt()           { return salt;           }

    //public void setPassword(String s)  { password = s;    }
    public void setUsername(String s)  { username = s;    }
    public String getFirstname()        { return firstname; }
    public String getLastname()        { return lastname; }
	public int     hashCode()          { return username.hashCode(); }

	public void setHashedPassword(String x)  { hashedPassword = x; }
	public void setPassword(String s)        { salt = newSalt(); hashedPassword = hash(s); }
	public void setSalt(int x)               { salt = x;           }

    public void setFirstname(String s)  { firstname = s;    }
    public void setLastname(String s)  { lastname = s;    }
    private String hash(String clearPassword) {
		if (salt == 0) return null;

		MessageDigest md = null;
		try {
		  md = MessageDigest.getInstance("SHA1");
		} catch (NoSuchAlgorithmException e) {
		  throw new AssertionError("Can't find the SHA1 algorithm in the java.security package");
		}

		String saltString = String.valueOf(salt);
		
		md.update(saltString.getBytes());
		md.update(clearPassword.getBytes());
		byte[] digestBytes = md.digest();

		// Format the digest as a String
		StringBuffer digestSB = new StringBuffer();
		for (int i=0; i<digestBytes.length; i++) {
		  int lowNibble = digestBytes[i] & 0x0f;
		  int highNibble = (digestBytes[i]>>4) & 0x0f;
		  digestSB.append(Integer.toHexString(highNibble));
		  digestSB.append(Integer.toHexString(lowNibble));
		}
		String digestStr = digestSB.toString();

		return digestStr;
	}

	private int newSalt() {
		Random random = new Random();
		return random.nextInt(8192)+1;  // salt cannot be zero
	}

}