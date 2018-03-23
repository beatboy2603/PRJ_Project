<%-- 
    Document   : share.jsp
    Created on : Mar 22, 2018, 7:25:14 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Share ${file.fName} to</h1>
        <select name="userToShare" form="shareList">
            <c:forEach items="${usernames}" var = "current">
                <option value="${current}">${current}</option>
            </c:forEach>    
        </select>
        <form action="./Share" method="POST" id="shareList">
            <input type="hidden" name="fId" value="${file.fId}">
            <input type="submit" value="Share">
            <a href="./File">Back</a>
        </form>
    </body>
</html>
