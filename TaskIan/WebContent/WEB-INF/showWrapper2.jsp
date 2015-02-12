<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<!DOCTYPE html>
<html>
<head>
<title>PhotPrint</title>

<link rel='stylesheet' href="css/bootstrap.css" media='screen' />
<link rel='stylesheet' href="css/style.css"     media='screen' />

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<!--[if lt IE 9]>
<script src="//html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<script src="js/blocksit.min.js"></script>
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
</head>
<body>

<!-- Header -->

<!-- NavBar  -->
 <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Navigation</span> <span
                        class="icon-bar"></span> <span class="icon-bar"></span> <span
                        class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">PhotPrint</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse"
                id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li><a href="#">Flickr</a></li>
                    <li><a href="#">Services</a></li>
                    <li><a href="#">Contact</a></li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>


<!-- Content -->
<section id="wrapper">
<hgroup>
<h2>PhotPrint</h2>
<p><a href="http://www.jqueryscript.net/"><strong>Location: </strong></a> Pittsburgh</p>
<button type="button" class="btn btn-warning">Food</button>
<button type="button" class="btn btn-danger">Game</button>
<button type="button" class="btn btn-info">Book</button>
</hgroup>
<div id="container">
<div class="grid">
<div class="imgholder"> <img src="http://static.flickr.com/7556/16191210386_bc6a766051.jpg" /> </div>
<strong>Sunset Lake</strong>
<p>A peaceful sunset view...</p>
<div class="meta">by j osborn</div>
</div>
<div class="grid">
<div class="imgholder"> <img src="http://static.flickr.com/8645/16031167127_d3f8a94cd3.jpg" /> </div>
<strong>Bridge to Heaven</strong>
<p>Where is the bridge lead to?</p>
<div class="meta">by SigitEko</div>
</div>
<div class="grid">
<div class="imgholder"> <img src="http://static.flickr.com/7509/16209744125_c6732afa19.jpg" /> </div>
<strong>Autumn</strong>
<p>The fall of the tree...</p>
<div class="meta">by Lars van de Goor</div>
</div>
<div class="grid">
<div class="imgholder"> <img src="http://static.flickr.com/8663/16112291228_7d545a9af3.jpg" /> </div>
<strong>Winter Whisper</strong>
<p>Winter feel...</p>
<div class="meta">by Andrea Andrade</div>
</div>
<div class="grid">
<div class="imgholder"> <img src="http://static.flickr.com/8668/16197553922_464c8fc992.jpg" /> </div>
<strong>Light</strong>
<p>The only shinning light...</p>
<div class="meta">by Lars van de Goor</div>
</div>
<div class="box col-lg-3 col-md-4 col-xs-6 thumb">
                <div class="thumbnail" href="#">
                    <a href="#"> <img class="img-responsive"
                        src="http://static.flickr.com/7556/16191210386_bc6a766051.jpg"
                        alt="">
                    </a>
                    <h3>Thumbnail label</h3>
                    <p>
                        <a href="#" class="btn btn-primary" role="button">Button</a> 
                        <a href="#" class="btn btn-danger" role="button">Button</a>
                    </p>
                </div>
            </div>

<div class="grid">
<div class="imgholder"> <a href="#"> <img class="img-responsive"
                        src="http://static.flickr.com/7546/15682782584_02d8a74018.jpg"
                        alt="">
                    </a> </div>
<strong>Rooster's Ranch</strong>
<p>Rooster's ranch landscape...</p>
<p> <a href="#" class="btn btn-primary" role="button">Like</a>
	<a href="#" class="btn btn-danger" role="button">Dislike</a>
</p>
</div>


