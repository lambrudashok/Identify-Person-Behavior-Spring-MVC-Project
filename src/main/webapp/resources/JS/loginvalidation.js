/**
 * 
 */

function loginfun() {

    var username= document.getElementById("username").value;
    var password = document.getElementById("password").value;
    
    document.getElementById("usernamemsg").innerHTML="";
	document.getElementById("passwordmsg").innerHTML="";
	
    if(username.trim() =='' && password.trim() ==''){
		document.getElementById("usernamemsg").innerHTML="Username is required";
		document.getElementById("passwordmsg").innerHTML="Password is required";
		return false;
	}
    
    if(username.trim() ==''){
		document.getElementById("usernamemsg").innerHTML="Username is required";
		return false;
	}
    if (password.trim() =='') {
		document.getElementById("passwordmsg").innerHTML="Password is required";
        return false;
    }else
		return true;

    
}





