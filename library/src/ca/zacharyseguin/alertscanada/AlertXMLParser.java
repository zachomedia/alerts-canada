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

import ca.zacharyseguin.util.geo.Circle;
import ca.zacharyseguin.util.geo.Coordinate;
import ca.zacharyseguin.util.geo.Polygon;

import ca.zacharyseguin.util.xml.XMLHelpers;

import java.net.URL;

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
    private static Calendar getCalendar(Object parent, String path)
    {
        return parseCalendar(getString(parent, path));
    }// End of getCalendar method

    /**
     * Returns the URL value of an element.
     *
     * @param parent The parent object, for the XPath search.
     * @param path The XPath path for the matching element.
     *
     * @return UR value if value is provided and correct, <code>null</code> otherwise.
     *
     * @since 1.0
     */
    private static URL getURL(Object parent, String path)
    {
        try
        {
            return new URL(getString(parent, path));
        }// End of try
        catch (Exception e)
        {
            // An error occured.
            // Right now, nothing is done about it.
        }// End of catch

        return null;
    }// End of getURL method

    /**
     * Returns a key, value set.
     *
     * @param parent The parent object, for the XPath search.
     * @param path The XPath path for the matching elements.
     *
     * @return Map of key, value set.
     *
     * @since 1.0
     */
    private static Map<String, String> getKeyValue(Object parent, String path)
    {
        Map<String, String> pairs = new TreeMap<String, String>();

        try
        {
            NodeList nodes = XMLHelpers.getNodes(parent, path);

            for (int x = 0; x < nodes.getLength(); ++x)
            {
                Node node = nodes.item(x);

                pairs.put(getString(node, "valueName"), getString(node, "value"));
            }// End of for
        }// End of try
        catch (Exception e)
        {
            // An error occured.
            // Right now, nothing is done about it.
        }// End of catch

        return pairs;
    }// End of getKeyValue method

    /**
     * Returns the Double value of a single node.
     *
     * @param parent The parent object, for the XPath search.
     * @param path THe XPath path for the matching element.
     *
     * @return The Double of the element.
     *
     * @since 1.0
     */
    private static Double getDouble(Object parent, String path)
    {
        Node node = XMLHelpers.getNode(parent, path);

        if (node == null) return null;

        try
        {
            return new Double(node.getTextContent());
        }// End of try
        catch (Exception e)
        {
            // An error occured.
            // Right now, nothing happens.
        }// End of catch

        return null;
    }// End of getDouble method

    /**
     * Returns the Integer value of a single node.
     *
     * @param parent The parent object, for the XPath search.
     * @param path THe XPath path for the matching element.
     *
     * @return The Integer of the element.
     *
     * @since 1.0
     */
    private static Integer getInteger(Object parent, String path)
    {
        Node node = XMLHelpers.getNode(parent, path);

        if (node == null) return null;

        try
        {
            return new Integer(node.getTextContent());
        }// End of try
        catch (Exception e)
        {
            // An error occured.
            // Right now, nothing happens.
        }// End of catch

        return null;
    }// End of Integer method

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
    private static String getString(Object parent, String path)
    {
        Node node = XMLHelpers.getNode(parent, path);

        if (node == null) return null;

        return node.getTextContent();
    }// End of getString method

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
    private static List<String> getStrings(Object parent, String path)
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

    private static List<Circle> getCircles(Object parent, String path)
    {
        List<Circle> circles = new ArrayList<Circle>();

        NodeList nodes = XMLHelpers.getNodes(parent, path);

        for (int x = 0; x < nodes.getLength(); ++x)
        {
            Node node = nodes.item(x);
            String value = node.getTextContent();

            if (value == null) return null;

            String[] components = value.split("\\s");

            if (components.length != 2) continue;

            String[] coordinateComponents = components[0].split(",");

            if (coordinateComponents.length != 2) continue;

            try
            {
                circles.add(new Circle(new Coordinate(Double.parseDouble(coordinateComponents[0]), Double.parseDouble(coordinateComponents[1])), Double.parseDouble(components[1])));
            }// End of catch
            catch (Exception e)
            {
                // An error occured.
                // For now, nothing will happen.
            }// End of try
        }// End of for

        return circles;
    }// End of getCircles method

    private static List<Polygon> getPolygons(Object parent, String path)
    {
        List<Polygon> polygons = new ArrayList<Polygon>();

        NodeList nodes = XMLHelpers.getNodes(parent, path);

        for (int x = 0; x < nodes.getLength(); ++x)
        {
            Node node = nodes.item(x);
            String value = node.getTextContent();
            List<Coordinate> coordinates = new ArrayList<Coordinate>();

            if (value == null) return null;

            String[] pairs = value.split("\\s");

            for (String pair : pairs)
            {
                String[] components = pair.split(",");

                if (components.length != 2) continue;

                try
                {
                    coordinates.add(new Coordinate(Double.parseDouble(components[0]), Double.parseDouble(components[1])));
                }// End of try
                catch (Exception e)
                {
                    // Faild to parse
                    // That's fine, we'll move on.
                }// End of catch
            }// End of for

            polygons.add(new Polygon(coordinates));
        }// End of for

        return polygons;
    }// End of getPolygons method

    /**
     * Returns the resources of alert information (event).
     *
     * @param parent The parent object, for the XPath search.
     * @param path The XPath path for the matching elements.
     *
     * @return Resources of the alert event.
     *
     * @since 1.0
     */
    private static List<AlertResource> getResources(Object parent, String path)
    {
        List<AlertResource> resources = new ArrayList<AlertResource>();

        try
        {
            NodeList nodes = XMLHelpers.getNodes(parent, path);

            for (int x = 0; x < nodes.getLength(); ++x)
            {
                Node node = nodes.item(x);

                resources.add(new AlertResource.Builder()
                                                    .description(getString(node, "resourceDesc"))
                                                    .mimeType(getString(node, "mimeType"))
                                                    .size(getInteger(node, "size"))
                                                    .uri(getURL(node, "uri"))
                                                    .derefUri(getString(node, "derefUri"))
                                                    .digest(getString(node, "digest"))
                                                    .build());
            }// End of for
        }// End of catch
        catch (Exception e)
        {
            // An error occured.
            // Just return what we have so far.
        }// End of catch

        return resources;
    }// End of getResources method

    /**
     * Returns the Areas of alert information (event).
     *
     * @param parent The parent object, for the XPath search.
     * @param path The XPath path for the matching elements.
     *
     * @return Areas of the alert event.
     *
     * @since 1.0
     */
    private static List<AlertArea> getAreas(Object parent, String path)
    {
        List<AlertArea> areas = new ArrayList<AlertArea>();

        try
        {
            NodeList nodes = XMLHelpers.getNodes(parent, path);

            for (int x = 0; x < nodes.getLength(); ++x)
            {
                Node node = nodes.item(x);

                areas.add(new AlertArea.Builder()
                                            .description(getString(node, "areaDesc"))
                                            .polygons(getPolygons(node, "polygon"))
                                            .circles(getCircles(node, "circle"))
                                            .geocodes(getKeyValue(node, "geocode"))
                                            .altitude(getDouble(node, "altitude"))
                                            .ceiling(getDouble(node, "ceiling"))
                                            .build());
            }// End of for
        }// End of catch
        catch (Exception e)
        {
            // An error occured.
            // Just return what we have so far.
        }// End of catch

        return areas;
    }// End of getAreas method

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
                                        .eventCodes(getKeyValue(node, "eventCode"))
                                        .effective(getCalendar(node, "effective"))
                                        .onset(getCalendar(node, "onset"))
                                        .expires(getCalendar(node, "expires"))
                                        .senderName(getString(node, "senderName"))
                                        .headline(getString(node, "headline"))
                                        .description(getString(node, "description"))
                                        .instruction(getString(node, "instruction"))
                                        .web(getURL(node, "web"))
                                        .contact(getString(node, "contact"))
                                        .parameters(getKeyValue(node, "parameter"))
                                        .resources(getResources(node, "resource"))
                                        .areas(getAreas(node, "area"))
                                        .build());
            }// End of for
        }// End of catch
        catch (Exception e)
        {
            // An error occured.
            // Just return what we have so far.
        }// End of catch

        return info;
    }// End of getInformation method

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
    private static List<AlertReference> getReferences(Object parent, String path)
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
