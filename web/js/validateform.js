function validateForm() {
    var x = document.getElementById("name").value;
    var y = document.getElementById("desc").value;
    if ((x == null || x == "") && (y==null || y=="")) {
        document.getElementById("name").style.borderColor="red";
        document.getElementById("desc").style.borderColor="red";
        //alert("All fields are required!");
        return false;
    } else if (x==null || x=="") {
        document.getElementById("name").style.borderColor="red";
        return false;
    } else if (y==null || y=="") {
        document.getElementById("desc").style.borderColor="red";
        return false;
    } else {
        return true;
    }
    
    
}