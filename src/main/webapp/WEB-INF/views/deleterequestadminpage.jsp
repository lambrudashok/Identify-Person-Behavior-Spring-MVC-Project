<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.model.*,java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/CSS/deleterequestadminStyle.css">
<script type="text/javascript" src='resources/JS/useradminvalidation.js'></script>
</head>
<body>

<div class="deletecontainer">

<div class="menustag">
	<%@include file="adminmenus.jsp" %>
</div> <!-- menustag -->

<div class="main">

<div class="adjust">
	
	<div id="deleteuserrequestGrid">
	
	<%
	
		List<RegistrationModel> list =(List<RegistrationModel>) request.getAttribute("list");
		
		if(list!=null){
			
			%>
			<h2>User Requests Found</h2>
			<div class="columnname">
			<div id="columnid">RegisterId</div>
			<div id="columnphoto">Profile Photo</div>
			<div id="columnnam">Name</div>
			<div id="columnusername">UserName</div>
			<div id="columnarrive">Arrive Date</div>
			<div id="columnremaining">Remaining Days</div>
			<div id="columnother">Others</div>
			</div> <!-- columname -->
			<%
			
			for(RegistrationModel info:list){
				%>
			
				<div class="details">
				<div id="userid"><%=info.getRegisterid() %></div>
				<div id="photo"><img alt="" src="resources/Profile_Images/<%=info.getProfileimgname()%>"></div>
				<div id="cname"><%=info.getCustomername() %></div>
				<div id="username"><%=info.getUsername() %></div>
				<div id="arivedate"><%=info.getDate() %></div>
				<div id="remainingdays">days : <%=info.getRemain()%></div>
				<div id="delete"><a onclick="deleteAccountRequestUser(<%=info.getRegisterid()%>)">Confirm</a></div>
				</div> <!-- details -->
				<%
			}
		}else{
			%>
			<h2>Account Delete Requests Not Found</h2>
			<%
		}
		
	%>
	</div>
	</div>



	
	
	

</div>	<!-- main -->

</div>  <!-- deletecontainer -->

</body>
</html>