<%-- 
    Document   : admin
    Created on : Apr 21, 2016, 4:17:05 PM
    Author     : thodo
--%>

<%@page import="java.util.Set"%>
<%@page import="MeditorPersistence.Cycle"%>
<%@page import="MeditorPersistence.Visit"%>
<%@page import="MeditorPersistence.Doctor"%>
<%@page import="MeditorPersistence.User"%>
<%@page import="MeditorPersistence.Visitor"%>
<%@page import="MeditorServlets.LoginSrvlt"%>
<%@page import="MeditorServlets.VisitInfoSrvlt"%>
<%@page import="MeditorServlets.ShowVisitSrvlt"%>
<%@page import="MeditorServlets.UpdateVisitSrvlt"%>
<%@page import="MeditorServlets.ShowVisitsPerCycleSrvlt"%>
<%@page import="MeditorServlets.EditVisitSrvlt"%>
<%@page import="MeditorJavaClasses.VisitServices"%>
<%@page import="MeditorJavaClasses.CycleServices"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <%@ include file="includes/vst_common_head.jsp" %>
    
    <body> 
        <%@ include file="includes/visitor_common.jsp" %>
        <section id="main" class="column">
            <% VisitServices visitServices = (VisitServices) request.getAttribute("visitServices");%>
            <% CycleServices cycleServices = (CycleServices) request.getAttribute("cycleServices");%>
            
            <article class="module width_quarter" style="width: 22%; margin-left: 10px;">
            <header><h3>Filter for Visitor's Visits</h3></header>
                <div class="module_content">
                           
                    <fieldset style="width:100%;"> 
                            <label style="width:100%;">Select cycle period</label>
                            <select name="period" id="period" form="search_cycle_form" style="width:70%;">
                                    <option value="0">All periods </option>
                                    <%for (Cycle obj : cycleServices.cyclesList()) { %>
                                        <option value="<%=obj.getId()%>"><%= obj.getCycle()%></option>
                                    <%}%>
                            </select>
                    </fieldset>
                    
                </div>
                <footer>
                    <div class="submit_link">
                        <form id="search_cycle_form" name="search_cycle_form" method="post" action="ShowVisitsPerCycle" class="alt_btn" >
                            <input type="submit" class="alt_btn" value="Search"/>
                            <input type="button" id="helpSearch" onclick="help5();" class="alt_btn" value="Help"/>
                        </form>

                    </div>
                </footer>
            </article> <!-- end of visitor filters article -->
            
            <article class="module width_3_quarter" style="width: 74.5%; margin-left: 10px;">
                <header><h3>Visitor's Visits filtered by:<%= request.getAttribute("period")%></h3></header>

                <div class="tab_container">
                    <div id="tab1" class="tab_content">
                        <table class="tablesorter" cellspacing="0"> 
                        <thead> 
                            <tr>
                                <th>Visit Offset</th>
                                <th>Doc's Name</th> 
                                <th>Doc's Address</th>
                                <th>Accomp. by Trainee</th>
                                <th>Status</th> 
                                <th>Date</th>
                                <th>Cycle</th>
                                <th>Extra Visit</th>
                                <th style="word-break:break-all; word-wrap:break-word; max-width:100px; ">Comments</th>
                            </tr> 
                        </thead> 
                        <tbody> 
                            <%if (cycleServices.getVisitorVisits().isEmpty() == false) { 
                                for (Visit obj : cycleServices.getVisitorVisits()) { %>
                                    <tr>
                                        <td><%=obj.getVisitOffset()%></td>
                                        <td><%= obj.getDoctor().getName()%></td> 
                                        <td><%= obj.getDoctor().getAddress()%></td> 
                                        <td>
                                            <%for (Visitor vst : (Set<Visitor>) obj.getVisitors()) {
                                                if (vst.getVisitorLevel().equals("trainee")) {
                                                    out.println(vst.getFirstname()+" "+vst.getSurname()+"\n");
                                                }
                                            }%>
                                        </td>
                                        <td><%= obj.getStatus()%></td> 
                                        <td><%= obj.getDate()%></td>
                                        <td><%= obj.getCycle().getCycle()%></td>
                                        <td><%if(obj.getExtraVisit()==true) {%> 
                                            <input type="checkbox" name="extraVisit" id="extra_visit_yes" value="<%= obj.getExtraVisit()%>" checked="checked" disabled><%} else {%>
                                            <input type="checkbox" name="extraVisit" id="extra_visit_no" value="<%= obj.getExtraVisit()%>" disabled><%}%></td>
                                        <td style="word-break:break-all; word-wrap:break-word; max-width:100px; "><%if (obj.getComments()==null || obj.getComments().equals("null")) {%> No comments <%} else {%><%= obj.getComments()%><%}%></td>
                                        
                                <%}%>
                            <%} else {%>
                                        <td><p style="color:red; font-weight: bold">There ara no visits for this period!</p></td>
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

            </article><!-- end of doctors article -->
            
        </section>
        <script type="text/javascript" src="js/validateform.js"></script>                    
        <script type="text/javascript" src="js/help.js"></script>
        <script type="text/javascript" src="js/currentlinkstyle.js"></script>    
    </body>
</html>