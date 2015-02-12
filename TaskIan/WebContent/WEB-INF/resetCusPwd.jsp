<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="template-top.jsp" />
<jsp:include page="error-list.jsp" />
<jsp:include page="message.jsp" />

<div class="container">
	<br /> <br /> <br />
	<form method="post" action="resetCustomerPassword.do">
		<c:choose>
			<c:when test="${ (empty customer) }">
				<h4 style="margin-left: 380px">
					Customer Username: &nbsp;&nbsp;&nbsp; <input name="username"
						type="text" value="${form.username}" />
				</h4>
			</c:when>
			<c:otherwise>
			<h4 style="margin-left: 380px">Customer Name:
					&nbsp;&nbsp;&nbsp; ${customer.firstname}&nbsp; ${customer.lastname}</h4>
			<br />
				<h4 style="margin-left: 380px">Customer Username:
					&nbsp;&nbsp;&nbsp; <input type="hidden"  name = "username" value="${customer.username}" > ${customer.username}</h4>
			</c:otherwise>
		</c:choose>
		<br />
		<h4 style="margin-left: 380px">
			New Password: &nbsp;&nbsp;&nbsp; <input name="newPassword"
				type="password" value="${form.newPassword}" />
		</h4>
		<br />

		<h4 style="margin-left: 380px">
			Confirm: &nbsp;&nbsp;&nbsp; <input type="password" name="confirm"
				value="${form.confirm}" />
		</h4>
		<br />
		<h6 align="center">
			<input align="center" class="btn btn-default" type="submit" value="Reset">
		</h6>

		<br /> <br /> <br />
	</form>
</div>



<jsp:include page="template-bottom.jsp" />
