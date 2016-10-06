<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<fmt:setLocale value="${sessionScope.locale}" />
	<fmt:setBundle basename="localization.locale" var="loc" />
	<fmt:message bundle="${loc}" key="menu.usermessage" var="usermessage" />
	<fmt:message bundle="${loc}" key="menu.language.en" var="en" />
	<fmt:message bundle="${loc}" key="menu.language.ru" var="ru" />	
	<fmt:message bundle="${loc}" key="user.menu.deposit" var="deposit" />
	<fmt:message bundle="${loc}" key="user.menu.betHistory" var="betHistory" />
	<fmt:message bundle="${loc}" key="user.menu.line" var="lineList" />
	<fmt:message bundle="${loc}" key="user.menu.result" var="resultList" />
	<fmt:message bundle="${loc}" key="user.logout" var="logout" />

	<!-- Page icon -->
	<link type="image/x-icon" href="resources/img/logo.ico" rel="shortcut icon">

	<!-- For compatibility with the greatest possible version IE -->
	<meta http-equiv="X-UA-Compatible" content="IE=edge">

	<!-- For adaptability to any device -->
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<!-- Link font from Google Fonts -->
	<link href='https://fonts.googleapis.com/css?family=Open+Sans&subset=latin,cyrillic' rel='stylesheet' type='text/css'>
	
	<!-- Link bootstrap mini version -->
	<link rel="stylesheet" href="resources/css/bootstrap.min.css">
	
	<!-- Link own styles -->
	<link rel="stylesheet" href="resources/css/style.css">
	
	<!--[if lt IE 9]>
	  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>

	<!-- Header navigation panel. Bootstrap template -->
	<header>
		<nav class="navbar navbar-default menu">
			<div class="container">
			
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="Controller?command=home"><img src="resources/img/logo3.png" height="100" width="100"></a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li id="deposit"><a href="#"><c:out value="${deposit}"/></a></li>
						<li id="betHistory"><a href="#"><c:out value="${betHistory}"/></a></li>
						<li id="line"><a href="#"><c:out value="${lineList}"/></a></li>
						<li id="result"><a href="#"><c:out value="${resultList}"/></a></li>
					</ul>
		
					<ul class="nav navbar-nav navbar-right icons">
						<li><a href="Controller?command=logout" title="SIGN OUT" role="button" class="btn"><img src="resources/img/logout.png" height="26" width="27"></a></li>
						
						<li><a href="Controller?command=change-locale&locale=ru">${ru}</a></li>
						<li><a href="Controller?command=change-locale&locale=en">${en}</a></li>
					</ul>
					<p class="navbar-text pull-right usermessage"><c:out value="${usermessage}"/><span class="maincolor"><c:out value="${sessionScope.client.login}" /></span></p>
				</div><!-- /.navbar-collapse -->
				<i class="glyphicon glyphicon-log-out globe"></i>
			</div><!-- /.container -->
		</nav>
	</header>
	
	<form method="POST" action="Controller" id="form">
		<input type="hidden" name="command" id="command" value=""/>
		<input type="hidden" name="currentPage" value="1"/>
	</form>
	
	<!-- jQuery and JavaScript-->
	<script src="http://code.jquery.com/jquery.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script> 
	
	<script type="text/javascript">
		$("#deposit").click(function() {
			$("#command").val("show-deposit");
			$("#form").submit();
		});
		$("#betHistory").click(function() {
			$("#command").val("show-bet-history");
			$("#form").submit();
		});
		$("#line").click(function() {
			$("#command").val("show-line");
			$("#form").submit();
		});
		$("#result").click(function() {
			$("#command").val("show-result");
			$("#form").submit();
		});
	</script>
	
</body>
</html>