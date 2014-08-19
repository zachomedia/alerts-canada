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

import ca.zacharyseguin.util.xml.XMLHelpers;

import java.util.*;

import java.text.SimpleDateFormat;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
            String value = getString(parent, path);

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
     * Returns the Enum values based upon the string values.
     *
     * @param enumType The Enum type.
     * @param parent The parent object, for the XPath search.
     * @param path The XPath path for the matching element.
     *
     * @return List of Enum values matching their string counterparts in the XML.
     *
     * @since 1.0
     */
    private static <T extends Enum<T> & AlertEnum> List<T> getEnums(Class<T> enumType, Object parent, String path)
    {
        List<T> values = new ArrayList<T>();

        try
        {
            List<String> rawValues = getStrings(parent, path);

            for (String value : rawValues)
            {
                for (T ae : (T[]) enumType.getDeclaredMethod("values").invoke(null))
                {
                    if (value.equals(ae.getValue()))
                    {
                        values.add(ae);
                    }// End of if
                }// End of for
            }// End of for
        }// End of try
        catch (Exception e)
        {
            // Unfortunately, an error occured.
            // Right now, nothing happens.
        }// End of catch

        return values;
    }// End of getEnums method

    /**
     * Returns the Calendar (Date/Time) value of a string.
     * <br />
     * Expected Format: yyyy-MM-dd'T'HH:mm:ssX
     *
     * @param value The String value to parse.
     *
     * @return Calendar value if value is provided and correct, <code>null</code> otherwise.
     *
     * @since 1.0
     */
    private static Calendar parseCalendar(String value)
    {
        try
        {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");

            Calendar time = GregorianCalendar.getInstance();
            time.setTime(format.parse(value));

            return time;
        }// End of try
        catch(Exception e)
        {
            // Unfortunately, an error occured.
            // Right now, nothing happens.
        }// End of catch

        return null;
    }// End of parseCalendar method

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
    private static Calendar getCalendar(Object parent, String path) throws Exception
    {
        return parseCalendar(getString(parent, path));
    }// End of getCalendar method

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
    private static String getString(Object parent, String path) throws Exception
    {
        Node node = XMLHelpers.getNode(parent, path);

        if (node == null) return null;

        return node.getTextContent();
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
    private static List<String> getStrings(Object parent, String path) throws Exception
    {
        NodeList nodes = XMLHelpers.getNodes(parent, path);

        if (nodes == null) return null;

        List<String> values = new ArrayList<String>();

        for (int x = 0; x < nodes.getLength(); ++x)
        {
            values.add(nodes.item(x).getTextContent());
        }// End of for

        return values;
    }// End of getStrings method

    /**
     * Parse a single <info> block.
     *
     * @return AlertInfo object from the info block.
     *
     * @since 1.0
     */
    static AlertInfo parseInfo()
    {
        AlertInfo.Builder builder = new AlertInfo.Builder();

        try
        {

        }// End of try
        catch (Exception e)
        {
            // Unfortunately, an error occured.
            // Right now, nothing happens.
            // null will be returned to the caller.
            return null;
        }// End of catch

        return builder.build();
    }// End of parseInfo method

    /**
     * Return the references of the alert.
     *
     * @param parent The parent object, for the XPath search.
     * @param path The XPath path for the matching elements.
     *
     * @return The references of the alert.
     *
     * @since 1.0
     */
    private static List<AlertReference> getReferences(Object parent, String path) throws Exception
    {
        List<AlertReference> references = new ArrayList<AlertReference>();
        String rawReferences = getString(parent, path);

        if (rawReferences == null) return references;

        String[] seperatedRawReferences = rawReferences.split("\\s");

        for (String rawReference : seperatedRawReferences)
        {
            String[] referenceComponents = rawReference.split(",");

            if (referenceComponents.length != 3) continue;

            references.add(new AlertReference.Builder()
                                                .sender(referenceComponents[0])
                                                .identifier(referenceComponents[1])
                                                .sent(parseCalendar(referenceComponents[2]))
                                                .build());
        }// End of for

        return references;
    }// End of getReferences method

    /**
     * Return the information objects of the alert.
     *
     * @param parent The parent object, for the XPath search.
     * @param path The XPath path for the matching elements.
     *
     * @return The information objects of the alert.
     *
     * @since 1.0
     */
    private static List<AlertInfo> getInformation(Object parent, String path)
    {
        List<AlertInfo> info = new ArrayList<AlertInfo>();

        try
        {
            NodeList nodes = XMLHelpers.getNodes(parent, path);

            for (int x = 0; x < nodes.getLength(); ++x)
            {
                Node node = nodes.item(x);

                info.add(new AlertInfo.Builder()
                                        .language(getString(node, "language"))
                                        .categories(getEnums(AlertCategory.class, node, "category"))
                                        .event(getString(node, "event"))
                                        .responseTypes(getEnums(AlertResponseType.class, node, "responseType"))
                                        .severity(getEnum(AlertSeverity.class, node, "severity"))
                                        .urgency(getEnum(AlertUrgency.class, node, "urgency"))
                                        .certainty(getEnum(AlertCertainty.class, node, "certainty"))
                                        .audience(getString(node, "audience"))
                                        //.eventCodes(getKeyValue(node, "eventCode"))
                                        .effective(getCalendar(node, "effective"))
                                        .onset(getCalendar(node, "onset"))
                                        .expires(getCalendar(node, "expires"))
                                        .senderName(getString(node, "senderName"))
                                        .headline(getString(node, "headline"))
                                        .description(getString(node, "description"))
                                        .instruction(getString(node, "instruction"))
                                        //.web(getURL(node, "web"))
                                        .contact(getString(node, "contact"))
                                        //.parameters(getKeyValue(node, "parameter"))
                                        //.resources(getResources(node, "resource"))
                                        //.areas(getAreas(node, "area"))
                                        .build());
            }// End of for
        }// End of catch
        catch (Exception e)
        {
            return info;
        }// End of catch

        return info;
    }// End of getInformation method

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
        try
        {
            Element root = XMLHelpers.parseXML(xml).getDocumentElement();

            return new Alert.Builder()
                                .identifier(getString(root, "identifier"))
                                .sender(getString(root, "sender"))
                                .sent(getCalendar(root, "sent"))
                                .status(getEnum(AlertStatus.class, root, "status"))
                                .type(getEnum(AlertType.class, root, "msgType"))
                                .source(getString(root, "source"))
                                .scope(getEnum(AlertScope.class, root, "scope"))
                                .restriction(getString(root, "restriction"))
                                .addresses(getStrings(root, "addresses"))
                                .codes(getStrings(root, "code"))
                                .note(getString(root, "note"))
                                .references(getReferences(root, "references"))
                                .incidents(getStrings(root, "incidents"))
                                .information(getInformation(root, "info"))
                                .build();
        }// End of try
        catch (Exception e)
        {
            // Unfortunately, an error occured.
            // Right now, nothing happens.
            // null will be returned to the caller.
        }// End of catch

        return null;
    }// End of parse
}// End of class
