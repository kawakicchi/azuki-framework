<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://azuki.jp/tags/base" prefix="azBase" %>
<%@ taglib uri="http://azuki.jp/tags/html" prefix="azHtml" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>MikeNeko - AzukiFramework</title>

    <!-- CSS -->
    <azHtml:link rel="stylesheet" href="css/bootstrap.css" type="text/css"/>
    <style type="text/css">

      /* Sticky footer styles
      -------------------------------------------------- */

      html,
      body {
        height: 100%;
        /* The html and body elements cannot have any padding or margin. */
      }

      /* Wrapper for page content to push down footer */
      #wrap {
        min-height: 100%;
        height: auto !important;
        height: 100%;
        /* Negative indent footer by it's height */
        margin: 0 auto -60px;
      }

      /* Set the fixed height of the footer here */
      #push,
      #footer {
        height: 60px;
      }
      #footer {
        background-color: #f5f5f5;
      }

      /* Lastly, apply responsive CSS fixes as necessary */
      @media (max-width: 767px) {
        #footer {
          margin-left: -20px;
          margin-right: -20px;
          padding-left: 20px;
          padding-right: 20px;
        }
      }



      /* Custom page CSS
      -------------------------------------------------- */
      /* Not required for template or sticky footer method. */

      #wrap > .container {
        padding-top: 60px;
      }
      .container .credit {
        margin: 20px 0;
      }

      code {
        font-size: 80%;
      }

    </style>
    <azHtml:link rel="stylesheet" href="css/bootstrap-responsive.css" type="text/css"/>

</head>
<body style="margin: 0px;">
	
    <!-- Part 1: Wrap all page content here -->
    <div id="wrap">

      <!-- Fixed navbar -->
      <div class="navbar navbar-fixed-top">
        <div class="navbar-inner">
          <div class="container">
<jsp:include page="menu.jsp" flush="true" />
          </div>
        </div>
      </div>

      <!-- Begin page content -->
      <div class="container">
<jsp:include page="<%=request.getParameter(\"client\") %>" flush="true"></jsp:include>
      </div>

      <div id="push"></div>
    </div>

    <div id="footer">
      <div class="container">
<jsp:include page="footer.jsp" flush="true" />
      </div>
    </div>
    
    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <azHtml:script src="js/jquery.js"></azHtml:script>
    <azHtml:script src="js/bootstrap-transition.js"></azHtml:script>
    <azHtml:script src="js/bootstrap-alert.js"></azHtml:script>
    <azHtml:script src="js/bootstrap-modal.js"></azHtml:script>
    <azHtml:script src="js/bootstrap-dropdown.js"></azHtml:script>
    <azHtml:script src="js/bootstrap-scrollspy.js"></azHtml:script>
    <azHtml:script src="js/bootstrap-tab.js"></azHtml:script>
    <azHtml:script src="js/bootstrap-tooltip.js"></azHtml:script>
    <azHtml:script src="js/bootstrap-popover.js"></azHtml:script>
    <azHtml:script src="js/bootstrap-button.js"></azHtml:script>
    <azHtml:script src="js/bootstrap-collapse.js"></azHtml:script>
    <azHtml:script src="js/bootstrap-carousel.js"></azHtml:script>
    <azHtml:script src="js/bootstrap-typeahead.js"></azHtml:script>

</body>
</html>