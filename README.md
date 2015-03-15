GeoCoordinatesLib
===================
A Java library to deal with geographical coordinates.
It can parse one of the four formats:

* degrees minutes seconds: 40º 26' 46'' N, 79º 58' 56'' W
* degrees decimal minutes: 40º 26.767' N, 79º 58.933' W
* decimal degrees: 40.446º N, 79.982º W
* decimal: 40.446, 79.982

 Refer to [Geographic coordinate conversion](http://en.wikipedia.org/wiki/Geographic_coordinate_conversion)
 Might be usefull to work with my other library [ForecastIO-Lib-Java](https://github.com/dvdme/forecastio-lib-java)

####How it works:

* The `Coordinates` class acts as a parser and a container for latitude and longitude values.
* The `CoordinatesCalculations` class has several static methods to perform usefull calculations with geographical coordinates:
 * `isInSquareArea` Calculates if target is inside a square area where center is the center of the square and radius is half the side of the square.
 * `isInCircleArea` Calculates if target is inside a circle area where center is the center of the circle.
 * `getDerivedPosition` Calculates the end-point from a given source at a given range (meters) and bearing (degrees). This methods uses simple geometry equations to calculate the end-point.
 * `getDistanceBetweenTwoPoints` Calculates the distance in meters between two coordinates.
 * `getRandomLocation` Gets a random location given a center point and a radius.

Usage Examples
--------------

```java
//Create new coordinates
Coordinates c1 = new Coordinates(38.7252993, -9.1500364);
Coordinates c2 = new Coordinates("40º 26' 46''  N ", " 79º 58' 56'' W");

//Print coordinates in [latitude],[longitude] decimal format		
System.out.println(c1.toString());
System.out.println(c2.toString());

//Check if c1 and c2 are in a circle area of each other		
System.out.println(CoordinatesCalculations.isInCircleArea(c1, c1, 100)); //true
System.out.println(CoordinatesCalculations.isInCircleArea(c2, c2, 100)); //true		
System.out.println(CoordinatesCalculations.isInCircleArea(c1, c2, 100)); //false
System.out.println(CoordinatesCalculations.isInCircleArea(c2, c1, 100)); //false
```

Issues
------
To report issues please do it in [Github](https://github.com/dvdme/GeoCoordinatesLib) or
send me an <a href="mailto:david.dme@gmail.com">email</a>.<br>

Documentation
-------------
I generated a javadoc based in the comments I made.
It is included in the files under the javadoc/ folder but
do not expect it to be best documentation ever.

Contributors
------------
* [David Ervideira](http://github.com/dvdme) 
  * Initial implementation and main development 

License
-------
The code is available under the terms of the [Eclipse Public License](http://www.eclipse.org/legal/epl-v10.html).

