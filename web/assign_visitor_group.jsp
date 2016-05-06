<%-- 
    Document   : admin
    Created on : Apr 21, 2016, 4:17:05 PM
    Author     : thodo
--%>

<%@page import="MeditorPersistence.User"%>
<%@page import="MeditorPersistence.Visitor"%>
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
            
            <% if (request.getAttribute("revealSuccesMsg") == "true") { %>
                <h4 class="alert_success">Visitor Assigned to Group Successfully</h4>
            <%}%>
        
            <article class="module width_3_quarter">
		<header><h3 class="tabs_involved">Select a Medical Visitor</h3>
		</header>

		<div class="tab_container">
                    <div id="tab1" class="tab_content">
                        <table class="tablesorter" cellspacing="0"> 
                        <thead> 
                            <tr> 
                                <th></th> 
                                <th>Name</th> 
                                <th>Surname</th> 
                                <th>Level</th> 
                                <th>Superior</th> 
                            </tr> 
                        </thead> 
                        <tbody>
                            <% GroupServices groupServices = (GroupServices) request.getAttribute("groupServices");
                            for (Visitor obj : groupServices.visitorsList()) { %>
                            <tr> 
                                <td><input type="checkbox" name="assignedVisitor" id="visitor_chbx" value="<%= obj.getId()%>" form="assign_form"></td> 
                                <td><%= obj.getFirstname()%></td>
                                <td><%= obj.getSurname()%></td>
    				<td><%= obj.getVisitorLevel() %></td> 
    				<td><%= obj.getSuperiorName() %></td> 
                            </tr> 
                            <%}%>
                        </tbody> 
                        </table>
                    </div><!-- end of #tab1 -->
		</div><!-- end of .tab_container -->
		
            </article><!-- end of visitors -->
            
            <div class="clear"></div>
            
            <article class="module width_3_quarter">
		<header><h3 class="tabs_involved">Select a Medical Visitor</h3>
		</header>

		<div class="tab_container">
                    <div id="tab1" class="tab_content">
                        <table class="tablesorter" cellspacing="0"> 
                        <thead> 
                            <tr> 
                                <th></th> 
                                <th>Name</th> 
                                <th>Surname</th> 
                                <th>Level</th> 
                                <th>Superior</th> 
                            </tr> 
                        </thead> 
                        <tbody>
                            <% GroupServices groupServices = (GroupServices) request.getAttribute("groupServices");
                            for (Visitor obj : groupServices.visitorsList()) { %>
                            <tr> 
                                <td><input type="checkbox" name="assignedVisitor" id="visitor_chbx" value="<%= obj.getId()%>" form="assign_form"></td> 
                                <td><%= obj.getFirstname()%></td>
                                <td><%= obj.getSurname()%></td>
    				<td><%= obj.getVisitorLevel() %></td> 
    				<td><%= obj.getSuperiorName() %></td> 
                            </tr> 
                            <%}%>
                        </tbody> 
                        </table>
                    </div><!-- end of #tab1 -->
		</div><!-- end of .tab_container -->
		
            </article><!-- end of groups -->
            
            <form class="post_message" id="assign_form" method="post" action="AssignVisitorGroup">
                    <input type="submit" class="alt_btn" value="Assign"/>
            </form>
            
        </section>  
        <script type="text/javascript" src="js/currentlinkstyle.js"></script> 
    </body>
</html>
