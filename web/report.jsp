<%@page import="MeditorPersistence.Visitor"%>
<%@page import="java.util.List"%>
<%@page import="MeditorPersistence.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <%@ include file="includes/adm_common_head.jsp" %>
    
    <body> 
        <%@ include file="includes/admin_common.jsp" %>
        
        <section id="main" class="column">
            
            <%List<String[]> reportList = (List<String[]>) request.getAttribute("reportList");%>
            <%Visitor visitor = (Visitor) request.getAttribute("visitor");%>
            
            <article class="module width_3_quarter">
            <h2 style="margin-left:10px">Statistics for <%= visitor.getFirstname() + " " + visitor.getSurname()%></h2>
            </article>
            
            <article class="module width_3_quarter">
                <header><h3 class="tabs_involved">Statistics table</h3>
		</header>
                
		<div class="tab_container">
			<div>
			<table class="tablesorter" cellspacing="0"> 
			<thead> 
				<tr>  
    				<th>Number of Visits</th> 
    				<th>Area</th> 
    				<th>Status</th>  
				</tr> 
			</thead> 
			<tbody> 
                                <%for (String[] str : reportList) { %>
				<tr> 
    				<td><%= str[0]%></td> 
    				<td><%= str[1]%></td> 
    				<td><%= str[2]%></td> 
				</tr>
                                <%}%>
			</tbody> 
			</table>
			</div><!-- end of #tab1 -->
			
		</div><!-- end of .tab_container -->
		
            </article><!-- end of doctors article -->
  
        </section>
    <script type="text/javascript" src="js/currentlinkstyle.js"></script>   
    </body>
</html>
