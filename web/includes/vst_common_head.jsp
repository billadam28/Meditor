<%if (session.getAttribute("username") == null || session.getAttribute("user_type").equals(1)){%>
        <jsp:forward page="/index.jsp" >
            <jsp:param name="noSession" value="1" />
        </jsp:forward>
    <%}%>

    <head>
        <meta charset="utf-8" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard | Medical Visitor Panel</title>
        <link rel="stylesheet" href="css/admin.css"  />
        <!--[if lt IE 9]>
	<link rel="stylesheet" href="css/admin_ie.css" type="text/css" media="screen" />
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
        
    <%@ include file="header_js.jsp" %>
    
    </head>
