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

import java.io.Serializable;

import java.util.Calendar;
import java.util.List;

/**
 * Alert represents a single Alert received from the NAAD system.
 * <br /><br />
 * All fields a guaranteed to be present, unless specified otherwise.
 *
 * @author      Zachary Seguin (contact@zacharyseguin.ca)
 * @version     1.0
 * @since       1.0
 */
public class Alert implements Serializable
{
    /**
     * Identifier of the alert.
     * @since 1.0
     */
    private String identifier;

    /**
     * Identifier of the sender.
     * @since 1.0
     */
    private String sender;

    /**
     * Date and time of the origination of the alert.
     * @since 1.0
     */
    private Calendar sent;

    /**
     * Code denoting the appropriate handling of the alert.
     * @since 1.0
     */
    private AlertStatus status;

    /**
     * Code denoting the nature of the alert.
     * @since 1.0
     */
    private AlertType type;

    /**
     * Source of the alert.
     * <br />
     * This field is optional.
     */
    private String source;

    /**
     * Code denoting the intended distribution of the alert.
     * @since 1.0
     */
    private AlertScope scope;

    /**
     * Rule for limiting the distribution of restricted alerts.
     * <br />
     * This field is only required when scope is {@link ca.zacharyseguin.alertscanada.AlertScope#RESTRICTED}
     *
     * @since 1.0
     */
    private String restriction;

    /**
     * Listing of intended recipients of the alert.
     * <br />
     * Required when scope is {@link ca.zacharyseguin.alertscanada.AlertScope#PRIVATE}, optional otherwise.
     *
     * @since 1.0
     */
    private List<String> addresses;

    /**
     * Codes denoting special handling of the alert.
     * <br />
     * This field is optional.
     *
     * @since 1.0
     */
    private List<String> codes;

    /**
     * Text describing the purpose or significance of the alert.
     * <br />
     * This field is optional.
     *
     * @since 1.0
     */
    private String note;

    /**
     * References to previous alerts.
     * <br />
     * This field is optional.
     *
     * @since 1.0
     */
    private List<AlertReference> references;

    /**
     * Listing of reference incidents of the alert.
     * <br />
     * This field is optional.
     *
     * @since 1.0
     */
    private List<String> incidents;

    /**
     * Alert information
     *
     * @since 1.0
     */
    private List<AlertInfo> information;
}// End of class
