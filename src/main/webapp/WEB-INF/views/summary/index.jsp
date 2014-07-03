<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="vi">
<head>
  <title><spring:message code="summary.head.title"/></title>
  <meta content="text/html; charset=UTF-8" http-equiv="content-type"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
<body>
<div class="widget-box widget-color-blue" style="font-family: Arial, Verdana, Geneva, Lucida, 'lucida grande', helvetica, sans-serif;
    padding: 0;box-shadow: none;margin: 3px 0;border-bottom: 1px solid #CCC;">
  <div style="position: relative;
    min-height: 38px;
    background: #f2f2f2;
    background: -moz-linear-gradient(top, #fff 0, #eee 100%);
    background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #fff), color-stop(100%, #eee));
    background: -webkit-linear-gradient(top, #fff 0, #eee 100%);
    background: -o-linear-gradient(top, #fff 0, #eee 100%);
    background: -ms-linear-gradient(top, #fff 0, #eee 100%);
    background: linear-gradient(to bottom, #fff 0, #eee 100%);
    filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#FFFFFF', endColorstr='#EEEEEE', GradientType=0);
    color: #669fc7;
    border: 1px solid #CCC;
    border-bottom: 1px solid #DDD;
    padding-left: 12px;
    text-align: right;position: relative;background: #307ECC;border-color: #307ECC;color: #FFF;min-height: 31px;padding-left: 10px;
    filter:progid:DXImageTransform.Microsoft.gradient(enabled=false);">
    <div style="line-height: 36px;padding: 0;margin: 0;float: left;text-align: left;font-size: 13px;font-weight: bold;">
      <spring:message code="summary.body.title"/>
    </div>
  </div>

  <div style="border: 1px solid #307ECC;border-top: 0;background-color: #FFF;">
    <div style="padding: 12px;text-align: center">
            <span style="font-size: 14px;font-weight: bold;">
                <spring:message code="summary.body.summary_message" arguments="${month_unit}" argumentSeparator=";"/>
            </span>
      <br />
      <spring:url value="/summary/details.iws?token=${token}" var="details_url"/>
      <a href="${details_url}" target="_blank" style="text-decoration: none;">
      <span class="" style=" display: inline-block;
    color: #FFF !important;
    text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25) !important;
    background-image: none !important;
    border: 5px solid #FFF;
    border-radius: 0;
    box-shadow: none !important;
    -webkit-transition: all ease .15s;
    transition: all ease .15s;
    cursor: pointer;
    vertical-align: middle;
    margin: 0;
    position: relative;
    display: inline-block;
    min-width: 100px;
    font-size: 18px;
    font-weight: normal;
    color: #FFF;
    text-align: center;
    text-shadow: 0 -1px -1px rgba(0, 0, 0, 0.2) !important;
    border: 0;
    border-radius: 12px;
    padding: 12px 0 8px;
    margin: 2px;
    line-height: 1.7;
    position: relative;
    min-height: 50px;
    width: 80px;
    font-size: 16px;
    border-radius: 10px;
    line-height: 1.5;
    background: #fee088 !important;
    background-image: -webkit-gradient(linear, left 0, left 100%, from(#ffe8a5), to(#fcd76a)) !important;
    background-image: -webkit-linear-gradient(top, #ffe8a5, 0%, #fcd76a, 100%) !important;
    background-image: -moz-linear-gradient(top, #ffe8a5 0, #fcd76a 100%) !important;
    background-image: linear-gradient(to bottom, #ffe8a5 0, #fcd76a 100%) !important;
    background-repeat: repeat-x !important;
    filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffffe8a5', endColorstr='#fffcd76a', GradientType=0) !important;
    color: #963 !important;
    text-shadow: 0 -1px 0 rgba(255, 255, 255, 0.4) !important;">
        <span style="line-height: 1;font-size: 170%"> <fmt:formatNumber value="${total}" type="number"/> </span>
        <br>
        <span style="line-height: 1;font-size: 90%"> <spring:message code="summary.body.summary_unit"/></span>
      </span>
      </a>
      <br>
        <span style="font-size: 11px;font-style: italic;">
          (<spring:message code="summary.body.summary_footer" arguments="${auto_update_date}" argumentSeparator=";"/>)
        </span>
    </div>
  </div>
</div>
</body>
</html>