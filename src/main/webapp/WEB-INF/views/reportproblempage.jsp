<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/CSS/reportproblemStyle.css">
<script type="text/javascript" src='resources/JS/notification.js'></script> 

</head>
<body onload="reportbtnshowfun()">

<div class="reportcontainer">

	<div class="reportmenu">
	<%@ include file="menus.jsp" %>
	</div><!-- reportmenu -->
	
<div class="reportmain">
			
<div class="reportbox">
	<div class="reportdiv"> 
			<h4>Report a problem</h4>
		
		<div class="closetab">
			<a href="userhomepage"><i class="fa-solid fa-xmark"></i></a>
		</div><!--closetab -->
		
	</div> <!-- reportdiv -->
	<div class="desc">	
		<form name="frm" action="sendreportproblemuser" method="POST">	
		<h6>Issue title :</h6>
		<input type="text" name="title" id="title" placeholder="What is your issue ?"  onkeyup="reportbtnshowfun()"><br>
		<h6>Problem Description :</h6>
		<textarea name="description" id="description" placeholder="Please describe as much info as possible..." onkeyup="reportbtnshowfun()"></textarea><br>
		<button type="submit" name="reportbtn" id="reportbtn"  >Send report</button>
		</form>
	</div> <!-- desc -->
	<div id="msg">${msg}</div>
	
</div><!--  reportbox  -->
			
</div><!-- reportmain-->

</div> <!-- reportcontainer -->


</body>
</html>