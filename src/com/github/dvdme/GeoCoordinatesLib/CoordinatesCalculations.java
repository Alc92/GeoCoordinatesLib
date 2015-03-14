package com.github.dvdme.GeoCoordinatesLib;

import java.util.Random;

public class CoordinatesCalculations {
	
	private CoordinatesCalculations () {
		//Empty private constructor to prevent instantiation
	}

	public static boolean isInSquareArea(Coordinates coordinate,Coordinates center, int radius){

		boolean latres = false;
		boolean lonres  = false;

		Coordinates p1 = getDerivedPosition(center, radius, 0);
		Coordinates p2 = getDerivedPosition(center, radius, 90);
		Coordinates p3 = getDerivedPosition(center, radius, 180);
		Coordinates p4 = getDerivedPosition(center, radius, 270);

		if(coordinate.getLatitude() >= p3.getLatitude())
			if(coordinate.getLatitude() <= p1.getLatitude())
				latres = true;

		if(coordinate.getLongitude() <= p2.getLongitude())
			if(coordinate.getLongitude() >= p4.getLongitude())
				lonres = true;

		return (latres && lonres);
	}

	public static boolean isInCircleArea(Coordinates coordinate,Coordinates center, int radius){

		boolean res = isInSquareArea(coordinate, center, radius);
		if (getDistanceBetweenTwoPoints(coordinate, center) <= radius)
			res = true;
		else
			res = false;

		return res;

	}

	/**
	 * Calculates the end-point from a given source at a given range (meters)
	 * and bearing (degrees). This methods uses simple geometry equations to
	 * calculate the end-point.
	 * 
	 * @param point
	 *            Point of origin
	 * @param range
	 *            Range in meters
	 * @param bearing
	 *            Bearing in degrees
	 * @return End-point from the source given the desired range and bearing.
	 */
	public static Coordinates getDerivedPosition(Coordinates coordinate,double range, double bearing) {
		// http://stackoverflow.com/questions/3695224/sqlite-getting-nearest-locations-with-latitude-and-longitude

		double EarthRadius = 6371000; // meters

		double latA = Math.toRadians(coordinate.getLatitude());
		double lonA = Math.toRadians(coordinate.getLongitude());
		double angularDistance = range / EarthRadius;
		double trueCourse = Math.toRadians(bearing);

		double lat = Math.asin(Math.sin(latA) * Math.cos(angularDistance) + Math.cos(latA) * Math.sin(angularDistance) * Math.cos(trueCourse));

		double dlon = Math.atan2(Math.sin(trueCourse) * Math.sin(angularDistance) * Math.cos(latA), Math.cos(angularDistance) - Math.sin(latA) * Math.sin(lat));

		double lon = ((lonA + dlon + Math.PI) % (Math.PI * 2)) - Math.PI;

		lat = Math.toDegrees(lat);
		lon = Math.toDegrees(lon);

		return new Coordinates(lat, lon);

	}


	public static double getDistanceBetweenTwoPoints(Coordinates c1, Coordinates c2) {
		// http://stackoverflow.com/questions/3695224/sqlite-getting-nearest-locations-with-latitude-and-longitude
		
		double R = 6371000; // m
		double dLat = Math.toRadians(c2.getLatitude() - c1.getLatitude());
		double dLon = Math.toRadians(c2.getLongitude() - c1.getLongitude());
		double lat1 = Math.toRadians(c1.getLatitude());
		double lat2 = Math.toRadians(c2.getLatitude());

		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double d = R * c;

		return d;
	}
	
	public static Coordinates getRandomLocation(double centerLatitude, double centerLongitude, int radius) {
		//http://gis.stackexchange.com/questions/25877/how-to-generate-random-locations-nearby-my-location
		
		Coordinates coor = new Coordinates(centerLatitude, centerLongitude);
		
		return getRandomLocation(coor, radius);
	}
	
	public static Coordinates getRandomLocation(Coordinates center, int radius) {
		//http://gis.stackexchange.com/questions/25877/how-to-generate-random-locations-nearby-my-location
		
		Random random = new Random();

		// Convert radius from meters to degrees
		double radiusInDegrees = radius / 111000f;

		double u = random.nextDouble();
		double v = random.nextDouble();
		double w = radiusInDegrees * Math.sqrt(u);
		double t = 2 * Math.PI * v;
		double x = w * Math.cos(t);
		double y = w * Math.sin(t);

		// Adjust the x-coordinate for the shrinking of the east-west distances
		double new_x = x / Math.cos(center.getLongitude());

		double foundLatitude = new_x + center.getLatitude();
		double foundLongitude = y + center.getLongitude();
		
		return new Coordinates(foundLatitude, foundLongitude);
	}

}
