<%-- 
    Document   : download
    Created on : Mar 22, 2018, 4:29:22 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Download</title>
    </head>
    <body >
        <h1>Download ${fName}</h1>
        <a href="./FileDownloadManager?f=${link}"><h1>${link}</h1></a>
    </body>
</html>
