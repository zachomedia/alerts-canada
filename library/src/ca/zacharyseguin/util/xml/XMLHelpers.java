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

package ca.zacharyseguin.util.xml;

import java.io.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;
import org.xml.sax.*;

public class XMLHelpers
{
    /**
     * XPath object for performing XPath operations.
     * @since 1.0
     */
    private static XPath xpath;

    /**
     * Parses the XML document.
     *
     * @param xml The XML document, as a String
     *
     * @return The parsed document. Null if an error occured.
     */
    public static Document parseXML(String xml)
    {
        try
        {
            return DocumentBuilderFactory
                                    .newInstance()
                                    .newDocumentBuilder()
                                    .parse(new ByteArrayInputStream(xml.getBytes()));
        }// End of try
        catch (Exception e)
        {
            return null;
        }// End of catch
    }// End of parseXML method

    /**
     * Returns the nodes matching the designated path from the parent.
     *
     * @param parent The parent object used during XPath evaluation.
     * @param path   The XPath path for the nodes.
     *
     * @return A NodeList of nodes matching the requested path.
     *
     * @since 1.0
     */
    public static NodeList getNodes(Object parent, String path)
    {
        try
        {
            XPathExpression expression = xpath.compile(path);
            return (NodeList)expression.evaluate(parent, XPathConstants.NODESET);
        }// End of try
        catch (Exception e)
        {
            return null;
        }// End of catch
    }// End of getNodes method

    /**
     * Returns the first node matching the designated path from the parent.
     *
     * @param parent The parent object used during XPath evaluation.
     * @param path   The XPath path for the node.
     *
     * @return The first matching node.
     *
     * @since 1.0
     */
    public static Node getNode(Object parent, String path)
    {
        try
        {
            XPathExpression expression = xpath.compile(path);
            return (Node)expression.evaluate(parent, XPathConstants.NODE);
        }// End of try
        catch (Exception e)
        {
            return null;
        }// End of catch
    }// End of getNode method

    /**
     * Configure the XPath object.
     */
    static {
        xpath = XPathFactory.newInstance().newXPath();
    } // End of static
}// End of class
