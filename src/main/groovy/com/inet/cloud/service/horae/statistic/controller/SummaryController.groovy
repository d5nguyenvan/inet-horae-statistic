package com.inet.cloud.service.horae.statistic.controller

import groovy.transform.CompileStatic
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

import javax.servlet.http.HttpSession

/**
 * SummaryController
 *
 * @author Dzung Nguyen
 * @version $Id SummaryController 2014-07-02 22:39:30z dungvnguyen $
 * @since 1.0
 */
@Controller
@RequestMapping('/summary')
@CompileStatic
class SummaryController {
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
    def date = new Date();
    model.addAttribute('auto_update_date', '00:00;' + date.format('d/M/yyyy'))
    model.addAttribute('total', 79652)
    model.addAttribute('month_unit', '7;Thành phố')
    model.addAttribute('token', 'DU5PJU3GtHbQaX0zxiWoCMq8Z')
    model.addAttribute('total_unit', 59)

    'summary/index'
  }

  @RequestMapping(value='/details.iws', method = [RequestMethod.GET, RequestMethod.POST])
  String details(HttpSession httpSession) {
    'summary/details'
  }
}
