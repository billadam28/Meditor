<%-- 
    Document   : admin
    Created on : Apr 21, 2016, 4:17:05 PM
    Author     : thodo
--%>

<%@page import="MeditorPersistence.User"%>
<%@page import="MeditorServlets.LoginSrvlt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Visitor's Page</title>
    </head>
    <body> 
            <%User user = (User) session.getAttribute("user");%>
            <b>Welcome <%= user.getFirstname() + " " + user.getSurname()%>
    </body>
</html>
