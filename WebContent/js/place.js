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