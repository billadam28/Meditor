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
            
            <% if (request.getAttribute("revealSuccessMsg") == "true") { %>
                <h4 class="alert_success">Visitor(s) Assigned to Group Successfully</h4>
            <%}%>
        
            <article class="module width_3_quarter" style="float:none; margin:auto; margin-top: 20px;">
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
                                <th>Group</th>
                                <th></th>
                            </tr> 
                        </thead> 
                        <tbody>
                            <% GroupServices groupServices = (GroupServices) request.getAttribute("groupServices");%>
                            <%if (groupServices.visitorsList().isEmpty() == false) { 
                                for (Visitor obj : groupServices.visitorsList()) { %>
                                    <tr>                            
                                        <td><%= obj.getFirstname()%></td>
                                        <td><%= obj.getSurname() %></td>
                                        <td><%= obj.getVisitorLevel() %></td> 
                                        <td><%if (obj.getSuperior() != null) {%><%= obj.getSuperior().getSurname()+ " " + obj.getSuperior().getFirstname()%><%} else {%> --- <%}%></td>
                                        <td><%if (obj.getGroup() != null) {%><%= obj.getGroup().getName()%><%} else {%> --- <%}%></td>
                                        <td><input type="checkbox" name="assignedVisitor" id="visitor_chbx" value="<%= obj.getId()%>" form="assign_form"></td>
                                <%}%>
                            <%} else {%>
                                        <td><p style="color:red; font-weight: bold">There ara no available Medical Visitors for assignment!</p></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                            <%}%>
                            </tr> 
                        </tbody> 
                        </table>
                    </div><!-- end of #tab1 -->
		</div><!-- end of .tab_container -->
		
                <div class="module_content" style="width:40%; margin: auto;">
                    <fieldset>
                        <label>Select a Group</label>
                        <select  name="assignedGroup" form="assign_form" style="width:92%;">
                            <%for (Group obj : groupServices.groupsList()) { %>
                            <option value="<%= obj.getId()%>"><%= obj.getName()%></option>
                            <%}%>
                        </select>
                    </fieldset>
                </div>
            <footer>
                    
                        <form style="margin-right:20px; float:right;" class="post_message" id="assign_form" name="assign_form" method="post" action="AssignVisitorGroup">
                            <%if (groupServices.visitorsList().isEmpty() == true) {%>
                                <input type="submit" style="background-color:grey; color: gray; border: 1px solid grey"  value="Assign Visitor" disabled/>
                            <%} else {%>
                                <input type="submit" class="alt_btn" value="Assign Visitor" onclick="return validateAssignForm()"/>
                            <%}%>
                            <input type="reset" id="helpForAssignVisitor" onclick="help2();" class="alt_btn" value="Help"/>
                        </form>
                   
            </footer>    
            </article><!-- end of groups -->
            
        </section>
        <script type="text/javascript" src="js/validateform.js"></script>                    
        <script type="text/javascript" src="js/help.js"></script>
        <script type="text/javascript" src="js/currentlinkstyle.js"></script> 
    </body>
</html>
