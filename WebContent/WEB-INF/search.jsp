<jsp:include page="template-top.jsp" />
<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places"></script>
<script src="js/place.js"></script>


<div id="home">
	<div class="overlay">
		<h1 align="center">
			<strong><font face="lucida handwriting" color="white"
				size="8">Hello, ${user.userName}!</font></strong>
		</h1>
		<h3 align="center">
			<strong><font face="lucida handwriting" color="AliceBlue">Where
					do you want to travel to?</font></strong>
		</h3>
		<h4 align="center">
			<strong><font face="lucida handwriting" color="AliceBlue">Type
					in the location and tags, we will show the information you
					need!</font></strong>
		</h4>
		<br />
		<form method="post" action="search.do">
			<h4 style="margin-left: 480px">
				<font face="comic sans MS" color="white"> Please input your
					destination:</font> <input id="searchPlace" placeholder="Pittsburgh"  boader="2px" type="text" name="location"
					value="" style="color: black" />
			</h4>
			<h4 style="margin-left: 580px">
				<font face="comic sans MS" color="white"> Please input tags:
				</font><input type="text" name="description" value="" style="color: black" />
			</h4>
		<br>
			<h4 style="margin-left: 680px">
				<input class="btn btn-default" type="submit" name="button"
					value="Search!" style="font-family: comic sans MS" />
			</h4>
		</form>
		<br /> <br />
	</div>
</div>
<jsp:include page="template-bottom.jsp" />