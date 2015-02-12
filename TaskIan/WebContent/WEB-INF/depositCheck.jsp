<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="template-top.jsp" />

<jsp:include page="error-list.jsp" />
<jsp:include page="message.jsp" />

<div class="container">
<br />
 <br />
 <br />
<form method="post" action="depositCheck.do">

<table style = " border-spacing: 5px">

<c:choose>
			<c:when test="${ (empty customer) }">
			<tr>
				<td> <h4 style="margin-left: 380px">
					Customer Username: &nbsp;&nbsp;&nbsp;</h4>
				</td> 
				<td><input name="username"
						type="text" value="${form.username}" />
				</td>
			</tr>	
			</c:when>
			<c:otherwise>
			<tr>
			<td><h4 style="margin-left: 380px">
			Customer Name:
					&nbsp;&nbsp;&nbsp; </h4>
			</td>
			<td><h4>${customer.firstname}&nbsp; ${customer.lastname}</h4>
			</td>
			</tr>
			<tr>
				<td ><h4 style="margin-left: 380px">Customer Username:

					&nbsp;&nbsp;&nbsp;</h4>
				</td>
				<td><h4>
				<input type="hidden"  name = "username" value="${customer.username}" > ${customer.username}
				</h4>
				</td>
			</tr>

			</c:otherwise>
		</c:choose>

 <tr>
 <td><h4 style = "margin-left:380px">   Deposit Amount: &nbsp;</h4>
 </td>
 <td>
     <h4> <input type="text" name="amount" placeholder="1-1000000"  value="${form.amount}"/> </h4>
      </td>
   </tr>
     <tr>
     <td> </td>
     <td> </td>
     </tr>
 
 <tr  >
 <td colspan="2" align="center"><h6 align = "center" style = "margin-left:380px">
 	<input align="center"  class="btn btn-default" type="submit" value="Deposit"></h6>
 </td>
 </tr>
 </table>
 <br />
 <br />
 <br />
 
</form>
</div>

  

<jsp:include page="template-bottom.jsp" />
