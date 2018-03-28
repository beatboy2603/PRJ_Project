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
        <style>
            body{
                margin: 0;
                height: 600px;
                color:white;
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
        <jsp:include page="header.jsp"></jsp:include>
            <div id="mainDiv">
                <h1>Add a file</h1>
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
            <a href='./File'><input type="button" value="Back"/></a>
        </div>
    </body>
</html>
