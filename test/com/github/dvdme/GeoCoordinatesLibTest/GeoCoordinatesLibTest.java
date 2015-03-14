package com.github.dvdme.GeoCoordinatesLibTest;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.github.dvdme.GeoCoordinatesLib.Coordinates;
import com.github.dvdme.GeoCoordinatesLib.CoordinatesCalculations;

public class GeoCoordinatesLibTest {

	public static void main(String [] args) throws IOException{

		//Some coordinates for testing
		//Lisbon:   38.7252993 , -9.1500364
		//Madrid:   40.41678 , -3.70379
		//Ceuta:    35.88838 , -5.32464
		//Paris:    48.85661 , 2.35222 
		//Berlin:   52.51917 , 13.40609
		//Brasilia: -15.83454 , -47.98828
		//London:   51.51121 , -0.11982
		//Alcatraz: 37.8267 , -122.423

		//degrees minutes seconds: 40º 26' 46'' N 79º 58' 56'' W
		//degrees decimal minutes: 40º 26.767' N 79º 58.933' W
		//decimal degrees: 40.446º N 79.982º W

		Coordinates c1 = new Coordinates("38,7252993" , "-9,1500364");
		Coordinates c2 = new Coordinates("40º 26' 46''  N ", " 79º 58' 56'' W");
		
		System.out.println(c1.toString());
		System.out.println(c2.toString());
		
		System.out.println(CoordinatesCalculations.isInCircleArea(c1, c1, 100)); //true
		System.out.println(CoordinatesCalculations.isInCircleArea(c2, c2, 100)); //true
		
		System.out.println(CoordinatesCalculations.isInCircleArea(c1, c2, 100)); //false
		System.out.println(CoordinatesCalculations.isInCircleArea(c2, c1, 100)); //false

		long start = 0;
		long stop = 0;
		double [] duration = new double[10000];

		for(int i=0; i<10000; i++){
			start = System.nanoTime();
			Coordinates random1 = CoordinatesCalculations.getRandomLocation(c1, 2000);
			Coordinates random2 = CoordinatesCalculations.getRandomLocation(c1, 2000);
			CoordinatesCalculations.isInSquareArea(random1, c1, 500);
			CoordinatesCalculations.isInCircleArea(random1, c1, 500);
			CoordinatesCalculations.isInSquareArea(random1, c2, 500);
			CoordinatesCalculations.isInCircleArea(random1, c2, 500);
			CoordinatesCalculations.isInSquareArea(random2, c1, 500);
			CoordinatesCalculations.isInCircleArea(random2, c1, 500);
			CoordinatesCalculations.isInSquareArea(random2, c2, 500);
			CoordinatesCalculations.isInCircleArea(random2, c2, 500);
			stop = System.nanoTime();
			duration[i] = (stop-start)/1000000d;
		}
		//System.out.println(duration/100000);

		BufferedWriter br = new BufferedWriter(new FileWriter("myfile.txt"));
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<duration.length; i++) {
			sb.append((i+1)+": "+duration[i]);
			sb.append("\n");
		}

		br.write(sb.toString());
		br.close();

	}

}

