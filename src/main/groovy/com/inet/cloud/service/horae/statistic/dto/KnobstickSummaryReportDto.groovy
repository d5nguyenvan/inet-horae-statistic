package com.inet.cloud.service.horae.statistic.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.CompileStatic

/**
 * KnobstickSummaryReportDto
 *
 * @author Dzung Nguyen
 * @version $Id KnobstickSummaryReportDto 2014-07-25 00:22:30z dungvnguyen $
 * @since 1.0
 */
@CompileStatic
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
class KnobstickSummaryReportDto {
  //~ class properties ========================================================
  @JsonProperty('agency_id')
  String agencyId

  @JsonProperty('agency_code')
  String agencyCode

  @JsonProperty('agency_name')
  String agencyName

  @JsonProperty('number_of_sent')
  Integer numberOfSent

  @JsonProperty('number_of_received')
  Integer numberOfReceived
}
