
<jsp:include page="template-top.jsp" />

<jsp:include page="error-list.jsp" />
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
	width:30em;
    height:30em;
	left: 50%;
	margin-left: 30em; /*set to a negative number 1/2 of your width*/
}
</style>
<body>
	<p>	<div id="big-form" class="well" >
		<form method="post" action="upload.do" enctype="multipart/form-data">
			<fieldset>

				<!-- Form Name -->
				<legend>Upload tweet</legend>

				<!-- Textarea -->
				<div class="form-group">
					<label class=" control-label" for="textarea">Tweet Post</label>
					<div class="">
						<textarea class="form-control" id="textarea" name="caption"
							placeholder="Share your life today!">${caption}</textarea>
					</div>
				</div>

				<!-- File Button -->
				<div class="form-group">
					<label class=" control-label" for="filebutton">Photos</label>
					<div class="">

						<input id="filebutton" name="file" value="${filename}"
							class="input-file" type="file">
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

				

				

       
            <!-- Multiple Checkboxes (inline) -->
          <div class="form-group">
            <label class=" control-label" for="checkboxes">Tags:</label>
            <div class="">
              <label class="checkbox-inline" for="checkboxes-0">
                <input name="Travel" id="checkboxes-0" value="1" type="checkbox">
                Travel
              </label>
              <label class="checkbox-inline" for="checkboxes-1">
                <input name="Friends" id="checkboxes-1" value="2" type="checkbox">
                Friends
              </label>
              <label class="checkbox-inline" for="checkboxes-2">
                <input name="Myself" id="checkboxes-2" value="3" type="checkbox">
                Myself
              </label>
              <label class="checkbox-inline" for="checkboxes-3">
                <input name="Others" id="checkboxes-3" value="4" type="checkbox">
                Others
              </label>
            </div>
          </div>

				<div class="form-group">
					<div class="">
						<button id="singlebutton" name="singlebutton"
							class="btn btn-primary">Upload</button>
					</div>
				</div>

			</fieldset>
		</form>
		<div class="clearfix"></div>
	</div>



<jsp:include page="template-bottom.jsp" />

    


	