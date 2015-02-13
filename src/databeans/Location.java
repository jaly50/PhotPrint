package databeans;

import java.io.IOException;

import utils.GeoInfo;
import databeans.Photo;

public class Location {
	private double lat;
	private double lng;
	private String location;
	
	public Location(Photo photo) {
		try {
			double[] data = GeoInfo.getGeoCode(photo.getLocation());
			lat = data[0];
			lng = data[1];
			location = photo.getLocation();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
 	
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String s) {
		location = s;
	}
}
