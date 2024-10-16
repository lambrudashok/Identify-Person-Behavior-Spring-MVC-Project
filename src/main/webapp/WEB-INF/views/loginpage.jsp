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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
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
			<div id="seprate">
			<i class="fa-solid fa-user"></i>
			<input type="text" name="username" id="username"	placeholder="Enter username" >
			</div>
			<div class="msg" id="usernamemsg"></div>
			<div id="seprate">
			<i id="passicon" class="fa-solid fa-key"></i><input type="password"	name="password" id="password" placeholder="Enter password" >
			<i id="passview" class="fa-solid fa-eye-slash" onclick="showpass()"></i>
			</div>
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