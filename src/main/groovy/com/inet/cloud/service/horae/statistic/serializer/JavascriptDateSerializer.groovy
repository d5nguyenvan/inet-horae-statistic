package com.inet.cloud.service.horae.statistic.serializer

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import groovy.transform.CompileStatic
import org.joda.time.DateTime
import org.joda.time.DateTimeZone

/**
 * JavascriptDateSerializer
 *
 * @author Dzung Nguyen
 * @version $Id JavascriptDateSerializer 2014-07-25 01:09:30z dungvnguyen $
 * @since 1.0
 */
@CompileStatic
class JavascriptDateSerializer extends JsonSerializer<Date> {
  //~ class properties ========================================================
  private static final String ISO8601_DATE_FORMAT_JS = "yyyy-MM-dd'T'HH:mm:ss+00:00";

  //~ class members ===========================================================
  @Override
  void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
    if (value != null) {
      jgen.writeString(new DateTime(value).withZone(DateTimeZone.UTC).toString(ISO8601_DATE_FORMAT_JS))
    }
  }
}
