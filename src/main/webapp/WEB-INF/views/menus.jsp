<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Behavior Menu</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>   
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    
    <link rel="stylesheet" href="resources/CSS/menuStyle.css">
</head>
<body>

<!-- Bootstrap Navbar for Mobile View -->

<nav class="navbar navbar-expand-lg navbar-dark bg" id="mobilemenus">
    <a class="navbar-brand menulogo" href="#">
        <img src="resources/asset/backgroud1.jpg" width="70" height="50" style="border-radius: 30px;" class="d-inline-block align-top" alt="">FutureAI</a>
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
                <a class="nav-link menulogo text-white" href="notificationspage">
                <i class="fa-solid fa-bell logosize"></i>Notifications</a>
            </li>
            <li class="nav-item">
                <a class="nav-link menulogo text-white" href="reportproblempage">
                <i class="fa-solid fa-circle-exclamation logosize"></i>Report Problem</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle menulogo text-white" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                    <i class="fa-solid fa-gear logosize"></i>Settings
                </a>
                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <li><a class="dropdown-item menulogo" href="changepasswordpage">Change Password</a></li>
                    <li><a class="dropdown-item menulogo" href="deleteaccountpage">Delete Account</a></li>
                    <li><a class="dropdown-item menulogo" href="logoutpage">Logout</a></li>
                </ul>
            </li>
        </ul>
    </div>
</nav>

<!-- Vertical Menu for Desktop View -->
<div class="mainmenulaptop">
		<div class="nav flex-column nav-pills sticky-top" id="v-pills-tab" role="tablist" aria-orientation="vertical">
		<a class="nav-link text-white" id="logoPerson"  href="userhomepage"  aria-selected="false">
  			<img src="resources/asset/backgroud1.jpg" class="logoimg" alt="">FutureAI</a>
  			
  		<a class="nav-link text-white menulogo" id="homeid"  href="userhomepage"  aria-selected="true" style="margin-top:30px">
  			<i class="fa-solid fa-house logosize"></i>Home</a>
  			
  		<a class="nav-link text-white menulogo" id="searchid"  href="searchprofilepage"  aria-selected="false">
  		<i class="fa-solid fa-magnifying-glass logosize"></i>Search</a>
  		
  		<a class="nav-link text-white menulogo" id="createpostid"  href="postcreatepage"  aria-selected="false">
  		<i class="fa-regular fa-square-plus logosize"></i>Create Post</a>
  		
  		<a class="nav-link text-white menulogo" id="profileid"  href="profilepage"  aria-selected="false">
  		<i class="fa-regular fa-user logosize"></i>Profile</a>
  		
  		<a class="nav-link text-white menulogo" id="notificationid"  href="notificationspage"  aria-selected="false">
  		<i class="fa-solid fa-bell logosize"></i>Notifications</a>
  		
  		<a class="nav-link text-white menulogo" id="reportid"  href="reportproblempage"  aria-selected="false">
  		<i class="fa-solid fa-circle-exclamation logosize"></i>Report Problem</a>
  		
  		<!-- settings part below-->
  		
  		<a class="nav-link text-white menulogo" id="changepassid"  href="changepasswordpage"  aria-selected="false">
  		Change Password</a>
  		
  		<a class="nav-link text-white menulogo" id="deleteaccountid"  href="deleteaccountpage"  aria-selected="false">
  		Delete Account</a>
  		
  		<a class="nav-link text-white menulogo" id="logoutid"  href="logoutpage"  aria-selected="false">
  		Logout</a>
 
		</div>
</div>  <!-- mainmenulaptop -->


</body>
</html>