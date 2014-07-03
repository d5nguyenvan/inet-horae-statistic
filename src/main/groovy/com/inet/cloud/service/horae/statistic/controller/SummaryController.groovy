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
    model.addAttribute('auto_update_date', '12:00;03/07/2014')
    model.addAttribute('total', 12003)
    model.addAttribute('month_unit', '7;Thành phố')
    model.addAttribute('token', 'token')

    'summary/index'
  }

  @RequestMapping(value='/details.iws', method = [RequestMethod.GET, RequestMethod.POST])
  @ResponseBody
  String details(HttpSession httpSession) {
    '{hello:1}'
  }
}
