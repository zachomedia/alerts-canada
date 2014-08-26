package ca.zacharyseguin.util;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonWithDateObjectMapper extends ObjectMapper
{
   private static final Logger logger = Logger.getLogger(JacksonWithDateObjectMapper.class);
   public JacksonWithDateObjectMapper()
   {
      super();
      
      logger.info("Configuring Jackson Mapper to display timestamps");
      
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
      dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
      super.setDateFormat(dateFormat);
      super.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
   }
}
