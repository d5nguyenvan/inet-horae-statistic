package com.inet.cloud.service.horae.statistic.controller

import com.inet.cloud.service.horae.statistic.dto.AgencyDto
import com.inet.cloud.service.horae.statistic.dto.ErrorMessage
import com.inet.cloud.service.horae.statistic.dto.KnobstickReportCriteriaDto
import com.inet.cloud.service.horae.statistic.dto.KnobstickSummaryReportDto
import com.inet.cloud.service.horae.statistic.dto.Message
import com.inet.cloud.service.horae.statistic.dto.OkMessage
import com.inet.cloud.service.horae.statistic.service.AgencyService
import com.inet.cloud.service.horae.statistic.service.KnobstickReportService
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

import javax.annotation.Resource
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

/**
 * SummaryController
 *
 * @author Dzung Nguyen
 * @version $Id SummaryController 2014-07-02 22:39:30z dungvnguyen $
 * @since 1.0
 */
@Slf4j('LOG')
@Controller
@RequestMapping('/summary')
@CompileStatic
class SummaryController implements InitializingBean {
  //~ class properties ========================================================
  @Autowired
  private KnobstickReportService knobstickReportService

  @Autowired
  private AgencyService agencyService

  @Resource
  @Qualifier('exceptList')
  private List<String> exceptList

  private List<String> resolveExceptList
  //~ class methods ===========================================================
  /**
   * @return redirect to index.
   */
  @RequestMapping(method = [RequestMethod.GET])
  String noneSlash() {
    'redirect:/summary/index.iws'
  }

  /**
   * @return redirect to index.
   */
  @RequestMapping(value = '/', method = [RequestMethod.GET])
  String slash() {
    'redirect:/summary/index.iws'
  }

  @RequestMapping(value = '/index.iws', method = [RequestMethod.GET])
  String index(Model model, @RequestParam(value = "unit", required = false) String unit) {
    // compute data.
    KnobstickSummaryReportDto summaryReportDto = null
    List<AgencyDto> agencies = []

    try {
      summaryReportDto = knobstickReportService.executeSummary(
          new KnobstickReportCriteriaDto(unit: unit, type: 'edoc'),
          resolveExceptList
      )
      agencies = (unit ? [ agencyService.findByCode(unit) ] : agencyService.findAllExcept(resolveExceptList))
    } catch (Exception ex) {
      LOG.warn('An error occurs during summarizing knobstick data, all data will be reset.', ex)
    }

    model.addAttribute('auto_update_date', DateTime.now().toString(DateTimeFormat.forPattern("HH:mm;dd/MM/yyyy")))
    model.addAttribute('total', summaryReportDto.numberOfSent + summaryReportDto.numberOfReceived)
    if (unit) {
      model.addAttribute('month_unit', String.valueOf(DateTime.now().monthOfYear) + ';' + agencies[0].name)
    } else {
      model.addAttribute('month_unit', String.valueOf(DateTime.now().monthOfYear) + ';Thành phố')
    }

    model.addAttribute('token', 'DU5PJU3GtHbQaX0zxiWoCMq8Z')
    model.addAttribute('total_unit', agencies.size())

    'summary/index'
  }

  @RequestMapping(value='/details.iws', method = [RequestMethod.GET, RequestMethod.POST])
  String details(HttpSession httpSession) {
    'summary/details'
  }

  /**
   * @return the report details.
   */
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(value='/details-report.json', produces = [MediaType.APPLICATION_JSON_VALUE], method = RequestMethod.POST)
  Message generateReportDetails(HttpServletRequest request,
                                HttpServletResponse response) {
    try {
      new OkMessage<List<KnobstickSummaryReportDto>>(
          requestId: UUID.randomUUID().toString(),
          result: knobstickReportService.executeDetails(
              new KnobstickReportCriteriaDto(type: 'edoc'),
              resolveExceptList
          ),
          action: 'details-report'
      )
    } catch (Exception ex) {
      new ErrorMessage('unknown_error', 'An known error occurs during generating detail report', '/summary/details-report.json', 'unknown')
    }
  }

  @Override
  void afterPropertiesSet() throws Exception {
    // find exception list.
    if (agencyService != null && resolveExceptList == null) {
      try {
        resolveExceptList = agencyService.findExceptionList()
      } catch (Exception ex) { /** no worry, we don't need handle this exception. */ }
    }

    // resolve exception list.
    if (resolveExceptList == null) {
      resolveExceptList = new ArrayList<>()
    }

    // add full exception list.
    exceptList.each { code -> if (!resolveExceptList.contains(code)) resolveExceptList.add(code) }
  }
}
