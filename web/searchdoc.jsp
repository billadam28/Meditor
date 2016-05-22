<%-- 
    Document   : searchdoc
    Created on : May 15, 2016, 4:49:19 PM
    Author     : glalas
--%>

<%@page import="MeditorPersistence.Institution"%>
<%@page import="MeditorPersistence.Visitor"%>
<%@page import="MeditorJavaClasses.VisitorDAO"%>
<%@page import="MeditorPersistence.Specialty"%>
<%@page import="MeditorJavaClasses.AddDoctorLists"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
 <%@ include file="includes/vst_common_head.jsp" %>
 <%@ include file="includes/visitor_common.jsp" %>
 <html>
     <title>Search For A Doctor</title>
<article class="module width_3_quarter">
    <header><h3 class="tabs_involved"><center>Search for A Doctor</center></h3>
		<ul class="tabs">
   			<li><a href="#tab1">By Name</a></li>
                        <li><a href="#tab2">By Specialty</a></li>
                        <li><a href="#tab3">By Visitor</a></li>
                        <li><a href="#tab4">By Institution</a></li>
		</ul>
		</header>
    <form name="searchform" action="SearchEditSrvlt" method="get">
    <div class="tab_container" style="height: 110px;">
	<div id="tab1" class="tab_content">
            <table class="tablesorter" cellspacing="0">   
                <fieldset style="width: 50%; margin-left:10px;">
            <label>Search by Name: </label>
            <input style="width: 80%;" type="text" name="name" value="" onfocus="if(!this._haschanged){this.value=''};this._haschanged=true;">
            <br>
            </fieldset>
                <input type="submit" name="submit" style="margin-left:9px;" value="Search"/>
            </table>

        </div>
         <div id="tab2" class="tab_content">
            <table class="tablesorter" cellspacing="0">    
            
            <fieldset style="width: 50%; margin-left:10px;">
            <label>Search by Specialty: </label>
            <select style="width:90%;" name ="specialty">
                <option>Select A Specialty</option>
                <% AddDoctorLists spec = (AddDoctorLists) request.getAttribute("Specialty");%>
                <%for (Specialty obj : spec.getSpecialtyList()) { %>
                <option value="<%=obj.getId()%>"><%=obj.getSpecialtyName()%></option>
                <%}%>
            </select>
            <br>
            </fieldset>
            <input type="submit" name="submit" style="margin-left:9px;" value="Search"/>
            </table>
         </div>
            
            <div id="tab3" class="tab_content">
            <table class="tablesorter" cellspacing="0"> 
            
            <fieldset style="width: 50%; margin-left:10px;">
                <label>Search By Visitor</label>
            <select style="width:90%;" name ="visitor">
                <option>Select A Visitor</option>
                <% VisitorDAO vst = (VisitorDAO) request.getAttribute("Visitor");%>
                <%for (Visitor obj : vst.getAllVisitors()) { %>
                <option value="<%=obj.getId()%>"><%=obj.getFirstname()%> <%=obj.getSurname()%></option>
                <%}%>
            </select>
            <br>
            </fieldset>
            <input type="submit" name="submit" style="margin-left:9px;" value="Search"/>
            </table>
            </div>
            
           <div id="tab4" class="tab_content">
            <table class="tablesorter" cellspacing="0"> 
            
            <fieldset style="width: 50%; margin-left:10px;">
            <label>Institution: </label>
            <select style="width:90%;" name="institution">
                <option>Select Institution</option>
            <% AddDoctorLists inst =(AddDoctorLists) request.getAttribute("Institution");%>
                <% for (Institution obj: inst.getInstituteList()){ %>
                <option value="<%= obj.getId() %>"><%=obj.getInstitutionName()%></option>
                <%}%>
	    </select>
            <br>
            </fieldset>
            <input type="submit" name="submit" style="margin-left:9px;" value="Search"/>
            </table>
           </div>
            
            
        </form>
        <script type="text/javascript" src="js/currentlinkstyle.js"></script> 
        </div>
    </article>
 </html>