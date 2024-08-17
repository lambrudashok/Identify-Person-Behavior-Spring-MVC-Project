<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="CSS/changepassStyle.css">
<script type="text/javascript" src='JS/changepassvalidation.js'></script>
</head>
<body>
<div class="changecontainer">
	
<div class="leftmenu">
	<%@ include file="menus.jsp" %>
</div> <!-- leftmenu -->
	
<div class="changesection">
	<div class="divider">
	
	<h3>Change Password</h3>
	<h6>password must contain 6 character include digit.</h6>
	
	<form name="frm" action="changepassword" method="POST" onsubmit="return checkPassword()">
	<input type="text" name="curpass" id="curpass" placeholder="Current Password" required><br>
	<input type="text" name="newpass" id="newpass" placeholder="New Password" required><br>
	<input type="text" name="retypepass" id="retypepass" placeholder="Retype New Password" required><br>
	<div id="msg" class="msg"></div>
	<button type="submit" name="change" id="change">Change Password</button>
	</form>
	</div> <!-- divider -->
</div> <!-- changesection -->

</div> <!-- changecontainer -->
</body>
</html>