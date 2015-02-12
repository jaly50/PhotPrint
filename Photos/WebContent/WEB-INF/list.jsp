<jsp:include page="template-top.jsp" />

<%@ page import="databeans.Favorite" %>
<p>
	<table>
<%
	for (Favorite photo : (Favorite[])request.getAttribute("photoList")) {
%>
		<tr>
			<td><a href="view.do?id=<%=photo.getId()%>"><%=photo.getCaption()%></a></td>
		</tr>
<%
		}
%>
	</table>
</p>

<jsp:include page="template-bottom.jsp" />
