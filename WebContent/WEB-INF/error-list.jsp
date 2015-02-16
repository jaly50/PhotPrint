<br/>
<br/>
<br/>
<%
    java.util.List<String> errors = (java.util.List) request.getAttribute("errors");
	if (errors != null && errors.size() > 0) {
		out.println("<p style=\"font-size:medium; color:red\">");
		for (String error : errors) {
			out.println(error+"<br/>");
		}
		out.println("</p>");
	}
%>
<%
    String success =  (String)request.getAttribute("success");
	if (success != null && success.length() > 0) {
		out.println("<div class=\"alert alert-success\" role=\"alert\"><div class=\"text-center\">");
		out.println(success);

		out.println("</div></div>");
	}
%>