<%-- 
    Document   : signup
    Created on : Mar 22, 2018, 6:40:50 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Signup</title>
    </head>
    <body>
        <h1>Signup Page</h1>
        <c:if test = "${error != null}">
            <p style="color: red">${error}</p>
        </c:if>
        <form action="./Signup" method="POST">
            Username: <input type="text" name="username"/>
            <br/>
            Password: <input type="password" name="pass"/>
            <br/>
            <input type="submit" value="Signup"/>
            <a href="./Login">Back</a>
        </form>
    </body>
</html>
