<header id="header">
		<hgroup>
			<h1 class="site_title">Meditor</h1>
			<h2 class="section_title">Medical Visitor</h2><div class="btn_view_site"></div>
		</hgroup>
</header> <!-- end of header bar -->
	
	<section id="secondary_bar">
		<div class="user">
			<p>Welcome <%= session.getAttribute("firstName") + " " + session.getAttribute("surName")%></p>
			<a class="logout_user" href="Logout" title="Logout">Logout</a>
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
		<h3>My Visits</h3>
		<ul class="toggle">
			<li class="icn_alert_success"><a href="#" onclick="highlightCurrentLink();">Completed</a></li>
			<li class="icn_categories"><a href="VisitInfo" onclick="highlightCurrentLink();">Scheduled</a></li>
		</ul>
		<h3>Doctors</h3>
		<ul class="toggle">
			<li class="icn_search"><a href="#" onclick="highlightCurrentLink();">Search or Edit info</a></li>
			<li class="icn_add_user" onclick="highlightCurrentLink();"><a href="#">Create new</a></li>
		</ul>
		
		<footer>
			<hr />
			<p><strong>&copy;Meditor</strong></p>
			<p>Theme by <a href="http://www.medialoot.com">MediaLoot</a></p>
		</footer>
	</aside><!-- end of sidebar -->
        
        <!-- <section id="main" class="column">
				
		<div class="spacer"></div>
	</section> -->
