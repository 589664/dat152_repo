<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Home</title>

</head>

	<body>
	
		<p><jsp:include page="chooseLanguage.jsp" /></p>
		
		<h1>Kaffekopper AS</h1>
		
		<img src="img/amongus.jpg" width="300" height="300">
		
		<fmt:bundle basename="i18n.Messages">
			<p><fmt:message key="look" /> <a href="products"><fmt:message key="products" /></a></p>
		</fmt:bundle>
		
		<p><jsp:include page="copyright.jsp" /></p>
		
	</body>
</html>


