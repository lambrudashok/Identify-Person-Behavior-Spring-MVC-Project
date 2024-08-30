<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/CSS/errorpageStyle.css">
</head>
<body>
<div class="errorpageContainer">
<div class="main">

<div class="errorphoto">
 <img alt="" src="resources/asset/error404image.jpg">
</div> <!-- errorphoto -->

<div class="content">
<h1>Page Not Found</h1>
<div id="heading1">Sorry, we not find page you requested.</div>
<div id="heading2">Go back to site</div>
<form action="loginpage" method="post">
<button id="btn" >Login Page</button>
</form>
</div>	<!-- content -->

</div> <!-- main -->
</div> <!-- errorpageContainer -->
</body>
</html>