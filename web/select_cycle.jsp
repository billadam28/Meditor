<%@page import="MeditorJavaClasses.CycleList"%>
<%@page import="MeditorPersistence.Cycle"%>
<%@page import="MeditorPersistence.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <%@ include file="includes/adm_common_head.jsp" %>
    
    <body> 
        <%@ include file="includes/admin_common.jsp" %>
        
        <section id="main" class="column">
             
            
            <%CycleList cycleList = (CycleList) request.getAttribute("cycleList");%>
            
            <article class="module width_full">
                <header><h3>Select cycle and number of visits</h3></header>
                    <div class="module_content">
			<fieldset style="width:47%; display: inline-block;"> <!-- to make two field float next to one another, adjust values accordingly -->
                            <label>Select cycle</label>
                            <select name="cycle" form="form1" style="width:90%;">
                                    <%for (Cycle obj : cycleList.getCycleList()) { %>
                                    <option value="<%= obj.getId()%>"><%=obj.getCycle()%></option>
                                    <%}%>
                        
                            </select>
			</fieldset>
                        <fieldset style="width:50%; float:right;"> <!-- to make two field float next to one another, adjust values accordingly -->
                            <label>Select number of visits</label>
                            <select name="number_of_visits" form="form1" style="width:90%;">
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                            </select>
			</fieldset>
                    </div>
                <form class="post_message" id="form1" method="post" action="CreateVisits">
                    <input type="submit" class="alt_btn" value="Load Doctors"/>
                </form>
           </article><!-- end of post new article -->

        </section>
    <script type="text/javascript" src="js/currentlinkstyle.js"></script>   
    </body>
</html>
