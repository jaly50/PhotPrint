<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="template-top.jsp" />
<div class="bg-primary">
<h3 class="text-center" >${user.userName} said &quot;${description}&quot; in ${location}</h3>
</div>

<div>
  <c:forEach var="photo" items="${photos}">  
<img src="image.do?id=${photo.id}" class="img-circle"/>
</c:forEach>
</div>

 <div class="clearfix"> </div>

<jsp:include page="template-bottom.jsp" />
