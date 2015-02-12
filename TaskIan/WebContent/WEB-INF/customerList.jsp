<!--View Customer Account-->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="template-top.jsp" />

<jsp:include page="error-list.jsp" />
<div class="container">
	<div class="container">
	<div class="container">
	  <h2>Account Management</h2>
	    <br>
	    <br>
	    <form method="POST" action="viewCustomerList.do">
	
	
	 
 <h5>Customer List:</h5>     
    <h4 align="right" style="margin-right: 55px">  
       	<input class="btn btn-default" type="submit" name="operation"  value="View Account">
       <input class="btn btn-default" type="submit" name="operation"  value="Deposit Check">
       <input class="btn btn-default" type="submit" name="operation"  value="Reset Password">
       <input class="btn btn-default" type="submit" name="operation"  value="View Transaction History">
 </h4>       
  <table class="table table-bordered">
    <thead>
      <tr>
        <th><p align="left">Full Name</th>
        <th><p align="left">User Name</th>
        <th><p align="right">Current Balance</th>
        <th><p align="right">Available Balance</th>
        <th></th>
      </tr>
    </thead>
    <tbody>
    <c:forEach var="c" items="${customerList}">
		<tr>
			<td valign="top">
					<input type="hidden" name="customer_id"
						value="${c.customer_id}" /> ${c.firstname}&nbsp;${c.lastname}
			</td>
			 <td>${c.username}</td>
        <td align="right"><fmt:formatNumber type="number" 
            pattern="#,##0.00" value="${c.totalbalance/100}" /></td>
        <td align="right"><fmt:formatNumber type="number" 
            pattern="#,##0.00" value="${c.availablebalance/100}" /></td>
        <td align="center"><input type="radio" name="select" value="${c.customer_id}"></td>
		</tr>
	</c:forEach>
    </tbody>
  </table>
  <br/>
  </form>

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
