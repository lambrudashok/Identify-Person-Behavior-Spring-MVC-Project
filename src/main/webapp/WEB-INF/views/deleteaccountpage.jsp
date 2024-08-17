<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*, com.model.*,com.service.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="CSS/deleteaccountStyle.css">
<script type="text/javascript" src='JS/deleteaccountvalidation.js'></script> 
</head>
<body>
<div class="deletecontainer">

<div class="main">

<div class="deletediv">
		
	<div id="profile">
		
	<%		
		session =request.getSession(false);
		int userId=Integer.parseInt(session.getAttribute("userID").toString());
		
		UserRegistrationService userSer = new UserRegistrationService();
		UserInfoModel userInfo=userSer.getUserInfo(userId);
	%>
					
		<div class="photo">
			<img alt="" src="Profile_Images/<%=userInfo.getProfileimage()%>">
		</div> <!-- photo -->
		<div class="userdetails">
			<div id="name"><%=userInfo.getName() %></div>
			<div id="username"><%=userInfo.getUsername() %></div>

		</div> <!-- userdetails -->
	
	</div> <!-- profile -->
		
	<div id="request">
	<div id="requestGrid">
	<%
	session =request.getSession(false);
	int userID=Integer.parseInt(session.getAttribute("userID").toString());
	int checkdelete=userSer.checkRequestDelete(userID);
	if(checkdelete!=0){
		%>
		<h2>Recover Account ?</h2>
		<div class="btndiv">
		<button type="submit" id="yesbtn" onclick="recoverRequest(<%=userID%>)">Yes</button>
		<form name="frm" action="userhomepage.jsp" method="post">
		<button type="submit" id="nobtn" >No</button>
		</form>
		</div>
		<%
	}else{
		%>
		<h2>Do You Want Delete Account ?</h2>
		<div class="btndiv">
		<button type="submit" id="yesbtn" onclick="deleteRequest(<%=userID%>)">Yes</button>
		<form name="frm" action="userhomepage.jsp" method="post">
		<button type="submit" id="nobtn" >No</button>
		</form>
		</div>
		<%
	}
	%>
	</div>
		<div id="msg"></div>
	</div> <!-- request -->
		
</div> <!-- deletediv -->

</div> <!-- main  -->

</div><!-- deletecontainer -->
</body>
</html>