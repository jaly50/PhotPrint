<jsp:include page="template-top.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<br/>
<script type="text/javascript"> 
var t=3;
setInterval("refer()",1000); 
function refer(){  
    if(t==0){ 
        location="transitionDay.do"; 
    } 
    document.getElementById('show').innerHTML="The page will turn to TransitionDay after "+t+" seconds";  
    t--;
} 
</script> 

<c:forEach var="m" items="${messages}">
	<h4 style="color: blue" align="center">${m}</h4>
</c:forEach>
<br/>
<h4 style="color: blue" align="center"><span id="show"></span></h4>

<br/>
<br/>

<jsp:include page="template-bottom.jsp" />