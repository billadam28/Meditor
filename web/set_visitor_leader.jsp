<%-- 
    Document   : admin
    Created on : Apr 21, 2016, 4:17:05 PM
    Author     : thodo
--%>

<%@page import="MeditorPersistence.User"%>
<%@page import="MeditorPersistence.Visitor"%>
<%@page import="MeditorPersistence.Group"%>
<%@page import="MeditorJavaClasses.GroupServices"%>
<%@page import="MeditorServlets.LoginSrvlt"%>
<%@page import="MeditorServlets.AssignVisitorGroupSrvlt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <%@ include file="includes/adm_common_head.jsp" %>
    
    <body> 
        <%@ include file="includes/admin_common.jsp" %>
        
        <section id="main" class="column">
            
           <%-- <% if (request.getAttribute("revealSuccesMsg") == "true") { %>
                <h4 class="alert_success">Visitor Assigned to Group Successfully</h4>
            <%}%>--%>
        
            <article class="module width_full">
		<header><h3>Select a Medical Visitor</h3></header>

		<div class="tab_container">
                    <div id="tab1" class="tab_content">
                        <table class="tablesorter" cellspacing="0"> 
                        <thead> 
                            <tr>  
                                <th>Name</th> 
                                <th>Surname</th> 
                                <th>Level</th> 
                                <th>Superior</th> 
                                <th>Member of Group</th>
                                <th>Check for leader</th>
                            </tr> 
                        </thead> 
                        <tbody>
                            <%--<% GroupServices groupServices = (GroupServices) request.getAttribute("groupServices");
                            for (Visitor obj : groupServices.visitorsList()) { %>--%>
                            <tr>                                
                                <td><%--<%= obj.getFirstname()%>--%></td>
                                <td><%--<%= obj.getSurname()%>--%></td>
    				<td><%--<%= obj.getVisitorLevel() %>--%></td> 
    				<td><%--<%= obj.getSuperiorName() %>--%></td> 
                                <td><%--<%= obj.getAssignedGroupName() %>--%></td>
                                <td><input type="checkbox" name="leaderVisitor" id="visitor_chbx" value="<%--<%= obj.getId()%>--%>" form="set_form"></td>
                            </tr> 
                            <%--<%}%>--%>
                        </tbody> 
                        </table>
                    </div><!-- end of #tab1 -->
		</div><!-- end of .tab_container -->
		
            <footer>
                    
                        <form style="margin-right:20px; float:right;" class="post_message" id="set_form" method="post" action="SetVisitorLeader">
                            <input type="submit" class="alt_btn" value="Set Leader"/>
                            <input type="reset" id="helpForSetLeader" onclick="help3();" class="alt_btn" value="Help"/>
                        </form>
                   
            </footer>    
            </article><!-- end of groups -->
            
        </section>
        <script type="text/javascript" src="js/help.js"></script>
        <script type="text/javascript" src="js/currentlinkstyle.js"></script> 
    </body>
</html>
