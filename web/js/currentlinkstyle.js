/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function highlightCurrentLink() {
    var arr = document.getElementsByTagName("a");
    for (var i = 0, len = arr.length; i < len; ++i) {
        if (arr[i].href == window.location.href) {
            arr[i].style.fontWeight = "bold";
            arr[i].style.color = "#1cb495";
            break;
        }
    }
} 
highlightCurrentLink();

