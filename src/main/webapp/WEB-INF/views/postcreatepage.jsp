<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/CSS/postStyle.css">
<script type="text/javascript" src='resources/JS/postcreation.js'></script>
</head>
<body onload="postfun()">
<div class="postcontainer">
  
	<div class="postmenus">
	<%@ include file="menus.jsp" %>
	</div> <!-- postmenus -->
	
	
	<div class="postsection">
		<div class="postimg">
		<img alt="" id="twitterpic" src="resources/asset/twitter.png">
		</div> <!-- postimg -->
		
		<div class="postlayout">
		<h3>Create Post</h3>
		
		
		<%
		String msg = (String)session.getAttribute("postMsg");
		if(msg!=null){
			%>
			<h4 class="suces"><%=msg%></h4>
			<%
			session.removeAttribute("postMsg");
		}
		%>
		
			<form name="frm" id="postfrm" action="postsubmit" method="POST" enctype="multipart/form-data" >
			<h6>Post:</h6>
			<input type="text" name="postname" id="postname" onkeyup="postfun(this.value)" placeholder="type your thoughts ?" >
			
			
			<h6>Post Image:</h6>
			<div id="select">
			<input type="file" name="postimagefile" id="postimagefile"  onchange="changeImg(this)">
			</div>
			<button type="submit" id="pst" name="pst" value="post">Post</button>
			</form>
		
		</div><!--  postlayout -->
	</div> <!-- postsection -->
</div>
</body>
</html>