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

import java.util.ArrayList;
import java.util.List;

/**
 * Polygon, containg a list of coordinates.
 *
 * @author      Zachary Seguin (contact@zacharyseguin.ca)
 * @version     1.0
 * @since       1.0
 */
public class Polygon implements Cloneable, Serializable
{
    /**
     * Coordinates of the polygon.
     * @since 1.0
     */
    private List<Coordinate> coordinates;

    /**
     * Default constructor, using an empty list of coordinates.
     *
     * @since 1.0
     */
    public Polygon()
    {
        this.coordinates = new ArrayList<Coordinate>();
    }// End of constructor

    /**
     * Constructs a new Coordinates object, with the specified coordinates.
     *
     * @param coordinates The coordinates of the polygon.
     *
     * @since 1.0
     */
    public Polygon(List<Coordinate> coordinates)
    {
        this.coordinates = coordinates;
    }// End of constructor method

    /**
     * Returns the coordinates of the polygon.
     *
     * @return Coordinates of the polygon.
     * @since 1.0
     */
    public List<Coordinate> getCoordinates()
    {
        return this.coordinates;
    }// End of getCoordinates method

    /**
     * Sets the coordinates of the polygon.
     *
     * @param coordinates The coordinates of the polygon.
     * @since 1.0
     */
    public void setCoordinates(List<Coordinate> coordinates)
    {
        this.coordinates = coordinates;
    }// End of setCoordinates method
}// End of class
