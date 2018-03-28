<%-- 
    Document   : download
    Created on : Mar 22, 2018, 4:29:22 AM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Download</title>
        <style>
            body{
                margin: 0;
                height: 600px;
            }
            #mainDiv{
                position:fixed;
                top:25%;
                left: 30%;
                height: 40%;
                width: 40%;
                margin: 0px;
                padding: 2% 1% 2% 1%;
                display: inline-block;
                text-align: center;
                background-color: #D3D3D3;
                box-shadow: 0 8px 6px -3px grey
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <div id="mainDiv">
                <h1>Download</h1>
                <p>File Name: "${file.fName}"</p>
            <p>Size: 
                <c:choose>
                    <c:when test="${file.fSize/1024/1024>1}">
                        <fmt:formatNumber type = "number" 
                                          maxFractionDigits = "3" value = "${file.fSize/1024/1024}"/> MB
                    </c:when>
                    <c:otherwise>
                        <fmt:formatNumber type = "number" 
                                          maxFractionDigits = "3" value = "${file.fSize/1024}"/> KB
                    </c:otherwise>
                </c:choose>
            </p>
            <a href="./FileDownloadManager/${link}/${file.fName}"><h1>Click to download</h1></a>
                <c:if test="${admin!=null}">
                <a href='./File?username=${param.username}'><input type="button" value="Back"/></a>
            </c:if>
            <c:if test="${admin==null}">
                <a href='./File'><input type="button" value="Back"/></a>
            </c:if>
        </div>
    </body>
</html>
