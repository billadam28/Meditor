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
    var isChecked = 0;
    var checkbox = document.getElementsByName('assignedVisitor');
    
    for (i = 0; i < checkbox.length; i++) {
        if (checkbox[i].checked) {
            isChecked = isChecked +1;
        }
    }
    
    if(isChecked > 0) { 
        return true; 
    } else { 
        alert('You must select at least one Medical Visitor!'); 
        return false; 
    }
}

function validateSetForm () {
    var isChecked = 0;
    var radios = document.getElementsByName('leaderVisitor');
    for (i = 0; i < radios.length; i++) {
        if (radios[i].checked) {
            isChecked = isChecked +1;
        }
    }
    
    if (isChecked > 0) {
        return true;
    } else {
        alert('You must select a Medical Visitor!');
        return false;
    }
}

function validateDoctorForm () {
    var isChecked = 0;
    var radios = document.getElementsByName('selectDoc');
    for (i = 0; i < radios.length; i++) {
        if (radios[i].checked) {
            isChecked = isChecked +1;
        }
    }
    
    if (isChecked > 0) {
        return true;
    } else {
        alert('You must select a Doctor!');
        return false;
    }
}

function validateVisitForm() {
    var isChecked = 0;
    var radios = document.getElementsByName('selectVisit');
    for (i = 0; i < radios.length; i++) {
        if (radios[i].checked) {
            isChecked = isChecked +1;
        }
    }
    
    if (isChecked > 0) {
        return true;
    } else {
        alert('You must select a visit!');
        return false;
    }
}

function isValidDate() {
  var dateString = document.getElementById('date').value;  
  var regEx = /^\d{4}-\d{2}-\d{2}$/;
  if(dateString.match(regEx)) {
    return true;
  } else {
    alert('You must enter a valid date. The pattern should be YYYY-MM-DD');  
    return false;    
  }
  
}