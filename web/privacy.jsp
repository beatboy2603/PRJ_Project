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
        <style>
            body{
                margin: 0;
                height: 600px;
            }
            #mainDiv{
                position:fixed;
                top:25%;
                left: 25%;
                height: 40%;
                width: 50%;
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
                <h1>Privacy of "${file.fName}"</h1>

            <div class ="subDiv" id="switchPrivacyDiv">
                <form action="./Privacy" method="POST">
                    This file's privacy is "${file.privacy}". Do you want to switch to "${file.privacy=="public"?"private":"public"}"?<br/>
                    <input type="hidden" name="fId" value="${file.fId}">
                    <input type="submit" name="action" value="Switch">
                </form>
            </div>
            <br/>
            <div class ="subDiv" id="shareDiv">
                Share file to other user!
                <form action="./Privacy" method="GET">
                    Enter name to filter: <input type="text" name="filterName" value="${subFilterName}">
                    <input type="hidden" name="fId" value="${file.fId}">
                    <input type="submit" value="Filter">
                </form>
                <form action="./Privacy" method="POST" id="shareList">
                    <c:if test="${usernames.size()>0}">
                        <select name="userToShare" form="shareList" style="width: 100px;">
                            <c:forEach items="${usernames}" var = "current">
                                <option value="${current}">${current}</option>
                            </c:forEach>    
                        </select>
                        <input type="hidden" name="fId" value="${file.fId}">
                        <input type="submit" name="action" value="Share">
                    </c:if>
                </form>
            </div>
            <br/>
            <a href="./File"><input type="button" value="Back"></a>
        </div>
    </body>
</html>
