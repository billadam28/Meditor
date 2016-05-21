<%-- 
    Document   : addsuccess
    Created on : May 17, 2016, 3:59:06 PM
    Author     : glalas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="MeditorServlets.SubmitDocServlet"%>

<!DOCTYPE html>

<html>
    <%@ include file="includes/vst_common_head.jsp" %>
     
    <body>
        <%@ include file="includes/visitor_common.jsp" %>

     
    </head>
    <body>
       
                <% if (request.getAttribute("revealCreateSuccessMsg") == "true") { %>
        <h1 class="alert_success"> Doctor was created successfully!</h1>
            <%}%>
        
                <% if (request.getAttribute("revealEditSuccessMsg") == "true") { %>
        <h1 class="alert_success"> Doctor was Edited successfully!</h1>
            <%}%>
        
    </body>
    <script type="text/javascript" src="js/currentlinkstyle.js"></script> 
</html>
