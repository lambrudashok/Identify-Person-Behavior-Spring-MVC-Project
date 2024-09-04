<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
   <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/@emailjs/browser@4/dist/email.min.js"></script>
   <script type="text/javascript">
       (function(){
          emailjs.init({
            publicKey: "cSJw4UQuV4JA_SXo7",
          });
       })();
    </script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="resources/CSS/forgotpassStyle.css">
<script type="text/javascript" src='resources/JS/forgotpassvalidation.js'></script>

</head>
<body>
<div class="changecontainer">
	
<div class="changesection">
	<div class="divider" id="forgotGrid">
	
	<div class="closetab">
		<a href="loginpage"><i class="fa-solid fa-xmark"></i></a>
	</div><!--closetab -->
	
	<div class="usernamediv" id="usernamediv">
	
	<h3>Forgot Password</h3>
	<input type="text" name="username" id="username" placeholder="Enter Username" required><br>
	<div id="msgusername" class="msg"></div>
	<button type="submit" name="usernamenextbtn" id="usernamenextbtn" onclick="checkUsername()" >Next</button><br>
	<form action="loginpage" method="post">
	<button type="submit" id="cancelbtn" >Cancel</button><br>
	</form>
	</div> <!-- usernamediv -->
	
	
	
	<div class="emaildiv" id="emaildiv">
	<h3>Confirm your email</h3>
	<input type="email" id="email" placeholder="Enter Your Email"><br>
	<div id="msgemail" class="msg"></div>
	<button type="submit" name="emailconfirmbtn" id="emailconfirmbtn" onclick="checkConfirmEmail()" >Confirm</button><br>
	<form action="loginpage" method="post">
	<button type="submit"  id="cancelbtn">Cancel</button><br>
	</form>
	</div> <!-- emaildiv -->
	
	
	<div class="sendotpdiv" id="sendotpdiv">
	<h3>Where should we send a confirmation code</h3>
	<input type="hidden" value="" id="email"><br>
	<h5>Send an email to</h5>
	<button type="submit" name="sendotpbtn" id="sendotpbtn" onclick="checkOTP()"  >Send OTP</button><br>
	</div><!-- sendotpdiv -->
	
	
	<div class="otpverifydiv" id="otpverifydiv">
	<input type="hidden" value="" id="email"><br>
	<div id="otpconfirmGrid">
	<h3>Check your email to get your confirmation code</h3>
	<input type="text" id="otpinput"  placeholder="Enter OTP code"><br>
	<div id="msgotp" class="msg"></div>
	<button type="submit" name="otpverifybtn" id="otpverifybtn"  >Confirm</button><br>
	</div>
	</div><!-- otpverifydiv -->
	
	
	<div class="forgotdiv" id="forgotdiv">
	<h3>Forgot Password</h3>
	<input type="text" name="newpass" id="newpass" placeholder="New Password" required><br>
	<input type="text" name="retypepass" id="retypepass" placeholder="Retype New Password" required><br>
	<div id="msg" class="msg"></div>
	<button type="submit" name="forgot" id="forgot" onclick="checkPassword()" >Forgot Password</button>
	</div><!--  forgotdiv -->
	
	
	</div> <!-- divider -->
</div> <!-- changesection -->

</div> <!-- changecontainer -->
</body>
</html>