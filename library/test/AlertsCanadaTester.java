import ca.zacharyseguin.alertscanada.*;

import java.io.*;
import com.google.gson.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.lang.reflect.*;

public class AlertsCanadaTester
{
    public static void main (String[] args) throws Exception
    {
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
