<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
a.two:link {color:#000000;}
a.two:visited {color:#000000;}
a.two:hover {background:#66ff66;}
input:focus {
    background-color: #ffffcc;
}

a:link {
    background-color: yellow;
}
body {
    background-image: url("background1.jpg");
    
    background-position: center;
   
}
</style>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" >
		
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	google.charts.load('current', {'packages':['bar']});
	google.charts.setOnLoadCallback(drawChart);

	 function drawChart() {
		
		  data = google.visualization.arrayToDataTable
		([['Date', 'Temperature'], 
		  
		  <c:forEach var="dataIntValue" items="${output.list}" varStatus="loop">
	    	/* [timeConverter(${dataIntValue.dt}), ${dataIntValue.temp.max}, ${dataIntValue.temp.min}], */
	    	[timeConverter(${dataIntValue.dt}), ${dataIntValue.temp.day}],
			</c:forEach>
		  
		  ]);
		 var options = {
		          chart: {
		            title: 'Weather forecast for 7 days',
		            subtitle: 'These data will be stored in database',
		          },
		          bars: 'vertical',
		          vAxis: {format: 'decimal', title:'vaxis'},
		          hAxis: {title: 'something'},
		          height: 400,
		          backgroundColor: "transparent",
		          colors: ['#1b9e77', '#d95f02', '#7570b3']
		        }; 
		 var chart = new google.charts.Bar(document.getElementById('columnchart_material'));

	     chart.draw(data, options); 
	 
	 }




	function timeConverter(UNIX_timestamp){
		  var a = new Date(UNIX_timestamp * 1000);
		  var months = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
		  var year = a.getFullYear();
		  var month = months[a.getMonth()];
		  var date = a.getDate();
		  var time = date + ' ' + month + ' ' + year;
		  return time;
		}

	</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form method="post">
<div class="form-group">
<br/>
<center><label>City Name:</label><input  type="text" class="form-control" name="cityName" style="width: 300px" placeholder="Enter the name of the city" /></br>
<label>Number of Days(forecast):</label><input type="text" class="form-control" name="days" style="width: 300px" placeholder="Number of days, by default 7 days." /> <b>Note: 16 days Max, Default 7 days</b>
<br /></center>
</div>
<center><input type="submit" class="btn btn-success" value="Search" /></center>
<br/></form>
<b><div align="center"><a  class="two" href="WebServiceClient">Show Analysis</a></b></div><br/><br/>

<c:if test="${not empty output}">
City :
${output.city.name }, ${output.city.country }
<table class="table table-striped">
<tr>
 <th>Date(Today)</th>
 <td>
	<script type="text/javascript">
	document.write(timeConverter(${output.list[0].dt}));
	</script>
 </td>
</tr>
<tr>
 <th>Minimum Temperature(<sup>o</sup>C)</th>
 <td>${output.list[0].temp.min}</td>
</tr>
<tr>
 <th>Maximum Temperature(<sup>o</sup>C)</th>
 <td>${output.list[0].temp.max}</td>
</tr>
<tr>
 <th>Pressure(hPa)</th>
 <td>${output.list[0].pressure}</td>
</tr>
<tr>
 <th>Humidity(%)</th>
 <td>${output.list[0].humidity}</td>
</tr>
<tr>
 <th>Condition</th>
 <td>${output.list[0].weather[0].description }</td>
</tr>
<tr>
 <th>Wind Speed(m/h)</th>
 <td>${output.list[0].speed }</td>
</tr>
<%-- <c:forEach items="${output.list}" var="singleItem" varStatus="loop"> --%>
</table>
<br /><br />
    <div id="columnchart_material" style="width: 900px; height: 500px;"></div>
</c:if>



<%-- ${output.list } <br />
${output.city.country }
 --%>

</body>
</html>