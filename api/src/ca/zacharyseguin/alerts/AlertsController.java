package ca.zacharyseguin.alerts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.zacharyseguin.alertscanada.Alert;
import ca.zacharyseguin.alertscanada.AlertsCanada;

@RestController
@RequestMapping(value="/")
public class AlertsController
{
   @Autowired
   private AlertsCanada alertsCanada;
   
   @RequestMapping
   public List<Alert> alerts() throws Exception
   {      
      return this.alertsCanada.getAlerts();
   }// End of alerts method
}
