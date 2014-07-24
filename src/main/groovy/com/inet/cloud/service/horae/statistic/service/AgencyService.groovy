package com.inet.cloud.service.horae.statistic.service

import com.inet.cloud.service.horae.statistic.db.mongo.MongoService
import com.inet.cloud.service.horae.statistic.dto.AgencyDto
import com.mongodb.DB
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * AgencyService
 *
 * @author Dzung Nguyen
 * @version $Id AgencyService 2014-07-25 01:23:30z dungvnguyen $
 * @since 1.0
 */
@Slf4j('LOG')
@Service('agencyService')
class AgencyService {
  //~ class properties ========================================================
  @Autowired
  private MongoService mongoService

  //~ class members ===========================================================
  /**
   * @return the agency information from the given agency code.
   */
  AgencyDto findByCode(String code) {
    if (LOG.isInfoEnabled()) {
      LOG.info("BEGIN finding the agency that has the code: [${code}]")
    }

    try {
      mongoService.withDb('mercury') { DB db ->
        def agency = db.agencies.findOne([code: code])
        if (agency)
          new AgencyDto(id: agency.id, code: agency.code, name: agency.name)
        else null
      }
    } finally {
      if (LOG.isInfoEnabled()) {
        LOG.info("END finding the agency that has the code: [${code}]");
      }
    }
  }

  /**
   * @return the list of agencies information except agency has code contain in except list.
   */
  List<AgencyDto> findAllExcept(List<String> exceptList) {
    if (LOG.isInfoEnabled()) {
      LOG.info("BEGIN finding all agencies except: [${exceptList}]")
    }

    try {
      mongoService.withDb('mercury') { DB db ->
        def cursor = db.agencies.find([code: [$nin: exceptList]]).sort([name: 1])
        try {
          if (cursor) {
            cursor.collect { agency -> new AgencyDto(id: agency.id, code: agency.code, name: agency.name) }
          } else []
        } finally {
          cursor.close()
        }
      }
    } finally {
      if (LOG.isInfoEnabled()) {
        LOG.info("END finding all agencies except: [${exceptList}]")
      }
    }
  }
}
