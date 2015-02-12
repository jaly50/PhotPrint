<!-- Name: Charlotte Lin -->
<!-- Date: 01/18/2015 -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="template-top.jsp" />

<jsp:include page="error-list.jsp" />

<!-- Page Content -->
<!-- This is a very simple parallax effect achieved by simple CSS 3 multiple backgrounds, made by http://twitter.com/msurguy -->

<div class="container">
<div class="container">
<div class="container">
	<h2>Fund Price History</h2>
  <h4>Fund Name: ${fund.name}<br> Fund Ticker: ${fund.symbol }</h4>
    </br>     
  <table class="table table-bordered">
    <thead>
      <tr>       
        <th><p align = center>Date</th>
        <th><p align = right>Fund Price</th>
      </tr>
    </thead>
    <tbody>
     <c:forEach var="fundPriceHistory" items="${fundPriceHistory}">    
    	<tr> 
    		<td><p align = center><fmt:formatDate value='${fundPriceHistory.price_date}' pattern='MM/dd/yyyy'/></td>
        <td><p align = right><fmt:formatNumber value="${fundPriceHistory.price / 100}" pattern = "#,##0.00" type="number"/></td>
	</tr>			    
	</c:forEach>   	
    </tbody>
  </table>
  </br>

  
</div>
</div>
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
</div>

 
<jsp:include page="template-bottom.jsp" />