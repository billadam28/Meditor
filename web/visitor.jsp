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

    <%if (request.getAttribute("user") == null){%>
                <jsp:forward page="/index.jsp" >
                    <jsp:param name="nullUser" value="1" />
                </jsp:forward>
    <%}%>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard | Medical Visitor Panel</title>
        <link rel="stylesheet" href="css/admin.css"  />
        <!--[if lt IE 9]>
	<link rel="stylesheet" href="css/admin_ie.css" type="text/css" media="screen" />
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
        
    <%@ include file="includes/header_js.jsp" %>
    
    </head>
    
    <body> 
        <header id="header">
		<hgroup>
			<h1 class="site_title">Meditor Medical Visitor</h1>
			<h2 class="section_title">Dashboard</h2><div class="btn_view_site"></div>
		</hgroup>
	</header> <!-- end of header bar -->
	
	<section id="secondary_bar">
		<div class="user">
                        <%User user = (User) request.getAttribute("user");%>
			<p>Welcome <%= user.getFirstname() + " " + user.getSurname()%></p>
			<!-- <a class="logout_user" href="#" title="Logout">Logout</a> -->
		</div>
		<div class="breadcrumbs_container">
			<!--<article class="breadcrumbs"><a href="admin.jsp">Meditor Medical Visitor</a> <div class="breadcrumb_                         divider"></div> <a class="current">Dashboard</a></article> -->
		</div>
	</section><!-- end of secondary bar -->
        
        <aside id="sidebar" class="column">
		<form class="quick_search">
			<input type="text" placeholder="Search" onfocus="if(!this._haschanged){this.value=''};
                            this._haschanged=true;">
		</form>
		<hr/>
		<h3>Visits</h3>
		<ul class="toggle">
			<li class="icn_edit_article"><a href="#">Enter info</a></li>
			<li class="icn_alert_info"><a href="#">View info</a></li>
		</ul>
		<h3>Doctors</h3>
		<ul class="toggle">
			<li class="icn_search"><a href="#">Search</a></li>
			<li class="icn_add_user"><a href="#">Create</a></li>
			<li class="icn_edit"><a href="#">Edit</a></li>
		</ul>
		<h3>Medical Visitor</h3>
		<ul class="toggle">
			<li class="icn_settings"><a href="#">Options</a></li>
			<li class="icn_jump_back"><a href="Logout">Logout</a></li>
		</ul>
		
		<footer>
			<hr />
			<p><strong>&copy;Meditor</strong></p>
			<p>Theme by <a href="http://www.medialoot.com">MediaLoot</a></p>
		</footer>
	</aside><!-- end of sidebar -->
        
        <section id="main" class="column">
				
		<div class="spacer"></div>
	</section>
        
    </body>
</html>