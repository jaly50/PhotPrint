<html>
  <head>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['Task', 'Hours per Day'],
          ['Friend',     11],
          ['Travel',      2],
          ['Family',  2],
          ['Myself', 2],
          ['Others',    7]
        ]);

        var options = {
          title: 'My Photo Category'
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

<<<<<<< Updated upstream
<!-- Page Content -->
<!-- This is a very simple parallax effect achieved by simple CSS 3 multiple backgrounds, made by http://twitter.com/msurguy -->

<div class="container">
<div class="container">
<div class="container">
  <h2>${customer.firstname} ${customer.lastname}'s Transaction History</h2>
    </br>
  
  <table class="table table-bordered">
    <thead> 
    <tr>
        <th><p align = left>Date</th>
        <th><p align = left>Operation</th>
        <th><p align = left>Fund Name</th>
        <th><p align = left>Fund Ticker</th>
        <th><p align = right>Shares</th>
        <th><p align = right>Price</th>
        <th><p align = right>Amount</th>
      </tr>      
    </thead>
    
    <tbody>
    	<c:forEach var="transactionInfo" items="${transactionInfo}">    
    	<tr> 
	    <td><p align = left><c:out value = '${transactionInfo.date}' escapeXml='true' /></td>
        <td><p align = left><c:out value = '${transactionInfo.operation}' escapeXml='true' /></td>
        <td><p align = left><c:out value = '${transactionInfo.name}' escapeXml='true' /></td>
        <td><p align = left><c:out value = '${transactionInfo.symbol}' escapeXml='true' /></td>
        <td><p align = right><c:out value = '${transactionInfo.shares}' escapeXml='true' /></td>
        <td><p align = right><c:out value = '${transactionInfo.price}' escapeXml='true' /></td>
        <td><p align = right><c:out value = '${transactionInfo.amount}' escapeXml='true' /></td>
	</tr>			    
	</c:forEach>   	 
    </tbody>
  </table>
</div>
</div>
   
    <br />
    <br />
    <br />
</div>

 
<jsp:include page="template-bottom.jsp" />
=======
        chart.draw(data, options);
      }
    </script>
  </head>
  <body>
  	<h2>2014-12-05, in NewYork, I took 100 photos.
  	55 myself.
  	10 friends.
  	20 others.
  	Happy!
  	<a href= clickPhotoHistory.do>View Photos</a>	
  	<a href= clickPhotoHistory.do>Back to previous page</a>	
  	</h2>
    <div id="piechart" style="width: 900px; height: 500px;"></div>
  </body>
</html>
>>>>>>> Stashed changes
