<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="vi">
<head>
  <title><spring:message code="summary.head.title"/></title>
  <meta content="text/html; charset=UTF-8" http-equiv="content-type"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
<body>
<div style="font-family: Arial, Verdana, Geneva, Lucida, 'lucida grande', helvetica, sans-serif;
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
    text-align: center;
    position: relative;
    background: #4F81BD;
    border-color: #1F497D;color: #FFF;min-height: 31px;padding-left: 10px;
    filter:progid:DXImageTransform.Microsoft.gradient(enabled=false);">
    <div style="width:100%;line-height: 36px;padding: 0;margin: 0;float: left;text-align: center;font-size: 13px;font-weight: bold;">
      <spring:message code="summary.body.title"/>
    </div>
  </div>

  <div style="border: 1px solid #307ECC;border-top: 0;background-color: #FFF;">
    <div style="padding: 12px;text-align: center">
            <span style="font-size: 14px;font-weight: bold;color:#1F497D;margin-bottom: 5px;">
                <spring:message code="summary.body.summary_message" arguments="${month_unit}" argumentSeparator=";"/>
            </span>
      <div style="padding-bottom: 7px;"></div>
      <spring:url value="/summary/details.iws?token=${token}" var="details_url"/>
      <a href="${details_url}" target="_blank" style="text-decoration: none;">
        <span style="color:#C00000;font-size: 40px; font-weight: bold;"> <fmt:formatNumber value="${total}" type="number"/></span>
        <br />
        <span style="color:#1F497D;font-weight:bold;font-size: 90%"> <spring:message code="summary.body.summary_unit"/>
        <br />
         <spring:message code="summary.body.between_the_unit" arguments="59" argumentSeparator=";"/>
        </span>
        <br />
      </a>
        <div style="padding-bottom: 7px;"></div>
        <span style="font-size: 11px;font-style: italic;font-weight:bold;color:#1F497D">
          (<spring:message code="summary.body.summary_footer" arguments="${auto_update_date}" argumentSeparator=";"/>)
        </span>
    </div>
  </div>
</div>
</body>
</html>