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

import java.util.ArrayList;
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
public class Alert implements Cloneable, Serializable
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
     * Source of the alert. (Optional)
     * @since 1.0
     */
    private String source;

    /**
     * Code denoting the intended distribution of the alert.
     * @since 1.0
     */
    private AlertScope scope;

    /**
     * Rule for limiting the distribution of restricted alerts. (Optional)
     * <br />
     * This field is only required when scope is {@link ca.zacharyseguin.alertscanada.AlertScope#RESTRICTED}
     *
     * @since 1.0
     */
    private String restriction;

    /**
     * Listing of intended recipients of the alert. (Optional)
     * <br />
     * Required when scope is {@link ca.zacharyseguin.alertscanada.AlertScope#PRIVATE}, optional otherwise.
     *
     * @since 1.0
     */
    private List<String> addresses;

    /**
     * Codes denoting special handling of the alert. (Optional)
     *
     * @since 1.0
     */
    private List<String> codes;

    /**
     * Text describing the purpose or significance of the alert. (Optional)
     *
     * @since 1.0
     */
    private String note;

    /**
     * References to previous alerts. (Optional)
     *
     * @since 1.0
     */
    private List<AlertReference> references;

    /**
     * Listing of reference incidents of the alert. (Optional)
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

    /**
     * Default constructor - Construct alert objects using the Alert.Builder.
     * @since 1.0
     */
    private Alert()
    {
        this.identifier = null;
        this.sender = null;
        this.sent = null;
        this.status = null;
        this.type = null;
        this.source = null;
        this.scope = null;
        this.restriction = null;
        this.addresses = new ArrayList<String>();
        this.codes = new ArrayList<String>();
        this.note = null;
        this.references = new ArrayList<AlertReference>();
        this.incidents = new ArrayList<String>();
        this.information = new ArrayList<AlertInfo>();
    }// End of constructor

    /**
     * Returns the identifier of the alert.
     * @return The identifier of the alert.
     * @since 1.0
     */
    public String getIdentifier()
    {
        return this.identifier;
    }// End of getIdentifier method

    /**
     * Returns the identifier of the alert sender.
     * @return The identifier of the alert sender.
     * @since 1.0
     */
    public String getSender()
    {
        return this.identifier;
    }// End of getSender method

    /**
     * Returns the date/time the alert was sent.
     * @return The date/time the alert was sent.
     * @since 1.0
     */
    public Calendar getSent()
    {
        return this.sent;
    }// End of getSent method

    /**
     * Returns the code denoting the appropriate handling of the alert.
     * @return The code denoting the appropriate handling of the alert.
     * @since 1.0
     */
    public AlertStatus getStatus()
    {
        return this.status;
    }// End of getStatus method

    /**
     * Returns the code denoting the nature of the alert.
     * @return The code denoting the nature of the alert.
     * @since 1.0
     */
    public AlertType getType()
    {
        return this.type;
    }// End of getType method

    /**
     * Returns the source of the alert.
     * @return The source of the alert.
     * @since 1.0
     */
    public String getSource()
    {
        return this.source;
    }// End of getSource method

    /**
     * Returns the code denoting the indented distribution of the alert.
     * @return The code denoting the indented distribution of the alert.
     * @since 1.0
     */
    public AlertScope getScope()
    {
        return this.scope;
    }// End of getScope method

    /**
     * Returns the rule for limiting distribution of the alert.
     * @return The rule for limiting distribution of the alert.
     * @since 1.0
     */
    public String getRestriction()
    {
        return this.restriction;
    }// End of getRestriction method

    /**
     * Returns the intended recipients of the alert.
     * @return The intended recipients of the alert.
     * @since 1.0
     */
    public List<String> getAddresses()
    {
        return this.addresses;
    }// End of getAddresses method

    /**
     * Returns the codes denoting special handling of the alert.
     * @return The codes denoting special handling of the alert.
     * @since 1.0
     */
    public List<String> getCodes()
    {
        return this.codes;
    }// End of getCodes method

    /**
     * Returns the purpose or significance of the alert.
     * @return The purpose or significance of the alert.
     * @since 1.0
     */
    public String getNote()
    {
        return this.note;
    }// End of getNote method

    /**
     * Returns the references to previous alerts.
     * @return The references to previous alerts.
     * @since 1.0
     */
    public List<AlertReference> getReferences()
    {
        return this.references;
    }// End of getReferences method

    /**
     * Returns a list of related incidents.
     * @return List of related incidents.
     * @since 1.0
     */
    public List<String> getIncidents()
    {
        return this.incidents;
    }// End of getIncidents method

    /**
     * Returns the alert information.
     * @return The alert information.
     * @since 1.0
     */
    public List<AlertInfo> getInformation()
    {
        return this.information;
    }// End of getInformation method

    /**
     * Alert builder.
     *
     * @since 1.0
     */
    public static class Builder
    {
        /**
         * The alert object being constructed.
         * @since 1.0
         */
        private Alert alert;

        /**
         * Constructs a new Alert Builder object.
         * @since 1.0
         */
        public Builder()
        {
            this.alert = new Alert();
        }// End of constructor method

        /**
         * Contructs and returns the alert.
         * @return The constructed alert object.
         * @since 1.0
         */
        public Alert build()
        {
            try
            {
                return (Alert)this.alert.clone();
            }// End of try
            catch (Exception e)
            {
                return null;
            }// End of catch
        }// End of build method

        public Builder identifier(String identifier)
        {
            this.alert.identifier = identifier;
            return this;
        }// End of identifier method

        public Builder sender(String sender)
        {
            this.alert.sender = sender;
            return this;
        }// End of sender method

        public Builder sent(Calendar sent)
        {
            this.alert.sent = sent;
            return this;
        }// End of sent method

        public Builder status(AlertStatus status)
        {
            this.alert.status = status;
            return this;
        }// End of status method

        public Builder type(AlertType type)
        {
            this.alert.type = type;
            return this;
        }// End of type method

        public Builder source(String source)
        {
            this.alert.source = source;
            return this;
        }// End of source method

        public Builder scope(AlertScope scope)
        {
            this.alert.scope = scope;
            return this;
        }// End of scope method

        public Builder restriction(String restriction)
        {
            this.alert.restriction = restriction;
            return this;
        }// End of restriction method

        public Builder addresses(List<String> addresses)
        {
            this.alert.addresses = addresses;
            return this;
        }// End of addresses method

        public Builder codes(List<String> codes)
        {
            this.alert.codes = codes;
            return this;
        }// End of codes method

        public Builder note(String note)
        {
            this.alert.note = note;
            return this;
        }// End of note method

        public Builder references(List<AlertReference> references)
        {
            this.alert.references = references;
            return this;
        }// End of references method

        public Builder incidents(List<String> incidents)
        {
            this.alert.incidents = incidents;
            return this;
        }// End of incidents method

        public Builder information(List<AlertInfo> information)
        {
            this.alert.information = information;
            return this;
        }// End of information method
    }// End of Builder class
}// End of class
