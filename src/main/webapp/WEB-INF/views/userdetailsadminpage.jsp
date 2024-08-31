<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.model.*,java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/CSS/detailsadminStyle.css">
<script type="text/javascript" src='resources/JS/useradminvalidation.js'></script>
</head>
<body>

<div class="detailscontainer">

<div class="main">

<h2>User Details</h2>
<div class="columnname">
<div id="columnid">userId</div>
<div id="columnphoto">Profile Photo</div>
<div id="columnnam">Name</div>
<div id="columnemail">Email</div>
<div id="columnusername">UserName</div>
<div id="columnpassword">Password</div>
<div id="columdelete">Delete</div>
<div id="columnother">Others</div>
</div> <!-- columname -->

<div class="adjust">

<div id="detailsGrid">

<%

	List<RegistrationModel> list =(List<RegistrationModel>)request.getAttribute("list");
	for(RegistrationModel info:list){
		%>
	
		<div class="details">
		<div id="userid"><%=info.getRegisterid() %></div>
		<div id="photo"><img alt="" src="resources/Profile_Images/<%=info.getProfileimgname()%>"></div>
		<div id="cname"><%=info.getCustomername() %></div>
		<div id="email"><%=info.getEmail() %></div>
		<div id="username"><%=info.getUsername() %></div>
		<div id="password"><%=info.getPassword() %></div>
		<div id="delete"><a onclick="userDelete(<%=info.getRegisterid()%>)">Delete</a></div>
		<div id="freezeGrid<%=info.getRegisterid()%>">
		<%
		String status=info.getStatus();
		if(status.compareTo("freeze")==0){
			%>
			<div id="unfreezediv">
			<button name="unfreeze" id="unfreeze" onclick="unFreezeUserFun(<%=info.getRegisterid()%>)" >UnFreeze</button>            
			</div>
			<%
		}else{
			%>
			<div id="freezediv">
			<button name="freeze" id="freeze" onclick="freezeUserFun(<%=info.getRegisterid()%>)"  >Freeze</button>           
			</div>	
			<%
		}
		%>
		</div> <!-- freezeGrid -->
		</div> <!-- details -->
	
	<%
	}
%>
</div>
</div>
</div> <!-- main -->
</div>  <!-- detailscontainer -->

</body>
</html>