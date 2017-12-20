<%-- 
    Document   : login
    Created on : Feb 8, 2017, 3:25:21 PM
    Author     : SONU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body><center>
        <form method="processRequest" action="login">
            <h1><B>User Login</B></h1>
            <table>
                <tr>
                    <td>Enter the user name</td>
                    <td><input type="text" name="name" placeholder="Username"></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="text" name="password"></td>
                </tr>
            </table>
            <input type="submit" name="s1" value="Submit">
        </form>
    </center>
    </body>
</html>
