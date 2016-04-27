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
        <meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
        <title>Dashboard | Admin Panel</title>
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
			<h1 class="site_title">Meditor Admin</h1>
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
			<!--<article class="breadcrumbs"><a href="admin.jsp">Meditor Admin</a> <div class="breadcrumb_                         divider"></div> <a class="current">Dashboard</a></article> -->
		</div>
	</section><!-- end of secondary bar -->
        
        <aside id="sidebar" class="column">
		<form class="quick_search">
			<input type="text" placeholder="Search" onfocus="if(!this._haschanged){this.value=''};this._haschanged=true;">
		</form>
		<hr/>
		<h3>General Tasks</h3>
		<ul class="toggle">
			<li class="icn_new_article"><a href="#">Create New Cycle of Schedules</a></li>
			<li class="icn_logout"><a href="#">Delete a Doctor</a></li>
			<li class="icn_logout"><a href="#">Delete visit</a></li>
		</ul>
		<h3>Medical Visitors</h3>
		<ul class="toggle">
			<li class="icn_add_user"><a href="#">Create new</a></li>
			<li class="icn_edit"><a href="#">Edit</a></li>
			<li class="icn_logout"><a href="#">Delete</a></li>
                        <li class="icn_tags"><a href="#">Set visitor as group leader</a></li>
		</ul>
                <h3>Groups</h3>
		<ul class="toggle">
			<li class="icn_new_article"><a href="#">Create new</a></li>
			<li class="icn_edit_article"><a href="#">Edit</a></li>
			<li class="icn_logout"><a href="#">Delete</a></li>
                        <li class="icn_categories"><a href="#">Assign visitor to a group</a></li>
		</ul>
		<h3>Reports</h3>
		<ul class="toggle">
			<li class="icn_folder"><a href="#">1</a></li>
			<li class="icn_photo"><a href="#">2</a></li>
			<li class="icn_audio"><a href="#">3</a></li>
			<li class="icn_video"><a href="#">4</a></li>
		</ul>
		<h3>Admin</h3>
		<ul class="toggle">
			<li class="icn_settings"><a href="#">Options</a></li>
			<li class="icn_jump_back"><a href="Logout">Logout</a></li>
		</ul>
		
		<footer>
			<hr />
			<p><strong>&copy; Meditor</strong></p>
			<p>Theme by <a href="http://www.medialoot.com">MediaLoot</a></p>
		</footer>
	</aside><!-- end of sidebar -->
        
        <section id="main" class="column">
				
		<div class="spacer"></div>
	</section>
        
    </body>
</html>
