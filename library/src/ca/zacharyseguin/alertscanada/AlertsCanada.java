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

import ca.zacharyseguin.util.net.HttpContents;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;

import java.text.SimpleDateFormat;

import java.io.*;
import java.net.*;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

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
public class AlertsCanada
{
    private static Logger logger = LogManager.getLogger(AlertsCanada.class);

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssXXX";
    public static final SimpleDateFormat DATE_TIME_FORMATTER = new SimpleDateFormat(DATE_TIME_FORMAT);
    static { DATE_TIME_FORMATTER.setTimeZone(TimeZone.getTimeZone("UTC")); }

    public static final String NAAD_HOSTNAME_OAKVILLE = "streaming1.naad-adna.pelmorex.com";
    public static final String NAAD_MISSED_HOSTNAME_OAKVILLE = "capcp1.naad-adna.pelmorex.com";

    public static final String NAAD_HOSTNAME_MONTREAL = "streaming2.naad-adna.pelmorex.com";
    public static final String NAAD_MISSED_HOSTNAME_MONTREAL = "capcp2.naad-adna.pelmorex.com";

    public static final int NAAD_PORT = 8080;

    private static final String NAAD_HEARTBEAT_SENDER = "NAADS-Heartbeat";

    /**
     * List of active alerts.
     * <br />
     * (Alert Identifier, Alert)
     *
     * @since 1.0
     */
    private Map<String, Alert> alerts;

    private List<String> processedAlerts;

    /**
     * List of registered alert listeners.
     *
     * @since 1.0
     */
    private List<AlertsListener> alertsListeners;

    public AlertsCanada() throws UnknownHostException, IOException
    {
        logger.info("Initializing Alerts Canada...");

        this.alerts = new TreeMap<String, Alert>();
        this.processedAlerts = new ArrayList<String>();
        this.alertsListeners = new ArrayList<AlertsListener>();

        StreamListener listener = new StreamListener(NAAD_HOSTNAME_OAKVILLE, NAAD_PORT);
        listener.start();
    }

    public List<Alert> getAlerts()
    {
        return new ArrayList<Alert>(this.alerts.values());
    }

    // TODO: Perform request in a seperate thread
    public void requestAlert(String hostname, AlertReference alertReference)
    {
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
        dateTimeFormat.setTimeZone(alertReference.getSent().getTimeZone());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(alertReference.getSent().getTimeZone());

        String dateTime = dateTimeFormat.format(alertReference.getSent().getTime()).replace("Z", "-00:00");

        String file = dateTime + "I" + alertReference.getIdentifier();
        file = file.replaceAll("(-|:)", "_");
        file = file.replaceAll("\\+", "p");

        try
        {
            URL url = new URL("http://" + hostname + "/" + dateFormat.format(alertReference.getSent().getTime()) + "/" + file + ".xml");
            this.parseAlert(HttpContents.getURLContents(url));
        }// End of try
        catch (Exception e)
        {
            e.printStackTrace();
        }// End of catch
    }// End of requestAlert method

