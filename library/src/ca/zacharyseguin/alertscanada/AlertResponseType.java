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
public enum AlertResponseType implements AlertEnum
{
    /**
     * Take shelter in place or per instructions.
     * @since 1.0
     */
    SHELTER("Shelter"),

    /**
     * Relocate as instructed in the instructions.
     * @since 1.0
     */
    EVACUATE("Evacuate"),

    /**
     * Make preparations per the instructions.
     * @since 1.0
     */
    PREPARE("Prepare"),

    /**
     * Execute a pre-planned activity identified in instructions.
     * @since 1.0
     */
    EXECUTE("Execute"),

    /**
     * Avoid the subject event as per the instructions.
     * @since 1.0
     */
    AVOID("Avoid"),

    /**
     * Attend to information sources as described in instructions.
     * @since 1.0
     */
    MONITOR("Monitor"),

    /**
     * Evaluate the information in this message.
     * <br />
     * NOTE: This should not be used in publich warning application.
     *
     * @since 1.0
     */
    ASSESS("Assess"),

    /**
     * The subject event poses a threat or concern and any follow on action is describe in the instructions.
     * @since 1.0
     */
    ALL_CLEAR("AllClear"),

    /**
     * No action is recommended.
     * @since 1.0
     */
    NONE("None");

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
    AlertResponseType(String value)
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
