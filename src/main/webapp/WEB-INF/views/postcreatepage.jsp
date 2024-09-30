<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/CSS/postStyle.css">
<script type="text/javascript" src='resources/JS/postcreation.js'></script>
<!-- dark model below-->
<link rel="stylesheet" href="resources/CSS/darkmode.css">
<script type="text/javascript" src="resources/JS/darkmode.js"></script>
</head>
<body>
<div class="postcontainer" onload="postfun()">
  
	<div class="postmenus">
	<%@ include file="menus.jsp" %>
	</div> <!-- postmenus -->
	
	
	<div class="postsection">
		
		<div class="postlayout">
		<h3>Create Post</h3>
		
		<div class="postimg">
			<img alt="" id="twitterpic" src="resources/Post_Images/person.png">
		</div> <!-- postimg -->
		
			<form name="frm" id="postfrm" action="postsubmit" method="POST" enctype="multipart/form-data" >
			<h6 id="pm">Post Image:</h6>
			<div id="select">
			<input type="file" name="postimagefile" id="postimagefile"  onchange="changeImg(this)">
			</div>
			<h6>Caption:</h6>
			<textarea name="postname" id="postname" onkeyup="postfun(this.value)" placeholder="type your thoughts ?" ></textarea>
			<%
				String msg = (String)session.getAttribute("postMsg");
				if(msg!=null){
					%>
					<h4 class="suces"><%=msg%></h4>
					<%
					session.removeAttribute("postMsg");
				}
				%>
			<br><button type="submit" id="pst" name="pst" value="post">Post</button>
			</form>
		
		</div><!--  postlayout -->
	</div> <!-- postsection -->
</div>
</body>
</html>