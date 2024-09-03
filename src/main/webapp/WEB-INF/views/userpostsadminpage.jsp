<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.model.*,java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/CSS/userpostsadminStyle.css">
</head>
<body>
<div class="postcontainer">

<div class="menustag">
	<%@include file="adminmenus.jsp" %>
</div> <!-- menustag -->

<div class="main">

<h2>Posts Details</h2>
<div class="columnname">
<div id="columnpostid">PostId</div>
<div id="columnusername">UserName</div>
<div id="columnpostimage">Post Image</div>
<div id="columnpost">Post</div>
</div> <!-- columnname -->

<div class="adjust">

<div id="postsGrid">

<%

	List<PostLayoutModel> list =(List<PostLayoutModel>) request.getAttribute("list");
	for(PostLayoutModel info:list){
		%>
	
		<div class="details">
		<div id="postid"><%=info.getPostid()%></div>
		<div id="username"><%=info.getUsername() %></div>
		<div id="postimage"><img alt="" src="resources/Post_Images/<%=info.getImgname()%>"></div>
		<div id="post"><%=info.getPost()%></div>
		</div> <!-- details -->
	
	<%
	}
%>
</div>
</div>
</div> <!-- main -->
</div>  <!-- postcontainer -->
</body>
</html>