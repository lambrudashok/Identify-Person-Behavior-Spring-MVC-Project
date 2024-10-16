<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.*,com.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="resources/CSS/followerStyle.css">
<script type="text/javascript" src="resources/JS/followervalidation.js"></script>
<!-- dark model below-->
<link rel="stylesheet" href="resources/CSS/darkmode.css">
<script type="text/javascript" src="resources/JS/darkmode.js"></script>
</head>
<body>

<div class="followercontainer">
	<div class="main">
		
		<div class="followersection">
			<div class="adj">
			<h4>Followers</h4>			
			<div class="closetab">
				<a href="profilepage"><i class="fa-solid fa-xmark"></i></a>
			</div><!--closetab -->
		</div> <!-- adj -->
			
			<%
			List <UserInfoModel> list =(List<UserInfoModel>) request.getAttribute("followerlist");
			if(list!=null){
	
				for(UserInfoModel userInfo:list){
					%>
					
					<div class="userfollower">
					
						<div class="photo">
							<img alt="" src="<%= request.getContextPath() %>/resources/Profile_Images/<%=userInfo.getProfileimage()%>">
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
				<h4 id="fnn">Follower Not Available</h4>
				<%
			}
			
			%>
		
		</div> <!-- followersection -->
	</div> <!-- main -->
	
	

</div>
</body>
</html>