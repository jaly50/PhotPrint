
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<br/>
<c:forEach var="error" items="${errors}">
	<h4 align="center" style="color: red">${error}</h4>
</c:forEach>


