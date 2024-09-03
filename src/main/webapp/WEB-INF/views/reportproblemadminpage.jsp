<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.model.*,java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/CSS/reportadminStyle.css">
<script type="text/javascript" src="resources/JS/notification.js"></script>
</head>
<body>


<div class="reportcontainer">

<div class="menustag">
	<%@include file="adminmenus.jsp" %>
</div> <!-- menustag -->

<div class="main">
<%
	List<ReportProblemModel> list =(List<ReportProblemModel>)request.getAttribute("list");
	if(list!=null){
%>

<h2>Reports</h2>
<div class="columnname">
<div id="columnid">ReportId</div>
<div id="columnusername">UserName</div>
<div id="columndate">Date</div>
<div id="columtitle">Title</div>
<div id="columdescription">Description</div>
<div id="columuserid">userID</div>
<div id="columstatus">Status</div>
</div> <!-- columname -->

<div class="adjust">

<div id="detailsGrid">

	<%
	for(ReportProblemModel info:list){
	%>
	
		<div class="details" >
		<div id="reportid"><%=info.getReportid() %></div>
		<div id="username"><%=info.getUsername() %></div>
		<div id="date"><%=info.getDate() %></div>
		<div id="title"><%=info.getTitle() %></div>
		<div id="description"><%=info.getDescription() %></div>
		<div id="userid"><%=info.getRegisterid() %></div>
		<div id="solveGrid<%=info.getReportid()%>">
		<%
		String status=info.getStatus();
		if(status.compareTo("pending")==0){
			%>
			<div id="pendingdiv">
			<input type="submit" value="pending" name="pending" id="pending" onclick="pendingReportFun(<%=info.getReportid()%>,'<%=info.getTitle()%>','<%=info.getRegisterid()%>')" >            
			</div>
			<%
		}else{
			%>
			<div id="solvediv">
			<button name="solve" id="solve"  >Solve</button>           
			</div>	
			<%
		}
		%>
		</div> <!-- solveGrid -->
		</div> <!-- details -->
	
	<%
	}
%>
</div>
</div>

<%
	}else{
		%>
		<h2>Reports Not Found</h2>
		<%
	}
%>

</div> <!-- main -->
</div>  <!-- detailscontainer -->

</body>
</html>