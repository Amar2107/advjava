<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login page</title>
<link rel="stylesheet" type="text/css" href="main.css">
</head>
<body>
<form action="response" method="get">
<br>
<h1 align="center">Login Page</h1>
<table align="center">
<tr>
<td>User name</td>
<td>
<input type="text" name="name"/>
</td>
</tr>
<tr>
<td>Email </td>
<td>
<input type="text" name="email"/>
</td>
</tr>
<tr>
<td>Enter Password</td>
<td>
<input type="password" name="password"/>
</td>
</tr>
<tr>
<td><input type="submit" name="submit"/></td>
</tr>
</table>
<%
String msg=request.getParameter("msg");
%>
<p><%=msg%> </p>
</form>
</body>
</html>