function validateForm() {
    var x = document.getElementById("name").value;
    var y = document.getElementById("desc").value;
    if ((x == null || x == "" || x.match(/^\s*$/)) && (y==null || y=="" || y.match(/^\s*$/))) {
        document.getElementById("name").style.borderColor="red";
        document.getElementById("desc").style.borderColor="red";
        var m = document.getElementById("msg").value;
        document.getElementById("msg2").innerHTML = m;
        //alert("All fields are required!");
        return false;
    } else if (x==null || x=="" || x.match(/^\s*$/)) {
        document.getElementById("name").style.borderColor="red";
        var m = document.getElementById("msg").value;
        document.getElementById("msg2").innerHTML = m;
        return false;
    } else if (y==null || y=="" || y.match(/^\s*$/)) {
        document.getElementById("desc").style.borderColor="red";
        var m = document.getElementById("msg").value;
        document.getElementById("msg2").innerHTML = m;
        return false;
    } else {
        return true;
    }
    
    
}

function validateAssignForm () {
    if(document.getElementById('visitor_chbx').checked) { 
        return true; 
    } else { 
        alert('You must select at least one Medical Visitor!'); 
        return false; 
    }
}