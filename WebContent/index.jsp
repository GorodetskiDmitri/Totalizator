<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="by.epam.totalizator.localization.login" var="loc" />
<fmt:message bundle="${loc}" key="login.title" var="title" />
<fmt:message bundle="${loc}" key="login.legend" var="legend" />
<fmt:message bundle="${loc}" key="login.language.en" var="en" />
<fmt:message bundle="${loc}" key="login.language.ru" var="ru" />
<title>${title}</title>
</head>
<body>

<legend>${legend}</legend>
<form id="login-form" method="POST" action="Controller" accept-charset="UTF-8">

<a href="Controller?command=changeLocale&locale=ru">${ru}</a> |
<a href="Controller?command=changeLocale&locale=en">${en}</a>
</form>
</body>
</html>