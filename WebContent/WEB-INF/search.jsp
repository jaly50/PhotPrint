<jsp:include page="template-top.jsp" />


<jsp:include page="error-list.jsp" />


<div class="container">
	<br /> <br /> 
	<form method="post" action="search.do">
		<h4 style="margin-left: 380px">
			Location: <input type="text" name="location" value="" />
		</h4>
		<br />
		<h4 style="margin-left: 380px">
			Description: <input type="text" name="description" value="" />
		</h4>
		<br />
		<h4 style="margin-left: 480px">
			<input class="btn btn-default" type="submit" name="button"
				value="Search!" />
		</h4>
	</form>
	<br /> <br />
</div>

<jsp:include page="template-bottom.jsp" />