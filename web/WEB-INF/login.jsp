<%-- 
    Document   : login
    Created on : Mar 21, 2018, 2:16:18 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login Page</h1>
        <c:if test = "${error != null}">
            <p style="color: red">${error}</p>
        </c:if>
        <form action="./Login" method="POST">
            Username: <input type="text" name="username"/>
            <br/>
            Password: <input type="password" name="pass"/>
            <br/>
            <input type="submit" value="Login"/>
        </form>
        <form action="./Signup" method="Get">
            <input type="submit" value="Signup"/>
        </form>
    </body>
</html>