    /**
     * TODO: Make ignoring of alert optional.
     */
    public void parseAlert(String xml)
    {
        System.out.println(xml);
        Alert alert = AlertXMLParser.parse(xml);

        // Process the alert
        if (alert != null)
        {
            // Ignore test, exercise and draft messages
            if (   alert.getStatus() != AlertStatus.SYSTEM
                && alert.getStatus() != AlertStatus.ACTUAL) return;

            // Check System alerts for heartbeat
            // If heartbeat, ensure we have received all previous alert.
            if (alert.getStatus() == AlertStatus.SYSTEM)
            {
                if (alert.getSender().equals(NAAD_HEARTBEAT_SENDER))
                {
                    // Ensure that we have received all references
                    for (AlertReference reference : alert.getReferences())
                    {
                        if (!this.processedAlerts.contains(reference.getIdentifier()))
                        {
                            this.requestAlert(NAAD_MISSED_HOSTNAME_OAKVILLE, reference);
                        }// End of if
                    }// End of for
                }// End of if

                // Ignore the alert, we don't need to worry about it.
                return;
            }// End of if

            // If it's a new alert, add it and say we received a new alert.
            if (alert.getType() == AlertType.UPDATE)
            {
                Boolean update = false;

                // Remove old alerts and mention that it was updated
                for (AlertReference reference : alert.getReferences())
                {
                    if (this.alerts.containsKey(reference.getIdentifier()))
                    {
                        update = true;

                        for (AlertsListener listener : this.alertsListeners)
                        {
                            listener.alertUpdated(alert, this.alerts.get(reference.getIdentifier()));
                        }// End of for

                        this.alerts.remove(reference.getIdentifier());
                    }// End of if
                }// End of for

                if (!update)
                {
                    for (AlertsListener listener : this.alertsListeners)
                    {
                        listener.alertReceived(alert);
                    }// End of for
                }// End of if
            }// End of if
            else if (alert.getType() == AlertType.CANCEL)
            {
                // TODO: Verfiy this is correct

                // Remove old alerts and mention that it was cancelled
                for (AlertReference reference : alert.getReferences())
                {
                    if (this.alerts.containsKey(reference.getIdentifier()))
                    {
                        for (AlertsListener listener : this.alertsListeners)
                        {
                            listener.alertEnded(this.alerts.get(reference.getIdentifier()));
                        }// End of for

                        this.alerts.remove(reference.getIdentifier());
                    }// End of if
                }// End of for
            }// End of else if
            else
            {
                for (AlertsListener listener : this.alertsListeners)
                {
                    listener.alertReceived(alert);
                }// end of for
            }// End of else

            // Store the alert
            this.alerts.put(alert.getIdentifier(), alert);

            this.processedAlerts.add(alert.getIdentifier());
            if (this.processedAlerts.size() > 50) this.processedAlerts.remove(0);


        }// End of if
    }// End of parseAlert method

    public void addAlertsListener(AlertsListener listener)
    {
        this.alertsListeners.add(listener);
    }

    public class StreamListener extends Thread
    {
        private Socket socket;
        private String hostname;
        private int port;

        public StreamListener(String hostname, int port) throws UnknownHostException, IOException
        {
            this.hostname = hostname;
            this.port = port;
        }// End of constructor method

        public void run()
        {
            while(true)
            {
                try
                {
                    this.socket = new Socket(hostname, port);
                    this.socket.setSoTimeout(80 * 1000); // 80 seconds
                    SocketInputStreamListener listener = new SocketInputStreamListener(this.socket.getInputStream());
                    listener.start();
                    listener.join();
                }// End of try
                catch (Exception e)
                {
                    // Some error occured.
                    // Let's wait a minute and try again
                    try
                    {
                        Thread.sleep(60 * 1000);
                    }// End of try
                    catch (Exception ex)
                    {
                        // Do nothing, there is really nothing that can be done about it :(
                    }// End of catch
                }// End of catch
            }// End of while
        }// End of run method

        public class SocketInputStreamListener extends Thread
        {
            private InputStream inputStream;

            public SocketInputStreamListener(InputStream inputStream)
            {
                this.inputStream = inputStream;
            }// End of constructor method

            public void run()
            {
                try
                {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(this.inputStream, "UTF-8"));
                    StringBuilder content = new StringBuilder();

                    while(true)
                    {
                        try
                        {
                            int c = reader.read();

                            if (c == -1)
                            {
                                break;
                            }// End of if

                            content.append((char)c);
                            if (content.toString().contains("</alert>"))
                            {
                                logger.debug(content.toString());
                                parseAlert(content.toString());
                                content = new StringBuilder();
                            }// End of if
                        }// End of try
                        catch (Exception e)
                        {
                            e.printStackTrace();
                            content = new StringBuilder();
                            break;
                        }// End of catch
                    }// End of while
                }// End of try
                catch (Exception e)
                {
                    e.printStackTrace();
                    // do nothing for now.
                }
            }// End of run method
        }// End of SocketStreamListener class
    }// End of StreamListener class
}// End of class
