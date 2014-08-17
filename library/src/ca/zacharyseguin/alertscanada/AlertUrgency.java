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

/**
 * Alert urgencies denoting the urgency of an alert.
 *
 * @author      Zachary Seguin (contact@zacharyseguin.ca)
 * @version     1.0
 * @since       1.0
 */
public enum AlertUrgency implements AlertEnum
{
    /**
     * Responsive action should be taken immediately.
     * @since 1.0
     */
    IMMEDIATE("Immediate"),

    /**
     * Responsive action should be taken soon (within next hour).
     * @since 1.0
     */
    EXPECTED("Expected"),

    /**
     * Responsive action sould be taken in the near future.
     * @since 1.0
     */
    FUTURE("Future"),

    /**
     * Responsive action is no longer required.
     * @since 1.0
     */
    PAST("Past"),

    /**
     * Urgency is not known.
     * @since 1.0
     */
    UNKNOWN("Unknown");

    /**
     * Value provided by NAAD.
     * @since 1.0
     */
    private String value;

    /**
     * Constructs the enum value.
     *
     * @param value The value of the enum in NAAD.
     *
     * @since 1.0
     */
    AlertUrgency(String value)
    {
        this.value = value;
    }// End of constructor

    /**
     * Returns the value as provided by NAAD.
     *
     * @since 1.0
     */
    public String getValue()
    {
        return this.value;
    }// End of getValue method
}// End of interface
