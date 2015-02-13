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
    	                                                    ['Place',          'Number of Photos'],
    	                                                    <c:forEach items="${locationsData}" var="place"> 
    	            										,[${place.location}, ${place.number}]
    	            										</c:forEach>
    	                                                  ]);

    	                                                  var options = {
    	                                                    title: 'Population of Largest U.S. Cities',
    	                                                    width: 1000,
    	                                                    height: 563,
    	                                                    hAxis: {
    	                                                      title: 'Total Population',
    	                                                      minValue: 0
    	                                                    },
    	                                                    vAxis: {
    	                                                      title: 'City'
    	                                                    }
    	                                                  };
        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.BarChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
    </script>
  </head>
<body>
    
<div class="container">
  <h3>${user.userName}'s PhotPrint Analysis</h3>
  <c:forEach var="locationsData" items="${locationsData}">  
  	<c:out value = '${locationsData.location}' escapeXml='true' />
  	<c:out value = '${locationsData.number}' escapeXml='true' />
  	</br>
  </c:forEach>   	
  </br>
  <!--Div that will hold the pie chart-->
    <div id="chart_div"></div>
</div>
<jsp:include page="template-bottom.jsp" />
  </body>
</html>

