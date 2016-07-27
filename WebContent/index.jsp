<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<fmt:setLocale value="${sessionScope.locale}" />
	<fmt:setBundle basename="localization.login" var="loc" />
	<fmt:message bundle="${loc}" key="login.title" var="title" />
	<fmt:message bundle="${loc}" key="login.legend" var="legend" />
	<fmt:message bundle="${loc}" key="login.language.en" var="en" />
	<fmt:message bundle="${loc}" key="login.language.ru" var="ru" />
	<fmt:message bundle="${loc}" key="login.login" var="login" />
	<fmt:message bundle="${loc}" key="login.error.login" var="errorLogin" />
	<fmt:message bundle="${loc}" key="login.password" var="password" />
	<fmt:message bundle="${loc}" key="login.error.password" var="errorPassword" />
	<fmt:message bundle="${loc}" key="login.signin" var="signin" />
	<title>${title}</title>
</head>

<body>
	<legend>${legend}</legend>
	<br/>
	<a href="Controller?command=change-locale&locale=ru">${ru}</a> |
	<a href="Controller?command=change-locale&locale=en">${en}</a>
		
	<form id="login-form" method="POST" action="Controller" accept-charset="UTF-8">
		<input type="hidden" name="command" value="login" />
		<input type="text" name="login" id="login" placeholder="${login}">
		<span id="span-login">${errorLogin}</span>
		<input type="password" name="password" id="password" placeholder="${password}">
		<span id="span-password" >${errorPassword}</span>
		
		<button type="submit">${signin}</button>
	</form>
</body>
</html>