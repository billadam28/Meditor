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
            
            <%List<Visitor> vstList = (List<Visitor>) request.getAttribute("vstList");%>
              
            <article class="module width_3_quarter">
                <header><h3 class="tabs_involved">Select a Visitor to generate Statistics</h3>
		</header>
                
		<div class="tab_container">
			<div>
			<table class="tablesorter" cellspacing="0"> 
			<thead> 
				<tr> 
   				<th></th> 
    				<th>Name</th> 
    				<th>Level</th> 
    				<th>Superior</th> 
    				<th>Group Name</th> 
				</tr> 
			</thead> 
			<tbody> 
                                <%for (Visitor obj : vstList) { %>
				<tr> 
                                    <td><input type="radio" name="visitor" id="visitor_rd" value="<%= obj.getId()%>" form="form1"></td> 
    				<td><%= obj.getFirstname() + " " + obj.getSurname()%></td> 
    				<td><%= obj.getVisitorLevel() %></td> 
    				<td><%if (obj.getSuperior() != null) {%><%= obj.getSuperior().getSurname()%><%} else {%> --- <%}%></td> 
    				<td><%if (obj.getGroup() != null) {%><%= obj.getGroup().getName()%><%} else {%> --- <%}%></td> 
				</tr>
                                <%}%>
			</tbody> 
			</table>
			</div><!-- end of #tab1 -->
			
		</div><!-- end of .tab_container -->
                
                <form class="post_message" id="form1" method="post" action="GenerateReport1">
                    <input type="submit" class="alt_btn" value="Generate Statistics" style="color:green"/>
                </form>
		
            </article><!-- end of doctors article -->
  
        </section>
    <script type="text/javascript" src="js/currentlinkstyle.js"></script>   
    </body>
</html>
