<%-- 
    Document   : admin
    Created on : Apr 21, 2016, 4:17:05 PM
    Author     : thodo
--%>

<%@page import="MeditorPersistence.User"%>
<%@page import="MeditorServlets.LoginSrvlt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <%@ include file="includes/adm_common_head.jsp" %>
    
    <body> 
        <%@ include file="includes/admin_common.jsp" %>
        
        <section id="main" class="column">
            
            <% if (request.getAttribute("revealSuccessMsg") == "true") { %>
                <h4 class="alert_success">Group "<%=session.getAttribute("nameOfGroup")%>" was created successfully!</h4>
            <%}%>
            
            <% if (request.getAttribute("revealErrorMsg") == "true") { %>
            <h4 class="alert_error">The name "<%=session.getAttribute("nameOfGroup")%>" has been assigned to another group. Please give another name.</h4>
            <%}%>
            
            <article class="module width_full">
                <header><h3>Create New Group</h3></header>
                    <div class="module_content">
			<fieldset style="width:67%; display: inline-block;">
                            <label>Group Name<sup>*</sup></label>
                                <input style="width:93%;" type="text" id="name" form="create_group_form" name="nameOfGroup" placeholder="Give the name of the group (up to 50 characters)" maxlength="50">
			</fieldset>
                        <fieldset style="width:30%; float: right; height: 49px;"> <!-- to make two field float next to one another, adjust values accordingly -->
                            <label>Select Parent Group</label>
                            <select style="width:90%;">
                                    <option>Articles</option>
                                    <option>Tutorials</option>
                                    <option>Freebies</option>
                            </select>
			</fieldset>
			<fieldset style="width:100%;">
				<label>Description<sup>*</sup></label>
				<textarea style="width:95.5%;" rows="5" id="desc" form="create_group_form" name="descOfGroup" placeholder="Give a short description for the group (up to 250 characters)" maxlength="250"></textarea>
			</fieldset>
                    </div>
		<footer>
                    <div class="submit_link">
                        <form id="create_group_form" name="create_group_form" method="post" action="GroupProcessor" class="alt_btn" >
                            <input type="submit" class="alt_btn" value="Create" onclick="return validateForm()"/>
                            <input type="reset" class="alt_btn" value="Reset"/>
                        </form>
                        
                    </div>
                    <p style="margin-left: 20px;"><sup>*</sup> Required Fields</p>
		</footer> 
           </article><!-- end of post new article -->
           <input type="hidden" id="msg" value="All fields are required!">
           <p id="msg2" style="color:red; margin-left: 30px; font-weight: bold"></p>
        </section>  
        <script type="text/javascript" src="js/validateform.js"></script>
        <script type="text/javascript" src="js/currentlinkstyle.js"></script> 
    </body>
</html>
