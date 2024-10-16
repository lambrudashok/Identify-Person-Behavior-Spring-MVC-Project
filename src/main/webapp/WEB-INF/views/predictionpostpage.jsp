<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.*,com.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/CSS/predictionpostStyle.css"> 
<script type="text/javascript" src='resources/JS/predictionvalidation.js'></script> 

</head>
<body>

<div class="postcontainer">

<div class="menustag">
	<%@include file="adminmenus.jsp" %>
</div> <!-- menustag -->

<div class="main">

<%
int registerId= Integer.parseInt(request.getAttribute("registerId").toString());
List<PostLayoutModel> list =(List<PostLayoutModel>) request.getAttribute("list");
%>
<form name="frm" action="predictionoverall" method="POST">
<button id="overallpredictionbtn" name="overallpredictionbtn" value="<%=registerId%>" >Over All Prediction</button>
</form>
<%
if(list!=null){

%>
<h2>Select Post For Prediction</h2>
<div class="columnname">
<div id="columnpostid">PostId</div>
<div id="columnusername">UserName</div>
<div id="columnpostimage">Post Image</div>
<div id="columnpost">Post</div>
</div> <!-- columnname -->

<div class="adjust">

<%	
	for(PostLayoutModel info:list){
	%>
	<a href="predictionsinglepost?postid=<%=info.getPostid()%>">
		<div class="details">
		<div id="postid"><%=info.getPostid()%></div>
		<div id="username"><%=info.getUsername() %></div>
		<div id="postimage"><img alt="" src="<%= request.getContextPath() %>/resources/Post_Images/<%=info.getImgname()%>"></div>
		<div id="post"><%=info.getPost()%></div>
		</div> <!-- details -->
	</a>
	 <%
	}
%>
</div>
<%

}else{
%>	
<h2>Posts Not Found</h2>
<%
}
%>

</div> <!-- main -->
</div>  <!-- postcontainer -->
</body>
</html>