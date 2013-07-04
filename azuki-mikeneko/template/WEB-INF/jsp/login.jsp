<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://azuki.jp/tags/base" prefix="azBase" %>
<%@ taglib uri="http://azuki.jp/tags/html" prefix="azHtml" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>

	<link rel="stylesheet" type="text/css" href="css/menu.css">

</head>
<body style="margin: 0px;">

	<azHtml:form action="login.do" method="post">
	<table style="width: 100%;">
		<tr>
			<td style="text-align: center; padding-top: 100px; padding-bottom: 40px;">
				<azHtml:img src="img/logo.gif"/>
			</td>
		</tr>
		<tr>
			<td style="text-align: center;">
				<center>
				<table>
					<tr>
						<td style="text-align: right;"><azBase:label name="LOGIN.NAME"/></td>
						<td style="text-align: left;"><azHtml:textbox name="name"/></td>
					</tr>
					<tr>
						<td style="text-align: right;"><azBase:label name="LOGIN.PASS"/></td>
						<td style="text-align: left;"><azHtml:password name="pass"/></td>
					</tr>
					<tr>
						<td colspan="2" style="text-align: center;">
							<input type="submit" value="login" />
						</td>
					</tr>
				</table>
				</center>
			</td>
		</tr>
	</table>
	</azHtml:form>

</body>
</html>