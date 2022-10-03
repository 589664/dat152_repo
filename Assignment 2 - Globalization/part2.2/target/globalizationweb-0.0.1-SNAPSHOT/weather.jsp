<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.Date"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My i18n Application</title>
</head>

<body>
	<p><jsp:include page="chooseLanguage.jsp" /></p>

	<fmt:bundle basename="no.hvl.dat152.i18n.Messages">
		<h3>
			<fmt:message key="todaysWeather" />
			-
			<% pageContext.setAttribute("date", new java.util.Date()); %>
			<fmt:formatDate value="${date}" dateStyle="long" />
		</h3>
		<p>bla bla bla ...</p>
		<p>
			<a href="index.jsp"><fmt:message key="home" /> </a>
		</p>
	</fmt:bundle>

</body>
</html>
