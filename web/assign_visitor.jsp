<%@page import="MeditorPersistence.Doctor"%>
<%@page import="MeditorPersistence.Visitor"%>
<%@page import="MeditorJavaClasses.AssignVisitorProcessor"%>
<%@page import="MeditorPersistence.User"%>
<%@page import="MeditorServlets.LoginSrvlt"%>
<%@page import="MeditorServlets.AssignVisitorSrvlt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <%@ include file="includes/adm_common_head.jsp" %>
    
    <body> 
        <%@ include file="includes/admin_common.jsp" %>
        
        <section id="main" class="column">
            
            <% if (request.getAttribute("revealSuccesMsg") == "true") { %>
                <h4 class="alert_success">Visitor Assigned Successfully</h4>
            <%}%>   
              
            
            <article class="module width_quarter">
            <header><h3>Filters for Med. Visitor List</h3></header>
                <div class="module_content">
                           
                    <fieldset style="width:100%; float:left; margin-right: 3%;"> <!-- to make two field float next to one another, adjust values accordingly -->
                            <label>Select Group</label>
                            <select style="width:90%;">
                                    <option>Articles</option>
                                    <option>Tutorials</option>
                                    <option>Freebies</option>
                            </select>
                    </fieldset>
                    <fieldset style="width:100%; float:left;"> <!-- to make two field float next to one another, adjust values accordingly -->
                            <label>Search</label>
                            <input type="text" style="width:85%;">
                    </fieldset><div class="clear"></div>
                </div>
            <footer>
                    <form class="post_message">
                            <input type="submit" class="alt_btn" value="Refresh"/>
                    </form>
            </footer>
            </article> <!-- end of visitor filters article -->

            <article class="module width_3_quarter">
                <header><h3 class="tabs_involved">Select a Medical Visitor</h3>
		</header>
                
		<div class="tab_container">
			<div>
			<table class="tablesorter" cellspacing="0"> 
			<thead> 
				<tr> 
   				<th></th> 
    				<th>Name</th> 
    				<th>Level</th> 
    				<th>Superior</th> 
    				<th>Group Name</th> 
				</tr> 
			</thead> 
			<tbody> 
                                <% AssignVisitorProcessor assignVisitor = (AssignVisitorProcessor) request.getAttribute("assignVisitor");
                                for (Visitor obj : assignVisitor.getVisitorList()) { %>
				<tr> 
                                    <td><input type="radio" name="visitorToAssign" id="visitor_rd" value="<%= obj.getId()%>" form="assign_form"></td> 
    				<td><%= obj.getFirstname() + " " + obj.getSurname()%></td> 
    				<td><%= obj.getVisitorLevel() %></td> 
    				<td><%if (obj.getSuperior() != null) {%><%= obj.getSuperior().getSurname()%><%} else {%> not assigned <%}%></td> 
    				<td><%if (obj.getGroup() != null) {%><%= obj.getGroup().getName()%><%} else {%> not assigned <%}%></td> 
				</tr> 
                                <%}%>
			</tbody> 
			</table>
			</div><!-- end of #tab1 -->
			
		</div><!-- end of .tab_container -->
		
            </article><!-- end of visitors article -->
            
            <div class="clear"></div>
            
            <article class="module width_quarter">
            <header><h3>Filters for Doctor List</h3></header>
                <div class="module_content">
                           
                    <fieldset style="width:100%; float:left; margin-right: 3%;"> <!-- to make two field float next to one another, adjust values accordingly -->
                            <label>Select Geo Area</label>
                            <select style="width:90%;">
                                    <option>Articles</option>
                                    <option>Tutorials</option>
                                    <option>Freebies</option>
                            </select>
                    </fieldset>
                    
                    <fieldset style="width:100%; float:left; margin-right: 3%;"> <!-- to make two field float next to one another, adjust values accordingly -->
                            <label>Select City</label>
                            <select style="width:90%;">
                                    <option>Articles</option>
                                    <option>Tutorials</option>
                                    <option>Freebies</option>
                            </select>
                    </fieldset>
                    
                    <fieldset style="width:100%; float:left; margin-right: 3%;"> <!-- to make two field float next to one another, adjust values accordingly -->
                            <label>Select Institution</label>
                            <select style="width:90%;">
                                    <option>Articles</option>
                                    <option>Tutorials</option>
                                    <option>Freebies</option>
                            </select>
                    </fieldset>
                    
                    <fieldset style="width:100%; float:left; margin-right: 3%;"> <!-- to make two field float next to one another, adjust values accordingly -->
                            <label>Select Specialty</label>
                            <select style="width:90%;">
                                    <option>Articles</option>
                                    <option>Tutorials</option>
                                    <option>Freebies</option>
                            </select>
                    </fieldset>
                    
                    <fieldset style="width:100%; float:left;"> <!-- to make two field float next to one another, adjust values accordingly -->
                            <label>Search</label>
                            <input type="text" style="width:85%;">
                    </fieldset><div class="clear"></div>
                </div>
            <footer>
                    <form class="post_message">
                            <input type="submit" class="alt_btn" value="Refresh"/>
                    </form>
            </footer>
            </article> <!-- end of visitor filters article -->
              
            <article class="module width_3_quarter">
                <header><h3 class="tabs_involved">Select a Doctor</h3>
		</header>
                
		<div class="tab_container">
			<div>
			<table class="tablesorter" cellspacing="0"> 
			<thead> 
				<tr> 
   				<th></th> 
    				<th>Name</th> 
    				<th>Assigned_Visitor</th> 
    				<th>Specialty</th> 
    				<th>Position</th>
                                <th>Institution</th> 
    				<th>Geo_Area</th> 
    				<th>City</th>
				</tr> 
			</thead> 
			<tbody> 
                                <%for (Doctor obj : assignVisitor.getDoctorList()) { %>
				<tr> 
                                    <td><input type="checkbox" name="doctorToAssign" id="doctor_chbx" value="<%= obj.getId()%>" form="assign_form"></td> 
    				<td><%= obj.getName()%></td> 
    				<td><%if (obj.getAssignedVisitor() != null) {%><%= obj.getAssignedVisitor().getSurname()%><%} else {%> not assigned <%}%></td> 
    				<td><%= obj.getSpecialty().getSpecialtyName()%></td> 
    				<td><%= obj.getPosition()%></td>
                                <td><%= obj.getInstitution().getInstitutionName()%></td>
                                <td><%= obj.getInstitution().getCity().getGeoArea().getGeoName()%></td>
                                <td><%= obj.getInstitution().getCity().getCityName()%></td>
				</tr> 
                                <%}%>
			</tbody> 
			</table>
			</div><!-- end of #tab1 -->
			
		</div><!-- end of .tab_container -->
                
                <form class="post_message" id="assign_form" method="post" action="AssignVisitor">
                    <input type="submit" class="alt_btn" value="Assign"/>
                </form>
		
            </article><!-- end of doctors article -->
            
            
           
        
        </section>
    <script type="text/javascript" src="js/currentlinkstyle.js"></script>   
    </body>
</html>
