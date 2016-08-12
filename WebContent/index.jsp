<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<fmt:setLocale value="${sessionScope.locale}" />
	<fmt:setBundle basename="localization.menu" var="menu" />
	<fmt:setBundle basename="localization.login" var="log" />
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
	<fmt:message bundle="${log}" key="login.legend" var="legend" />
	<fmt:message bundle="${log}" key="login.login" var="login" />
	<fmt:message bundle="${log}" key="login.errorLogin" var="errorLogin" />
	<fmt:message bundle="${log}" key="login.password" var="password" />
	<fmt:message bundle="${log}" key="login.errorPassword" var="errorPassword" />
	<fmt:message bundle="${log}" key="login.errorAccess" var="errorAccess" />
	<fmt:message bundle="${log}" key="login.signin" var="signin" />
	
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
						<li><a href="Controller?command=home">${home}</a></li>
						<li><a href="Controller?command=line">${bet}</a></li>
						<li><a href="Controller?command=registration">${registration}</a></li>
						<li><a href="Controller?command=contacts">${contacts}</a></li>
					</ul>
		
					<ul class="nav navbar-nav navbar-right icons">
						<li><a id="modalLogin1" href="#myModal" title="SIGN IN" role="button" class="btn" data-toggle="modal"><img src="resources/img/login.png" height="26" width="27"></a></li>
						
   
						
<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">${legend}</h4>
      </div>
      
      <div class="modal-body">
       <c:if test="${accessDenied == true}">
			<div class="alert alert-danger">
    			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    			<h5><c:out value="${errorAccess}"/></h5>
  			</div>
		</c:if>
       
        <form id="login-form" method="POST" action="Controller" accept-charset="UTF-8">
			<input type="hidden" name="command" value="login" />
			<div id="login-control-group" class="control-group">
				<div class="controls">
					<input type="text" name="login" id="login" class="span4" value="${param.login}" placeholder="${login}" onkeypress="delSpan('login')">
					<span id="span-login" class="help-inline error">${errorLogin}</span>
					<br/><br/>
				</div>
			</div>
			<div id="password-control-group" class="control-group">
				<div class="controls">
					<input type="password" name="password" id="password" value="${param.password}" placeholder="${password}" onkeypress="delSpan('password')">
					<span id="span-password" class="help-inline error">${errorPassword}</span>
					<br/><br/>
				</div>
			</div>
			<button class="btn btn-primary" type="submit">${signin}</button>
		</form>
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
							<a href="betlist.html#euro"><img src="resources/img/photo/bet1.jpg" height="173" width="218"></a>
						</div>
					</div>
					<div class="col-md-3 col-sm-6">
						<div class="card">
							<h4>Krumkachy - BATE</h4>
							<p>Football. Belarus. Day 21</p>
							<a href="betlist.html#football"><img src="resources/img/photo/bet2.jpg" height="173" width="218"></a>
						</div>
					</div>
					<div class="col-md-3 col-sm-6">
						<div class="card">
							<h4>S.Halep - M.Kis</h4>
							<p>Wimbledon. Round of 16</p>
							<a href="betlist.html#tennis"><img src="resources/img/photo/bet3.jpg" height="173" width="218"></a>
						</div>
					</div>
					<div class="col-md-3 col-sm-6">
						<div class="card">
							<h4>Tyson Fury - Vl. Klitschko</h4>
							<p>Box. Battle for the titul WBA/WBO </p>
							<a href="betlist.html#box"><img src="resources/img/photo/bet4.jpg" height="173" width="218"></a>
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
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/main.js"></script>
	
	<c:if test="${accessDenied == true}">
		<script type="text/javascript">
			$(document).ready(function() {
				$("#myModal").modal();
			});
		</script>
	</c:if>
	
	<script type="text/javascript">
		$(function() {
			$("#span-login").hide();
			$("#span-password").hide();
		});
		
		$("#login-form").submit(function() {
			if ($("#login").val() == "" && $("#password").val() == "") {
				$("#span-login").show();
				$("#span-password").show();
				$("#login").focus();
			} else {
				$("#span-login").hide();
				$("#span-password").hide();
			}
			
			if ($("#login").val() == "") {
				$("#span-login").show();
				$("#login").focus();
				return false;
			} else {
				$("#span-login").hide();
			}
			
			if ($("#password").val() == "") {
				$("#span-password").show();
				$("#password").focus();
				return false;
			} else {
				$("#span-password").hide();
			}
		})
		
		function delSpan(span) {
			if (span == "login") { 
				$("#span-login").hide();
			}
			if (span == "password") { 
				$("#span-password").hide(); 
			}
		}
	</script>
	
</body>
</html>