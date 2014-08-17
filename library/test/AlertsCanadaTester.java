import ca.zacharyseguin.alertscanada.*;

import java.io.*;
import com.google.gson.*;

public class AlertsCanadaTester
{
    public static void main (String[] args) throws Exception
    {
        Alert alert = new Alert.Builder()
                        .sender("Zachary Seguin")
                        .build();

        Gson gson = new GsonBuilder().serializeNulls().create();
        System.out.println(gson.toJson(alert));
    }// End of main method
}// End of class
