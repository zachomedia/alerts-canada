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
public class AlertResource implements Serializable
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
}// End of class
