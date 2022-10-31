<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="no.hvl.dat152.obl3.util.CSRF"%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search</title>
</head>
<body>
	<h3>Main search page</h3>
	<p><a href="mydetails">My personal details and search history</a></p>
	<form action="dosearch" method="get">
		<input type="hidden" name="user" value="${user.username}" /> 
		<p>Dictionary search (enter word, e.g. Car): 
			<input type="text" name="searchkey" />
			<input type="submit" value="Go!"/></p>
			
			<%
			// generate a random CSRF token 
			String csrfToken = CSRF.getToken();
			
			// place the CSRF token in a cookie
			javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie("csrfToken", csrfToken);
			response.addCookie(cookie);
			%>
			<input type="hidden" name="csrfToken" value="<%= csrfToken %>"/>
	</form>
	<p><b>Last 5 searches done by anyone (Only visible to super Admins)</b></p>
	<p>
	<c:forEach varStatus="counter" var="searchItem" items="${top5history}">
		<b>${counter.count}:</b> ${searchItem.datetime} 
		<a href="dosearch?user=${user.username}&searchkey=${searchItem.searchkey}">
		${searchItem.searchkey}</a><br>
	</c:forEach><br>
	<p><b>You are logged in as ${user.username}. <a href="logout">Log out</a></b></p>
</body>
</html>