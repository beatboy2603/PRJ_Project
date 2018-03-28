<%-- 
    Document   : header
    Created on : Mar 28, 2018, 4:32:01 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            #topDivBuffer{
                height:15%;
                width:100%;
            }
        </style>
    </head>
    <body>
        <div id="topDiv">
            <div id="iconDiv">
                <a href="./File"><img src="iconReplacement/cloudIcon.png" style="height:50px; width:50px"/></a>
            </div>
        </div>
        <div id="topDivBuffer"></div>
    </body>
</html>
