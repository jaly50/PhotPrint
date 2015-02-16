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
  <script LANGUAGE="JavaScript">
		function urlClick(url, site) {
		window.open(url);
		window.location = site;}
</script>

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
    	                                             	  	['Photo Title',          'Number of People in Favor of This Photo']
    	                                             	    <c:forEach  var="photoFavors" items="${photoFavors}"> 
    	                                             	    ,['${photoFavors.title}', ${photoFavors.count_Like}]
    	                                             	    </c:forEach>
    	                                             	    ]);
    	                                             	  
    	   
    	   var options = {
    	      title: 'Top 5 "LIKE" Photos',
    	      width: 1000,
    	      height: 563,
    	      hAxis: {
    	      title: 'Number of People in Favor of This Photo',
    	      minValue: 0
    	       },
    	      vAxis: {
    	      title: 'Photo Title'
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
<br>

<br>
<h2 align="center"><strong><font face="lucida handwriting" color="crimson">Top 5 "LIKE" Photos</font></strong></h2>
<h3 align="center"><strong><font face="comic sans MS" color="teal">at ${location}<br>of "${tag}" Tag</font></strong></h3>

<div id="chart_div"></div>
  <c:forEach var="photoFavors" items="${photoFavors}">  
  <p><strong><font face="comic sans MS" color="teal"
				size="3">Number of "Like" Click: </font></strong><strong><font face="arial" color="black" size="3"><c:out value = '${photoFavors.count_Like}' escapeXml='true' /></font></strong></p>
  <p><strong><font face="comic sans MS" color="teal"
				size="3">Photo Title: </font></strong><strong><font face="arial" color="black" size="3"><c:out value = '${photoFavors.title}' escapeXml='true' /></font></strong></p>
  
  <p><strong><font face="comic sans MS" color="teal"
				size="3">Click to View Photo: </font></strong><strong><font face="arial" color="black" size="3"><a href= "javascript:urlClick('${photoFavors.url}', 'viewAnalysis.do')">
       					<c:out value = '${photoFavors.url}' escapeXml='true' /></a></font></strong></p>
  
  	</br>
  	</br>
  </c:forEach>   	
  </br>
  
</div>
<jsp:include page="template-bottom.jsp" />
  </body>
</html>

