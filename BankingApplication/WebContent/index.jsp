<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
</head>
<body>
	<h1>Banking Application</h1>
	
	<form action="login" method="post">
		<h3>Please login</h3>
		<label>Username: <input type="input" name="username" /></label>
		<label>Password: <input type="input" name="password" /></label>
		
		<input type="submit" text="Login"/>
	</form>
	
	<%
		/*  PrintWriter out = response.getWriter();*/
		out.print("</br><bold><i>This statement was printed using JSP script tags</i></bold>");
		
	%>
</body>
</html>