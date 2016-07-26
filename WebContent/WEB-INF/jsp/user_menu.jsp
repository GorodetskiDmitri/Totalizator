<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="by.epam.totalizator.localization.menu" var="loc" />
<fmt:message bundle="${loc}" key="menu.brand" var="brand" />
<fmt:message bundle="${loc}" key="menu.user.listbook" var="listbook" />
<fmt:message bundle="${loc}" key="menu.user.mybooks" var="mybooks" />
<fmt:message bundle="${loc}" key="menu.user.myorders" var="myorders" />
<fmt:message bundle="${loc}" key="menu.user.currentorder" var="currentorder" />
<fmt:message bundle="${loc}" key="menu.usermessage" var="usermessage" />
<fmt:message bundle="${loc}" key="menu.language" var="language" />
<fmt:message bundle="${loc}" key="menu.logout" var="logout" />
</head>
<body>
<jsp:useBean id="user" type="by.epam.totalizator.entity.User" scope="session"></jsp:useBean>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<button type="button" class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="brand" href="Controller?command=show-main-page">${brand}</a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li class="divider-vertical"></li>
						<li id="user-list"><a href="#">${listbook}</a></li>
						<li id="my-books"><a href="#">${mybooks}</a></li>
						<li id="my-orders"><a href="#">${myorders}</a></li>
						<li id="current-order"><a href="#">${currentorder}</a></li>
					</ul>
					<ul class="nav pull-right">
						<li class="dropdown">
                			<a href="#" class="dropdown-toggle" data-toggle="dropdown"><c:out value="${user.login}"/> <b class="caret"></b></a>
                			<ul class="dropdown-menu">
                  				<li class="dropdown-submenu">
                  					<a href="#">${language}</a>
                  					<ul class="dropdown-menu">
                  						<li><a href="Controller?command=change-locale&locale=ru">Русский</a></li>
										<li><a href="Controller?command=change-locale&locale=en">English</a></li>
                  					</ul>
                  				</li>
                  				<li class="divider"></li>
                  				<li><a href="Controller?command=logout">${logout}</a></li>
                			</ul>
              			</li>
					</ul>
					<p class="navbar-text pull-right">${usermessage}</p>
				</div>
			</div>
		</div>
	</div>
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="bootstrap/js/bootstrap.js"></script>
</body>
</html>