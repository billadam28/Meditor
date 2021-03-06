<%@page import="MeditorJavaClasses.CreateVisitsProcessor"%>
<%@page import="MeditorPersistence.Specialty"%>
<%@page import="MeditorPersistence.Institution"%>
<%@page import="MeditorPersistence.City"%>
<%@page import="MeditorPersistence.GeographicalArea"%>
<%@page import="MeditorPersistence.Doctor"%>
<%@page import="MeditorPersistence.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <%@ include file="includes/adm_common_head.jsp" %>
    
    <body> 
        <%@ include file="includes/admin_common.jsp" %>
        
        <section id="main" class="column">
            
            <% if (request.getAttribute("revealSuccesMsg") == "true") { %>
                <h4 class="alert_success">New visits created successfully</h4>
            <%}%> 
            
            <%CreateVisitsProcessor createVisitsProc = (CreateVisitsProcessor) request.getAttribute("createVisits");%>
            
            <article class="module width_quarter">
            <header><h3>Filters for Doctor List</h3></header>
                <div class="module_content">
                           
                    <fieldset style="width:100%; float:left; margin-right: 3%;"> <!-- to make two field float next to one another, adjust values accordingly -->
                            <label>Select Geo Area</label>
                            <select style="width:90%;" form="doctor_filter" id="geo_area_dd" onchange="updateCity(this)">
                                    <option>none</option>
                                    <%for (GeographicalArea obj : createVisitsProc.getGeoAreaListObj().getGeoAreaList()) { %>
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
                                    <%for (Specialty obj : createVisitsProc.getSpecialtyListObj().getSpecialtyList()) { %>
                                    <option value="<%= obj.getId()%>"><%=obj.getSpecialtyName()%></option>
                                    <%}%>
                            </select>
                    </fieldset>
                    <div class="clear"></div>

                </div>
            <footer>
                <button type="button" class="alt_btn" onclick="updateDoctorList()"
                        style="color:orange;font-weight:bold">Refresh</button>
            </footer>
            </article> <!-- end of visitor filters article -->
              
            <article class="module width_3_quarter">
                <header><h3 class="tabs_involved">Select Doctors to create new visits</h3>
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
                        <tbody><tr><td><input type="checkbox" name="selectall" onclick="toggle(this)"></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr></tbody>
			<tbody id="doc_table"> 
                            
                                <%for (Doctor obj : createVisitsProc.getDoctorListObj().getDoctorList()) { %>
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
                
                <form class="post_message" id="form1" method="post" action="CreateVisits">
                    <input type="submit" class="alt_btn" value="Create Visits"/>
                </form>
		
            </article><!-- end of doctors article -->
            
            
           
        
        </section>
    <script type="text/javascript" src="js/currentlinkstyle.js"></script>   
    </body>
</html>
