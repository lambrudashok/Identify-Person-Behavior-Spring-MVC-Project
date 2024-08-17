/**
 * 
 */

 function checkemail(email){
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			document.getElementById("emailGrid").innerHTML=this.responseText;
		}
	};
	xhttp.open("POST","registercheckemail?email="+email,true);
	xhttp.send();
 }
 
 
  function checkusername(username){
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			document.getElementById("usernameGrid").innerHTML=this.responseText;
		}
	};
	xhttp.open("POST","registercheckusername?username="+username,true);
	xhttp.send();
 }


function validateForm() {
    var name = document.getElementById("name").value;
    var email = document.getElementById("email").value;
    var username= document.getElementById("username").value;
    var password = document.getElementById("password").value;
   
   
    document.getElementById("namemsg").innerHTML="";
	document.getElementById("usernamemsg").innerHTML="";
	document.getElementById("emailmsg").innerHTML="";
    document.getElementById("passwordmsg").innerHTML="";
    
     var recaptchaResponse = grecaptcha.getResponse(); // get response check or not 


    if (!validateName(name)) {
		document.getElementById("namemsg").innerHTML="Invalid name. Please use alphabets only.";
        return false;
    }
    
     if(username.trim()==''){
		document.getElementById("usernamemsg").innerHTML="username not null";
		return false;
	}
	
    if (!validateEmail(email)) {
		document.getElementById("emailmsg").innerHTML="Invalid email format.";
        return false;
    }
   
    if (!validatePassword(password)) {
		document.getElementById("passwordmsg").innerHTML="Password must be strong. \n6 character length include digit";
        return false;
    }
    
    
    if(recaptchaResponse.length==0){
		return false;             // check capture click or not if not click then length is 0 but click there are somany words
	}
    else{
	return true;	
	}
    
}

function validateName(name) {
	//Allows only alphabets and spaces
    let nameRegex = /^[a-zA-Z\s]+$/;
    return nameRegex.test(name);
}

function validateEmail(email) {
    let emailRegex = /^[a-zA-Z0-9]*@gmail.com$/;
    return emailRegex.test(email);
}

function validatePassword(password) {
	//password must contain 6 character include digit.
    let passwordRegex = /^(?=.*\d).{6,}$/;
    return passwordRegex.test(password);
}






