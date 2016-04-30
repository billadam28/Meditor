    <%if (session.getAttribute("username") == null) {%>
        <jsp:forward page="/index.jsp" >
            <jsp:param name="noSession" value="1" />
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
        
        <%@ include file="header_js.jsp" %>
        
    </head>
