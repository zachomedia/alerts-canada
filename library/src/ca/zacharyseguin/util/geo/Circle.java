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
 * Circle on earth, center coordinate and radius.
 *
 * @author      Zachary Seguin (contact@zacharyseguin.ca)
 * @version     1.0
 * @since       1.0
 */
public class Circle implements Serializable
{
    /**
     * Coordinate of the center of the circle.
     * @since 1.0
     */
    private Coordinate center;

    /**
     * Radius of the circle.
     * @since 1.0
     */
    private double radius;

    /**
     * Default constructor, using default center point of (0.0, 0.0) and a radius of 0.0.
     *
     * @since 1.0
     */
    public Circle()
    {
        this.center = new Coordinate();
        this.radius = 0.0;
    }// End of constructor

    /**
     * Constructs a new cirlce object, with the specified center point and radius.
     *
     * @param center The coordinates to the center of the circle.
     * @param radius The radius of the circle.
     *
     * @since 1.0
     */
    public Circle(Coordinate center, double radius)
    {
        this.center = center;
        this.radius = radius;
    }// End of constructor method

    /**
     * Returns the center coordinate of the circle.
     *
     * @return The center coordinate of the circle.
     * @since 1.0
     */
    public Coordinate getCenter()
    {
        return this.center;
    }// End of getCenter method

    /**
     * Returns the radius of the circle.
     *
     * @return The radius of the circle.
     * @since 1.0
     */
    public double getRadius()
    {
        return this.radius;
    }// End of getRadius method

    /**
     * Sets the center of the circle.
     *
     * @param center The center of the circle.
     * @since 1.0
     */
    public void setCenter(Coordinate center)
    {
        this.center = center;
    }// End of setCenter method

    /**
     * Sets the radius of the circle.
     *
     * @param radius The radius of the circle.
     * @since 1.0
     */
    public void setRadius(double radius)
    {
        this.radius = radius;
    }// End of setRadius method
}// End of class
