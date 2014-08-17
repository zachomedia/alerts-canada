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
import java.net.URL;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Details of an issued alert.
 *
 * @author      Zachary Seguin (contact@zacharyseguin.ca)
 * @version     1.0
 * @since       1.0
 */
public class AlertInfo implements Serializable
{
    /**
     * Code denoting the language of alert info.
     * <br />
     * This field is optional
     *
     * @since 1.0
     */
    private String language;

    /**
     * Codes denoting the categories of the event of the alert.
     * @since 1.0
     */
    private List<AlertCategory> categories;

    /**
     * Text denoting the type of the event of the alert.
     * @since 1.0
     */
    private String event;

    /**
     * Codes denoting the type of action recommended for the target audience.
     * <br />
     * This field is optional.
     * @since 1.0
     */
    private List<AlertResponseType> responseTypes;

    /**
     * Code denoting the severity of the event of the alert.
     * @since 1.0
     */
    private AlertSeverity severity;

    /**
     * Code denoting the urgency of the event of the alert.
     * @since 1.0
     */
    private AlertUrgency urgency;

    /**
     * Code deonting the certainty of the event.
     * @since 1.0
     */
    private AlertCertainty certainty;

    /**
     * Text describing the audience of the alert.
     * <br />
     * This field is optional.
     *
     * @since 1.0
     */
    private String audience;

    /**
     * System-specific event codes identifying the type of alert message.
     * <br />
     * (ValueName, Value)
     *
     * @since 1.0
     */
    private Map<String, String> eventCodes;

    /**
     * Effective time of the alert message.
     * <br />
     * This field is optional.
     *
     * @since 1.0
     */
    private Calendar effective;

    /**
     * The expected time of the beginning of the event of the alert.
     * <br />
     * This field is optional.
     *
     * @since 1.0
     */
    private Calendar onset;

    /**
     * The expiry time of the alert message.
     * <br />
     * This field is optional.
     *
     * @since 1.0
     */
    private Calendar expires;

    /**
     * Name of the agency/authority sending the alert.
     * <br />
     * This field is optional.
     *
     * @since 1.0
     */
    private String senderName;

    /**
     * Headling of the event of the alert.
     * <br />
     * This field is optional.
     *
     * @since 1.0
     */
    private String headline;

    /**
     * Text describing the event of the alert.
     * <br />
     * This field is optional.
     *
     * @since 1.0
     */
    private String description;

    /**
     * Text describing the recommended action to be taken by the recipients of the alert.
     * <br />
     * This field is optional.
     *
     * @since 1.0
     */
    private String instruction;
    private URL web;
    private String contact;
    private Map<String, String> parameters;
    private List<AlertResource> resources;
    private List<AlertArea> areas;
}// End of class
