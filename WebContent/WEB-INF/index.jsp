<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="template-top.jsp" />
<div class="bg-primary">
<h3 class="text-center" >${user.userName} said &quot;${description}&quot; in ${location}</h3>
</div>
<h1 align="center"><strong><font face="lucida handwriting" color="teal">${date}&nbsp&nbsp&nbsp&nbsp Hello, ${user.userName}!</font></strong></h1>
<div>
  <c:forEach var="photo" items="${photos}">  
<img src="image.do?id=${photo.id}" width="33%" height="55%" />
</c:forEach>
</div>

 <div class="clearfix"> </div>

<jsp:include page="template-bottom.jsp" />
