<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%
   String ctx = request.getContextPath();
   String resource = ctx + "/resources";
%>
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="vi">
<head>
    <title><spring:message code="summary.head.title"/></title>
    <meta content="text/html; charset=UTF-8" http-equiv="content-type"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <link rel="stylesheet" type="text/css" href="<%=resource%>/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="<%=resource%>/css/bootstrap-responsive.css"/>

    <link rel="stylesheet" type="text/css" href="<%=resource%>/css/font-awesome.css"/>
    <!--[if IE 7]>
    <link rel="stylesheet" type="text/css" href="<%=resource%>/css/font-awesome-ie7.css"/><![endif]-->
    <link rel="stylesheet" type="text/css" href="<%=resource%>/css/ace.css"/>
    <link rel="stylesheet" type="text/css" href="<%=resource%>/css/ace-responsive.css"/>
    <!--[if lt IE 9]>
    <link rel="stylesheet" type="text/css" href="<%=resource%>/css/ace-ie.css"/><![endif]-->
    <link rel="stylesheet" type="text/css" href="<%=resource%>/css/horae.css"/>
    <link rel="stylesheet" type="text/css" href="<%=resource%>/css/bootstrap-datepicker.css"/>
    <link rel="stylesheet" type="text/css" href="<%=resource%>/css/grid.css"/>

<body class="navbar-fixed" style="overflow: hidden">
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="brand" href="javascript:;">
                <small><i class="icon-building"></i> <spring:message code="detail.head.title"/></small>
            </a>
        </div>
    </div>
</div>
<div class="container-fluid" id="main-container">
    <div id="main-content" class="clearfix row-fluid">
        <div id="page-content" class="clearfix">
            <div id="agency-summary" style="color: #3A87AD;
            background-color: #D9EDF7;border-color: #BCE8F1;padding: 8px 35px 8px 14px;
            text-shadow: 0 1px 0 rgba(255, 255, 255, 0.5);
            text-align: right;
            border: 1px solid #FBEED5;">
                <span><i class="icon-arrow-up bigger-120 blue"></i> <spring:message code="detail.body.number_of_sent"/>: <b id="knobstick-number-of-sent">0</b></span> | <span>
                <i class="icon-arrow-down bigger-120 blue"></i> <spring:message code="detail.body.number_of_received"/>: <b id="knobstick-number-of-received">0</b></span>
            </div>
            <div class="row-fluid">
                <div id="knobstick-report-basic-search">
                    <div class="row-fluid">
                        <div class="span12">
                            <div class="row-fluid">
                                <div class="span3">
                                    <div class="input-icon input-icon-right date">
                                        <input id="knobstick-report-search-basic-txt-fromdate" placeholder='<spring:message code="detail.body.fdate"/>'  disabled="disabled" type="text" class="span12"/>
                                        <i class="icon-calendar"></i>
                                    </div>
                                </div>
                                <div class="span3">
                                    <div class="input-icon input-icon-right date">
                                        <input id="knobstick-report-search-basic-txt-todate" disabled="disabled" placeholder='<spring:message code="detail.body.tdate"/>' type="text" class="span12"/>
                                        <i class="icon-calendar"></i>
                                    </div>
                                </div>
                                <div class="span3">
                                    <select id="knobstick-report-search-basic-select-type" class="span12" disabled="disabled">
                                        <option value="" selected="selected"><spring:message code="detail.body.type_all"/></option>
                                        <option value="edoc"><spring:message code="detail.body.type_edoc"/></option>
                                        <option value="etask" disabled><spring:message code="detail.body.type_etask"/></option>
                                    </select>
                                </div>
                                <div class="span3">
                                    <button data-action-search="search" type="button" class="btn btn-small" disabled="disabled">
                                        <i class="icon-search"></i> <spring:message code="detail.body.submit"/>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="knobstick-report-grid"></div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="<%=resource%>/js/lib/jquery.min.js"></script>
<script type="text/javascript" src="<%=resource%>/js/lib/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=resource%>/js/lib/inet-core.min.js"></script>
<script type="text/javascript" src="<%=resource%>/js/lib/inet-ui.min.js"></script>
<script type="text/javascript" src="<%=resource%>/js/lib/inet-utilities.min.js"></script>
<script type="text/javascript" src="<%=resource%>/js/resources/knobstick-lang-vi.js"></script>
<script type="text/javascript" src="<%=resource%>/js/lib/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="<%=resource%>/js/lib/grid.min.js"></script>
<script type="text/javascript" src="<%=resource%>/js/ui/KnobstickReportService.js"></script>
</body>
</html>