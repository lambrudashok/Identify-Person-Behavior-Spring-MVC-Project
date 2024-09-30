<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="resources/CSS/changepassStyle.css">
<script type="text/javascript" src='resources/JS/changepassvalidation.js'></script>
<!-- dark model below-->
<link rel="stylesheet" href="resources/CSS/darkmode.css">
<script type="text/javascript" src="resources/JS/darkmode.js"></script>
</head>
<body>
<div class="changecontainer">
	
<div class="changesection">
	<div class="divider">
	<div class="adj">
	<div class="closetab">
		<a href="userhomepage"><i class="fa-solid fa-xmark"></i></a>
	</div><!--closetab -->

	<h3>Change Password</h3>
	<h6>password must contain 6 character include digit.</h6>
	</div> <!-- adj -->
	<form name="frm" action="changepassword" method="POST" onsubmit="return checkPassword()">
	<input type="password" name="curpass" id="curpass" placeholder="Current Password" required><br>
	<input type="password" name="newpass" id="newpass" placeholder="New Password"><br>
	<input type="password" name="retypepass" id="retypepass" placeholder="Retype New Password"><br>
	<div id="msg">${msg}</div>
	<div id="success" class="success">${success}</div>
	
	<button type="submit" name="change" id="change">Change Password</button>
	</form>
	</div> <!-- divider -->
</div> <!-- changesection -->

</div> <!-- changecontainer -->
</body>
</html>