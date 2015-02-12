<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

     <script type="text/javascript" src="https://www.google.com/jsapi?autoload={'modules':[{'name':'visualization','version':'1','packages':['corechart']}]}"></script>
  <script type="text/javascript">
  google.load('visualization', '1', {packages: ['corechart']});
  google.setOnLoadCallback(drawChart);

  function drawChart() {

    var data = new google.visualization.DataTable();
    data.addColumn('date', 'X');
    data.addColumn('number', '${fund.name}');

    data.addRows([
                  <c:forEach var="ph" items="${fundPriceHistory}">    
            	  <fmt:formatDate value='${ph.price_date}' var='year' type='date' pattern='yyyy'/>
            	  <fmt:formatDate value='${ph.price_date}' var='month' type='date' pattern='MM'/>
            	  <fmt:formatDate value='${ph.price_date}' var='day' type='date' pattern='dd'/>
            	  <fmt:formatNumber value='${ph.price/100}' var='price' type='number' minFractionDigits='2' maxFractionDigits='2'/>
            	  [new Date('${year}', '${month - 1}', '${day}'), '${price}'],
                  </c:forEach>   	
  
                ]);


    var options = {
      width: 1000,
      height: 563,
      hAxis: {
        title: 'Date'
      },
      vAxis: {
        title: 'Price'
      }
    };

    var chart = new google.visualization.LineChart(
      document.getElementById('ex0'));

    chart.draw(data, options);

  }
    
  </script>

 <div id="ex0"></div>
