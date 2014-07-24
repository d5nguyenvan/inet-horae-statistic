package com.inet.cloud.service.horae.statistic.service

import com.inet.cloud.service.horae.statistic.db.mongo.MongoService
import com.inet.cloud.service.horae.statistic.dto.AgencyDto
import com.inet.cloud.service.horae.statistic.dto.KnobstickReportCriteriaDto
import com.inet.cloud.service.horae.statistic.dto.KnobstickSummaryReportDto
import com.mongodb.DB
import groovy.util.logging.Slf4j
import org.joda.time.DateTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * KnobstickReportService
 *
 * @author Dzung Nguyen
 * @version $Id KnobstickReportService 2014-07-25 01:15:30z dungvnguyen $
 * @since 1.0
 */
@Slf4j('LOG')
@Service('knobstickReportService')
class KnobstickReportService {
  //~ class properties ========================================================
  @Autowired
  private MongoService mongoService

  @Autowired
  private AgencyService agencyService

  //~ class members ===========================================================
  /**
   * Execute the report.
   *
   * @param criteriaDto the given report criteria.
   * @return the list of knobstick report.
   */
  List<KnobstickSummaryReportDto> executeDetails(KnobstickReportCriteriaDto criteriaDto, List<String> exceptList) {
    if (LOG.isInfoEnabled()) {
      LOG.info("BEGIN perform the report from the given criteria: ${criteriaDto}")
    }

    List<AgencyDto> nodes = agencyService.findAllExcept(exceptList)
    try {
      def criteria = [type: criteriaDto?.type ?: 'edoc']
      def from = criteriaDto?.from ?: null
      def to = criteriaDto?.to ?: null
      if (from != null && to == null) {
        criteria['create_at'] = [$gte: from]
      } else if (from == null && to != null) {
        criteria['create_at'] = [$lte: to]
      } else if (from != null && to != null) {
        criteria['create_at'] = [$gte: from, $lte: to]
      } else {
        from = DateTime.now().withMonthOfYear(1).withDayOfMonth(1).withTimeAtStartOfDay().toDate()
        to = DateTime.now().withTime(23, 59, 59, 999).toDate()
        criteria['create_at'] = [$gte: from, $lte: to]
      }

      // compute the sender report.
      def senders = [:], receivers = [:]

      mongoService.withDb('mercury') { DB db ->
        def senderAggregate = db.sknobsticks.aggregate([$match: criteria], [$group: [_id: '$sender.code', total: [$sum: 1]]])
        def receiverAggregate = db.rknobsticks.aggregate([$match: criteria],
            [$group: [
                _id: '$receiver.code',
                total: [$sum: 1]
            ]])

        if (senderAggregate) {
          senderAggregate.results().each { result ->
            senders[result._id] = result.total
          }
        }

        if (receiverAggregate) {
          receiverAggregate.results().each { result ->
            receivers[result._id] = result.total
          }
        }
      }

      // return the report information.
      nodes.collect { node -> new KnobstickSummaryReportDto(
          agencyId: node.id, agencyName: node.name, agencyCode: node.code,
          numberOfSent: senders[node.code] ?: 0,
          numberOfReceived: receivers[node.code] ?: 0)
      }
    } finally {
      if (LOG.isInfoEnabled()) {
        LOG.info("END perform the report from the given criteria: ${criteriaDto}")
      }
    }
  }

  /**
   * Summary knobstick.
   *
   * @param criteriaDto the given criteria to summary.
   * @return the summary data.
   */
  KnobstickSummaryReportDto executeSummary(KnobstickReportCriteriaDto criteriaDto, List<String> exceptList) {
    if (LOG.isInfoEnabled()) {
      LOG.info("BEGIN perform the summary report from the given criteria: ${criteriaDto}")
    }

    AgencyDto node = criteriaDto.unit == null ? null : agencyService.findByCode(criteriaDto.unit)
    try {
      def criteria = [type: criteriaDto?.type ?: 'edoc']
      def from = criteriaDto?.from ?: null
      def to = criteriaDto?.to ?: null
      if (from != null && to == null) {
        criteria['create_at'] = [$gte: from]
      } else if (from == null && to != null) {
        criteria['create_at'] = [$lte: to]
      } else if (from != null && to != null) {
        criteria['create_at'] = [$gte: from, $lte: to]
      } else {
        from = DateTime.now().withMonthOfYear(1).withDayOfMonth(1).withTimeAtStartOfDay().toDate()
        to = DateTime.now().withTime(23, 59, 59, 999).toDate()
        criteria['create_at'] = [$gte: from, $lte: to]
      }

      // compute the sender report.
      def senders = [:], receivers = [:]
      mongoService.withDb('mercury') { DB db ->
        // copy to sender criteria.
        def senderCriteria = criteria.clone()
        if (criteriaDto.unit) senderCriteria['sender.code'] = criteriaDto.unit
        else if (exceptList) senderCriteria['sender.code'] = [$nin: exceptList]
        def senderAggregate = db.sknobsticks.aggregate([$match: senderCriteria], [$group: [_id: (criteriaDto.unit == null ? null : '$sender.code'), total: [$sum: 1]]])

        // copy to receiver criteria.
        def receiverCriteria = criteria.clone()
        if (criteriaDto.unit) receiverCriteria['receiver.code'] = criteriaDto.unit
        else if (exceptList) receiverCriteria['receiver.code'] = [$nin: exceptList]
        def receiverAggregate = db.rknobsticks.aggregate([$match: receiverCriteria],[$group: [ _id: (criteriaDto.unit == null ? null : '$receiver.code'), total: [$sum: 1] ]])

        if (senderAggregate) {
          senderAggregate.results().each { result ->
            senders[criteriaDto.unit == null ? '0' : result._id] = result.total
          }
        }

        if (receiverAggregate) {
          receiverAggregate.results().each { result ->
            receivers[criteriaDto.unit == null ? '0' : result._id] = result.total
          }
        }
      }

      if (node) {
        new KnobstickSummaryReportDto(
            agencyId: node.id, agencyCode: node.code, agencyName: node.name,
            numberOfReceived: receivers[node.code] ?: 0,
            numberOfSent: senders[node.code] ?: 0
        )
      } else new KnobstickSummaryReportDto(numberOfSent: senders['0'] ?: 0, numberOfReceived: receivers['0'] ?: 0)
    } finally {
      if (LOG.isInfoEnabled()) {
        LOG.info("END perform the summary report from the given criteria: ${criteriaDto}")
      }
    }
  }
}
