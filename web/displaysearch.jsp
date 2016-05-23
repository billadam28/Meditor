<%-- 
    Document   : displaysearch
    Created on : May 15, 2016, 7:15:16 PM
    Author     : glalas
--%>
<%@page import="MeditorJavaClasses.AddDoctorLists"%>
<%@page import="MeditorPersistence.Institution"%>
<%@page import="MeditorPersistence.Specialty"%>
<%@page import="MeditorServlets.addDoctSrvlt"%>
<%@page import="MeditorServlets.CommitEditDocSrvlt"%>
<%@page import="MeditorPersistence.Doctor"%>
<%@page import="MeditorJavaClasses.SearchEditDoc"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
  <%@ include file="includes/vst_common_head.jsp" %>
     
        
        <%@ include file="includes/visitor_common.jsp" %>
        
                
                
                <article class="module width_3_quarter">
		<header><h3 class="tabs_involved">Search Results</h3>
		<ul class="tabs">
   			<li><a href="#tab1">View</a></li>
                        <li><a href="#tab2">Edit</a></li>
    		
		</ul>
		</header>

		<div class="tab_container">
			<div id="tab1" class="tab_content">
			<table class="tablesorter" cellspacing="0" > 
			<thead> 
				<tr> 
    				<th>Name</th> 
    				<th>Specialty</th> 
    				<th>Address</th> 
    				<th>Phone</th> 
                                <th>Position</th>
                                <th>Institution</th>
                                <th>City</th>
                                <th>Geographical Area</th>
				</tr> 
			</thead> 
			<tbody> 
                                <% SearchEditDoc doc = (SearchEditDoc) request.getAttribute("Doctor");%>
                                <%for (Doctor obj : doc.getSearchResults()) { %>
                                <tr>
                                    <td><%= obj.getName() %></td> 
                                    <td><%= obj.getSpecialty().getSpecialtyName() %></td> 
                                    <td><%= obj.getAddress() %></td>
                                    <td><%= obj.getPhone() %></td>
                                    <td><%= obj.getPosition() %></td>
                                    <td><%= obj.getInstitution().getInstitutionName() %></td>
                                    <td><%= obj.getInstitution().getCity().getCityName() %></td>
                                    <td><%= obj.getInstitution().getCity().getGeoArea().getGeoName() %></td>
                                        </tr> 
                                        <%}%>
    				
			</tbody> 
			</table>
			</div><!-- end of #tab1 -->
			
                        
                        
			<div id="tab2" class="tab_content">
                        <form action="CommitEditDocSrvlt" method="get">
			<table class="tablesorter" cellspacing="0" > 
			<thead> 
                        Edit Doctor
			</thead> 
			<tbody> 
                                
                                <%for (Doctor obj : doc.getSearchResults()) { %>
                                <tr>
                                    
                                    <fieldset style="width:100%; display: inline-block;">
                                    <label style="width:100%; text-align: center;"> Doctor ID</label>
                                    <input type="text" name="docId" value="<%= obj.getId()%>" >
                                    </fieldset> 
                                    
                                    <fieldset style="width:100%; display: inline-block;">
                                    <label style="width:100%; text-align: center;">Doctor Name</label>
                                    
                                    <input style="width:80%;" type="text" name="docname" value="<%= obj.getName()%>" >
                                    </fieldset> 
                                    
                                    <fieldset style="width:100%; display: inline-block;">
                                    <label style="width:100%; text-align: center;">Specialty</label>
                                    <select style="width:80%;" name ="specialty" >
                                        <option value="<%=obj.getSpecialty().getId()%>"><%= obj.getSpecialty().getSpecialtyName() %></option>
                                        <% AddDoctorLists spec = (AddDoctorLists) request.getAttribute("Specialty");%>
                                        <%for (Specialty objs : spec.getSpecialtyList()) { %>
                                        <option value="<%=objs.getId()%>"><%=objs.getSpecialtyName()%></option>
                                        <%}%>
                                    </select>
                                    </fieldset>
                                    
                                    <fieldset style="width:100%; display: inline-block;">
                                    <label style="width:100%; text-align: center;">Address</label>
                                    <input style="width:80%;" type="text" name="docaddress" value="<%= obj.getAddress()%>">
                                    </fieldset> 
                                    
                                    <fieldset style="width:100%; display: inline-block;">
                                    <label style="width:100%; text-align: center;">Phone</label>
                                    <input style="width:80%;" type="text" name="docphone" value="<%= obj.getPhone()%>">
                                    </fieldset> 
                                    
                                    
                                    <fieldset style="width:100%; display: inline-block;">
                                    <label style="width:100%; text-align: center;">Position</label>
                                    <input style="width:80%;" type="text" name="docposition" value="<%= obj.getPosition()%>">
                                    </fieldset> 
                                    
                                    <fieldset style="width:100%; display: inline-block;">
                                    <label style="width:100%; text-align: center;">Specialty</label>
                                    <select style="width:80%;" name ="institution" >
                                        <option value="<%=obj.getInstitution().getId()%>"><%= obj.getInstitution().getInstitutionName() %></option>
                                        <% AddDoctorLists inst = (AddDoctorLists) request.getAttribute("Institution");%>
                                        <%for (Institution objs : inst.getInstituteList()) { %>
                                        <option value="<%=objs.getId()%>"><%=objs.getInstitutionName()%></option>
                                        <%}%>
                                    </select>
                                    </fieldset>
                                    
                                    <input type="submit" name="submit" value="Submit">
                                    <%}%>
                                </tr> 
                                       
                        	
                                 
			</tbody> 
			</table>
                        </form>
			</div><!-- end of #tab2 -->
                        
                        
			
		</div><!-- end of .tab_container -->
		
		</article><!-- end of content manager article -->
                
                
                
                
                
<script type="text/javascript" src="js/currentlinkstyle.js"></script>
