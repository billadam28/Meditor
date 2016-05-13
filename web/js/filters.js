/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var req;
var isIE;

function init() {
    completeField = document.getElementById("");
}

function updateDoctorList() {
    var e = document.getElementById("geo_area_dd");
    var geoArea = e.options[e.selectedIndex].value;
    
    e = document.getElementById("city_dd");
    var city = e.options[e.selectedIndex].value;
    
    e = document.getElementById("institution_dd");
    var institution = e.options[e.selectedIndex].value;
    
    e = document.getElementById("specialty_dd");
    var specialty = e.options[e.selectedIndex].value;
    
    var url = "AsyncUpdateDoctorList?geoArea=" + geoArea + "&city=" + city + 
            "&institution=" + institution + "&specialty=" + specialty ;
 
    req = initRequest();
    req.open("GET", url, true);
    req.onreadystatechange = callback; 
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

function callback() {
    if (req.readyState == 4 && req.status == 200) {
            document.write(5 + 5);
    }
    if (req.readyState == 4 && req.status == 204) {
            document.write(5 + 90);
    }

}




