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

}
