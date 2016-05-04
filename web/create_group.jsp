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
                <h4 class="alert_success">Group created successfully!</h4>
            <%}%>
            
            <% if (request.getAttribute("revealErrorMsg") == "true") { %>
                <h4 class="alert_error">Group cannot be created This name has been assigned to another group.</h4>
            <%}%>
            
            <article class="module width_full">
                <header><h3>Create New Group</h3></header>
                    <div class="module_content">
                        <form id="create_group_form" method="post" action="GroupProcessor" class="alt_btn">
			<fieldset>
                        	<label>Group Name</label>
                                <input type="text" placeholder="Give the name of the group (up to 50 characters)" maxlength="50">
			</fieldset>
			<fieldset>
				<label>Description</label>
				<textarea rows="12" placeholder="Give a short description for the group (up to 250 characters)" maxlength="250"></textarea>
			</fieldset>
                    </div>
		<footer>
                    <div class="submit_link">
                            <input type="submit" class="alt_btn" value="Create"/>
                            <input type="reset" class="alt_btn" value="Reset"/>
                    </div>
		</footer>
            </article><!-- end of post new article -->
        </section>        
    <script type="text/javascript" src="js/currentlinkstyle.js"></script>    
    </body>
</html>
