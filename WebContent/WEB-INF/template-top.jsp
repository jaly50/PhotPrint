<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta name="description" content="" />
<meta name="author" content="" />
<!--[if IE]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <![endif]-->
<title>PhotPrint</title>
<!-- BOOTSTRAP STYLE SHEET -->
<link href="css/bootstrap.css" rel="stylesheet" />
<!-- FONT AWESOME ICONS STYLE SHEET -->
<link href="css/font-awesome.css" rel="stylesheet" />
<!-- CUSTOM STYLES -->
<link href="css/style.css" rel="stylesheet" />
<!-- HTML5 Shiv and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->


</head>
<body>
	<div class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<div
					style="font-family: Impact, Charcoal, sans-serif; font-size: 30px">
					<p>PhotPrint</p>
				</div>
			</div>
			<div class="navbar-collapse collapse navbar-left scroll-me">
				<ul class="nav navbar-nav ">
					<li><a href="index.do">HOME</a></li>
					<li><a href="manage.do">UPLOAD</a></li>
					<li><a href="viewPhotPrint.do">ANALYSIS</a></li>
					<li><a href="search.do">SEARCH</a></li>
				</ul>
				
			</div>
			<c:choose>
					<c:when test="${ (!empty user)}">
						<p class="navbar-text navbar-right" style="color: #ffffcc;" >
							&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;${user.userName}&emsp;&emsp;&emsp;<a href="./logout"
								class="navbar-link" style="color: #ffffff;">logout</a>
						</p>
					
					</c:when>
				</c:choose>
<div class="clearfix"></div>
		</div>
	<div class="clearfix"></div>	
	</div>
		<div class="clearfix"></div>
	
	<!-- NAVBAR CODE END -->