/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var req;
var isIE;

function updateDoctorList() {
    e = document.getElementById("institution_dd");
    var institution = e.options[e.selectedIndex].value;
    
    e = document.getElementById("specialty_dd");
    var specialty = e.options[e.selectedIndex].value;
    
    var url = "AsyncUpdateDoctorList?institution=" + institution + "&specialty=" + specialty ;
 
    req = initRequest();
    req.open("GET", url, true);
    req.onreadystatechange = callbackDoctor; 
    req.send();
}

function updateVisitorList() {
    var e = document.getElementById("group_dd");
    var group = e.options[e.selectedIndex].value;
    var url = "AsyncUpdateVisitorList?group=" + group;
 
    req = initRequest();
    req.open("GET", url, true);
    req.onreadystatechange = callbackVisitor; 
    req.send();
}

function initRequest() {
    if (window.XMLHttpRequest) {
        if (navigator.userAgent.indexOf('MSIE') != -1) {
            isIE = true;
        }
        return new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        isIE = true;
        return new ActiveXObject("Microsoft.XMLHTTP");
    }
}

function callbackDoctor() {
    var doc_table = document.getElementById("doc_table");
    if (req.readyState == 4 && req.status == 200) {
            new_table = "";
            var xmlDoc = req.responseXML;
            var doc_elem = xmlDoc.getElementsByTagName("doctor");
            for (i = 0; i <doc_elem.length; i++) { 
                new_table += "<tr><td><input type=\"checkbox\" name=\"doctorList\"id=\"doctor_chbx\" value=\""+doc_elem[i].getElementsByTagName("id")[0].childNodes[0].nodeValue+"\" form=\"form1\"></td>"+ 
                            "<td>"+doc_elem[i].getElementsByTagName("name")[0].childNodes[0].nodeValue+"</td>"+
                            "<td>"+doc_elem[i].getElementsByTagName("assignedVisitor")[0].childNodes[0].nodeValue+"</td>"+ 
                            "<td>"+doc_elem[i].getElementsByTagName("specialty")[0].childNodes[0].nodeValue+"</td>"+ 
                            "<td>"+doc_elem[i].getElementsByTagName("position")[0].childNodes[0].nodeValue+"</td>"+
                            "<td>"+doc_elem[i].getElementsByTagName("institution")[0].childNodes[0].nodeValue+"</td>"+
                            "<td>"+doc_elem[i].getElementsByTagName("geoArea")[0].childNodes[0].nodeValue+"</td>"+
                            "<td>"+doc_elem[i].getElementsByTagName("city")[0].childNodes[0].nodeValue+"</td>"+
                          "</tr>";  
            }   
            doc_table.innerHTML = new_table;
    }
    if (req.readyState == 4 && req.status == 204) {
            doc_table.innerHTML="<tr><td></td><td>no results found with this criteria</td></tr>";
    }

}

function callbackVisitor() {
    var vst_table = document.getElementById("vst_table");
    if (req.readyState == 4 && req.status == 200) {
            new_table = "";
            var xmlDoc = req.responseXML;
            var doc_elem = xmlDoc.getElementsByTagName("visitor");
            for (i = 0; i <doc_elem.length; i++) { 
                new_table += "<tr><td><input type=\"radio\" name=\"visitorToAssign\"id=\"visitor_rd\" value=\""+doc_elem[i].getElementsByTagName("id")[0].childNodes[0].nodeValue+"\" form=\"form1\"></td>"+ 
                            "<td>"+doc_elem[i].getElementsByTagName("name")[0].childNodes[0].nodeValue+"</td>"+
                            "<td>"+doc_elem[i].getElementsByTagName("level")[0].childNodes[0].nodeValue+"</td>"+ 
                            "<td>"+doc_elem[i].getElementsByTagName("superior")[0].childNodes[0].nodeValue+"</td>"+ 
                            "<td>"+doc_elem[i].getElementsByTagName("group")[0].childNodes[0].nodeValue+"</td>"+
                          "</tr>";  
            }   
            vst_table.innerHTML = new_table;
    }
    if (req.readyState == 4 && req.status == 204) {
            vst_table.innerHTML="<tr><td></td><td>no results found with this criteria</td></tr>";
    }

}

function toggle(source) {
  checkboxes = document.getElementsByName('doctorList');
  var n = checkboxes.length;
  for (var i=0; i<n; i++) {
      checkboxes[i].checked = source.checked;
  }
}




