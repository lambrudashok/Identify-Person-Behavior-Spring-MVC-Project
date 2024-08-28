<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*, com.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/CSS/predictionStyle.css">
<script type="text/javascript" src="resources/JS/predictionvalidation.js"></script>
</head>
<body>

<div class="searchcontainer">
	
	<div class="searchsection">
		<div class="searchbar">
	   	<input type="search" id="searchuser" value="" placeholder="Search User Here For Prediction" onkeyup="searchUserProfile(this.value)">
	   </div><!--  searchbar -->
	   
	   <div class="userInfo">
	   		<div class="info">
	   	
	   	<div id="searchGrid">
	  
	   	<%
	   	List <UserInfoModel> list =(List<UserInfoModel>) request.getAttribute("list");
			
			if(list!=null){
				
				for(UserInfoModel userInfo:list){
					%>
					
					<a class="userappinfo" id="userappinfo" href="predictionpostpage?id=<%=userInfo.getRegisterid() %>" > 
						<div class="photo">
							<img alt="" src="resources/Profile_Images/<%=userInfo.getProfileimage()%>">
						</div> <!-- photo -->
						<div class="userdetails">
							<div class="namediv">
							<div id="name"><%=userInfo.getName() %></div>
							<div id="username"><%=userInfo.getUsername() %></div>
							</div>
						</div> <!-- userdetails -->
					</a> <!-- userappinfo -->
					
					<%
				}
				
			}else{
				%>
				<h4>User Not Found</h4>
				<%
			}
			
			%>
	   	
	   	
	   	</div>
	   	</div> <!-- info -->
	   	
	   </div> <!-- userInfo -->
	   
	   
	</div> <!-- searchsection -->
</div> <!-- searchcontainer -->

</body>
</html>