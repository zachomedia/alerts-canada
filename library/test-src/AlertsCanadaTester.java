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
    public static void main (String[] args) throws Exception
    {
        AlertArea alertArea = new AlertArea.Builder()
                                            .description("Test Area")
                                            .circle(new Circle())
                                            .ceiling(0.0)
                                            .build();

        AlertResource alertResource = new AlertResource.Builder()
                                                        .description("Test Resource")
                                                        .mimeType("text/html")
                                                        .uri(new URL("https://zacharyseguin.ca/"))
                                                        .build();

        AlertInfo alertInfo = new AlertInfo.Builder()
                                            .language("en-CA")
                                            .categories(Arrays.asList(new AlertCategory[]{
                                                AlertCategory.OTHER
                                            }))
                                            .event("TEST EVENT")
                                            .responseTypes(Arrays.asList(new AlertResponseType[]{
                                                AlertResponseType.NONE
                                            }))
                                            .severity(AlertSeverity.MINOR)
                                            .urgency(AlertUrgency.EXPECTED)
                                            .certainty(AlertCertainty.LIKELY)
                                            .audience("TEST ONLY")
                                            .effective(GregorianCalendar.getInstance())
                                            .onset(GregorianCalendar.getInstance())
                                            .expires(GregorianCalendar.getInstance())
                                            .senderName("Zachary Seguin")
                                            .headline("TEST EVENT ONLY")
                                            .description("This is only a test. No threat is present.")
                                            .instruction("TEST ONLY -- NO ACTION REQUIRED")
                                            .web(new URL("https://zacharyseguin.ca/"))
                                            .contact("Zachary Seguin")
                                            .resources(Arrays.asList(new AlertResource[] {
                                                alertResource
                                            }))
                                            .areas(Arrays.asList(new AlertArea[] {
                                                alertArea
                                            }))
                                            .build();

        Alert alert = new Alert.Builder()
                                .identifier("ZS-0001")
                                .sender("Zachary Seguin")
                                .sent(GregorianCalendar.getInstance())
                                .status(AlertStatus.TEST)
                                .type(AlertType.ALERT)
                                .scope(AlertScope.PUBLIC)
                                .restriction("TEST ONLY")
                                .addresses(Arrays.asList(new String[]{ "Zachary Seguin" }))
                                .note("TEST ONLY")
                                .information(Arrays.asList(new AlertInfo[] {
                                    alertInfo
                                }))
                                .build();

        Gson gson = new GsonBuilder()
                    .serializeNulls()
                    .registerTypeAdapter(GregorianCalendar.class, new JsonSerializer<GregorianCalendar>() {
                        public JsonElement serialize(GregorianCalendar date, Type type, JsonSerializationContext context) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
                            return new JsonPrimitive(sdf.format(date.getTime()));
                        }
                    })
                    .create();
        System.out.println(gson.toJson(alert));
    }// End of main method
}// End of class
