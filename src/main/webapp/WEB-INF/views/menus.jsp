<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>menus</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>   
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="resources/CSS/menuStyle.css">
    <!-- dark model below-->
<link rel="stylesheet" href="resources/CSS/darkmode.css">
<script type="text/javascript" src="resources/JS/darkmode.js"></script>  
</head>

<body>

<!-- Bootstrap Navbar for Mobile View -->

<nav class="navbar navbar-expand-lg navbar-dark bg" id="mobilemenus">
    <a class="navbar-brand" href="#">
        <img src="resources/asset/logoU2.png" width="45" height="45" style="border-radius: 30px; margin-right: 10px;" class="d-inline-block align-top" alt="">FutureAI</a>
    <button class="navbar-toggler togglebtn" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <i class="fa-solid fa-bars clr"></i>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ms-auto">
            <li class="nav-item">
                <a class="nav-link menulogo text-white" href="userhomepage">
                <i class="fa-solid fa-house logosize"></i>Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link menulogo text-white" href="searchprofilepage">
                <i class="fa-solid fa-magnifying-glass logosize"></i>Search</a>
            </li>
            <li class="nav-item">
                <a class="nav-link menulogo text-white" href="postcreatepage">
                <i class="fa-regular fa-square-plus logosize"></i>Create Post</a>
            </li>
            <li class="nav-item">
                <a class="nav-link menulogo text-white" href="profilepage">
                <i class="fa-regular fa-user logosize"></i>Profile</a>
            </li>

            <li class="nav-item">
                <a class="nav-link menulogo text-white" id="notificationid" href="notificationspage">
                <i class="fa-solid fa-bell logosize"></i>
  			    <span class="coutNty${countNty}" id="coutNty">
				<i class="fa-solid fa-circle" id="dot"></i>
				</span>Notifications</a>
            </li>
            
            <li class="nav-item">
                <a class="nav-link menulogo text-white" href="reportproblempage">
                <i class="fa-solid fa-circle-exclamation logosize"></i>Report Problem</a>
            </li>
            
            <!-- Settings  part below-->
            
            <li class="nav-item">
                <a class="nav-link menulogo text-white ps-2" href="changepasswordpage"></i>Change Password</a>
            </li>
            
            <li class="nav-item">
                <a class="nav-link menulogo text-white ps-2" href="deleteaccountpage">Delete Account</a>
            </li>
            
            <li class="nav-item">
                <a class="nav-link menulogo text-white ps-2" href="logoutpage">Logout</a>
            </li>
        </ul>
    </div>
</nav>



<!-- Vertical Menu for Desktop View -->
<div class="mainmenulaptop">
		<div class="nav flex-column nav-pills sticky-top" id="v-pills-tab" role="tablist" aria-orientation="vertical">
		<a class="nav-link text-white hvr" id="logoPerson"  href="userhomepage"  aria-selected="false">
  			<img src="resources/asset/logoU2.png" class="logoimg" alt="">FutureAI</a>
  			
  		<a class="nav-link text-white menulogo hvr" id="homeid"  href="userhomepage"  aria-selected="true">
  			<i class="fa-solid fa-house logosize"></i>Home</a>
  			
  		<a class="nav-link text-white menulogo hvr" id="searchid"  href="searchprofilepage"  aria-selected="false">
  		<i class="fa-solid fa-magnifying-glass logosize"></i>Search</a>
  		
  		<a class="nav-link text-white menulogo hvr" id="createpostid"  href="postcreatepage"  aria-selected="false">
  		<i class="fa-regular fa-square-plus logosize"></i>Create Post</a>
  		
  		<a class="nav-link text-white menulogo hvr" id="profileid"  href="profilepage"  aria-selected="false">
  		<i class="fa-regular fa-user logosize"></i>Profile</a>
  		
  		<a class="nav-link text-white menulogo hvr" id="notificationid"  href="notificationspage"  aria-selected="false">
  		<i class="fa-solid fa-bell logosize"></i> 
  				<span class="cout${Ncount}" id="cout">
  				<%
  				int Ncount= (Integer)request.getAttribute("Ncount");
		  		%>
				<i class="fa-solid fa-circle" id="dot"></i>
				</span> Notifications</a>
  		
  		<a class="nav-link text-white menulogo hvr" id="reportid"  href="reportproblempage"  aria-selected="false">
  		<i class="fa-solid fa-circle-exclamation logosize"></i>Report Problem</a>
  		
  		<a class="nav-link text-white menulogo" id="check" aria-selected="false">
  		<input type="checkbox" class="checkbox" id="checkbox">Dark Mode
  		<label for="checkbox" class="toggle-label"><span class="darkmodebtn" id="darkmodebtn"></span></label></a>
  		
  		<!-- settings part below-->
  		
  		<a class="nav-link text-white menulogo hvr" id="changepassid"  href="changepasswordpage"  aria-selected="false">
  		Change Password</a>
  		
  		<a class="nav-link text-white menulogo hvr" id="deleteaccountid"  href="deleteaccountpage"  aria-selected="false">
  		Delete Account</a>
  		
  		<a class="nav-link text-white menulogo hvr" id="logoutid"  href="logoutpage"  aria-selected="false">
  		Logout</a>
 
 
 
 

 
 
 
 
 
		</div>
</div>  <!-- mainmenulaptop -->


</body>
</html>