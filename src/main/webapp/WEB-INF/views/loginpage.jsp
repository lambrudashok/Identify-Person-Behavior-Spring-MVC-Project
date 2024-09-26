<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta content="width=device-width, initial-scale=1" name="viewport" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1" />
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css2?family=Jost:wght@500&display=swap" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="resources/CSS/style.css">
<script type="text/javascript" src='resources/JS/loginvalidation.js'></script>
</head>
<body>

<div class="logincontainer">

<img alt="" src="resources/asset/social3.gif">
	<div class="main">
	
		<form name="frm" id="frm" action="validation" method="POST" onsubmit="return loginfun()"><br>
			<h2>Login</h2> 
			<input type="text" name="username" id="username"	placeholder="Enter username" >
			<div class="msg" id="usernamemsg"></div>
			<input type="password"	name="password" id="password" placeholder="Enter password" >
			<div class="msg" id="passwordmsg">${msg}</div>
			<button type="submit" id="btn" name="s" value="login">Login</button><br>
		</form>
		<div class="next">
			<a id="fg" href="forgotpasswordpage">Forgot password?</a> &nbsp;&nbsp; 
			<a id="ank" href="registrationpage">SignUp</a>
		</div>
		
	</div>
</div>

</body>
</html>