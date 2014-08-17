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

package ca.zacharyseguin.alertscanada;

import ca.zacharyseguin.util.geo.Coordinate;
import ca.zacharyseguin.util.geo.Circle;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Alert resource for additional/supplemental information.
 * <br />
 * All fields are required, unless specified otherwise.
 *
 * @author      Zachary Seguin (contact@zacharyseguin.ca)
 * @version     1.0
 * @since       1.0
 */
public class AlertArea implements Serializable
{
    /**
     * Altitude was not provided.
     * @since 1.0
     */
    public static double ALTITUDE_NOT_PROVIDED = Double.MIN_VALUE;

    /**
     * Ceiling was not proivded.
     * @since 1.0
     */
    public static double CEILING_NOT_PROVIDED = Double.MIN_VALUE;

    /**
     * Text describing the affected area.
     * @since 1.0
     */
    private String description;

    /**
     * Points defining a polygon that delineates the affected area. (Optional)
     * <br />
     * A minimum of 4 coordinate pairs will appear, with the first and last coordinates will be the same.
     *
     * @since 1.0
     */
    private List<Coordinate> polygon;

    /**
     * Circle defining the affected area. (Optional)
     * @since 1.0
     */
    private Circle circle;

    /**
     * Geographic codes representing the affected area. (ValueName, Value) (Optional)
     * @since 1.0
     */
    private Map<String, String> geocodes;

    /**
     * Specific or minimum altitude area of the affected area. (Optional)
     * @since 1.0
     */
    private double altitude;

    /**
     * Maximum altitude of the affected area. (Optional)
     * @since 1.0
     */
    private double ceiling;

    /**
     * Default constructor - Use AlertArea.Builder to construct the AlertArea.
     * @since 1.0
     */
    private AlertArea()
    {
        this.description = null;
        this.polygon = new ArrayList<Coordinate>();
        this.circle = null;
        this.geocodes = new TreeMap<String, String>();
        this.altitude = ALTITUDE_NOT_PROVIDED;
        this.ceiling = CEILING_NOT_PROVIDED;
    }// End of constructor

    /**
     * Returns the description of the affected area.
     * @return Description of the affected area.
     * @since 1.0
     */
    public String getDescription()
    {
        return this.description;
    }// End of getDescription method

    /**
     * Returns the polygon describing the affected area.
     * <br />
     * If provided, a minimum of 4 points will appear and the first and last points will be the same.
     *
     * @return Polygon descriping the affected area.
     * @since 1.0
     */
    public List<Coordinate> getPolygon()
    {
        return this.polygon;
    }// End of getPolygon method

    /**
     * Return the circle describing the the affected area.
     * @return The circle describing the affected area.
     * @since 1.0
     */
    public Circle getCircle()
    {
        return this.circle;
    }// End of getCircle method

    /**
     * Returns the geographic codes describing the affected elements.
     * @return The geographic codes describing the affected elements.
     * @since 1.0
     */
    public Map<String, String> getGeocodes()
    {
        return this.geocodes;
    }// End of getGeocodes method

    /**
     * Returns the specific or minimum altitude of the affected area.
     * @return Specific or minimum altitude of the affected area. Returns {@link ca.zacharyseguin.alertscanada.AlertArea#ALTITUDE_NOT_PROVIDED} if the altitude is not provided.
     * @since 1.0
     */
    public double getAltitude()
    {
        return this.altitude;
    }// End of getAltitude method

    /**
     * Returns the maximum altitude of the affected area.
     * @return Maximum altitude of the affected area. Returns {@link ca.zacharyseguin.alertscanada.AlertArea#CEILING_NOT_PROVIDED} if the altitude is not provided.
     * @since 1.0
     */
    public double getCeiling()
    {
        return this.ceiling;
    }// End of getCeiling method
}// End of class
