<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<fmt:setLocale value="${sessionScope.locale}" />
	<fmt:setBundle basename="localization.menu" var="menu" />
	<fmt:message bundle="${menu}" key="menu.home" var="home" />
	<fmt:message bundle="${menu}" key="menu.bet" var="bet" />
	<fmt:message bundle="${menu}" key="menu.registration" var="registration" />
	<fmt:message bundle="${menu}" key="menu.contacts" var="contacts" />
	<fmt:message bundle="${menu}" key="menu.language.en" var="en" />
	<fmt:message bundle="${menu}" key="menu.language.ru" var="ru" />
	<fmt:message bundle="${menu}" key="menu.main.title" var="title" />
	<fmt:message bundle="${menu}" key="menu.main.slogan.part1" var="part1" />
	<fmt:message bundle="${menu}" key="menu.main.slogan.part2" var="part2" />
	<fmt:message bundle="${menu}" key="menu.main.slogan.part3" var="part3" />
	
	<title>${title}</title>

	<!-- Иконка страницы -->
	<link type="image/x-icon" href="bootstrap/img/logo.ico" rel="shortcut icon">

	<!-- Для совместимости с максимально возможной версией IE -->
	<meta http-equiv="X-UA-Compatible" content="IE=edge">

	<!-- Для адаптивности с любыми устройствами -->
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<!-- Подключение шрифта из Google Fonts -->
	<link href='https://fonts.googleapis.com/css?family=Open+Sans&subset=latin,cyrillic' rel='stylesheet' type='text/css'>
	
	<!-- Подключение mini версии bootstrap -->
	<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
	
	<!-- Подключение файла собственных стилей -->
	<link rel="stylesheet" href="bootstrap/css/style.css">
	
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
					<a class="navbar-brand" href="index.html"><img src="bootstrap/img/logo3.png" height="100" width="100"></a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li><a href="index.jsp">${home}</a></li>
						<li><a href="betlist.html">${bet}</a></li>
						<li><a href="registration.html">${registration}</a></li>
						<li><a href="Controller?command=contacts">${contacts}</a></li>
					</ul>
		
					<ul class="nav navbar-nav navbar-right icons">
						<li><a href="login.jsp" onclick="javascript:newWindow(this.href); return false;" title="SIGN IN"><img src="bootstrap/img/login.png" height="26" width="27"></a></li> 
						<!-- <li><a href="#myModal" title="SIGN IN" role="button" class="btn" data-toggle="modal"><img src="bootstrap/img/login.png" height="26" width="27"></a></li> -->
						
<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Modal Header</h4>
      </div>
      <div class="modal-body">
        <p>Some text in the modal.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button class="btn btn-primary">Save changes</button>
      </div>
    </div>

  </div>
</div>
						
						<li><a href="Controller?command=change-locale&locale=ru">${ru}</a></li>
						<li><a href="Controller?command=change-locale&locale=en">${en}</a></li>
					</ul>
				</div><!-- /.navbar-collapse -->
				<i class="glyphicon glyphicon-log-in globe"></i>
			</div><!-- /.container -->
		</nav>
	</header>

	<!-- Контент страницы -->
	<div class="content">
		<div class="container">
			<div class="slogan" style="margin-bottom: 120px">
				<h1>${part1} <span>${part2}</span>&nbsp;&nbsp;${part3}</h1>
			</div>
			<div class="row">
				<div class="card-row clearfix">
					<div class="col-md-3 col-sm-6">
						<div class="card">
							<h4>France - Iceland</h4>
							<p>EURO-2016. Quater-final</p>
							<a href="betlist.html#euro"><img src="bootstrap/img/photo/bet1.jpg" height="173" width="218"></a>
						</div>
					</div>
					<div class="col-md-3 col-sm-6">
						<div class="card">
							<h4>Krumkachy - BATE</h4>
							<p>Football. Belarus. Day 21</p>
							<a href="betlist.html#football"><img src="bootstrap/img/photo/bet2.jpg" height="173" width="218"></a>
						</div>
					</div>
					<div class="col-md-3 col-sm-6">
						<div class="card">
							<h4>S.Halep - M.Kis</h4>
							<p>Wimbledon. Round of 16</p>
							<a href="betlist.html#tennis"><img src="bootstrap/img/photo/bet3.jpg" height="173" width="218"></a>
						</div>
					</div>
					<div class="col-md-3 col-sm-6">
						<div class="card">
							<h4>Tyson Fury - Vl. Klitschko</h4>
							<p>Box. Battle for the titul WBA/WBO </p>
							<a href="betlist.html#box"><img src="bootstrap/img/photo/bet4.jpg" height="173" width="218"></a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<footer>
		<div class="container">
			<hr>
			<p class="pull-right">Design by Gorodetski Dmitri 2016</p>
		</div>
	</footer>

	<!-- Подключение jQuery и JavaScript-->
	<script src="http://code.jquery.com/jquery.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<script src="bootstrap/js/main.js"></script>
</body>
</html>