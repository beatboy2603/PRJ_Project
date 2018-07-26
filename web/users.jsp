<%-- 
    Document   : users
    Created on : Mar 27, 2018, 2:30:53 PM
    Author     : Think
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users management</title>
        <style>
            body{
                margin: 0;
                height: 600px;
            }
            #topDiv{ 
                height:10%;
                width:100%;
                position: fixed;
                background-color: #CCC;
                box-shadow: 0 8px 6px -3px grey;
            }
            #iconDiv{
                margin:5px;
                float:left;
            }
            #userDiv{
                margin: 20px;
                float:right;
            }
            #controlDiv{
                float:right;
                display: flex;
                flex-direction: row;
                justify-content: space-around;
            }
            .controlDivElement{
                height:30px;
                margin:15px;
                display: flex;
            }
            #topDivBuffer{
                height:15%;
                width:100%;
            }
        </style>

    </head>
    <body>
        <div id="topDiv">
            <div id="iconDiv">
                <img src="iconReplacement/cloudIcon.png" style="height:50px; width:50px"/>
            </div>
            <div id="userDiv">
                ${admin}
            </div>
            <div id="controlDiv">
                <form class="controlDivElement" action = "./Logout" method = "Get">
                    <input type="submit" name ="Logout" value="Logout"/>
                </form>
            </div>
        </div>
        <div id="topDivBuffer"></div>
        <table border="1" style="border-collapse: collapse" align="center">
            <thead>
                <tr>
                    <th>Username</th>
                    <th>Quota</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${users}" var="one">
                    <tr>
                        <td><a href="./File?username=${one.username}">${one.username}</a></td>
                        <td>${one.quota}</td>
                        <td>
                            <form action="./User" method="POST">
                                <input type="hidden" name="username" value="${one.username}"/>
                                <input type="submit" value="Delete"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>
