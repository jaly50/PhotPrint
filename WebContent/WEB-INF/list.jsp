<jsp:include page="template-top.jsp" />

<%@ page import="databeans.Tweetlist" %>
<p>
	<table>
<%
	for (Tweetlist list : (Tweetlist[])request.getAttribute("tweetslist")) {
%>
		<tr>
			<td><%=list.getUserScreenName() %></td><td><%=list.getUserTweet() %></td>
		</tr>
<%
		}
%>
	</table>
</p>

<jsp:include page="template-bottom.jsp" />
