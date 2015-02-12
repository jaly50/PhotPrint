<jsp:include page="template-top.jsp" />
<jsp:include page="error-list.jsp" />
<jsp:include page="message.jsp" />

<p>
<div class="container">

<form method="post" action="create_employee.do">
	<input type="hidden" name="redirect" value="${redirect}" />
	<table>
	   
		<tr> 
			<td><h4 style = "margin-left:380px">Username:<span style="color:red;">*</span></td>
			<td><input type="text" name="username" value="${form.username}" placeholder="Alphanumerics"/></td>
		</tr>
		<br>
		<tr>
			<td><h4 style = "margin-left:380px">First Name:<span style="color:red;">*</span></td>
			<td><input type="text" name="firstName"
				value="${form.firstName}" placeholder="Alphabets"/></td>
		</tr>
		<br>
		<tr>
			<td><h4 style = "margin-left:380px">Last Name:<span style="color:red;">*</span></td>
			<td><input type="text" name="lastName" value="${form.lastName}" placeholder="Alphabets"/></td>
		</tr>
<br>
		<tr>
			<td><h4 style = "margin-left:380px">Password:<span style="color:red;">*</span></td>
			<td><input type="password" name="password" value="" placeholder="6 to 15 characters long" /></td>
		</tr>
	<br>
		<tr>
			<td><h4 style = "margin-left:380px">Confirm Password:<span style="color:red;">*</span></td>
			<td><input type="password" name="confirm" value="" /></td>
		</tr>
		<br>
		<tr>
			<td colspan="2" align="center"><h6 align = "center" style = "margin-left:380px">
 <input align = "center" class="btn btn-default" type="submit" name="button" value="Create" /></td>
		</h6>
		</tr>
		<tr>
		<td><h6 style = "margin-left:380px"><span style="color:red;">*</span> indicates required</td>
		</tr>
		
	</table>
</form>
</p>
</div>
<jsp:include page="template-bottom.jsp" />

