package databeans;

import java.util.ArrayList;

public class LocationData {
	private String location;
	private int number;
	private ArrayList<String> tags;
	
	public LocationData(String l, int n, ArrayList<String> t) {
		location = l;
		number = n;
		tags = t;
	}
	
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
	
	public ArrayList<String> getTags() {
		return tags;
	}
	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int n) {
		number = n;
	}
}
