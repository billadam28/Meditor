<%-- 
    Document   : admin
    Created on : Apr 21, 2016, 4:17:05 PM
    Author     : thodo
--%>

<%@page import="MeditorPersistence.Doctor"%>
<%@page import="MeditorPersistence.User"%>
<%@page import="MeditorPersistence.Visitor"%>
<%@page import="MeditorServlets.LoginSrvlt"%>
<%@page import="MeditorServlets.VisitInfoSrvlt"%>
<%@page import="MeditorJavaClasses.VisitServices"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <%@ include file="includes/vst_common_head.jsp" %>
    
    <body> 
        <%@ include file="includes/visitor_common.jsp" %>
        <section id="main" class="column">
        
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
                                <% VisitServices visitServices = (VisitServices) request.getAttribute("visitServices");%>
                                <%if (visitServices.doctorsList().isEmpty() == false) { 
                                for (Doctor obj : visitServices.doctorsList()) { %>
                                    <tr>                            
                                        <td><%= obj.getName()%></td> 
                                        <td><%=obj.getAssignedVisitor()%></td> 
                                        <td><%= obj.getSpecialty().getSpecialtyName()%></td> 
                                        <td><%= obj.getPosition()%></td>
                                        <td><%= obj.getInstitution().getInstitutionName()%></td>
                                        <td><%= obj.getInstitution().getCity().getGeoArea().getGeoName()%></td>
                                        <td><%= obj.getInstitution().getCity().getCityName()%></td>
                                        <td><input type="radio" name="doctorInfo" id="doctor_radio" value="<%= obj.getId()%>" form="doctor_form"></td>
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
                    
                        <form style="margin-right:20px; float:right;" class="post_message" id="doctor_form" name="doctor_form" method="post" action="EnterVisitInfo">
                            <%if (visitServices.doctorsList().isEmpty() == true) {%>
                                <input type="submit" style="background-color:grey; color: gray; border: 1px solid grey"  value="Next" disabled/>
                            <%} else {%>
                                <input type="submit" class="alt_btn" value="Next" onclick="return validateDoctorForm()"/>
                            <%}%>
                            <input type="reset" id="helpForDoctorInfo" onclick="help4();" class="alt_btn" value="Help"/>
                        </form>
                    </footer>    
            

            </article><!-- end of doctors article -->
        </section>
        <script type="text/javascript" src="js/validateform.js"></script>                    
        <script type="text/javascript" src="js/help.js"></script>
        <script type="text/javascript" src="js/currentlinkstyle.js"></script>    
    </body>
</html>