<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@page import="java.util.*,com.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="resources/CSS/commentStyle.css">
<script type="text/javascript" src='resources/JS/predictionvalidation.js'></script> 
</head>
<body>
<div class="commentContainer">
<div class="main">
<div class="commentbox">

	<%
	UserInfoModel userInfo =(UserInfoModel)request.getAttribute("userInfo");
	%>
	<div class="profilediv">
	<div class="photo">
		<img alt="" src="resources/Profile_Images/<%=userInfo.getProfileimage()%>">
	</div> <!-- photo -->
	<div class="userdetails">
		<div class="namediv">
		<div id="name"><%=userInfo.getName() %></div>
		<div id="username"><%=userInfo.getUsername() %></div>
		</div>
	</div> <!-- userdetails -->
	<div class="closetab">
		<a onclick="closefun()"><i class="fa-solid fa-xmark"></i></a>
	</div> <!-- closetab -->
	</div>
	
	<%
	List<PostLayoutModel> commentlist =(List<PostLayoutModel>) request.getAttribute("commentlist");
	if(commentlist!=null){
	for(PostLayoutModel list : commentlist){
	%>
	<div class="commentuser">
	<div class="photouser">
		<img alt="" src="resources/Profile_Images/<%=list.getProfileimage()%>">
	</div> <!-- photouser -->
	<div class="userdetailscomment">
		<div class="namedivuser">
		<div id="username"><%=list.getUsername() %></div>
		<div id="commentpro"><%=list.getComment() %></div>
		</div>
		<div id="commentdate"><%=list.getCommentDate() %></div>
	</div> <!-- userdetailscomment -->
	</div>  <!-- commentuser -->
	<%
	}
	}else{
		%>
		<div id="cnf">Comments Not Found</div>
		<%
	}
	%>
</div> <!-- commentbox -->
</div> <!-- main -->
</div> <!-- commentContainer -->
</body>
</html>