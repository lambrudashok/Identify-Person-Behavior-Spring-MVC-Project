<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
 <link rel="stylesheet" href="resources/CSS/menuStyle.css">
</head>
<body>
<div class="main">
		<div class="nav flex-column nav-pills sticky-top" id="v-pills-tab" role="tablist" aria-orientation="vertical">
		<a class="nav-link text-white" id="logoPerson"  href="userhomepage"  aria-selected="false">
  			<img src="resources/asset/backgroud1.jpg" class="logoimg" alt="">Behavior</a>
  			
  		<a class="nav-link text-white menulogo" id="homeid"  href="userhomepage"  aria-selected="true" style="margin-top:30px">
  			<i class="fa-solid fa-house logosize"></i>Home</a>
  			
  		<a class="nav-link text-white menulogo" id="searchid"  href="searchprofilepage"  aria-selected="false">
  		<i class="fa-solid fa-magnifying-glass logosize"></i>Search</a>
  		
  		<a class="nav-link text-white menulogo" id="createpostid"  href="postcreatepage"  aria-selected="false">
  		<i class="fa-regular fa-square-plus logosize"></i>Create Post</a>
  		
  		<a class="nav-link text-white menulogo" id="profileid"  href="profilepage"  aria-selected="false">
  		<i class="fa-regular fa-user logosize"></i>Profile</a>
  		
		  <div class="nav-link text-white menulogo" id="settingsid" data-toggle="dropdown" aria-selected="false">
		  <i class="fa-solid fa-gear logosize"></i>Settings</div>
		  <div class="dropdown-menu bg" aria-labelledby="settingsid">
		    <a class="dropdown-item bg brd" href="changepasswordpage">Change Password</a>
		    <a class="dropdown-item bg brd" href="deleteaccountpage">Delete Account</a>
		    <a class="dropdown-item bg brd" href="loginpage">Logout</a>
		  </div>
  		
		</div>
</div>
</body>
</html>