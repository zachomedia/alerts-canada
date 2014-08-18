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

import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;
import org.xml.sax.*;

/**
 * AlertsCanada is the class which is responsible for handling
 * the connection to the NAAD system.
 * <br /><br />
 * The class also accepts handlers which are called when the application
 * receives a new alert, an updated alert or an alert has expired.
 *
 * @author      Zachary Seguin (contact@zacharyseguin.ca)
 * @version     1.0
 * @since       1.0
 */
class AlertXMLParser
{
    /**
     * XPath object for performing XPath operations.
     * @since 1.0
     */
    private static XPath xpath;

    /**
     * Returns the Enum value based upon the value. This method operates only a single matching element.
     *
     * @param enumType The Enum type.
     * @param parent The parent object, for the XPath search.
     * @param path The XPath path for the matching element.
     *
     * @return Enum value, if a match is found. <code>null</code> otherwise.
     *
     * @since 1.0
     */
    private static <T extends Enum<T> & AlertEnum> T getEnum(Class<T> enumType, Object parent, String path)
    {
        try
        {
            String value = getNodeValue(parent, path);

            for (T ae : (T[]) enumType.getDeclaredMethod("values").invoke(null))
            {
                if (value.equals(ae.getValue()))
                {
                    return ae;
                }// End of if
            }// End of for
        }// End of try
        catch (Exception e)
        {
            // Unfortunately, an error occured.
            // Right now, nothing happens.
            // null will be returned to the caller.
        }// End of catch

        return null;
    }// End of getEnum method

    /**
     * Returns the Calendar (Date/Time) value of an element.
     * <br />
     * Expected Format: yyyy-MM-dd'T'HH:mm:ssX
     *
     * @param parent The parent object, for the XPath search.
     * @param path The XPath path for the matching element.
     *
     * @return Calendar value if value is provided and correct, <code>null</code> otherwise.
     *
     * @since 1.0
     */
    private static Calendar getCalendarValue(Object parent, String path) throws Exception
    {
        String value = getNodeValue(parent, path);

        try
        {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
            System.out.println(format.format(new Date()));
            Calendar time = GregorianCalendar.getInstance();
            time.setTime(format.parse(value));
            return time;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }// End of getCalendarValue method

    /**
     * Returns the value of a single node.
     *
     * @param parent The parent object, for the XPath search.
     * @param path THe XPath path for the matching element.
     *
     * @return The value of the element.
     *
     * @since 1.0
     */
    private static String getNodeValue(Object parent, String path) throws Exception
    {
        if (xpath == null)
        {
            xpath = XPathFactory
                            .newInstance()
                            .newXPath();
        }// End of if

        XPathExpression expression = xpath.compile(path + "/text()");
        return expression.evaluate(parent);
    }// End of getNode method

    /**
     * Returns a List of node values.
     *
     * @param parent The parent object, for the XPath search.
     * @param path The XPath path for the matching elements.
     *
     * @return List of matching values.
     *
     * @since 1.0
     */
    private static List<String> getNodeValues(Object parent, String path) throws Exception
    {
        if (xpath == null)
        {
            xpath = XPathFactory
                            .newInstance()
                            .newXPath();
        }// End of if

        XPathExpression expression = xpath.compile(path + "/text()");
        NodeList nodes = (NodeList)expression.evaluate(parent, XPathConstants.NODESET);
        List<String> values = new ArrayList<String>();

        for (int x = 0; x < nodes.getLength(); ++x)
        {
            values.add(nodes.item(x).getTextContent());
        }// End of for

        return values;
    }// End of getNodeValues method

    /**
     * Parses the provided XML text into an Alert object.
     *
     * @param xml The XML text.
     *
     * @return The Alert object, or <code>null</code> if invalid.
     *
     * @since 1.0
     */
    static Alert parse(String xml)
    {
        Alert.Builder builder = new Alert.Builder();

        try
        {
            Element root = DocumentBuilderFactory
                        .newInstance()
                        .newDocumentBuilder()
                        .parse(new ByteArrayInputStream(xml.getBytes()))
                        .getDocumentElement();



            builder
                .identifier(getNodeValue(root, "identifier"))
                .sender(getNodeValue(root, "sender"))
                .sent(getCalendarValue(root, "sent"))
                .status(getEnum(AlertStatus.class, root, "status"))
                .type(getEnum(AlertType.class, root, "msgType"))
                .source(getNodeValue(root, "source"))
                .scope(getEnum(AlertScope.class, root, "scope"))
                .restriction(getNodeValue(root, "restriction"))
                .addresses(getNodeValues(root, "addresses"))
                .codes(getNodeValues(root, "code"))
                .note(getNodeValue(root, "note"))
                //.references()
                .incidents(getNodeValues(root, "incidents"));
                //.information();
        }// End of try
        catch (Exception e)
        {
            // Unfortunately, an error occured.
            // Right now, nothing happens.
            // null will be returned to the caller.
            return null;
        }// End of catch

        return builder.build();
    }// End of parse
}// End of class
