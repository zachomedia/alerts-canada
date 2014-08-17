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

import java.util.List;
import java.util.Map;

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
}// End of class
