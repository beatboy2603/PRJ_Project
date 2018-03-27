<%-- 
    Document   : users
    Created on : Mar 27, 2018, 2:30:53 PM
    Author     : Think
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users management</title>
    </head>
    <body>
        <table border="1">
            <thead>
                <tr>
                    <th>Username</th>
                    
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${users}" var="one">
                    <tr>
                        <td><a href="./File?username=${one.username}">${one.username}</a></td>
                        
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>
