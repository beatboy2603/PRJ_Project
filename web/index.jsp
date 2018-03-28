<%-- 
    Document   : index
    Created on : Mar 21, 2018, 12:31:49 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="./css/indexCss.css" >
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="./js/indexJs.js"></script>  
        <title>Index</title>
    </head>
    <body>
        <div id="topDiv">
            <div id="iconDiv">
                <img src="iconReplacement/cloudIcon.png" style="height:50px; width:50px"/>
            </div>
            <div id="userDiv">
                <c:choose>
                    <c:when test="${admin!=null}">
                        ${param.username}'s
                    </c:when>
                    <c:otherwise>
                        ${sessionScope.username}
                    </c:otherwise>
                </c:choose>
            </div>
            <div id="controlDiv">
                <c:choose>
                    <c:when test="${admin!=null}">
                        <a href="./User" style="text-decoration:none"><input class="controlDivElement" type="button" value="Back"/></a>
                        </c:when>
                        <c:otherwise>
                        <form class="controlDivElement" action = "./addFile.jsp" method = "GET">
                            <input type="submit" name ="action" value="Add"/>
                        </form>
                        <form class="controlDivElement" action = "./File" method = "Get">
                            <input type="hidden" name="fileType" value="myFiles"/>
                            <input type="submit" value="My Files"/>
                        </form>
                        <form class="controlDivElement" action = "./File" method = "Get">
                            <input type="hidden" name="fileType" value="sharedFiles"/>
                            <input type="submit" value="Shared To Me"/>
                        </form>
                    </c:otherwise>
                </c:choose>
                <form class="controlDivElement" action = "./Logout" method = "Get">
                    <input type="submit" name ="Logout" value="Logout"/>
                </form>
            </div>
        </div>
        <div id="topDivBuffer"></div>
        <div id="mainDiv">
            <c:set var="count" value="0"/>
            <c:forEach items="${files}" var = "current">
                <c:set var="count" value="${count + 1}"/>
                <div class="fileContainer" id="fileContainer${count}">
                    <c:choose>
                        <c:when test = "${fn:endsWith(current.fName, 'jpg')||fn:endsWith(current.fName, 'png')}">
                            <img src="fileManager/${current.fOwner}/${current.fName}"/>
                        </c:when>
                        <c:when test = "${fn:endsWith(current.fName, 'txt')}">
                            <img src="iconReplacement/txtIcon.png"/>
                        </c:when>
                        <c:when test = "${fn:endsWith(current.fName, 'rar')||fn:endsWith(current.fName, 'zip')}">
                            <img src="iconReplacement/rarIcon.png"/>
                        </c:when>
                        <c:otherwise>
                            <img src="iconReplacement/fileIcon.png"/>
                        </c:otherwise>
                    </c:choose>
                    <p>${current.fName}</p>
                    <div class="fileButtonDiv">
                        <form class ="fileButton" action="./Download" method="GET">
                            <c:if test="${admin!=null}">
                                <input type="hidden" name="username" value="${param.username}"/>   
                            </c:if>
                            <input type="hidden" name="fId" value="${current.fId}"/>   
                            <input type="submit" value="Download"/>
                        </form>
                        <c:choose>
                            <c:when test="${admin!=null||username!=current.fOwner}">
                            </c:when>
                            <c:otherwise>
                                <form class ="fileButton" action="./Privacy" method="GET">
                                    <input type="hidden" name="fId" value="${current.fId}" />
                                    <input type="submit" value="Privacy"/>
                                </form>
                            </c:otherwise>
                        </c:choose>
                        <c:if test="${username==current.fOwner||admin!=null}">
                            <form class ="fileButton deleteButton">
                                <c:if test="${admin!=null}">
                                    <input type="hidden" name="username" value="${param.username}"/>   
                                </c:if>
                                <input type="hidden" class="fileId" name="fId" value="${current.fId}" />
                                <input type="hidden" class="fileName" name="fName" value="${current.fName}" />
                                <input type="button" value="Delete"/>
                            </form>
                        </c:if>
                    </div>
                </div>
                <c:if test = "${count % 4 == 0}">
                    <br/>
                </c:if>
            </c:forEach>
        </div>
        <div id="confirmDelete"></div>

    </body>
</html>
