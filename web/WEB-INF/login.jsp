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
        <style>
            body{
                margin: 0;
                height: 600px;
                color: white;
            }
            #mainDiv{
                position:fixed;
                top:30%;
                left: 30%;
                height: 30%;
                width: 40%;
                margin: 0px;
                padding: 2% 1% 2% 1%;
                display: inline-block;
                text-align: center;
                background-color: #6495ED;
                box-shadow: 0 8px 6px -3px grey
            }
        </style>
    </head>
    <body>
        <jsp:include page="../header.jsp"></jsp:include>
            <div id="mainDiv">
                <h1>Login Page</h1>
                <form action="./Login" method="POST">
                    <table align="center">
                        <tr>
                            <td>Username:</td>
                            <td><input type="text" name="username"/></td>
                        </tr>
                        <tr>
                            <td>Password:</td>
                            <td><input type="password" name="pass"/></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input type="submit" value="Login"/>
                                <a href="./Signup"><input type="button" value="Signup"/></a>
                            </td>
                        </tr>
                    </table>
                </form>
                <c:if test = "${error != null}">
                    <p style="color: red">${error}</p>
                </c:if>
        </div>
    </body>
</html>
