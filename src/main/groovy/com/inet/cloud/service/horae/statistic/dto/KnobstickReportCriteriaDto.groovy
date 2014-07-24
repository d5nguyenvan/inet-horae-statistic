package com.inet.cloud.service.horae.statistic.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.inet.cloud.service.horae.statistic.serializer.JavascriptDateSerializer
import com.inet.cloud.service.horae.statistic.serializer.ShortFromDateDeserializer
import com.inet.cloud.service.horae.statistic.serializer.ShortToDateDeserializer
import groovy.transform.CompileStatic

/**
 * KnobstickReportCriteriaDto
 *
 * @author Dzung Nguyen
 * @version $Id KnobstickReportCriteriaDto 2014-07-25 00:24:30z dungvnguyen $
 * @since 1.0
 */
@CompileStatic
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
class KnobstickReportCriteriaDto {
  @JsonDeserialize(using =  ShortFromDateDeserializer.class)
  @JsonSerialize(using = JavascriptDateSerializer.class)
  Date from

  @JsonDeserialize(using =  ShortToDateDeserializer.class)
  @JsonSerialize(using = JavascriptDateSerializer.class)
  Date to

  String type

  String unit

  @Override
  public java.lang.String toString() {
    final java.lang.StringBuilder sb = new java.lang.StringBuilder('KnobstickReportCriteriaDto{');
    sb.append('from=').append(from);
    sb.append(', to=').append(to);
    sb.append(", type='").append(type).append('\'');
    sb.append(", unit='").append(unit).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
