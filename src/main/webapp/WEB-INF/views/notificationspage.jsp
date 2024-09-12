<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.*,com.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/CSS/notificationStyle.css">
<script type="text/javascript" src='resources/JS/notification.js'></script> 
</head>
<body>

<div class="notificationcontainer">

	<div class="notificationmenu">
	<%@ include file="menus.jsp" %>
	</div><!-- notificationmenu -->
	
<div class="notificationmain">
			
	<div class="notificationbox">
	<div class="notificationdiv"> 
		<div class="details">
			<h4>Notifications</h4>
			<div id="name"><a href=''>All</a></div>
		</div> <!-- details -->
		
		<div class="closetab">
			<a onclick="closefun()"><i class="fa-solid fa-xmark"></i></a>
		</div><!--closetab -->
	</div> <!-- notificationdiv -->
	
	<%
	List<NotificationModel> notificationlist =(List<NotificationModel>) request.getAttribute("list");
	if(notificationlist!=null){
	for(NotificationModel list : notificationlist){
	%>
	<div class="notificationuser">
	<div class="photouser">
		<img alt="notificationspage" src="resources/Profile_Images/<%=list.getProfileimage()%>">
	</div> <!-- photouser -->
	<div class="userdetailsnotification">
		<div class="namedivuser">
		<div id="username"><%=list.getUsername() %></div>
		<div id="notificationpro"><%=list.getNotification() %></div>
		</div>
		<div class="spdatetime">
		<div id="notificationdate"><%=list.getDate() %></div>
		<div id="notificationtime"><%=list.getTime() %></div>
		</div>
	</div> <!-- userdetailsnotification -->
	</div>  <!-- notificationuser -->
	<%
	}
	}else{
		%>
		<div id="nnf">Notifications Not Found</div>
		<%
	}
	%>
	</div><!--  notificationbox  -->
			
</div><!-- notificationmain-->

</div> <!-- notificationcontainer -->

</body>
</html>