package com.github.dvdme.GeoCoordinatesLibTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.dvdme.GeoCoordinatesLib.Coordinates;

public class CoordinatesTest {

	@Test
	public void testGetLatitude() {
		Coordinates c = new Coordinates("40º 26' 46''  N ", " 79º 58' 56'' W");
		assertEquals("", 40.446, c.getLatitude(), 0.001);
	}

	@Test
	public void testGetLongitude() {
		Coordinates c = new Coordinates("40º 26' 46''  N ", " 79º 58' 56'' W");
		assertEquals("", 79.982, c.getLongitude(), 0.001);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCoordinatesIllegalArgument() {
		Coordinates c = new Coordinates();
		c.setLatitude("60º 40º 26' 46''  S ");
		c.setLongitude(" 79º 58' 56'' 12''' E");
	}
	
	@Test
	public void testCoordinatesDegreesMinutesSeconds() {
		Coordinates c = new Coordinates();
		c.setLatitude("40º 26' 46''  S ");
		c.setLongitude(" 79º 58' 56'' E");
		assertEquals("", -40.446, c.getLatitude(), 0.001);
		assertEquals("", -79.982, c.getLongitude(), 0.001);
	}
	
	@Test
	public void testCoordinatesDegreesDecimalMinutes() {
		Coordinates c = new Coordinates();
		c.setLatitude("40º 26.767'  N ");
		c.setLongitude(" 79º 58.933 W");
		assertEquals("", 40.446, c.getLatitude(), 0.001);
		assertEquals("", 79.982, c.getLongitude(), 0.001);
	}
	
	@Test
	public void testCoordinatesDecimalDegrees() {
		Coordinates c = new Coordinates();
		c.setLatitude("40.446º S ");
		c.setLongitude(" 79.982º E");
		assertEquals("", -40.446, c.getLatitude(), 0.001);
		assertEquals("", -79.982, c.getLongitude(), 0.001);
	}

}
