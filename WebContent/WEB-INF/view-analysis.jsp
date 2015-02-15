<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="databeans.User"%>
<%@ page import="databeans.Photo"%>
<%@ page import="databeans.Location"%>
<%@ page import="databeans.LocationData"%>

<jsp:include page="template-top.jsp" />

<jsp:include page="error-list.jsp" />


<html>
  <head>
    <!--Load the AJAX API-->
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">

      // Load the Visualization API and the piechart package.
      google.load('visualization', '1.0', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {
    	   var data = google.visualization.arrayToDataTable([
    	                                             	  	['Location',          'Number of Photos']
    	                                             	    <c:forEach  var="locationsData" items="${locationsData}"> 
    	                                             	    ,['${locationsData.location}', ${locationsData.number}]
    	                                             	    </c:forEach>
    	                                             	    ]);
    	                                             	  
    	   
    	   var options = {
    	      title: 'My PhotPrint Analysis',
    	      width: 1000,
    	      height: 563,
    	      hAxis: {
    	      title: 'Number of Photos',
    	      minValue: 0
    	       },
    	      vAxis: {
    	      title: 'Location'
    	      }
    	     
    	  };
        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.BarChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      };
    </script>
  </head>
<body>
    
<div class="container">
  <h3><b>${user.userName}'s PhotPrint Analysis</h3>
  <!--Div that will hold the pie chart-->
    <div id="chart_div"></div>
  <c:forEach var="locationsData" items="${locationsData}">  
   
   <h4> Location: ${locationsData.location} &nbsp&nbsp&nbsp&nbsp
    Number of Photos: ${locationsData.number}  
   </h4>       	  	
  	<form action="viewAnalysisDetail.do" method="POST">  
  		<input type="hidden" name="location" value="${locationsData.location}"/> 	
  		<table class="table table-bordered">
    <thead>
    <tr>
    <td align="center"><p style = "color: green">My Posted Tag at ${locationsData.location}</p></td>
    <td align="center"><p style = "color: green">Select</p></td>
    </thead>
    <tbody>     
     <c:forEach var="tag" items="${locationsData.tags}">    
      <tr>
        <td align="center">${tag}</td>
        <td align="center"><input type="radio" name="tag" value="${tag}"/></td>
      </tr>
	</c:forEach>
    </tbody>
    
  </table>
		<h4 align="center"><input type="submit" name="button" value="View Tag Analysis"/></h4>
  	</form> 
  	</br>
  	</br>
  </c:forEach>   	
  </br>
  
</div>
<jsp:include page="template-bottom.jsp" />
  </body>
</html>

