<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<fmt:setLocale value="${sessionScope.locale}" />
	<fmt:setBundle basename="localization.menu" var="menu" />
	<fmt:setBundle basename="localization.login" var="loc" />
	<fmt:message bundle="${menu}" key="menu.home" var="home" />
	<fmt:message bundle="${menu}" key="menu.bet" var="bet" />
	<fmt:message bundle="${menu}" key="menu.registration" var="registration" />
	<fmt:message bundle="${menu}" key="menu.contacts" var="contacts" />
	<fmt:message bundle="${menu}" key="menu.language.en" var="en" />
	<fmt:message bundle="${menu}" key="menu.language.ru" var="ru" />
	
	<fmt:setLocale value="${sessionScope.locale}" />
	<fmt:setBundle basename="localization.user" var="loc" />
	<fmt:message bundle="${loc}" key="user.admin.title" var="title" />
	<fmt:message bundle="${loc}" key="user.slogan2" var="slogan2" />
	<fmt:message bundle="${loc}" key="user.logout" var="logout" />
	
	<title>${title}</title>

	<!-- Иконка страницы -->
	<link type="image/x-icon" href="resources/img/logo.ico" rel="shortcut icon">

	<!-- Для совместимости с максимально возможной версией IE -->
	<meta http-equiv="X-UA-Compatible" content="IE=edge">

	<!-- Для адаптивности с любыми устройствами -->
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<!-- Подключение шрифта из Google Fonts -->
	<link href='https://fonts.googleapis.com/css?family=Open+Sans&subset=latin,cyrillic' rel='stylesheet' type='text/css'>
	
	<!-- Подключение mini версии bootstrap -->
	<link rel="stylesheet" href="resources/css/bootstrap.min.css">
	
	<!-- Подключение файла собственных стилей -->
	<link rel="stylesheet" href="resources/css/style.css">
	
	<!-- Скрипты подключатся, если пользователь будет просматривать страницу в браузере IE ранее 9 версии-->
	<!--[if lt IE 9]>
	  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>

	<!-- Навигационная панель заголовка. Шаблон bootstrap -->
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
						<li><a href="#">Show Users</a></li>
						<li><a href="#">Allow Bet</a></li>
					</ul>
		
					<ul class="nav navbar-nav navbar-right icons">
						<li><a href="Controller?command=logout" title="SIGN OUT" role="button" class="btn"><img src="resources/img/login.png" height="26" width="27"></a></li>
						
						<li><a href="Controller?command=change-locale&locale=ru">${ru}</a></li>
						<li><a href="Controller?command=change-locale&locale=en">${en}</a></li>
					</ul>
				</div><!-- /.navbar-collapse -->
				<i class="glyphicon glyphicon-log-out globe"></i>
			</div><!-- /.container -->
		</nav>
	</header>

	<!-- Контент страницы -->
	<div class="content">
		<div class="container">
			<div class="slogan" style="margin-bottom: 120px">
				<h1>${slogan2}</h1>
				<c:out value="${sessionScope.client.login}" />
			</div>
			
		</div>
	</div>

	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
	
</body>
</html>