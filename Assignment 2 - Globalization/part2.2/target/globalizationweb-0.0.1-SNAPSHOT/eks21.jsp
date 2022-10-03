<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Eksamen 2021</title>
</head>
<body>
	<fmt:bundle basename="no.hvl.dat152.eks21.Message">
		<h1>
			<fmt:message key="info" />
		</h1>
		<p>
			<fmt:message key="time">
				<fmt:param>
					<%
					pageContext.setAttribute("date", new java.util.Date());
					%>
					<fmt:formatDate value="${date}" dateStyle="long" />
				</fmt:param>
			</fmt:message>
		</p>
		<p>
			<fmt:message key="duration">
				<fmt:param>
					<fmt:formatNumber value="16200" type="number" pattern="#,##0" />
				</fmt:param>
			</fmt:message>
		</p>
		<p>
			<fmt:message key="exam" />
		</p>
		<p>
			<fmt:message key="pass">
				<fmt:param>
					<fmt:formatNumber value="0.399" type="percent" maxIntegerDigits="2"
						minFractionDigits="1" />
				</fmt:param>
			</fmt:message>
		</p>
	</fmt:bundle>

</body>
</html>
