package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import sun.net.www.protocol.http.HttpURLConnection;


public class GeoInfo {

	public static double[] getGeoCode(String place) throws IOException {
		HttpURLConnection connection = null;
		
		String placeValid = parsePlace(place);
		
		try {
			URL url = new URL("http://maps.googleapis.com/maps/api/geocode/json?address=" + placeValid + "&sensor=false");
			connection =  (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			
			
			
			JSONObject obj = (JSONObject) JSONValue
					.parse(readResponse(connection));
			JSONArray results = (JSONArray) obj.get("results");
			if(results == null || results.size() == 0) {
				return null;
			}
			JSONObject result = (JSONObject) results.get(0);
			JSONObject geometry = (JSONObject) result.get("geometry");
			System.out.println("geometry" + geometry.toString());
			JSONObject location = (JSONObject) geometry.get("location");
			System.out.println("location" + location.toString());
			double lat = (Double) location.get("lat");
			double lng = (Double) location.get("lng");
			System.out.println("location!!!!!!!!!" + lat + " "+ lng);
			double[] xy = new double[2];
			xy[0] = lat;
			xy[1] = lng;
			return xy;
		} catch (MalformedURLException e) {
			throw new IOException("Invalid endpoint URL specified.", e);
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}

	}
	
	// Reads a response for a given connection and returns it as a string.
		private static String readResponse(HttpURLConnection connection) {
			try {
				StringBuilder str = new StringBuilder();

				BufferedReader br = new BufferedReader(new InputStreamReader(
						connection.getInputStream()));
				String line = "";
				while ((line = br.readLine()) != null) {
					str.append(line + System.getProperty("line.separator"));
				}
				return str.toString();
			} catch (IOException e) {
				return new String();
			}
		}
	
	public static String parsePlace(String place) {
		if (place == null || place.length() == 0) return null;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < place.length(); i++) {
			if (place.charAt(i) == ' ' || place.charAt(i) == '.'
					|| place.charAt(i) == ',') continue;
			sb.append(place.charAt(i));
		}
		return sb.toString();
	}
		
	public static void main(String[] args) {
		try {
			
			double [] locations = new GeoInfo().getGeoCode("?");
			System.out.print(Arrays.toString(locations));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
