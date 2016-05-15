<%@page import="MeditorPersistence.Group"%>
<%@page import="MeditorPersistence.Specialty"%>
<%@page import="MeditorPersistence.Institution"%>
<%@page import="MeditorPersistence.City"%>
<%@page import="MeditorPersistence.GeographicalArea"%>
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
              
            <%AssignVisitorProcessor assignVisitorProc = (AssignVisitorProcessor) request.getAttribute("assignVisitor");%>
                                      
            <article class="module width_quarter">
            <header><h3>Filters for Med. Visitor List</h3></header>
                <div class="module_content">
                           
                    <fieldset style="width:100%; float:left; margin-right: 3%;"> <!-- to make two field float next to one another, adjust values accordingly -->
                            <label>Select Group</label>
                            <select style="width:90%;" form="visitor_filter" id="group_dd">
                                    <%for (Group obj : assignVisitorProc.getGroupListObj().getGroupList()) { %>
                                    <option value="<%= obj.getId()%>"><%=obj.getName()%></option>
                                    <%}%>
                            </select>
                    </fieldset>
                    <fieldset style="width:100%; float:left;"> <!-- to make two field float next to one another, adjust values accordingly -->
                            <label>Search</label>
                            <input type="text" style="width:85%;">
                    </fieldset><div class="clear"></div>
                </div>
            <footer>
                    <button type="button" class="alt_btn" onclick="updateVisitorList()"
                        style="color:orange;font-weight:bold">Refresh</button>
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
			<tbody id="vst_table"> 
                                <%for (Visitor obj : assignVisitorProc.getVisitorListObj().getVisitorList()) { %>
				<tr> 
                                    <td><input type="radio" name="visitorToAssign" id="visitor_rd" value="<%= obj.getId()%>" form="form1"></td> 
    				<td><%= obj.getFirstname() + " " + obj.getSurname()%></td> 
    				<td><%= obj.getVisitorLevel() %></td> 
    				<td><%if (obj.getSuperior() != null) {%><%= obj.getSuperior().getSurname()%><%} else {%> --- <%}%></td> 
    				<td><%if (obj.getGroup() != null) {%><%= obj.getGroup().getName()%><%} else {%> --- <%}%></td> 
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
                            <select style="width:90%;" form="doctor_filter" id="geo_area_dd" onchange="updateCity(this)">
                                    <option>none</option>
                                    <%for (GeographicalArea obj : assignVisitorProc.getGeoAreaListObj().getGeoAreaList()) { %>
                                    <option value="<%= obj.getId()%>"><%=obj.getGeoName()%></option>
                                    <%}%>
                            </select>
                    </fieldset>
                    
                    <fieldset style="width:100%; float:left; margin-right: 3%;"> <!-- to make two field float next to one another, adjust values accordingly -->
                            <label>Select City</label>
                            <select style="width:90%;" form="doctor_filter" id="city_dd" onchange="updateInstitution(this)">
                                    
                                    <option>none</option>
                                    
                            </select>
                    </fieldset>
                    
                    <fieldset style="width:100%; float:left; margin-right: 3%;"> <!-- to make two field float next to one another, adjust values accordingly -->
                            <label>Select Institution</label>
                            <select style="width:90%;" form="doctor_filter" id="institution_dd">
                                    
                                    <option>none</option>
                            
                            </select>
                    </fieldset>
                    
                    <fieldset style="width:100%; float:left; margin-right: 3%;"> <!-- to make two field float next to one another, adjust values accordingly -->
                            <label>Select Specialty</label>
                            <select style="width:90%;" form="doctor_filter" id="specialty_dd">
                                    <%for (Specialty obj : assignVisitorProc.getSpecialtyListObj().getSpecialtyList()) { %>
                                    <option value="<%= obj.getId()%>"><%=obj.getSpecialtyName()%></option>
                                    <%}%>
                            </select>
                    </fieldset>
                    
                    <fieldset style="width:100%; float:left;"> <!-- to make two field float next to one another, adjust values accordingly -->
                            <label>Search</label>
                            <input type="text" style="width:85%;">
                    </fieldset><div class="clear"></div>
                </div>
            <footer>
                    <button type="button" class="alt_btn" onclick="updateDoctorList()"
                        style="color:orange;font-weight:bold">Refresh</button>
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
			<tbody id="doc_table"> 
                                <%for (Doctor obj : assignVisitorProc.getDoctorListObj().getDoctorList()) { %>
				<tr> 
                                    <td><input type="checkbox" name="doctorList" id="doctor_chbx" value="<%= obj.getId()%>" form="form1"></td> 
    				<td><%= obj.getName()%></td> 
    				<td><%if (obj.getAssignedVisitor() != null) {%><%= obj.getAssignedVisitor().getSurname()%><%} else {%> --- <%}%></td> 
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
                
                <form class="post_message" id="form1" method="post" action="AssignVisitor">
                    <input type="submit" class="alt_btn" value="Assign"/>
                </form>
		
            </article><!-- end of doctors article -->
            
            
           
        
        </section>
    <script type="text/javascript" src="js/currentlinkstyle.js"></script>   
    </body>
</html>
