<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.model.*,java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="resources/CSS/editprofileStyle.css">
<script type="text/javascript" src='resources/JS/editprofilevalidation.js'></script>
</head>
<body>
	
<div class="editcontainer">

<div class="editsection">
	
	<%
	
	 UserInfoModel model= (UserInfoModel)request.getAttribute("updatedata");

	%>
	<div class="divider" id="editGrid">
	<div class="heading">
	<h3>Edit Profile</h3>
	<div class="closetab">
			<a href="profilepage"><i class="fa-solid fa-xmark"></i></a>
	</div><!--closetab -->
	</div>
	
	<form name="frm" action="updateprofilephoto" method="POST" enctype="multipart/form-data" onsubmit="return profilefun()">	
	<div class="photo">
		<div class="image" id="imageGrid">
			<img onclick="a()" id="profilepic" src="resources/Profile_Images/<%=model.getProfileimage()%>">
		<a onclick="a()"><input type="file" class="chooseprofile" name="chooseprofile"  id="chooseprofile" style="display:none;" onchange="profileImgChange(this)" >+</a>
		</div>
	<div class="userdetail">
		<h4><%=model.getUsername() %></h4>
		<h4 id="name"><%=model.getName() %></h4>  
	</div>
		<button type="submit" name="changebtn" id="changebtn" >Change Profile</button>
	</div>
	</form>
	
	<input type="text" name="cname" id="cname" placeholder="name"><br>
	<div id="msgname" class="msg"></div>
	<input type="text" name="username" id="username" placeholder="Username"><br>
	<div id="msgusername" class="msg"></div>
	<input type="text" name="email" id="email" placeholder="Email"><br>
	<div id="msgemail" class="msg"></div>
	<input type="text" name="bio" id="bio" placeholder="Bio"><br>
	
	<div id="msg"></div>
	<button type="submit" name="editbtn" id="editbtn" onclick="return checkField()" >Save</button>
	
	</div> <!-- divider -->
</div> <!-- editsection -->

</div> <!-- editcontainer -->
</body>
</html>