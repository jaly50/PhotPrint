
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Carnegie Financial Services</title>


<!-- Bootstrap Core CSS -->

<link href="css/style.css" rel='stylesheet' type='text/css' />
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/shop-homepage.css" rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
</head>

<body>
	<!--header-->
	<div class="container">
		<div class="top-header">
			<div class="logo">
				<a href="login.do"><img src="images/c1.png" title="barndlogo" /></a>
			</div>
			<div class="top-header-info">
				<div class="top-contact-info">
					<ul class="unstyled-list list-inline">
						<c:choose>
							<c:when test="${ (!empty user)}">
								<li><a href="change-pwd.do">Change Password</a></li>
								<li>Welcome, ${user.firstname} ${user.lastname}</li>
							</c:when>
						</c:choose>
						<div class="clearfix"></div>
					</ul>
				</div>
				<div class="cart-details">
					<div class="login-rigister">
						<ul class="unstyled-list list-inline">
							<c:choose>
								<c:when test="${ (empty user)}">
									<li><a class="rigister" href="login.do">LOGIN <span>
										</span></a></li>
								</c:when>
								<c:otherwise>
									<li><a class="rigister" href="logout.do">LOGOUT <span>
										</span></a></li>
								</c:otherwise>
							</c:choose>
							<div class="clearfix"></div>
						</ul>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
		<!----//top-header---->
		<!---top-header-nav---->
		<div class="top-header-nav">
			<!----start-top-nav---->
			<nav class="top-nav main-menu">
				<ul class="top-nav">
					<c:choose>
						<c:when test="${ user.getClass().name =='databeans.Employee' }">
							<!-- For Employee -->
                            <li><a href="viewCustomerList.do">Customer Management</a><span>
							</span></li>
							<li><a href="create_employee.do">Create Employee Account</a><span>
							</span></li>
							<li><a href="create_customer.do">Create Customer Account</a><span>
							</span></li>
							<li><a href="createFund.do">Create Fund</a><span> </span></li>
							<li><a href="transitionDay.do">Transition Day</a><span>
							</span></li>
						</c:when>
						<c:otherwise>
							<!-- For customer -->
							<li><a href="viewAccAction.do">View Account</a><span>
							</span></li>
							<li><a href="buyFund.do">BUY FUND</a><span> </span></li>
							<li><a href="sellFund.do">SELL FUND</a><span> </span></li>
							<li><a href="TransactionHistoryAction.do">Transaction
									History</a><span> </span></li>
							<li><a href="showWrapper.do">Research Fund</a><span>
							</span></li>
							<li><a href="requestCheck.do">Request Check</a><span>
							</span></li>
						</c:otherwise>
					</c:choose>






					<div class="clearfix"></div>
				</ul>
				<a href="#" id="pull"><img src="images/nav-icon.png"
					title="menu" /></a>
			</nav>
		</div>