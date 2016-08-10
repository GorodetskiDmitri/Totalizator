<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<fmt:setLocale value="${sessionScope.locale}" />
	<fmt:setBundle basename="localization.registration" var="registration" />
	<fmt:message bundle="${registration}" key="registration.title" var="title" />
	<fmt:message bundle="${registration}" key="registration.slogan" var="slogan" />
	<fmt:message bundle="${registration}" key="registration.name" var="name" />
	<fmt:message bundle="${registration}" key="registration.placeholder.name" var="placeholder_name" />
	<fmt:message bundle="${registration}" key="registration.surname" var="surname" />
	<fmt:message bundle="${registration}" key="registration.placeholder.surname" var="placeholder_surname" />
	<fmt:message bundle="${registration}" key="registration.birthday" var="birthday" />
	<fmt:message bundle="${registration}" key="registration.placeholder.birthday" var="placeholder_birthday" />
	<fmt:message bundle="${registration}" key="registration.login" var="login" />
	<fmt:message bundle="${registration}" key="registration.placeholder.login" var="placeholder_login" />
	<fmt:message bundle="${registration}" key="registration.password" var="password" />
	<fmt:message bundle="${registration}" key="registration.placeholder.password" var="placeholder_password" />
	<fmt:message bundle="${registration}" key="registration.confirm" var="confirm" />
	<fmt:message bundle="${registration}" key="registration.placeholder.confirm" var="placeholder_confirm" />
	<fmt:message bundle="${registration}" key="registration.email" var="email" />
	<fmt:message bundle="${registration}" key="registration.placeholder.email" var="placeholder_email" />
	<fmt:message bundle="${registration}" key="registration.passport" var="passport" />
	<fmt:message bundle="${registration}" key="registration.placeholder.passport" var="placeholder_passport" />
	<fmt:message bundle="${registration}" key="registration.phone" var="phone" />
	<fmt:message bundle="${registration}" key="registration.placeholder.phone" var="placeholder_phone" />
	<fmt:message bundle="${registration}" key="registration.address" var="address" />
	<fmt:message bundle="${registration}" key="registration.placeholder.address" var="placeholder_address" />
	<fmt:message bundle="${registration}" key="registration.sex" var="sex" />
	<fmt:message bundle="${registration}" key="registration.male" var="male" />
	<fmt:message bundle="${registration}" key="registration.female" var="female" />
	<fmt:message bundle="${registration}" key="registration.agree" var="agree" />
	<fmt:message bundle="${registration}" key="registration.conditions" var="conditions" />
	<fmt:message bundle="${registration}" key="registration" var="registr" />
	<fmt:message bundle="${registration}" key="registration.reset" var="reset" />

	<title><c:out value="${title}"/></title>

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

	<!-- Подключение библиотеки jQuery -->
  	<script type="text/javascript" src="bootstrap/js/jquery-1.11.1.min.js"></script>
 	
 	<!-- Подключение скрипта moment-with-locales.min.js для работы с датами -->
  	<script type="text/javascript" src="bootstrap/js/moment-with-locales.min.js"></script>
  
  	<!-- Подключение скрипта платформы Twitter Bootstrap 3 -->
  	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
    
    <!-- Подключение скрипта виджета "Bootstrap datetimepicker" -->
  	<script type="text/javascript" src="bootstrap/js/bootstrap-datetimepicker.min.js"></script>
    
    <!-- Подключение CSS виджета "Bootstrap datetimepicker" -->  
  	<link rel="stylesheet" href="bootstrap/css/bootstrap-datetimepicker.min.css" />

	<!-- Скрипты подключатся, если пользователь будет просматривать страницу в браузере IE ранее 9 версии-->
	<!--[if lt IE 9]>
	  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>

