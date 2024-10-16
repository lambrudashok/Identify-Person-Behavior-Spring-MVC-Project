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
    }
    
    const usernam = username.trim();
    if (/\s/.test(usernam)) {
        document.getElementById("usernamemsg").innerHTML= "Username should not contain space.";
        return false; 
    }else
		return true;
    
}


// show password or hide login page
function showpass(){
	const pass=document.getElementById("password");
	const eyeicon=document.getElementById("passview");
	if(pass.type=="password"){
		pass.type="text";
		eyeicon.className="fa-solid fa-eye";
	}else{
		pass.type="password";
		eyeicon.className="fa-solid fa-eye-slash";
	}
}



