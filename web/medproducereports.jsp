<%-- 
    Document   : medproducereports
    Created on : May 21, 2016, 12:46:03 PM
    Author     : glalas
--%>

<%@page import="java.util.List"%>
<%@page import="MeditorPersistence.Cycle"%>
<%@page import="MeditorJavaClasses.CycleList"%>
<%@page import="MeditorJavaClasses.ReportVstHandler"%>
<%@page import="MeditorPersistence.User"%>
<%@page import="MeditorPersistence.Visitor"%>
<%@page import="MeditorServlets.AdminMedReportsSrvlt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <%@ include file="includes/adm_common_head.jsp" %>
    
    <body> 
        <%@ include file="includes/admin_common.jsp" %>

        <article class="module width_3_quarter">
            <form action="ProduceVstReportSrvlt" method="get">
            <header><h3><center>Select A Visitor To Display Statistics</center></h3></header>
            <div class="tab_container">
                <table class="tablesorter" cellspacing="0">
                    <thead>
                    <td></td>
                    <td> First Name</td>
                    <td> Surname </td>
                    <td> Level </td>
  
                    </thead>
                    <tbody>
                        <% ReportVstHandler vst = (ReportVstHandler) request.getAttribute("Visitors");%>
                        <% for (Visitor obj: vst.getVisitorResults()){ %>
                        <tr>
                        <td><input type="radio" name="selectvisitor" value="<%= obj.getId()%>"></td>
                        <td><%=obj.getFirstname()%></td>
                        <td><%=obj.getSurname()%></td>
                        <td><%= obj.getVisitorLevel() %></td>
                        <%}%>
                        
                        
 
                    </tbody>
                    
                </table>
                        <td><select style="width:20%;" name ="cycle">
                                <option>Select A Cycle</option>
                                <% List<Cycle> cycle = (List<Cycle>) request.getAttribute("cycleList");%>
                                 <%for (Cycle cyc: cycle) {%>
                                <option value="<%=cyc.getId()%>"><%=cyc.getCycle()%></option>
                                <%}%>
                            </select></td>
                        
                        
                        <input type="submit" name="submit" value="Get Reports">
            </div>
            </form>
        </article>
			
                            
                            
                        
    <script type="text/javascript" src="js/currentlinkstyle.js"></script>    
    </body>
</html>