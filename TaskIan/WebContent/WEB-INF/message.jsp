
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach var="m" items="${messages}">
	<h4 style="color: blue" align="center">${m}</h4>
</c:forEach>