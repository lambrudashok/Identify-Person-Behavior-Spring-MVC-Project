<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.*,com.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/CSS/followingStyle.css">
<script type="text/javascript" src="resources/JS/followingvalidation.js"></script>
</head>
<body>

<div class="followingcontainer">
	<div class="main">
		<div class="leftmenu">
			<%@ include file="menus.jsp" %>
		</div> <!-- leftmenu -->
		
		<div class="followingsection">
			
			<%
			
			List <UserInfoModel> list =(List<UserInfoModel>) request.getAttribute("list");
			if(list!=null){
				%>
				<h4>Following</h4>
				<%
				for(UserInfoModel userInfo:list){
					%>
					
					<div class="userfollowing">
					
						<div class="photo">
							<img alt="" src="resources/Profile_Images/<%=userInfo.getProfileimage()%>">
						</div> <!-- photo -->
						
						<div class="userdetails">
							<div id="namediv">
								<h5><%=userInfo.getUsername() %></h5>
								<h6><%=userInfo.getName() %></h6>
							</div>
							<div id="btndiv">
								<form name="frm" action="userfollowing" method="post">
								<button name="following" id="following" value="<%=userInfo.getFollowingregisterid()%>" onmouseover="unfollowShow(this)" onmouseleave="followingShow(this)" >Following</button>            
								</form>							
							</div>
						</div> <!-- userdetails -->
				
					</div> <!-- userfollowing -->
					
					<%
				}
				
			}else{
				%>
				<h4>Following Not Available</h4>
				<%
			}
			
			%>
		
		</div> <!-- followingsection -->
	</div> <!-- main -->
	
	

</div>
</body>
</html>