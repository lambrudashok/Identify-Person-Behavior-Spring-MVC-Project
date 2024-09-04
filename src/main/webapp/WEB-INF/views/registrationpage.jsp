<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css2?family=Jost:wght@500&display=swap" rel="stylesheet">
<script src="https://www.google.com/recaptcha/api.js" async defer></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/@emailjs/browser@4/dist/email.min.js"></script>
<script type="text/javascript">
    (function(){
       emailjs.init({
         publicKey: "cSJw4UQuV4JA_SXo7",
       });
    })();
 </script>
 <meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="resources/CSS/registrationStyle.css">
<script type="text/javascript" src='resources/JS/registrationvalidation.js'></script>
</head>
<body>

<div class="registercontainer">
<div class="adj">
<div class="register"> 
		<form name="frm" id="frm" action="registeruser" method="POST" onsubmit="return validateForm()" >
			<div class="logo">Register Here</div> 
			<input type="text" name="name" id="name" placeholder="First and surname" required><br>
			<div class="msg" id="namemsg"></div><br>
			<input type="email" name="email" id="email" onkeyup="checkemail(this.value)" placeholder="Email" required><br> 
			<div id="emailGrid">
			<div class="msg" id="emailmsg"></div><br>
			</div>
			<input type="text" name="username" id="username" onkeyup="checkusername(this.value)" placeholder="User name"><br>
			<div id="usernameGrid">
			<div class="msg" id="usernamemsg"></div><br>
			</div>
			<input type="password" name="password" id="password" placeholder="Password" required><br>
			<div class="msg" id="passwordmsg"></div><br>
			
			<div class="otpverifydiv" id="otpverifydiv">
			<h4>Check your email to get your confirmation code</h4>
			<div id="otpGrid">
			<div id="otpconfirmGrid">
			<input type="text" id="otpinput"  placeholder="Enter OTP code"><br>
			<button type="submit" name="otpverifybtn" id="otpverifybtn"  >Confirm</button><br>
			</div>
			<div id="msgotp" class="msg"></div>
			</div>
			</div><!-- otpverifydiv -->
			
			<div class="msgsuccess" >${msg}</div><br>
			
			<div class="capdiv">
			<div class="g-recaptcha" id="recapt" data-sitekey="6Lca4SQqAAAAAMR1uYXhG2V1o-yIw-dP3X3rZkyF"></div>
			</div>
			
			<button type="submit" name="s" value="register" >Register</button><br>
			<div class="next">
			<a id="regtag" href="loginpage">Login Page?</a>
			</div>
		</form>
	</div>  <!-- register -->
	</div> <!-- adj -->
</div><!-- registercontainer -->

</body>
</html>