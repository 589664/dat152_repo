<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="taglib"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script defer src="js/sum.js"></script>


<title>Products</title>

</head>

	<body>
	
		<p><jsp:include page="chooseLanguage.jsp" /></p>
	
		<fmt:bundle basename="i18n.Messages">
			
			<table>
			
			<c:forEach items="${products}" var="prod">
			<tr>
				<td><h1>${prod.name}</h1></td>
			</tr>
			
			<tr>
				<td>
				<img alt="${prod.name}" src="${prod.imageFile}" width="250" height="200">
				</td>
				
				<td>
				
				<fmt:message key="name" />: ${prod.name} 
				<br> 
				<fmt:message key="price" />: <fmt:formatNumber value = "${prod.price}" type = "currency"/>
				
				<p><taglib:shorttext maxchars="10">${prod.decsription}</taglib:shorttext></p>
				
				<form action="products" method="post">
					 <input name="itemid" id="itemid" type="hidden" value="${prod.id}" >
					 <input type="submit" value="<fmt:message key="add"/>" >
				 </form>
				 
				</td>
				
			</tr>
			
			</c:forEach>
			</table>
			
			<p><a href="home"><fmt:message key="home" /></a> <a href="cart"><fmt:message key="cart" /></a></p>
			
		</fmt:bundle>
		
		<p><jsp:include page="copyright.jsp" /></p>
		
	</body>
</html>
