package databeans;

public class LocationData {
	private String location;
	private int number;
	
	public LocationData(String l, int n) {
		location = l;
		number = n;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String s) {
		location = s;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int n) {
		number = n;
	}
}
