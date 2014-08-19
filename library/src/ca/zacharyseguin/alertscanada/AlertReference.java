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

/**
 * Alert reference providing details of referenced alert.
 *
 * @author      Zachary Seguin (contact@zacharyseguin.ca)
 * @version     1.0
 * @since       1.0
 */
public class AlertReference implements Cloneable, Serializable
{
    /**
     * Sender of the referenced alert.
     * @since 1.0
     */
    private String sender;

    /**
     * Identifier of the reference alert.
     * @since 1.0
     */
    private String identifier;

    /**
     * Time the referenced alert was sent.
     * @since 1.0
     */
    private Calendar sent;

    /**
     * Default constructor - Use AlertReference.Builder to construct a new object.
     * @since 1.0
     */
    private AlertReference()
    {
        this.sender = null;
        this.identifier = null;
        this.sent = null;
    }// End of constructor method

    /**
     * Returns the sender of the referenced alert.
     * @return Sender of the reference alert.
     * @since 1.0
     */
    public String getSender()
    {
        return this.sender;
    }// End of getSender method

    /**
     * Returns the identifier of the referenced alert.
     * @return Identifier of the reference alert.
     * @since 1.0
     */
    public String getIdentifier()
    {
        return this.identifier;
    }// End of getIdentifier method

    /**
     * Returns the time the referenced alert was sent.
     * @return Time the referenced alert was sent.
     * @since 1.0
     */
    public Calendar getSent()
    {
        return this.sent;
    }// End of getSent method

    /**
     * Alert Reference builder.
     * @since 1.0
     */
    static class Builder
    {
        /**
         * The AlertReference object being constructed.
         * @since 1.0
         */
        private AlertReference reference;

        /**
         * Constructs a new AlertReference.Builder object.
         * @since 1.0
         */
        public Builder()
        {
            this.reference = new AlertReference();
        }// End of constructor class

        /**
         * Constructs and returns the AlertReference object.
         * @return The constructed AlertReference object.
         * @since 1.0
         */
        public AlertReference build()
        {
            try
            {
                return (AlertReference)this.reference.clone();
            }// End of try
            catch (Exception e)
            {
                return null;
            }// End of catch
        }// End of build method

        public Builder sender(String sender)
        {
            this.reference.sender = sender;
            return this;
        }// End of sender method

        public Builder identifier(String identifier)
        {
            this.reference.identifier = identifier;
            return this;
        }// End of identifier method

        public Builder sent(Calendar sent)
        {
            this.reference.sent = sent;
            return this;
        }// End of sent method
    }// End of Builder class
}// End of class
