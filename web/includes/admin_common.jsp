        <header id="header">
		<hgroup>
			<h1 class="site_title">Meditor</h1>
			<h2 class="section_title">Administrator</h2><div class="btn_view_site"></div>
		</hgroup>
	</header> <!-- end of header bar -->
	
	<section id="secondary_bar">
		<div class="user">
			<p>Welcome <%= session.getAttribute("firstName") + " " + session.getAttribute("surName")%></p>
			<!--<a class="logout_user" href="#" title="Logout">Logout</a> -->
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
                        <li class="icn_new_article"><a href="AssignVisitor">Assign Visitor to a Doctor</a></li>
			<li class="icn_new_article"><a href="#">Create New Cycle of Scheduled Visits</a></li>
			<li class="icn_logout"><a href="#">Delete a Doctor</a></li>
		</ul>
                <h3>Groups</h3>
		<ul class="toggle">
			<li class="icn_new_article"><a href="#">Create new</a></li>
                        <li class="icn_categories"><a href="#">Assign visitor to a group</a></li>
                        <li class="icn_profile"><a href="#">Set visitor as a group leader</a></li>
		</ul>
		<h3>Reports</h3>
		<ul class="toggle">
			<li class="icn_folder"><a href="#">1</a></li>
			<li class="icn_photo"><a href="#">2</a></li>
			<li class="icn_audio"><a href="#">3</a></li>
			<li class="icn_video"><a href="#">4</a></li>
		</ul>
		<h3>Account</h3>
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
        
                
       <!-- <section id="main" class="column">
				
		<div class="spacer"></div>
	</section> -->
