package com.github.dvdme.GeoCoordinatesLibTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.dvdme.GeoCoordinatesLib.Coordinates;

public class CoordinatesTest {

	@Test
	public void testGetLatitude() {
		Coordinates c = new Coordinates("40� 26' 46''  N ", " 79� 58' 56'' W");
		assertEquals("", 40.446, c.getLatitude(), 0.001);
	}

	@Test
	public void testGetLongitude() {
		Coordinates c = new Coordinates("40� 26' 46''  N ", " 79� 58' 56'' W");
		assertEquals("", 79.982, c.getLongitude(), 0.001);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCoordinatesIllegalArgument() {
		Coordinates c = new Coordinates();
		c.setLatitude("60� 40� 26' 46''  S ");
		c.setLongitude(" 79� 58' 56'' 12''' E");
	}
	
	@Test
	public void testCoordinatesDegreesMinutesSeconds() {
		Coordinates c = new Coordinates();
		c.setLatitude("40� 26' 46''  S ");
		c.setLongitude(" 79� 58' 56'' E");
		assertEquals("", -40.446, c.getLatitude(), 0.001);
		assertEquals("", -79.982, c.getLongitude(), 0.001);
	}
	
	@Test
	public void testCoordinatesDegreesDecimalMinutes() {
		Coordinates c = new Coordinates();
		c.setLatitude("40� 26.767'  N ");
		c.setLongitude(" 79� 58.933 W");
		assertEquals("", 40.446, c.getLatitude(), 0.001);
		assertEquals("", 79.982, c.getLongitude(), 0.001);
	}
	
	@Test
	public void testCoordinatesDecimalDegrees() {
		Coordinates c = new Coordinates();
		c.setLatitude("40.446� S ");
		c.setLongitude(" 79.982� E");
		assertEquals("", -40.446, c.getLatitude(), 0.001);
		assertEquals("", -79.982, c.getLongitude(), 0.001);
	}

}
