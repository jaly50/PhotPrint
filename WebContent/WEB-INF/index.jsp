<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="template-top.jsp" />
<div id="home">
	<div class="overlay">
	<div class=container>
<div class="bg-primary">

</div>
<h1 align="center"><strong><font size="6" face="lucida handwriting" color="white">${date}<br><br> Hello, ${user.userName}!</font></strong></h1>
<br>
<br>
<div>
  <c:forEach var="photo" items="${photos}">  
<img src="image.do?id=${photo.id}" width="33%" class="img-thumbnail"/>
</c:forEach>
</div>
</div>
</div>
</div>
 <div class="clearfix"> </div>

<jsp:include page="template-bottom.jsp" />
