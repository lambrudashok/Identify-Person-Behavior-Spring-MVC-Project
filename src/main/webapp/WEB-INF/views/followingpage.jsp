<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.*,com.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="resources/CSS/followingStyle.css">
<script type="text/javascript" src="resources/JS/followingvalidation.js"></script>
<!-- dark model below-->
<link rel="stylesheet" href="resources/CSS/darkmode.css">
<script type="text/javascript" src="resources/JS/darkmode.js"></script>
</head>
<body>

<div class="followingcontainer">
	<div class="main">
		<div class="followingsection">
		<div class="adj">
			<h4>Following</h4>			
			<div class="closetab">
				<a href="profilepage"><i class="fa-solid fa-xmark"></i></a>
			</div><!--closetab -->
		</div> <!-- adj -->
			<%
			
			List <UserInfoModel> list =(List<UserInfoModel>) request.getAttribute("list");
			if(list!=null){
				
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
				<h4 id="fnn">Start Following Someone!</h4>
				<%
			}
			
			%>
		
		</div> <!-- followingsection -->
	</div> <!-- main -->
	
	

</div>
</body>
</html>