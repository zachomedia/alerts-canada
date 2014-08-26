import ca.zacharyseguin.alertscanada.*;
import ca.zacharyseguin.util.geo.*;

import java.io.*;
import java.net.URL;
import com.google.gson.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.lang.reflect.*;

public class AlertsCanadaTester
{
    private static void writeFile(String filename, String contents)
    {
        try
        {
            PrintWriter writer = new PrintWriter(new FileWriter(filename, true), true);
            writer.print(contents);
            writer.close();
        } catch (Exception e) {};
    }// End of writeFile method

    public static void main(String[] args) throws Exception
    {
        final Gson gson = new GsonBuilder()
                    .serializeNulls()
                    .registerTypeAdapter(GregorianCalendar.class, new JsonSerializer<GregorianCalendar>() {
                        public JsonElement serialize(GregorianCalendar date, Type type, JsonSerializationContext context) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
                            return new JsonPrimitive(sdf.format(date.getTime()));
                        }
                    })
                    .create();

        final AlertsCanada ac = new AlertsCanada();
        ac.addAlertsListener(new AlertsListener() {
            public void alertReceived(Alert alert) {
                System.out.println("AlertReceived: " + alert.getIdentifier());
                writeFile("alerts.json", gson.toJson(ac.getAlerts()));
            }

            public void alertUpdated(Alert newAlert, Alert oldAlert) {
                System.out.println("AlertUpdated: " + newAlert.getIdentifier() + " - " + oldAlert.getIdentifier());
                writeFile("alerts.json", gson.toJson(ac.getAlerts()));
            }

            public void alertEnded(Alert alert) {
                System.out.println("AlertEnded: " + alert.getIdentifier());
                writeFile("alerts.json", gson.toJson(ac.getAlerts()));
            }
        });
    }// End of main method
}// End of class
