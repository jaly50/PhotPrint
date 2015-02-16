<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%	String[] subString = (String[])session.getAttribute("subString"); %>
<%	String location = (String)session.getAttribute("location"); %>
<jsp:include page="template-top.jsp" />


<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="js/blocksit.min_ian.js"></script>
<script>
$(document).ready(function() {
	//vendor script
	$('#header')
	.css({ 'top':-50 })
	.delay(1000)
	.animate({'top': 0}, 800);
	
	$('#footer')
	.css({ 'bottom':-15 })
	.delay(1000)
	.animate({'bottom': 0}, 800);
	
	//blocksit define
	$(window).load( function() {
		$('#container').BlocksIt({
			numOfCol: 5,
			offsetX: 8,
			offsetY: 8
		});
	});
	
	//window resize
	var currentWidth = 1100;
	$(window).resize(function() {
		var winWidth = $(window).width();
		var conWidth;
		if(winWidth < 660) {
			conWidth = 440;
			col = 2
		} else if(winWidth < 880) {
			conWidth = 660;
			col = 3
		} else if(winWidth < 1100) {
			conWidth = 880;
			col = 4;
		} else {
			conWidth = 1100;
			col = 5;
		}
		
		if(conWidth != currentWidth) {
			currentWidth = conWidth;
			$('#container').width(conWidth);
			$('#container').BlocksIt({
				numOfCol: col,
				offsetX: 8,
				offsetY: 8
			});
		}
	});
});
</script>
<link rel="shortcut icon" href="http://www.inwebson.com/wp-content/themes/inwebson2/favicn.ico" />
<link rel="canonical" href="http://www.inwebson.com/demo/blocksit-js/demo2/" />
<!-- Header -->




<!-- Content -->
<section id="wrapper">
<hgroup>
<h2>PhotPrint</h2>
<form action="showWrapper.do" method="POST">
<p><a href="showWrapper.do"><strong>Location: </strong></a>${location}</p>
 </form>
<%
	int i = 0;
	for(; i < subString.length;i++)  {
	
			
			
%>
	<form action="showTags.do" method="POST">
	<%if(i % 3 == 0) {%>
	
			<input type="hidden" name="location" value="${location}">
			<input type="submit" class="btn btn-danger" role="button" name ="tag" value = <%=subString[i]%>>
	
	<%} %>
	<%if(i % 3 == 1) {%>
	
			<input type="hidden" name="location" value="${location}">
			<input type="submit" class="btn btn-warning" role="button" name ="tag" value = <%=subString[i]%>>
	
	<%} %>
	<%if(i % 3 == 2) {%>
	
			<input type="hidden" name="location" value="${location}">
			<input type="submit" class="btn btn-info" role="button" name ="tag" value =<%=subString[i]%>>
	</form>
	<%} %>

<%} %>

</hgroup>


<div id="container">

<c:forEach var="w" items="${WrapperTable}">    
	  <div class="grid">
		<div class="imgholder"><a href="${w.url}"> <img class="img-responsive" src="${w.photo}"/></a> </div>
		<strong>${w.title}</strong>
		<c:forEach var="wt" items="${w.tags}"> 
				<strong>${wt}</strong>
		</c:forEach>
		<form action="like.do" method="POST">
			<input type="hidden" name="photo" value="${w.photo}"/>
			<input type="submit" class="btn btn-primary" role="button" value = "Like"/>${w.count_like}
		</form>
		<form action="dislike.do" method="POST">
			<input type="hidden" name="photo" value="${w.photo}"/>
			<input type="submit" class="btn btn-danger" role="button" value = "Dislike"/>${w.count_dislike}
		</form>
	  </div>
</c:forEach>

<%
	int j = 0;
	//for(; i < 25;i++)  {
				
			
%>
 <c:forEach var="twt" items="${Tweetlist}">    
	   	<%if(j % 3 == 0) {%>
         	   
	   <div class="grid" style="background-color:#E0E6F8;">
	  <strong>${twt.userScreenName} </strong> 
	  <strong>${twt.userTweet}</strong>
      </div>
      
      	<%j++;} %>
    <%  if(j %2 == 0) {%>
	   
	   <div class="grid" style="background-color:#F7D358;">
	  <strong>${twt.userScreenName}</strong> 
	  <strong>${twt.userTweet}</strong>
      </div>
      
      	<%j++;} %>
    <% if(j % 5 == 0) {%>
	   
	   <div class="grid" style="background-color:#E1F5A9;">
	  <strong>${twt.userScreenName} </strong> 
	  <strong>${twt.userTweet}</strong>
      </div>
      
      	<%j++;} %>
    
      
	   <div class="grid" style="background-color:#A9E2F3;">
	  <strong>${twt.userScreenName} </strong> 
	  <strong>${twt.userTweet}</strong>
      </div>
    <% j++;%>

</c:forEach>
<!----> 
</div>
<div class="clearfix"></div>
</section>

<jsp:include page="template-bottom.jsp" />
