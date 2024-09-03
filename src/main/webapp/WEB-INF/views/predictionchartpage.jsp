<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*, com.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="resources/CSS/predictionchartStyle.css">
<script type="text/javascript" src='resources/JS/predictionchartvalidation.js'></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/4.4.1/chart.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

</head>
<body>

<div class="chartcontainer">

<div class="chart">
	<div class="heading">
	<h1>Predicted Behavior</h1>
	<div class="closetab">
		<a href="predictionadminpage"><i class="fa-solid fa-xmark"></i></a>
	</div><!--closetab -->
	</div>
<%
int openessToExperience=Integer.parseInt(request.getAttribute("openessToExperience").toString());
int conscientiousness=Integer.parseInt(request.getAttribute("conscientiousness").toString());
int extroversion=Integer.parseInt(request.getAttribute("extroversion").toString());
int agreeableness=Integer.parseInt(request.getAttribute("agreeableness").toString());
int neuroticism=Integer.parseInt(request.getAttribute("neuroticism").toString());

%>
<div class="area">
<div>
  <canvas id="myChart"></canvas>
</div>
<script type="text/javascript">

const ctx = document.getElementById('myChart').getContext('2d');
new Chart(ctx, {
  type: 'bar',
  data: {
    labels: ['Openess To Experience', 'Conscientiousness', 'Extroversion', 'Agreeableness', 'Neuroticism'],
    datasets: [{
      label: 'person behavior ratio',
      data: [<%=openessToExperience%>, <%=conscientiousness%>, <%=extroversion%>, <%=agreeableness%>, <%=neuroticism%>],
      backgroundColor:[
			
			'rgba(54,162,235,0.2)',
			'rgba(255,206,86,0.2)',
			'rgba(75,192,192,0.2)',
			'rgba(153,102,255,0.2)',
			'rgba(255,99,132,0.2)',
		],
		borderColor:[
			
			'rgba(54,162,235,1)',
			'rgba(255,206,86,1)',
			'rgba(75,192,192,1)',
			'rgba(153,102,255,1)',
			'rgba(255,99,132,1)',
		],
      borderWidth: 1
    }]
  },
  options: {
    scales: {
      y: {
        beginAtZero: true
      }
    }
  }
});

</script>
</div> <!-- area -->
</div> <!-- chart -->
</div><!-- chartcontainer -->
</body>
</html>