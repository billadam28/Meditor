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

            <header><h3><center>Statistics</center></h3></header>
            <div class="tab_container">
                <table class="tablesorter" cellspacing="0">
                    <thead>
                    <td>tobeadded</td>
                    
                    </thead>
                    <tbody>
                      <% ReportVstHandler vst = (ReportVstHandler) request.getAttribute("visit");%>
                        <% for (Visit obj: vst.displayVisits()){ %>
                        <tr>
                        <td><%=obj.getVisitOffset()%></td>
                        <td><%=obj.getStatus()%></td>
                        </tr>
                        <%}%>
                        
 
                    </tbody>
                    
                </table>

            </div>
            </form>
        </article>
			
                            
                            
                        
    <script type="text/javascript" src="js/currentlinkstyle.js"></script>    
    </body>
</html>