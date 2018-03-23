<%-- 
    Document   : addFile
    Created on : Mar 21, 2018, 2:54:55 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add</title>
    </head>
    <body>
        <h1>Add</h1>
        <c:if test = "${error != null}">
            <p style="color: red"><%=request.getAttribute("error")%></p>
        </c:if>
        <form action = "./File" method = "POST" enctype="multipart/form-data">
            <input type="file" name="file">
            <input type ="hidden" name="action" value="add"/>
            <input type="checkbox" name="privacy" value="public">Public
            <input type="submit" name ="action" value="Add"/>
        </form>
        <br/>
        <a href='./File'>Go back</a>
    </body>
</html>
