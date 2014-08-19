/*
The MIT License (MIT)

Copyright (c) 2014 Zachary Seguin

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package ca.zacharyseguin.util.geo;

import java.io.Serializable;

/**
 * Coordinate on earth, latitude and longitude.
 *
 * @author      Zachary Seguin (contact@zacharyseguin.ca)
 * @version     1.0
 * @since       1.0
 */
public class Coordinate implements Cloneable, Serializable
{
    /**
     * Latitude component of the coordinate.
     * @since 1.0
     */
    private double latitude;

    /**
     * Longitude component of the coordinate.
     * @since 1.0
     */
    private double longitude;

    /**
     * Default constructor, setting latitude and longitude to 0.0.
     *
     * @since 1.0
     */
    public Coordinate()
    {
        this.latitude = 0.0;
        this.longitude = 0.0;
    }// End of constructor

    /**
     * Constructs a new coordinate object, with the specified latitude and longitutde.
     *
     * @param latitude The latitude component of the coordinate.
     * @param longitude The longitude component of the coordinate.
     *
     * @since 1.0
     */
    public Coordinate(double latitude, double longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude;
    }// End of constructor method

    /**
     * Returns the latitude component of the represented coordinate.
     *
     * @return The latitude component of the represented coordinate.
     * @since 1.0
     */
    public double getLatitude()
    {
        return this.latitude;
    }// End of getLatitude method

    /**
     * Returns the longitude component of the represented coordinate.
     *
     * @return The longitude component of the represented coordinate.
     * @since 1.0
     */
    public double getLongitude()
    {
        return this.longitude;
    }// End of getLongitude method

    /**
     * Sets the latitude component of the represented coordinate.
     *
     * @param latitude The longitude component of the represented coordinate.
     * @since 1.0
     */
    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }// End of setLatitude method

    /**
     * Sets the longitude component of the represented coordinate.
     *
     * @param longitude The longitude component of the represented coordinate.
     * @since 1.0
     */
    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }// End of setLongitude method
}// End of class
