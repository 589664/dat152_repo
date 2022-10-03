<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="taglib"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="css/table.css" rel="stylesheet" type="text/css">

<title>Cart</title>

</head>

	<body>
	
		<p><jsp:include page="chooseLanguage.jsp" /></p>
		
		<h1>Cart</h1>
		
		<fmt:bundle basename="i18n.Messages">
		
		<table id="table">
			<tr id="titles">
				<th><fmt:message key="name" /></th>
				<th><fmt:message key="description" /></th>
				<th><fmt:message key="price" /></th>
				<th><fmt:message key="quantity" /></th>
				<th><fmt:message key="total" /></th>
			</tr>
			
			<c:forEach items="${cart.products}" var="prod">
			
			<tr>
				<td>${prod.name}</td>
				<td><taglib:shorttext maxchars="10">${prod.decsription}</taglib:shorttext></td>
				<td><fmt:formatNumber value = "${prod.price}" type = "currency"/></td>
				<td>${prod.quantity}</td>
				<td><fmt:formatNumber value = "${cart.getItemTotal(prod)}" type = "currency"/></td>
			</tr>
			
			</c:forEach>
			
			<tr>
				<th colspan="4" style=text-align:right><fmt:message key="sum" />:</th>
				<th colspan="3"><fmt:formatNumber value = "${cart.grandTotal()}" type = "currency"/></th>
			</tr>
		</table>
		
		<p><a href="home"><fmt:message key="home" /></a> <a href="products"><fmt:message key="products" /></a></p>
		
		</fmt:bundle>
		
		<p><jsp:include page="copyright.jsp" /></p>
		
	</body>
</html>