<body>

	<jsp:include page="/WEB-INF/jsp/user-header.jsp" />

	<!-- Контент страницы -->
	<div class="content">
		<div class="container">
			<div class="slogan">
				<h1><span><c:out value="${slogan}"/></span></h1>
			</div>

			<form name="registration" method="POST" class="form-horizontal  label-slyle" onsubmit="return validation();">	
				<div class="error form-group">
				    <label class="control-label col-xs-3" for="firstName"><c:out value="${name}"/></label>
				    <div class="col-xs-6">
				      	<input type="text" class="form-control" id="firstName" placeholder="${placeholder_name}">
				    </div>
				</div>
				<div class="form-group">
				    <label class="control-label col-xs-3" for="lastName"><c:out value="${surname}"/></label>
				    <div class="col-xs-6">
				    	<input type="text" class="form-control" id="lastName" placeholder="${placeholder_surname}">
				    </div>
				</div>
				<div class="form-group">
					<label class="control-label col-xs-3" for="datetimepicker"><c:out value="${birthday}"/></label>
					<div class="date col-xs-6" >
    					<input type="text" class="form-control" id="datetimepicker" placeholder="${placeholder_birthday}"/>
  					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-xs-3" for="login"><c:out value="${login}"/></label>
					<div class="col-xs-6">
				 		<input type="text" class="form-control" id="login" placeholder="${placeholder_login}">
					</div>
				</div>
				<div class="form-group">
				    <label class="control-label col-xs-3" for="inputPassword"><c:out value="${password}"/></label>
				    <div class="col-xs-6">
				      	<input type="password" class="form-control" id="inputPassword" placeholder="${placeholder_password}">
				      	<span id="span-password" class="help-inline">${password}</span>
				    </div>
				</div>
				<div class="form-group">
				    <label class="control-label col-xs-3" for="confirmPassword"><c:out value="${confirm}"/></label>
				    <div class="col-xs-6">
				    	<input type="password" class="form-control" id="confirmPassword" placeholder="${placeholder_confirm}">
				    </div>
				</div>
				<div class="form-group">
				    <label class="control-label col-xs-3" for="inputEmail"><c:out value="${email}"/></label>
				    <div class="col-xs-6">
				      	<input type="email" class="form-control" id="inputEmail" placeholder="${placeholder_email}">
				    </div>
				</div>
				<div class="form-group">
					<label class="control-label col-xs-3" for="passport"><c:out value="${passport}"/></label>
					<div class="col-xs-6">
				 		<input type="text" class="form-control" id="passport" placeholder="${placeholder_passport}">
					</div>
				</div>
				<div class="form-group">
				    <label class="control-label col-xs-3" for="phoneNumber"><c:out value="${phone}"/></label>
				    <div class="col-xs-6">
				      	<input type="tel" class="form-control" id="phoneNumber" placeholder="${placeholder_phone}">
				    </div>
				</div>
				<div class="form-group">
				    <label class="control-label col-xs-3" for="postalAddress"><c:out value="${address}"/></label>
				    <div class="col-xs-6">
				      	<textarea rows="2" class="form-control" id="postalAddress" placeholder="${placeholder_address}"></textarea>
				    </div>
				</div>
				<div class="form-group">
				    <label class="control-label col-xs-3"><c:out value="${sex}"/></label>
				    <div class="col-xs-1">
				      	<label class="radio-inline">
				        	<input type="radio" name="genderRadios" value="male" checked> <c:out value="${male}"/>
				      	</label>
				    </div>
				    <div class="col-xs-1">
				      	<label class="radio-inline">
				        	<input type="radio" name="genderRadios" value="female"> <c:out value="${female}"/>
				      	</label>
				    </div>
				</div>
				<div class="form-group">
				    <div class="col-xs-offset-3 col-xs-6">
				      	<label class="checkbox-inline">
				        	<input type="checkbox" value="agree">  <c:out value="${agree}"/> <a href="conditions.txt" onclick="javascript:newWindow(this.href); return false;"><c:out value="${conditions}"/></a>.
				      	</label>
				    </div>
				</div>
				<br />
				<div class="form-group">
				    <div class="col-xs-offset-3 col-xs-6">
				      	<input type="submit" class="btn btn-primary" value="${registr}">
				      	&nbsp;&nbsp;
				      	<input type="reset" class="btn btn-default" value="${reset}">
				    </div>
				</div> 
			</form>
				
		</div>
	</div>

	<footer>
		<div class="container">
			<hr>
			<p class="pull-right">Design by Gorodetski Dmitri 2016</p>		
		</div>
	</footer>
	 
	<!-- Подключение jQuery и JavaScript-->
	<script src="bootstrap/js/main.js"></script>
 
</body>
</html>