<div class="grid">
<div class="imgholder"> <img src="http://www.inwebson.com/demo/blocksit-js/demo2/images/img16.jpg" /> </div>
<strong>Autumn Light</strong>
<p>Sun shinning into forest...</p>
<div class="meta">by Lars van de Goor</div>
</div>
<div class="grid">
<div class="imgholder"> <img src="http://www.inwebson.com/demo/blocksit-js/demo2/images/img21.jpg" /> </div>
<strong>Yellow cloudy</strong>
<p>It is yellow cloudy...</p>
<div class="meta">by Zsolt Zsigmond</div>
</div>
<div class="grid">
<div class="imgholder"> <img src="http://static.flickr.com/7577/16099097707_d7b5f290a8.jpg" /> </div>
<strong>Herringfleet Mill</strong>
<p>Just a herringfleet mill...</p>
<div class="meta">by Ian Flindt</div>
</div>
<div class="grid">
<div class="imgholder"> <img src="http://static.flickr.com/7410/9249423969_d20159b5d8.jpg" /> </div>
<strong>Battle Field</strong>
<p>Battle Field for you...</p>
<div class="meta">by Andrea Andrade</div>
</div>
<div class="grid">
<div class="imgholder"> <img src="http://www.inwebson.com/demo/blocksit-js/demo2/images/img24.jpg" /> </div>
<strong>Sundays Sunset</strong>
<p>Beach view sunset...</p>
<div class="meta">by Robert Strachan</div>
</div>
<div class="grid">
<div class="imgholder"> <img src="http://www.inwebson.com/demo/blocksit-js/demo2/images/img19.jpg" /> </div>
<strong>Sun Flower</strong>
<p>Good Morning Sun flower...</p>
<div class="meta">by Zsolt Zsigmond</div>
</div>
<div class="grid">
<div class="imgholder"> <img src="http://www.inwebson.com/demo/blocksit-js/demo2/images/img5.jpg" /> </div>
<strong>Beach</strong>
<p>Something on beach...</p>
<div class="meta">by unknown</div>
</div>
<div class="grid">
<div class="imgholder"> <img src="http://www.inwebson.com/demo/blocksit-js/demo2/images/img25.jpg" /> </div>
<strong>Flowers</strong>
<p>Hello flowers...</p>
<div class="meta">by R A Stanley</div>
</div>
<div class="grid">
<div class="imgholder"> <img src="http://www.inwebson.com/demo/blocksit-js/demo2/images/img20.jpg" /> </div>
<strong>Alone</strong>
<p>Lonely plant...</p>
<div class="meta">by Zsolt Zsigmond</div>
</div>

<div class="grid">
<div class="imgholder"> <img src="http://www.inwebson.com/demo/blocksit-js/demo2/images/img20.jpg" /> </div>
<strong>Alone</strong>
<p>Lonely plant...</p>
<div class="meta">by Zsolt Zsigmond</div>
</div>

<div class="grid">
<div class="imgholder"> <img src="http://www.inwebson.com/demo/blocksit-js/demo2/images/img20.jpg" /> </div>
<strong>Alone</strong>
<p>Lonely plant...</p>
<div class="meta">by Zsolt Zsigmond</div>
</div>

<div class="grid">
<div class="imgholder"> <img src="http://www.inwebson.com/demo/blocksit-js/demo2/images/img20.jpg" /> </div>
<strong>Alone</strong>
<p>Lonely plant...</p>
<div class="meta">by Zsolt Zsigmond</div>
</div>

<div class="grid">
<div class="imgholder"> <img src="http://www.inwebson.com/demo/blocksit-js/demo2/images/img20.jpg" /> </div>
<strong>Alone</strong>
<p>Lonely plant...</p>
<div class="meta">by Zsolt Zsigmond</div>
</div>

<div class="grid">
<div class="imgholder"> <img src="http://www.inwebson.com/demo/blocksit-js/demo2/images/img20.jpg" /> </div>
<strong>Alone</strong>
<p>Lonely plant...</p>
<div class="meta">by Zsolt Zsigmond</div>
</div>
<!----> 
</div>
</section>

<!-- Footer -->

</body>
</html>