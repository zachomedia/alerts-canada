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

/**
 * Alert resource for additional/supplemental information.
 * <br />
 * All fields are required, unless specified otherwise.
 *
 * @author      Zachary Seguin (contact@zacharyseguin.ca)
 * @version     1.0
 * @since       1.0
 */
public class AlertResource implements Cloneable, Serializable
{
    /**
     * Text describing the type and contents of the resource file.
     * @since 1.0
     */
    private String description;

    /**
     * MIME content type of the resource.
     * @since 1.0
     */
    private String mimeType;

    /**
     * Size of the resource in bytes. (Optional)
     *
     * @since 1.0
     */
    private Integer size;

    /**
     * Hyperlink to the resource file. (Optional)
     * @since 1.0
     */
    private URL uri;

    /**
     * BASE64 encoded data of the resource. (Optional)
     * @since 1.0
     */
    private String derefUri;

    /**
     * Digital digest ("hash") computed of the resource. (Optional)
     * @since 1.0
     */
    private String digest;

    /**
     * Default constructor - Construct resource with AlertResources.Builder.
     * @since 1.0
     */
    private AlertResource()
    {
        this.description = null;
        this.mimeType = null;
        this.size = null;
        this.uri = null;
        this.derefUri = null;
        this.digest = null;
    }// End of constructor method

    /**
     * Returns the description of the resource.
     * @return Description of the resource.
     * @since 1.0
     */
    public String getDescription()
    {
        return this.description;
    }// End of getDescription method

    /**
     * Returns the mime type of the resource.
     * @return Mime type of the resource.
     * @since 1.0
     */
    public String getMimeType()
    {
        return this.mimeType;
    }// End of getMimeType method

    /**
     * Returns the size of the resource.
     * @return Size of the resource.
     * @since 1.0
     */
    public Integer getSize()
    {
        return this.size;
    }// End of getSize method

    /**
     * Retruns the hyperlink to the resource.
     * @return Hyperlink to the resource.
     * @since 1.0
     */
    public URL getUri()
    {
        return this.uri;
    }// End of getUri method

    /**
     * Returns the BASE64 encoded resource.
     * @return BASE64 encoded resource.
     * @since 1.0
     */
    public String getDerefUri()
    {
        return this.derefUri;
    }// End of getDerefUri method

    /**
     * Returns the digital digest (hash) of the resoruce.
     * @return Digital digest (hash) of the resource.
     * @since 1.0
     */
    public String getDigest()
    {
        return this.digest;
    }// End of getDigest method

    /**
     * Alert Resource builder.
     * @since 1.0
     */
    static class Builder
    {
        /**
         * The alert resource being constructed.
         * @since 1.0
         */
        private AlertResource alertResource;

        /**
         * Constructs a new AlertResource.Builder method
         * @since 1.0
         */
        public Builder()
        {
            this.alertResource = new AlertResource();
        }// End of constructor method

        /**
         * Returns the constructed AlertResource.
         * @return The constrcuted AlertResource.
         * @since 1.0
         */
        public AlertResource build()
        {
            try
            {
                return (AlertResource)this.alertResource.clone();
            }// End of try
            catch (Exception e)
            {
                return null;
            }// End of catch
        }// End of build method

        public Builder description(String description)
        {
            this.alertResource.description = description;
            return this;
        }// End of description method

        public Builder mimeType(String mimeType)
        {
            this.alertResource.mimeType = mimeType;
            return this;
        }// End of mimeType method

        public Builder size(int size)
        {
            this.alertResource.size = Integer.valueOf(size);
            return this;
        }// End of size method

        public Builder size(Integer size)
        {
            this.alertResource.size = size;
            return this;
        }// End of size method

        public Builder uri(URL uri)
        {
            this.alertResource.uri = uri;
            return this;
        }// End of uri method

        public Builder derefUri(String derefUri)
        {
            this.alertResource.derefUri = derefUri;
            return this;
        }// End of derefUri method

        public Builder digest(String digest)
        {
            this.alertResource.digest = digest;
            return this;
        }// End of digest method
    }// End of Builder class
}// End of class
