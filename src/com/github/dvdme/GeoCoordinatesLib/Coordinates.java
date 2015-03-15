package com.github.dvdme.GeoCoordinatesLib;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Geographical Coordinates Object
 * 
 * <p>Parses three formats of geographical coordinates</p>
 * <p>Format can be like one the following:</p>
 * <p>degrees minutes seconds: 40º 26' 46'' N 79º 58' 56'' W</p>
 * <p>degrees decimal minutes: 40º 26.767' N 79º 58.933' W</p>
 * <p>decimal degrees: 40.446º N 79.982º W</p>
 * 
 * @author David
 *@version 1.0
 */

public class Coordinates {

	private double latitude;
	private double longitude;

	/**
	 * Constructor
	 * Initializes latitude and longitude with 0.
	 */
	public Coordinates(){
		setLatitude(0);
		setLongitude(0);
	}

	/**
	 * Constructor
	 * Initializes latitude and longitude in one of the supported formats.
	 * 
	 * @param String with latitude 
	 * @param String with longitude
	 */
	public Coordinates(String latitude, String longitude){
		setLatitude(parseCoordinate(latitude));
		setLongitude(parseCoordinate(longitude));
	}

	/**
	 * Constructor
	 * Initializes latitude and longitude in decimal degrees.
	 * 
	 * @param Double with latitude 
	 * @param Double with longitude
	 */
	public Coordinates(double latitude, double longitude){
		setLatitude(latitude);
		setLongitude(longitude);
	}
	
	/**
	 * Gets the latitude
	 * @return latitude
	 */
	public double getLatitude() {
		return latitude;
	}
	
	/**
	 * Gets the latitude as string
	 * @return latitude as string
	 */
	public String getLatitudeAsString() {
		return String.valueOf(latitude);
	}

	/**
	 * Sets the latitude
	 * @param latitude
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	/**
	 * Sets the latitude
	 * @param latitude
	 */
	public void setLatitude(String latitude) {
		setLatitude( parseCoordinate(latitude) );
	}

	public double getLongitude() {
		return longitude;
	}

	public String getLongitudeAsString() {
		return String.valueOf(longitude);
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public void setLongitude(String longitude) {
		setLongitude( parseCoordinate(longitude) );
	}

	@Override
	public String toString(){
		return String.valueOf(latitude) + "," + String.valueOf(longitude);
	} 

	private double parseCoordinate(String c){

		double retval = 0.0f;

		ArrayList<String> ar = parseIntsAndFloats(c);
		String [] c2 = new String[ar.size()];
		for(int i=0; i<ar.size(); i++){
			c2[i] = ar.get(i).replace(",", ".");
		}

		if(c2.length == 3){

			double degrees = Double.parseDouble(c2[0]);
			double minutes = Double.parseDouble(c2[1]);
			double seconds = Double.parseDouble(c2[2]);
			retval = degrees + minutes / 60 + seconds / 3600;

		} else if(c2.length == 2) {

			double degrees = Double.parseDouble(c2[0]);
			double minutes = Double.parseDouble(c2[1]);
			retval = degrees + minutes / 60;

		} else if(c2.length == 1) {

			retval = Double.parseDouble(c2[0]);

		} else {

			throw new IllegalArgumentException();

		}

		return isNegative(c) ? retval * -1 : retval;
	}

	private ArrayList<String> parseIntsAndFloats(String raw) {

		ArrayList<String> listBuffer = new ArrayList<String>();

		Pattern p = Pattern.compile("[-]?[0-9]*\\.?,?[0-9]+");

		Matcher m = p.matcher(raw);

		while (m.find()) {
			listBuffer.add(m.group());
		}

		return listBuffer;
	}

	private boolean isNegative(String raw) {

		boolean retval = false;
		raw = raw.toUpperCase();

		if( raw.contains("N") || raw.contains("W") )
			retval = false;
		else if(raw.contains("S") || raw.contains("E") )
			retval = true;

		return retval;
	}

}


