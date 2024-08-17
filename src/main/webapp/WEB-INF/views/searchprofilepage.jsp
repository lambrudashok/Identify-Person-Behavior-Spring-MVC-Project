<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*, com.model.*,com.service.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="CSS/searchprofileStyle.css">
<script type="text/javascript" src="JS/searchprofilevalidation.js"></script>
</head>
<body>
<div class="searchcontainer">
  
	<div class="leftmenus">
	<%@ include file="menus.jsp" %>
	</div> <!-- leftmenus -->
	
	
	<div class="searchsection">
		<div class="searchbar">
	   	<input type="search" id="searchuser" value="" placeholder="Search" onkeyup="searchUserProfile(this.value)">
	   </div><!--  searchbar -->
	   
	   <div class="userInfo">
	   		<div class="info">
	   	
	   	<div id="searchGrid">
	  
	   	<%
			UserSearchService searchSer = new UserSearchService();
	   	List <UserInfoModel> list = searchSer.fetchAllUserDetails();
			
			if(list!=null){
				
				for(UserInfoModel userInfo:list){
					%>
					
					<a class="userappinfo" id="userappinfo" href="anotheruserprofilepage.jsp?id=<%=userInfo.getRegisterid() %>" > 
						<div class="photo">
							<img alt="" src="Profile_Images/<%=userInfo.getProfileimage()%>">
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