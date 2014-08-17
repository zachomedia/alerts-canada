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

import java.util.ArrayList;
import java.util.TreeMap;
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
public class AlertInfo implements Cloneable, Serializable
{
    /**
     * Code denoting the language of alert info. (Optional)
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
     * Codes denoting the type of action recommended for the target audience. (Optional)
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
     * Text describing the audience of the alert. (Optional)
     *
     * @since 1.0
     */
    private String audience;

    /**
     * System-specific event codes identifying the type of alert. (ValueName, Value)
     *
     * @since 1.0
     */
    private Map<String, String> eventCodes;

    /**
     * Effective time of the alert message. (Optional)
     *
     * @since 1.0
     */
    private Calendar effective;

    /**
     * The expected time of the beginning of the event of the alert. (Optional)
     *
     * @since 1.0
     */
    private Calendar onset;

    /**
     * The expiry time of the alert message. (Optional)
     *
     * @since 1.0
     */
    private Calendar expires;

    /**
     * Name of the agency/authority sending the alert. (Optional)
     *
     * @since 1.0
     */
    private String senderName;

    /**
     * Headline of the event of the alert. (Optional)
     *
     * @since 1.0
     */
    private String headline;

    /**
     * Text describing the event of the alert. (Optional)
     *
     * @since 1.0
     */
    private String description;

    /**
     * Text describing the recommended action to be taken by the recipients of the alert. (Optional)
     *
     * @since 1.0
     */
    private String instruction;

    /**
     * Hyperlink to additional information. (Optional)
     *
     * @since 1.0
     */
    private URL web;

    /**
     * Text describing the contact for follow-up and confirmation of the alert. (Optional)
     *
     * @since 1.0
     */
    private String contact;

    /**
     * System-specific additional parameters associated with the alert. (ValueName, Value) (Optional)
     *
     * @since 1.0
     */
    private Map<String, String> parameters;

    /**
     * Files with additional/supplemental information. (Optional)
     *
     * @since 1.0
     */
    private List<AlertResource> resources;

    /**
     * Areas for which the alert is effective. (Optional)
     *
     * @since 1.0
     */
    private List<AlertArea> areas;

    /**
     * Default constructor - Construct alert info objects using the AlertInfo.Builder.
     * @since 1.0
     */
    private AlertInfo()
    {
        this.language = null;
        this.categories = new ArrayList<AlertCategory>();
        this.event = null;
        this.responseTypes = new ArrayList<AlertResponseType>();
        this.severity = null;
        this.urgency = null;
        this.certainty = null;
        this.audience = null;
        this.eventCodes = new TreeMap<String, String>();
        this.effective = null;
        this.onset = null;
        this.expires = null;
        this.senderName = null;
        this.headline = null;
        this.description = null;
        this.instruction = null;
        this.web = null;
        this.contact = null;
        this.parameters = new TreeMap<String, String>();
        this.resources = new ArrayList<AlertResource>();
        this.areas = new ArrayList<AlertArea>();
    }// End of constructor

    /**
     * Returns the language of the alert.
     * @return The language of the alert.
     * @since 1.0
     */
    public String getLanguage()
    {
        return this.language;
    }// End of getLanguage method

    /**
     * Returns the codes denoting the categories of the event.
     * @return Codes denoting the categories of the event.
     * @since 1.0
     */
    public List<AlertCategory> getCategories()
    {
        return this.categories;
    }// End of getCategories method

    /**
     * Returns the text denoting the event.
     * @return Text denoting the event.
     * @since 1.0
     */
    public String getEvent()
    {
        return this.event;
    }// End of getEvent method

    /**
     * Returns the list of recommended actions to be taken by the target audience.
     * @return List of recommneded actions to be taken by the target audience.
     * @since 1.0
     */
    public List<AlertResponseType> getResponseTypes()
    {
        return this.responseTypes;
    }// End of getResponseTypes method

    /**
     * Returns the code denoting the severity of the event.
     * @return Code denoting the severity of the event.
     * @since 1.0
     */
    public AlertSeverity getSeverity()
    {
        return this.severity;
    }// End of getSeverity method

    /**
     * Returns the code denoting the urgency of the alert.
     * @return Code denoting the urgency of the alert.
     * @since 1.0
     */
    public AlertUrgency getUrgency()
    {
        return this.urgency;
    }// End of getUrgency method

    /**
     * Returns the code denoting the certainty of the event.
     * @return Code denoting the certainty of the event.
     * @since 1.0
     */
    public AlertCertainty getCertainty()
    {
        return this.certainty;
    }// End of getCertainty method

    /**
     * Returns the system-specific codes identifying the type of alert.
     * @return System-specific codes identfiying the type of the alert.
     * @since 1.0
     */
    public Map<String, String> getEventCodes()
    {
        return this.eventCodes;
    }// End of getEventCodes method

    /**
     * Returns the time at which the alert becomes effective.
     * @return Time at which the alert becomes effective.
     * @since 1.0
     */
    public Calendar getEffective()
    {
        return this.effective;
    }// End of getEffective method

    /**
     * Returns the time at which the event is expected to begin.
     * @return The time at which the event is expected to begin.
     * @since 1.0
     */
    public Calendar getOnset()
    {
        return this.onset;
    }// End of getOnset method

    /**
     * Returns the time at which the alert expires.
     * @return The time at which the alert expires.
     * @since 1.0
     */
    public Calendar getExpires()
    {
        return this.expires;
    }// End of getExpires method

