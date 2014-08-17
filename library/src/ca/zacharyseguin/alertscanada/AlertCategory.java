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
 * Alert categories denoting event categories.
 *
 * @author      Zachary Seguin (contact@zacharyseguin.ca)
 * @version     1.0
 * @since       1.0
 */
public enum AlertCategory implements AlertEnum
{
    /**
     * Geophysical.
     * @since 1.0
     */
    GEOPHYSICAL("Geo"),

    /**
     * Meteorological.
     * @since 1.0
     */
    METEOROLOGICAL("Met"),

    /**
     * General emergecny and public safety.
     * @since 1.0
     */
    SAFETY("Safety"),

    /**
     * Law enforcement, military, homeland and local/private security.
     * @since 1.0
     */
    SECURITY("Security"),

    /**
     * Rescue and recovery.
     * @since 1.0
     */
    RESCUE("Rescue"),

    /**
     * Fire suppression and rescue.
     * @since 1.0
     */
    FIRE("Fire"),

    /**
     * Medical and public health.
     * @since 1.0
     */
    HEALTH("Health"),

    /**
     * Pollution and other environmental.
     * @since 1.0
     */
    ENVIRONMENT("Env"),

    /**
     * Public and private transportation.
     * @since 1.0
     */
    TRANSPORT("Transport"),

    /**
     * Utility, telecommunication, other non-transport infrastructure.
     * @since 1.0
     */
    INFRASTRUCTURE("Infra"),

    /**
     * Chemical, Biological, Radiological, Nuclear or High-Yield Explosive threat or attack.
     * @since 1.0
     */
    CHEMICAL_BIOLOGICAL_RADIOLOGICAL_NUCLEAR_EXPLOSIVE("CBRNE"),

    /**
     * Other events.
     * @since 1.0
     */
    OTHER("Other");

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
    AlertCategory(String value)
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
