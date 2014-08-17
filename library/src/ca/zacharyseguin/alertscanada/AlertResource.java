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
     * Size was not provided.
     * @since 1.0
     */
    public static int SIZE_NOT_PROVIDED = -1;

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
    private int size;

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
        this.size = SIZE_NOT_PROVIDED;
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
     * @return Size of the resource. Returns AlertResource.SIZE_NOT_PROVIDED when size is not provided.
     * @since 1.0
     */
    public int getSize()
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
}// End of class
