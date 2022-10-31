<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="no.hvl.dat152.obl3.util.CSRF"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>		
	<h3>A Searchable English Dictionary</h3>
	<p>You must be logged in to use this service</p>
	<p><font color="red">${message}</font></p>
	<p><a href="login">Log in</a></p>
	<p><a href="newuser">New User</a></p>	
	
	<%
	// generate a random CSRF token 
	String csrfToken = CSRF.getToken();
	
	// place the CSRF token in a cookie
	javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie("csrfToken", csrfToken);
	response.addCookie(cookie);
	%>
	
	<form action="action" method="POST">
	  <input type="hidden" name="csrfToken" value="<%= csrfToken %>"/>
	</form>
	
	
</body>
</html>