    /**
     * Returns the name of the agency/authority issuing the alert.
     * @return Name of the agency/authority issuing the alert.
     * @since 1.0
     */
    public String getSenderName()
    {
        return this.senderName;
    }// End of getSenderName method

    /**
     * Returns the headline of the event.
     * @return The headline of the event.
     * @since 1.0
     */
    public String getHeadline()
    {
        return this.headline;
    }// End of getHeadline method

    /**
     * Returns the description of the event.
     * @return Description of the event.
     * @since 1.0
     */
    public String getDescription()
    {
        return this.description;
    }// End of getDescription method

    /**
     * Returns the recommended action to be taken by the intended audience of the alert.
     * @return Recommended action to be taken by the intended audience of the alert.
     * @since 1.0
     */
    public String getInstruction()
    {
        return this.instruction;
    }// End of getInstruction method

    /**
     * Returns a hyperlink to the internet for additional information about the event/alert.
     * @return Hyperlink to the internet for additional information about the event/alert.
     * @since 1.0
     */
    public URL getWeb()
    {
        return this.web;
    }// End of getWeb method

    /**
     * Returns the contact for follow-up and/or confirmation of the alert.
     * @return The contact for follow-up and/or confirmation of the alert.
     * @since 1.0
     */
    public String getContact()
    {
        return this.contact;
    }// End of getContact method

    /**
     * Returns the system-specific parameters associated with the alert.
     * @return System-specific parameters associated with the alert.
     * @since 1.0
     */
    public Map<String, String> getParameters()
    {
        return this.parameters;
    }// End of getParameters method

    /**
     * Return the resources providing additional/supplemental information about the alert.
     * @return Resources providing additional/supplemental information about the alert.
     * @since 1.0
     */
    public List<AlertResource> getResources()
    {
        return this.resources;
    }// End of getResources method

    /**
     * Returns the areas where the alert is effective.
     * @return Areas where the alert is effective.
     * @since 1.0
     */
    public List<AlertArea> getAreas()
    {
        return this.areas;
    }// End of getAreas method

    /**
     * AlertInfo builder.
     *
     * @since 1.0
     */
    public static class Builder
    {
        /**
         * The alert info object being constructed.
         * @since 1.0
         */
        private AlertInfo alertInfo;

        /**
         * Constructs a new AlertInfo.Builder object.
         * @since 1.0
         */
        public Builder()
        {
            this.alertInfo = new AlertInfo();
        }// End of constructor method

        /**
         * Constructs and returns the AlertInfo object.
         * @return The constructed AlertInfo object.
         * @since 1.0
         */
        public AlertInfo build()
        {
            try
            {
                return (AlertInfo)this.alertInfo.clone();
            }// End of try
            catch (Exception e)
            {
                return null;
            }// End of catch
        }// End of build method

        public Builder language(String language)
        {
            this.alertInfo.language = language;
            return this;
        }// End of language method

        public Builder categories(List<AlertCategory> categories)
        {
            this.alertInfo.categories = categories;
            return this;
        }// End of categories method

        public Builder event(String event)
        {
            this.alertInfo.event = event;
            return this;
        }// End of event method

        public Builder responseTypes(List<AlertResponseType> responseTypes)
        {
            this.alertInfo.responseTypes = responseTypes;
            return this;
        }// End of responseTypes method

        public Builder severity(AlertSeverity severity)
        {
            this.alertInfo.severity = severity;
            return this;
        }// End of severity method

        public Builder urgency(AlertUrgency urgency)
        {
            this.alertInfo.urgency = urgency;
            return this;
        }// End of urgency method

        public Builder certainty(AlertCertainty certainty)
        {
            this.alertInfo.certainty = certainty;
            return this;
        }// End of certainty method

        public Builder audience(String audience)
        {
            this.alertInfo.audience = audience;
            return this;
        }// End of audience method

        public Builder eventCodes(Map<String, String> eventCodes)
        {
            this.alertInfo.eventCodes = eventCodes;
            return this;
        }// End of eventCodes method

        public Builder effective(Calendar effective)
        {
            this.alertInfo.effective = effective;
            return this;
        }// End of effective method

        public Builder onset(Calendar onset)
        {
            this.alertInfo.onset = onset;
            return this;
        }// End of onset method

        public Builder expires(Calendar expires)
        {
            this.alertInfo.expires = expires;
            return this;
        }// End of expires method

        public Builder senderName(String senderName)
        {
            this.alertInfo.senderName = senderName;
            return this;
        }// End of senderName method

        public Builder headline(String headline)
        {
            this.alertInfo.headline = headline;
            return this;
        }// End of headline method

        public Builder description(String description)
        {
            this.alertInfo.description = description;
            return this;
        }// End of description method

        public Builder instruction(String instruction)
        {
            this.alertInfo.instruction = instruction;
            return this;
        }// End of instruction method

        public Builder web(URL web)
        {
            this.alertInfo.web = web;
            return this;
        }// End of web method

        public Builder contact(String contact)
        {
            this.alertInfo.contact = contact;
            return this;
        }// End of contact method

        public Builder parameters(Map<String, String> parameters)
        {
            this.alertInfo.parameters = parameters;
            return this;
        }// End of parameters method

        public Builder resources(List<AlertResource> resources)
        {
            this.alertInfo.resources = resources;
            return this;
        }// End of resources method

        public Builder areas(List<AlertArea> areas)
        {
            this.alertInfo.areas = areas;
            return this;
        }// End of areas method
    }// End of Builder class
}// End of class
