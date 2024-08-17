
// check username validate or not
function checkUsername(){
	let username = document.getElementById("username").value;
	
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			document.getElementById("usernamediv").innerHTML=this.responseText;
		}
	};
	xhttp.open("POST","forgotcheckusername?username="+username,true);
	xhttp.send(); 
	
}



// check email or confirm email particular user
function checkConfirmEmail(){
	let email = document.getElementById("email").value;
	
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			document.getElementById("emaildiv").innerHTML=this.responseText;
		}
	};
	xhttp.open("POST","forgotcheckemail?email="+email,true);
	xhttp.send(); 
}


 var otp ;
// when user enter wrong otp then its function call
function retypeVerifyOTP(){
	document.getElementById("otpverifybtn").addEventListener('click', function() {
               
    let otpInput = document.getElementById("otpinput").value;
	
    if (otpInput == otp) {   // otp correct
        alert("OTP correct");
        document.getElementById("otpverifydiv").style.display = "none";
		document.getElementById("forgotdiv").style.display = "block";
        
    } else {
        alert("Invalid OTP");   // otp wrong
        						// Call AJAX function to reload OTP form with error message
        let xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange=function(){
			if(this.readyState==4 && this.status==200){
				document.getElementById("otpconfirmGrid").innerHTML=this.responseText;
				
                retypeVerifyOTP(); // Re calling the event listener after updating the HTML
			}
		};
		xhttp.open("POST","forgotcheckotp",true);
		xhttp.send(); 
		
    }
});
}

// send otp user email
function checkOTP() {
    // Generate a random OTP
    otp = Math.floor(1000 + Math.random() * 9000);
    
    var params = {
        form_name: "ashok",
        message: otp,
        to_email: document.getElementById("email").value,
    };

    emailjs.send("service_ta51o2i", "template_6hcq9nm", params).then(
        function(response) {
            alert("OTP sent to your email.");
            
			document.getElementById("sendotpdiv").style.display = "none";
            document.getElementById("otpverifydiv").style.display = "block";

            retypeVerifyOTP();   //call the event listener initially
        },
        function(error) {
            alert("Failed to send OTP. Please try again.");
        }
    );
}



// validations for forgot password
function checkPassword() {
	
    var pass = document.getElementById("newpass").value;
    var retype = document.getElementById("retypepass").value;

    if (!password(pass)) {
		let msg = document.getElementById("msg");
		msg.style.color="red";
		msg.style.fontSize="20px";
        msg.innerHTML="Password must be at least 6 characters long and contain a digit.";
        return false; 
    }

    if (!retypepassword(pass, retype)) {
		let msg = document.getElementById("msg");
		msg.style.color="red";
		msg.style.fontSize="20px";
		msg.innerHTML="Passwords do not match.";
        return false; 
    }
    

	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			document.getElementById("forgotGrid").innerHTML=this.responseText;
		}
	};
	xhttp.open("POST","forgotpassword?pass="+pass,true);  //"forgotpassword?username="+username+"&pass="+pass,
	xhttp.send();    
    
}


function password(pass) {
    let passRex = /^(?=.*\d).{6,}$/; 
    return passRex.test(pass);
}

function retypepassword(pass, retype) {
    return pass === retype;
}