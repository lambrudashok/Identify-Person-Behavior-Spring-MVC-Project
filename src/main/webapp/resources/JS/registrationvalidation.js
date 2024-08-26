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




 var otp ;
 var otpSent = false;
// when user enter wrong otp then its function call
function retypeVerifyOTP(){
	document.getElementById("otpverifybtn").addEventListener('click', function() {
               
    let otpInput = document.getElementById("otpinput").value;
	
    if (otpInput == otp) {   // otp correct
		document.getElementById("frm").submit();
        
    } else {
		    // otp wrong
        						// Call AJAX function to reload OTP form with error message
        let xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange=function(){
			if(this.readyState==4 && this.status==200){
				document.getElementById("otpGrid").innerHTML=this.responseText;
				
                retypeVerifyOTP(); // Re calling the event listener after updating the HTML
			}
		};
		xhttp.open("POST","registercheckotp",true);
		xhttp.send(); 

    }
});
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
		
		// send otp user email
		if(!otpSent){
	    // Generate a random OTP
	    otp = Math.floor(1000 + Math.random() * 9000);
	    otpSent=true;
	    
	    var params = {
	        form_name: "ashok",
	        message: otp,
	        to_email: document.getElementById("email").value,
	    };
	
	    emailjs.send("service_ta51o2i", "template_6hcq9nm", params).then(
	        function(response) {
	            document.getElementById("otpverifydiv").style.display = "block";
	
	            retypeVerifyOTP();   //call the event listener initially
	        },
	        function(error) {
	            alert("Failed to send OTP. Please try again.");
	        }
	    );
	}
		
	return false;	
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






