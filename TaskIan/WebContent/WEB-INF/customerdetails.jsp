
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="template-top.jsp" />
<h4 style = "margin-left:380px">

<table border="1">

    <input type="hidden" name="customer_id" value="${customerdetails.customer_id}" />
        <h4>	
		<tr>
			<td align="center"><h4>Customer_id:</td>
	
			<td align="center"><h4>${customerdetails.customer_id}</td>
		</tr>
		<tr>
		    
			<td align="center"><h4>First Name:</td>
			<td align="center"><h4>${customerdetails.firstname}</td>
		</tr>
		<tr>
		    
			<td align="center"><h4>Last Name:</td>		
			<td align="center"><h4>${customerdetails.lastname}</td>
		</tr>
		<tr>
		    
			<td align="center"><h4>Address Line1:</td>
			<td align="center"><h4>${customerdetails.addr_line1}</td>
		</tr>
		<tr>
		     
			<td align="center"><h4>Address Line2:</td>			
			<td align="center"><h4>${customerdetails.addr_line2}</td>
		</tr>
		<tr>
		    
			<td align="center"><h4>City:</td>
			<td align="center"><h4>${customerdetails.city}</td>
		</tr>
		<tr>
			
			<td align="center"><h4>State:</td>
			<td align="center"><h4>${customerdetails.state}</td>
		</tr>
		<tr>
		
			<td align="center"><h4>Zip:</td>	
			<td align="center"><h4>${customerdetails.zip}</td>
		</tr>
		<tr>
		
			<td align="center"><h4>Available Balance:</td>
			<td align="center"><h4>${customerdetails.availablebalance}</td>
		</tr>
		<tr>
		    
			<td align="center"><h4>Total Balance:</td>
			<td align="center"><h4>${customerdetails.totalbalance}</td>
		</tr>
		
		
</table>
</h4>
<jsp:include page="template-bottom.jsp" />
