<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="databeans.User"%>
<%@ page import="databeans.Photo"%>
<%@ page import="databeans.Location"%>
<%@ page import="databeans.LocationData"%>

<jsp:include page="template-top.jsp" />

<jsp:include page="error-list.jsp" />


<html>
  <head>
  <script LANGUAGE="JavaScript">
		function urlClick(url, site) {
		window.open(url);
		window.location = site;}
</script>
  </head>
<body>
    
<div class="container">
  <h3>${location} ${tag} Analysis</h3>
  <c:forEach var="photoFavors" items="${photoFavors}">  
  
  <c:out value = '${locationsData.location}' escapeXml='true' />   
  <c:out value = '${photoFavors.count_Like}' escapeXml='true' />
  <a href= "javascript:urlClick('${photoFavors.url}')">
       					<c:out value = '${photoFavors.url}' escapeXml='true' /></a>
  
  	</br>
  	</br>
  </c:forEach>   	
  </br>
  
</div>
<jsp:include page="template-bottom.jsp" />
  </body>
</html>

