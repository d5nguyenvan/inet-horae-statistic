package com.inet.cloud.service.horae.statistic.serializer

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.springframework.util.StringUtils

/**
 * ShortToDateDeserializer
 *
 * @author Dzung Nguyen
 * @version $Id ShortToDateDeserializer 2014-07-25 01:05:30z dungvnguyen $
 * @since 1.0
 */
class ShortToDateDeserializer extends JsonDeserializer<Date> {
  //~ class properties ========================================================
  private static final String SHORT_DATE_FORMAT = "dd/MM/yyyy";

  //~ class members ===========================================================
  @Override
  Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    String date = jp.getText()
    if (!StringUtils.hasText(date)) return null

    DateTime toDate = DateTimeFormat.forPattern(SHORT_DATE_FORMAT).parseDateTime(date)
    toDate.withTime(23, 59, 59, 999).toDate()
  }
}
