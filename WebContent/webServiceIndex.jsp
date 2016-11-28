<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
a.two:link {color:#000000;}
a.two:visited {color:#000000;}
a.two:hover {background:#66ff66;}
a:link {
    background-color: yellow;
}
body {
    background-image: url("background1.jpg");
    
    background-position: center;
   
}
input:focus {
    background-color: yellow;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
		([['Date', 'Maximum Temperature', 'Minimum Temperature'], 
		  
		  <c:forEach var="dataIntValue" items="${output.list}" varStatus="loop">
	    	/* [timeConverter(${dataIntValue.dt}), ${dataIntValue.temp.max}, ${dataIntValue.temp.min}], */
	    	[timeConverter(${dataIntValue.dt}), ${dataIntValue.temp.max}, ${dataIntValue.temp.min}],
			</c:forEach>
		  
		  ]);
		 var options = {
		          chart: {
		            title: 'Weather forecast for 7 days',
		            subtitle: 'Maximum Average : ' + (${max}).toFixed(2),
		          },
		          bars: 'vertical',
		          vAxis: {format: 'decimal', title:'vaxis'},
		          hAxis: {title: 'something'},
		          height: 400,
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


</head>
<body >
<div class="form-group">
<form method="post">
<b><div align="left"><a  class="two" href="RmiClient">Back</a></b></div><br/><br/>
<br/><br/>
<center>
<label >Search from Database </label><input type="text" name="cityName" class="form-control" placeholder="Enter the name of the city" style="width: 300px"/>
<br/><input type="submit" value="Fetch Results" class="btn btn-info"/></center>
</form>
</div>


<c:if test="${not empty output}">
<table class="table table-hover" style="width: 300px">
<tr>
<th>Average Temperature</th>
<td>${average}</td>
<tr>
<tr>
<th>Minimum Average</th> <td>${min }</td>
</tr>
<tr>
<th>Maximum Average</th><td> ${max }</td>
</tr>
</table>
City : ${output.city.name }, ${output.city.country }
<table class="table table-striped">
<tr>
 <th>Date</th>
 <th>Minimum Temperature</th>
 <th>Maximum Temperature</th>
 <th>Pressure</th>
 <th>Humidity</th>
 <th>Condition</th>
 <th>Wind</th>
</tr>
<c:forEach items="${output.list}" var="singleItem" varStatus="loop">
<tr>
<td>
<script type="text/javascript">
document.write(timeConverter(${singleItem.dt}));
</script>
</td>
<td>
${singleItem.temp.min}
</td>
<td>
${singleItem.temp.max}
</td>
<td>
${singleItem.pressure}
</td>
<td>
${singleItem.humidity}
</td>
<td>
${singleItem.weather[0].description }
</td>
<td>
${output.list[0].speed }
</tr>
</c:forEach>


</table>
<br /><br />
    <div id="columnchart_material" style="width: 900px; height: 500px;"></div>
</c:if>

</body>
</html>