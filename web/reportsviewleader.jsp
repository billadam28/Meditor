<%-- 
    Document   : medproducereports
    Created on : May 21, 2016, 12:46:03 PM
    Author     : glalas
--%>

<%@page import="MeditorPersistence.Visit"%>
<%@page import="MeditorJavaClasses.ReportVstHandler"%>
<%@page import="MeditorPersistence.User"%>
<%@page import="MeditorPersistence.Visitor"%>
<%@page import="MeditorServlets.AdminMedReportsSrvlt"%>
<%@page import="MeditorServlets.ProduceVstReportSrvlt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <%@ include file="includes/adm_common_head.jsp" %>
    
    <body> 
        <%@ include file="includes/admin_common.jsp" %>

        <article class="module width_3_quarter">

            <header><h3><center>Statistics For Group Leader</center></h3></header>
            <div class="tab_container">
                <table class="tablesorter" cellspacing="0">
                    <% Integer cycid = (Integer) request.getAttribute("cycle");%>
                    <% Integer vstorId = (Integer) request.getAttribute("vstorId");%>
                    <% ReportVstHandler vst = (ReportVstHandler) request.getAttribute("visit");%>
                    <% Visitor visitor= (Visitor) request.getAttribute("visitor");%>
                    <thead>
                      
                    <td><center>Visitor Name</center></td>
                    <td><center>Total Doctors</center></td>
                    <td><center>Total Number Of Visits</center></td>
                    <td><center>Number of Visits On This Cycle</center></td>
                    <td><center>Coverage Percent of Current Cycle</center></td>
                    </tr>
      
                    </thead>
                    <tbody>
                        
                        <tr>
                        <td><center><%=visitor.getFirstname()%> <%=visitor.getSurname()%></center></td>       
                        <td><center><%=vst.totalDoctors(visitor) %></center></td>
                        <td><center><%=vst.totalVisits(visitor) %></center></td>
                        <td><center><%=vst.totalVisitsPerCycle(visitor, cycid)%></center></td>
                       <td><center><%=vst.getStatics(visitor,cycid)%></center></td>

                        </tr>
                        
                    
                        
                     <div class="tab_container">
                     <table class="tablesorter" cellspacing="0">

                    <thead>
                      
                    <td><center>Pending Visits</center></td>
                    <td><center>Completed Visits</center></td>
                    <td><center>Unsuccessful Visits</center></td>
            

                    </tr>
      
                    </thead>
                    <tbody>
                        
                        <tr>
                        <td><center><%=vst.pendingVisits(visitor, vstorId, cycid) %></center></td>       
                        <td><center><%=vst.completedVisits(visitor, vstorId, cycid) %></center></td>
                        <td><center><%=vst.unsuccessfulVisits(visitor, vstorId,cycid) %></center></td>
                        </tr>   
                        
                       
 
                    </tbody>
                    
                </table>

            </div>
            </form>
        </article>
			
                            
      
            <article class="module width_3_quarter">

            <header><h3><center>Statistics For Group</center></h3></header>
            <div class="tab_container">
                <table class="tablesorter" cellspacing="0">
                    
                    <% ReportVstHandler grpvst = (ReportVstHandler) request.getAttribute("groupvisitors");%>
                                    
                    <thead>
                    <td><center>Group Overall Coverage Percent of Current Cycle</center></td>
                    </thead>
                    <tbody>                        
                        <tr>
                        <td><center><%= grpvst.groupVisitorsStats(visitor, cycid)%> </center></td>
                        </tr>
                    
                </table>

            </div>
            </form>
        </article>
                        
                        
                        
                        
                        
            
                        
    <script type="text/javascript" src="js/currentlinkstyle.js"></script>    
    </body>
</html>