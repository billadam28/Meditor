<%-- 
    Document   : admin
    Created on : Apr 21, 2016, 4:17:05 PM
    Author     : thodo
--%>

<%@page import="MeditorPersistence.Visit"%>
<%@page import="MeditorPersistence.Doctor"%>
<%@page import="MeditorPersistence.User"%>
<%@page import="MeditorPersistence.Visitor"%>
<%@page import="MeditorServlets.LoginSrvlt"%>
<%@page import="MeditorServlets.VisitInfoSrvlt"%>
<%@page import="MeditorServlets.ShowVisitSrvlt"%>
<%@page import="MeditorServlets.UpdateVisitSrvlt"%>
<%@page import="MeditorServlets.EditVisitSrvlt"%>
<%@page import="MeditorJavaClasses.VisitServices"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <%@ include file="includes/vst_common_head.jsp" %>
    
    <body> 
        <%@ include file="includes/visitor_common.jsp" %>
        <section id="main" class="column">
            <% VisitServices visitServices = (VisitServices) request.getAttribute("visitServices");%>
            <% if (request.getAttribute("revealForm1") != "true" && request.getAttribute("revealForm2") != "true" && request.getAttribute("revealForm0") == "true") { %>
            <article class="module width_3_quarter" style="float:none; margin:auto; margin-top: 20px;">
                    <header><h3>Select a Doctor</h3></header>

                    <div class="tab_container">
                        <div id="tab1" class="tab_content">
                            <table class="tablesorter" cellspacing="0"> 
                            <thead> 
                                <tr>  
                                    <th>Name</th> 
                                    <th>Assigned_Visitor</th> 
                                    <th>Specialty</th> 
                                    <th>Position</th>
                                    <th>Institution</th> 
                                    <th>Geo_Area</th> 
                                    <th>City</th>
                                    <th></th>
                                </tr> 
                            </thead> 
                            <tbody> 
                                
                                <%if (visitServices.doctorsList().isEmpty() == false) { 
                                for (Doctor obj : visitServices.doctorsList()) { %>
                                    <tr>                            
                                        <td><%= obj.getName()%></td> 
                                        <td><%= obj.getAssignedVisitor().getSurname() + " " + obj.getAssignedVisitor().getFirstname()%></td> 
                                        <td><%= obj.getSpecialty().getSpecialtyName()%></td> 
                                        <td><%= obj.getPosition()%></td>
                                        <td><%= obj.getInstitution().getInstitutionName()%></td>
                                        <td><%= obj.getInstitution().getCity().getGeoArea().getGeoName()%></td>
                                        <td><%= obj.getInstitution().getCity().getCityName()%></td>
                                        <td><input type="radio" name="selectDoc" id="doctor_radio" value="<%= obj.getId()%>" form="selectDoc_form"></td>
                                <%}%>
                            <%} else {%>
                                        <td><p style="color:red; font-weight: bold">There ara no Doctors assigned!</p></td>
                                        <td></td>
                                        <td></td>
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

                    <footer>
                    
                        <form style="margin-right:20px; float:right;" class="post_message" id="selectDoc_form" name="selectDoc_form" method="post" action="ShowVisit">
                            <%if (visitServices.doctorsList().isEmpty() == true) {%>
                                <input type="submit" style="background-color:grey; color: gray; border: 1px solid grey"  value="Next" disabled/>
                            <%} else {%>
                                <input type="submit" class="alt_btn" value="Next" onclick="return validateDoctorForm()"/>
                            <%}%>
                            <input type="button" id="helpForDoctorInfo" onclick="help4();" class="alt_btn" value="Help"/>
                        </form>
                    </footer>    
            

            </article><!-- end of doctors article -->
            <%}%>
            <% if (request.getAttribute("revealForm1") == "true" && request.getAttribute("revealForm2") != "true" && request.getAttribute("revealForm0") != "true") { %>
            
            <article class="module width_full" style="float:none; margin:auto; margin-top: 20px;">
                    <header><h3>Select a Visit</h3></header>

                    <div class="tab_container">
                        <div id="tab1" class="tab_content">
                            <table class="tablesorter" cellspacing="0"> 
                            <thead> 
                                <tr>  
                                    <th>Visitor Id</th> 
                                    <th>Doctor Id</th> 
                                    <th>Status</th> 
                                    <th>Date</th>
                                    <th>Cycle</th>
                                    <th>Extra Visit</th>
                                    <th>Comments</th> 
                                    <th></th>
                                </tr> 
                            </thead> 
                            <tbody> 
                                
                                <%if (visitServices.visitsList().isEmpty() == false) { 
                                for (Visit obj : visitServices.visitsList()) { %>
                                    <tr>                            
                                        <td><%=request.getAttribute("vId")%></td> 
                                        <td><%= obj.getDoctor().getId()%></td> 
                                        <td><%= obj.getStatus()%></td> 
                                        <td><%if (obj.getDate()==null) {%> --- <%} else {%><%= obj.getDate()%><%}%></td>
                                        <td><%= obj.getCycle().getCycle()%></td>
                                        <td><%if(obj.getExtraVisit()==true) {%> 
                                            <input type="checkbox" name="extraVisit" id="extra_visit_yes" value="<%= obj.getExtraVisit()%>" form="selectVisit_form" checked="checked" disabled><%} else {%>
                                            <input type="checkbox" name="extraVisit" id="extra_visit_no" value="<%= obj.getExtraVisit()%>" form="selectVisit_form" disabled><%}%></td>
                                        <td style="word-break:break-all; word-wrap:break-word; max-width:100px; "><%if (obj.getComments()==null || obj.getComments().equals("null")) {%> No comments <%} else {%><%= obj.getComments()%><%}%></td>
                                        <td><input type="radio" name="selectVisit" id="visit_radio" value="<%= obj.getId()%>" form="selectVisit_form"></td>
                                <%}%>
                                <%} else {%>
                                        <td><p style="color:red; font-weight: bold">There ara no visits for this doctor yet!</p></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
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

                    <footer>
                    
                        <form style="margin-right:20px; float:right;" class="post_message" id="selectVisit_form" name="selectVisit_form" method="post" action="EditVisit">
                            <%if (visitServices.visitsList().isEmpty() == true) {%>
                                <input type="submit" style="background-color:grey; color: gray; border: 1px solid grey"  value="Edit" disabled/>
                            <%} else {%>
                                <input type="submit" class="alt_btn" value="Edit" onclick="return validateVisitForm()"/>
                            <%}%>
                            <input type="button" id="helpForVisitInfo" onclick="help4();" class="alt_btn" value="Help"/>
                        </form>
                    </footer>    
            

            </article><!-- end of doctors article -->
            <%}%>
            
            <% if (request.getAttribute("revealForm2") == "true" && request.getAttribute("revealForm1") != "true" && request.getAttribute("revealForm0") != "true") { %>
            
                <% Visit obj = visitServices.getVisit(); %>
                
                <article class="module width_3_quarter" style="float:none; margin:auto; margin-top: 20px;">
                    <header><h3>Edit Visit with ID: #<%= obj.getId()%></h3></header>
                    <input type="hidden" name="visit" value="<%= obj.getId()%>" form="update_visit_form">
                        <div class="module_content">
                            <fieldset style="width:20%; display: inline-block;">
                                <label style="width:80%; text-align: center;">Visitor Id</label>
                                <input style="width:60%; margin-left:18px;" type="text" id="vID" value="<%= request.getAttribute("vId")%>" form="update_visit_form" name="vID" disabled>
                            </fieldset>
                            <fieldset style="width:20%; display: inline-block; margin-left: 33px;">
                                <label style="width:80%; text-align: center;">Doctor Id</label>
                                <input style="width:60%; margin-left:18px;" type="text" id="dID" value="<%= obj.getDoctor().getId()%>" form="update_visit_form" name="dID" disabled>
                            </fieldset>
                            <fieldset style="width:20%; display: inline-block; margin-left: 33px;">
                                <label style="width:80%; text-align: center;">Date</label>
                                <input style="width:60%; margin-left:18px;" type="text" name="date" id="date" value="<%= obj.getDate()%>" form="update_visit_form">
                            </fieldset>
                            <fieldset style="width:10%; display: inline-block; margin-left: 33px; margin-top: 10px; position: absolute; height: 36px;"> 
                                <label style="width:85%; text-align: center; margin-top: -8px;">Change Status</label>
                                <select name="status" id="status" form="update_visit_form" style="width:84%;">
                                        <option value="pending">Pending</option>
                                        <option value="completed">Completed</option>
                                        <option value="unsuccessful">Unsuccessful</option>
                                </select>
                            </fieldset>
                            
                            
                            <fieldset style="width:30%; float:right;">
                                <label style="width:90%; text-align: center;">Cycle</label>
                                <input style="width:70%; margin-left:20px; text-align: center;" type="text" name="cycle" id="cycle" value="<%= obj.getCycle().getCycle()%>" form="update_visit_form" disabled>
                            </fieldset>
                            
                            
                            
                            <fieldset style="width:17%; margin-right: 33px; float:right;">
                                <label style="width:80%; text-align: center; ">Extra Visit</label>
                                <%if(obj.getExtraVisit()==true) {%> 
                                            <input style="width:60%; margin-left:18px;" type="checkbox" name="extra1" id="extrayes" value="<%= obj.getExtraVisit()%>" form="update_visit_form" checked="checked"><%} else {%>
                                            <input style="width:60%; margin-left:18px;" type="checkbox" name="extra1" id="extrano" value="<%= obj.getExtraVisit()%>" form="update_visit_form"><%}%>
                            </fieldset>
                            
                            <fieldset style="width:40%;">
				<label style="width:90%; text-align: center;">Comments</label>
				<textarea style="width:90%;" rows="5" id="comments" form="update_visit_form" name="comments" maxlength="250"><%= obj.getComments()%></textarea>
                            </fieldset>
                            <fieldset style="width: 40%;"> 
                                <label>Accompanied by trainee</label>
                                <select name="trainee" id="trainee" form="update_visit_form" style="width:84%;">
                                        <option value="0">No trainee</option>
                                        <%for (Visitor trainee : visitServices.traineesList()) { %>
                                            <option value="<%= trainee.getId()%>"><%=trainee.getFirstname()+ " " + trainee.getSurname()%></option>
                                        <%}%>
                                </select>
                            </fieldset>
                        </div>
                    <footer>
                        <div class="submit_link">
                            <form id="update_visit_form" name="update_visit_form" method="post" action="UpdateVisit" class="alt_btn" >
                                <input type="submit" class="alt_btn" value="Update" onclick="return isValidDate()"/>
                                <input type="reset" class="alt_btn" value="Reset"/>
                                <input type="button" id="helpForCreation" onclick="help4();" class="alt_btn" value="Help"/>
                            </form>

                        </div>
                    </footer> 
               </article><!-- end of post new article -->
            <%}%>
            
            <% if (request.getAttribute("revealForm3") == "true") { %> 
                <% if (request.getAttribute("revealSuccessMsg") == "true") { %>
                    <h4 class="alert_success">Visit #<%= request.getAttribute("visitId")%> updated successfully!</h4>
                <%}%>
            <%}%>
        </section>
        <script type="text/javascript" src="js/validateform.js"></script>                    
        <script type="text/javascript" src="js/help.js"></script>
        <script type="text/javascript" src="js/currentlinkstyle.js"></script>    
    </body>
</html>