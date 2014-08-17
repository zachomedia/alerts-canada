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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.io.*;
import java.net.*;

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
    public static final String NAAD_HOSTNAME_OAKVILLE = "streaming1.naad-adna.pelmorex.com";
    public static final String NAAD_HOSTNAME_MONTREAL = "streaming2.naad-adna.pelmorex.com";
    public static final int NAAD_PORT = 8080;

    /**
     * List of active alerts.
     * <br />
     * (Alert Identifier, Alert)
     *
     * @since 1.0
     */
    private Map<String, Alert> alerts;

    /**
     * List of registered alert listeners.
     *
     * @since 1.0
     */
    private List<AlertsListener> alertsListeners;

    public AlertsCanada() throws UnknownHostException, IOException
    {
        this.alertsListeners = new ArrayList<AlertsListener>();
        StreamListener listener = new StreamListener(NAAD_HOSTNAME_OAKVILLE, NAAD_PORT);
    }

    public void parseAlert(String xml)
    {
        Alert alert = AlertXMLParser.parse(xml);

        if (alert != null)
        {
            for (AlertsListener listener : alertsListeners)
            {
                listener.alertReceived(alert);
            }// end of for
        }// End of if
    }// End of parseAlert method

    public void addAlertsListener(AlertsListener listener)
    {
        this.alertsListeners.add(listener);
    }

    public class StreamListener
    {
        private Socket socket;

        public StreamListener(String hostname, int port) throws UnknownHostException, IOException
        {
            this.socket = new Socket(hostname, port);
            SocketInputStreamListener listener = new SocketInputStreamListener(this.socket.getInputStream());
            listener.start();
        }// End of constructor method

        public class SocketInputStreamListener extends Thread
        {
            private InputStream inputStream;

            public SocketInputStreamListener(InputStream inputStream)
            {
                this.inputStream = inputStream;
            }// End of constructor method

            public void run()
            {
                int failures = 0;
                BufferedReader reader = new BufferedReader(new InputStreamReader(this.inputStream));
                String content = "";

                while(true)
                {
                    try
                    {
                        int c = reader.read();

                        if (c == -1)
                        {
                            break;
                        }// End of if

                        content += (char)c;
                        if (content.contains("</alert>"))
                        {
                            parseAlert(content);
                        }// End of if
                        failures = 0;
                    }// End of try
                    catch (Exception e)
                    {
                        ++failures;
                        if (failures >= 3)
                        {
                            break;
                        }// End of if
                    }// End of catch
                }// End of while
            }// End of run method
        }// End of SocketStreamListener class
    }// End of StreamListener class
}// End of class
