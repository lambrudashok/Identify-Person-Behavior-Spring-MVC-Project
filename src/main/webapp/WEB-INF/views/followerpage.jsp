<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.*,com.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/CSS/followerStyle.css">
<script type="text/javascript" src="resources/JS/followervalidation.js"></script>
</head>
<body>

<div class="followercontainer">
	<div class="main">
		<div class="leftmenu">
			<%@ include file="menus.jsp" %>
		</div> <!-- leftmenu -->
		
		<div class="followersection">
			
			<%
			List <UserInfoModel> list =(List<UserInfoModel>) request.getAttribute("followerlist");
			if(list!=null){
				%>
				<h4>Followers</h4>
				<%
				for(UserInfoModel userInfo:list){
					%>
					
					<div class="userfollower">
					
						<div class="photo">
							<img alt="" src="resources/Profile_Images/<%=userInfo.getProfileimage()%>">
						</div> <!-- photo -->
						
						<div class="userdetails">
							<div id="namediv">
								<h5><%=userInfo.getUsername() %></h5>
								<h6><%=userInfo.getName() %></h6>
							</div>
						</div> <!-- userdetails -->
				
					</div> <!-- userfollower -->
					
					<%
				}
				
			}else{
				%>
				<h4>Follower Not Available</h4>
				<%
			}
			
			%>
		
		</div> <!-- followersection -->
	</div> <!-- main -->
	
	

</div>
</body>
</html>