<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign Up</title>
</head>
<body>
<form action="Sign_in" method="post">
<table align="center">
<tr>
<td>First Name</td>
<td>
<input type="text" name="fname"/></td>
</tr>
<tr>
<td>Last name Name</td>
<td>
<input type="text" name="lname"/></td>
</tr>
<tr>
<td>User Name</td>
<td>
<input type="text" name="uname"/></td>
</tr>
<tr>
<td>Password</td>
<td>
<input type="password" name="password"/></td>
</tr>
<tr>
<td>
<input type="submit" value="signup"/></td>
</tr>
</table>

<%
String msg=request.getParameter("msg");
%>
<p>
<% 
out.println(msg); %>
</p>

</form>
</body>
</html>