<jsp:include page="template-top.jsp" />
<jsp:include page="error-list.jsp" />
<script src="js/jquery.1.9.1.min.js"></script>
<script src="js/vendor/jquery.ui.widget.js"></script>
<script src="js/jquery.iframe-transport.js"></script>
<script src="js/jquery.fileupload.js"></script>
<!-- bootstrap just to have good looking page -->
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="js/myuploadfunction.js"></script>
<link href="css/style.css" rel="stylesheet">
<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places"></script>
<script src="js/place.js"></script>
<div id="home">
	<div class="overlay">
		<div class="well">
			<!-- Form Name -->
			<div class="text-center">
			<h2 align="center"><strong><font face="Browallia New" color = "black">Upload Photo</font></strong></h3> 
			<br>
			<h4 align="center"><strong><font face="lucida handwriting" color = "black">Your photo will also be uploaded to Twitter</font></strong></h3> 
			<hr>
			<input id="fileupload" type="file" name="files[]" data-url="upload"
				multiple>

			<h5 style="text-align: center">
			
				<i style="color: #ccc"><small>Max File Size: 2 Mb - 5
						Files maximum</small></i>
			</h5>

			<table id="uploaded-files" class="table">
				<tr>
					<th><p align="center"><strong><font face="arial" color = "black" size="3">File Name</font></strong></th>
					<th><p align="center"><strong><font face="arial" color = "black" size="3">File Size</font></strong></th>
					<th><p align="center"><strong><font face="arial" color = "black" size="3">File Type</font></strong></th>

				</tr>
			</table>

			<form method="post" action="upload.do" enctype="multipart/form-data">

				<!-- Textarea -->
				<div class="form-group">
				<p align="center"><strong><font face="lucida handwriting" color = "black" size="3">What do you want to say?</font></strong>
					
					<div class="">
						<textarea class="form-control" id="textarea" name="description"
							placeholder="Share your life today! You can add tags here like this. #tag #happy">${description}</textarea>
					</div>
				</div>

				<!-- Text input-->
				<div class="form-group">
				<p align="center"><strong><font face="lucida handwriting" color = "black" size="3">Where are you?</font></strong>
					
					<div class="">
						<input id="searchPlace" type="text" name="location"
							placeholder="Pittsburgh" value="${location}"
							class="form-control input-md">
					</div>
				</div>



				<div class="form-group">
					<div class="text-center">
						<button id="singlebutton" name="singlebutton"
							class="btn btn-primary">Upload</button>
					</div>
				</div>

			</form>
		</div>
		<div class="clearfix"></div>
	</div>
	<div class="clearfix"></div>
</div>
<div class="clearfix"></div>

<jsp:include page="template-bottom.jsp" />


