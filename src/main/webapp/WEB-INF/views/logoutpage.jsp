<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*, com.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/CSS/logoutStyle.css">
<script type="text/javascript" src='resources/JS/logoutvalidation.js'></script>
<!-- dark model below-->
<link rel="stylesheet" href="resources/CSS/darkmode.css">
<script type="text/javascript" src="resources/JS/darkmode.js"></script> 
</head>
<body>

<div class="logoutcontainer">
<div class="main">
<div class="logoutdiv">
	<div id="profile">
	<%	
		UserInfoModel userInfo =(UserInfoModel)request.getAttribute("userInfo");
	%>
		<div class="photo">
			<img alt="" src="<%= request.getContextPath() %>/resources/Profile_Images/<%=userInfo.getProfileimage()%>">
		</div> <!-- photo -->
		<div class="userdetails">
			<div id="name"><%=userInfo.getName() %></div>
			<div id="username"><%=userInfo.getUsername() %></div>
		</div> <!-- userdetails -->
	
	</div> <!-- profile -->
		
	<div id="requestlogout">
		<h2>Are you sure you want to logout ?</h2>
		<div class="btndiv">
		<form name="frm" action="loginpage" method="post">
		<button type="submit" id="okbtn">OK</button>
		</form>
		<button type="submit" onclick="cancelRequest()" id="cancelbtn" >Cancel</button>
		</div>
	</div> <!-- requestlogout -->
		
</div> <!-- logoutdiv -->
</div> <!-- main  -->
</div><!-- logoutcontainer -->

</body>
</html>