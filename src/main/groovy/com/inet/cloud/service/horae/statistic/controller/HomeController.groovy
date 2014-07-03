package com.inet.cloud.service.horae.statistic.controller

import groovy.transform.CompileStatic
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

/**
 * HomeController
 *
 * @author Dzung Nguyen
 * @version $Id HomeController 2014-07-03 12:41:30z dungvnguyen $
 * @since 1.0
 */
@Controller
@CompileStatic
class HomeController {
  //~ class members ===========================================================
  @RequestMapping(value='/', method = [RequestMethod.GET])
  def index() {
    'redirect:/summary/index.iws'
  }
}
