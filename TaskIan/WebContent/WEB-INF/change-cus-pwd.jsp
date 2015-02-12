
<jsp:include page="template-top.jsp" />
<jsp:include page="error-list.jsp" />
<jsp:include page="message.jsp" />

<p style="font-size:medium">
	Enter your new password
</p>


<p>
	<form method="POST" action="change-cus-pwd.do">
		<table>
			<tr>
				<td> <h4 style = "margin-left:380px">New Password: </td>
				<td><input type="password" name="newPassword" value=""/></td>
			</tr>
			<tr>
				<td><h4 style = "margin-left:380px"> Confirm New Password: </td>
				<td><input type="password" name="confirmPassword" value=""/></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><h6 align = "center">
					<input type="submit" align = "center" class="btn btn-default" name="button" value="Change Password"/>
				</h6>
				</td>
			</tr>
			</h4>
		</table>
	</form>
</p>

<jsp:include page="template-bottom.jsp" />
