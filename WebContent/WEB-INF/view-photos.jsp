<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="template-top.jsp" />
<div class="bg-primary">
<h3 class="text-center" >${user.userName} said &quot;${description}&quot; in ${location}</h3>
</div>
</br>
</br>
<div id="home">
	<div class="overlay">
<div class="container">
<h2 align="center"> <strong><font face="comic sans MS" color="white">${user.userName} <br><br>  at ${location}, said "${description}" </font></strong> 
   </h3>       	  	
</br>
</br>
<div>
  <c:forEach var="photo" items="${photos}">  
<img src="image.do?id=${photo.id}" width="30%" class="img-circle"/>
</c:forEach>
</div>

 <div class="clearfix"> </div>
</div>
</div>
</div>
<jsp:include page="template-bottom.jsp" />
