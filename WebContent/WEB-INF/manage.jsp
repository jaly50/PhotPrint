

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

<script>
	function initialize() {

		var input = /** @type {HTMLInputElement} */
		(document.getElementById('searchPlace'));

		var autocomplete = new google.maps.places.Autocomplete(input);
		autocomplete.bindTo('bounds', map);

		var infowindow = new google.maps.InfoWindow();
		var marker = new google.maps.Marker({
			map : map
		});

		google.maps.event.addListener(autocomplete, 'place_changed',
				function() {
					infowindow.close();
					marker.setVisible(false);
					var place = autocomplete.getPlace();
					if (!place.geometry) {
						return;
					}

					infowindow.setContent('<div><strong>' + place.name
							+ '</strong><br>');
				});

	}

	google.maps.event.addDomListener(window, 'load', initialize);
</script>
<style>

.well{
	font-family: 'Open Sans', sans-serif;
	font-size: 14px;
	font-weight: normal;
	line-height: 20px;
	margin-top: 0px;
	width:700px;
    height:30em;
    padding:20px;
	left: 50%;
	margin-left: 17em; /*set to a negative number 1/2 of your width*/
}
</style>


	<div id="big-form" class="well" >

     	<!-- Form Name -->
				<legend>Upload tweet</legend>

	<input id="fileupload" type="file" name="files[]" data-url="upload" multiple>
	
	<h5 style="text-align:center"><i style="color:#ccc"><small>Max File Size: 2 Mb - 5 Files maximum</small></i></h5>

	<table id="uploaded-files" class="table">
		<tr>
			<th>File Name</th>
			<th>File Size</th>
			<th>File Type</th>
			
		</tr>
	</table>
	
	<form method="post" action="upload.do" enctype="multipart/form-data">
	
					<!-- Textarea -->
				<div class="form-group">
					<label class=" control-label" for="textarea">Tweet Post</label>
					<div class="">
						<textarea class="form-control" id="textarea" name="description"
							placeholder="Share your life today! You can add tags here like this. #tag #happy">${description}</textarea>
					</div>
				</div>
				
				<!-- Text input-->
				<div class="form-group">
					<label class=" control-label" for="textinput">Location</label>
					<div class="">
						<input id="searchPlace" type="text" name="location"
				placeholder="Pittsburgh" value="${location}" 
							class="form-control input-md" > 
					</div>
				</div>
				
				
				
				<div class="form-group">
					<div class="">
						<button id="singlebutton" name="singlebutton"
							class="btn btn-primary">Upload</button>
					</div>
				</div>
				
			</form>
</div>


