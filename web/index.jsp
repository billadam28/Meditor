<!DOCTYPE HTML>
<!--
	Eventually by HTML5 UP
	html5up.net | @n33co
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
    <head>
        <title>Meditor</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
        <!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
        <link rel="stylesheet" href="css/main.css" />
        <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
        <!--[if lte IE 8]><link rel="stylesheet" href="/css/ie8.css" /><![endif]-->
        <!--[if lte IE 9]><link rel="stylesheet" href="/css/ie9.css" /><![endif]-->
    </head>
    
    <body>

    <!-- Header -->
    <header id="header">
        <h1>Meditor</h1>
        <p>Management Information System for managing the medical visitors <br />
            of a pharmaceutical company.
        </p>
    </header>

        <!-- Login Form -->
        <form id="login-form" method="post" action="Login">
            <input type="text" name="userId" id="login_text" placeholder="Username or email" />
            <input type="password" name="password" id="password" placeholder="Password" />
            <input type="submit" value="Login" />
        </form>

        <p><h7> <%if (request.getAttribute("invalidUser") == "invalid"){%>
                    Invalid username, email or password! Please try again...
                <% }
                String myval = request.getParameter("nullUser");
                if ((myval!= null) && (myval.equals("1"))) { %>
                    You have to log in to access this page!
                <% }%>
        </h7></p>
                        
        <!-- Footer -->
        <footer id="footer">
            <ul class="icons">
                <li><a href="https://github.com/billadam28/Meditor.git" class="fa fa-github"><span class="label">&nbsp;GitHub</span></a></li>
            </ul>
            <ul class="copyright">
                <li>&copy; MSc in Advanced Software Engineering</li><li>Engineering Internet Applications</li>
            </ul>
        </footer>
        
         <!-- Scripts -->
        <!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
        <script src="js/main.js"></script> 
    </body>                    
</html>

	

