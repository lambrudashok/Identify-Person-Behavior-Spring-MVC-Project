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
<!-- dark model below-->
<link rel="stylesheet" href="resources/CSS/darkmode.css">
<script type="text/javascript" src="resources/JS/darkmode.js"></script>

<script type="text/javascript">

window.addEventListener('load', function (event) {
	// view all notification when user view page
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			this.responseText;
		}
	};
	xhttp.open("POST","viewallautomatic",true);
	xhttp.send();

});
</script>
</head>

<body>

<div class="notificationcontainer">

	<div class="notificationmenu">
	<%@ include file="menus.jsp" %>
	</div><!-- notificationmenu -->
	
<div class="notificationmain">
			
	<div class="notificationbox">
	<div class="notificationdiv"> 
	
	<%
	int count= (Integer)request.getAttribute("Ncount");
	%>
		<div class="details">
			<div class="adj">
			<h4>Notifications</h4>
				<div class="cout<%=count %>" id="cout">
				<i class="fa-solid fa-circle"></i>
				<h6><%=count %></h6>
				</div>
			</div>
			<div id="name"><a href=''>All</a></div>
		</div> <!-- details -->
		
		<div class="closetab">
			<a href="userhomepage"><i class="fa-solid fa-xmark"></i></a>
		</div><!--closetab -->
	</div> <!-- notificationdiv -->
	
	<%
	List<NotificationModel> notificationlist =(List<NotificationModel>) request.getAttribute("list");
	if(notificationlist!=null){
	for(NotificationModel list : notificationlist){
	%>
	<div class="notificationuser" id="notificationuser<%=list.getView()%>">
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
	
		<i class="fa-solid fa-circle" id="dot"></i>
		
	<div class="deletenotification">
       <a href="deletenotification?nid=<%=list.getNid()%>"><i class="fa-solid fa-trash-can"></i></a>
	</div> <!-- deletenotification -->
	
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