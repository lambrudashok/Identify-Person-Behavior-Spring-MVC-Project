<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css2?family=Jost:wght@500&display=swap" rel="stylesheet">
<link rel="stylesheet" href="resources/CSS/style.css">
<script type="text/javascript" src='resources/JS/loginvalidation.js'></script>
</head>
<body>

<div class="logincontainer">
<img alt="" src="resources/asset/social3.gif">
	<div class="main">
	
		<form name="frm" id="frm" action="validation" method="POST" onsubmit="return loginfun()"><br>
			<h2>Login Here</h2> 
			<input type="text" name="username" id="username"	placeholder="Enter username" ><br>
			<div class="msg" id="usernamemsg"></div><br>
			<input type="password"	name="password" id="password" placeholder="Enter password" ><br>
			<div class="msg" id="passwordmsg">${msg}</div><br>
			<button type="submit" id="btn" name="s" value="login">Login</button><br>
		</form>
		<div class="next">
			<a id="fg" href="forgotpasswordpage">Forgot Password?</a> &nbsp;&nbsp; 
			<a id="ank" href="registrationpage">sign up</a>
		</div>
		
	</div>
</div>

</body>
</html>