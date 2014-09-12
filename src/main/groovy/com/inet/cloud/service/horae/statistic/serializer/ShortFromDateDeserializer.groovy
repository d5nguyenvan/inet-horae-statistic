package com.inet.cloud.service.horae.statistic.serializer

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import groovy.transform.CompileStatic
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.springframework.util.StringUtils

/**
 * ShortFromDateDeserializer
 *
 * @author Dzung Nguyen
 * @version $Id ShortFromDateDeserializer 2014-07-25 00:26:30z dungvnguyen $
 * @since 1.0
 */
@CompileStatic
class ShortFromDateDeserializer extends JsonDeserializer<Date> {
  //~ class properties ========================================================
  private static final String SHORT_DATE_FORMAT = "dd/MM/yyyy";

  //~ class members ===========================================================
  @Override
  Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    String date = jp.getText()
    if (!StringUtils.hasText(date)) return null

    DateTime fromDate = DateTimeFormat.forPattern(SHORT_DATE_FORMAT).parseDateTime(date)
    fromDate.withTimeAtStartOfDay().toDate()
  }
}
