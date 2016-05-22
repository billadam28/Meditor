<%-- 
    Document   : addDoc
    Created on : May 5, 2016, 11:09:33 AM
    Author     : glalas
--%>
<%@page import="MeditorPersistence.Institution"%>
<%@page import="MeditorPersistence.City"%>
<%@page import="MeditorPersistence.GeographicalArea"%>
<%@page import="MeditorJavaClasses.addDocQ"%>
<%@page import="MeditorPersistence.Specialty"%>
<%@page import="MeditorServlets.addDoctSrvlt"%>
<%@page import="MeditorServlets.SubmitDocServlet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <title>Add A Doctor</title>
    <%@ include file="includes/vst_common_head.jsp" %>
     
    <body>
        <%@ include file="includes/visitor_common.jsp" %>
        <section id="main" class="column">
             <% if (request.getAttribute("revealCreateSuccessMsg") == "true") { %>
            <h4 class="alert_success"> Doctor was created successfully!</h4>
            <%}%>
            <% if (request.getAttribute("revealCreateErrorMsg") == "true") { %>
            <h4 class="alert_error"> Doctor Already Exists!</h4>
            <%}%>
            
        <article class="module width_full">
            <h1><center>Add New Doctor</center></h1>
        <form name="addform" action="SubmitDocServlet" method="get">
            <fieldset>
            <label>Doctor Name: </label>
            <input type="text" name="name" value="" onfocus="if(!this._haschanged){this.value=''};this._haschanged=true;">
            <br>
            </fieldset>
            
            <fieldset>
            <label>Address: </label>
            <input type="text" name="address" value="" onfocus="if(!this._haschanged){this.value=''};this._haschanged=true;">
            <br>
            </fieldset>
            
            <fieldset>
            <label>Phone: </label>
            <input type="text" name="phone" value="" onfocus="if(!this._haschanged){this.value=''};this._haschanged=true;">
            <br>
            </fieldset>
            
            <fieldset>
             
            <label>Specialty: </label>
            <select style="width:90%;" name ="specialty">
                <option>Select A Specialty</option>
                <% addDocQ spec = (addDocQ) request.getAttribute("Specialty");%>
                <%for (Specialty obj : spec.getSpecialtyList()) { %>
                <option value="<%=obj.getId()%>"><%=obj.getSpecialtyName()%></option>
                <%}%>
            </select>
            <br>
            </fieldset>
            
            <fieldset>
            <label>Institution: </label>
            <select style="width:90%;" name="institution">
                <option>Select Institution</option>
            <% addDocQ inst =(addDocQ) request.getAttribute("Institution");%>
                <% for (Institution obj: inst.getInstituteList()){ %>
                <option value="<%= obj.getId() %>"><%=obj.getInstitutionName()%></option>
                <%}%>
	    </select>
            <br>
            </fieldset>

            <fieldset>
            <label>Position: </label>
            <input type="text" name="position" value="" onfocus="if(!this._haschanged){this.value=''};this._haschanged=true;">
            <br>
            </fieldset>
            
            
            
            
    
         
            <input type="submit" name="submit" value="Submit">

        
        
        </form>
        
           
            
    <script type="text/javascript" src="js/currentlinkstyle.js"></script>  

        </article>
        </section>>
</html>
    
