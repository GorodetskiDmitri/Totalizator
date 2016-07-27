<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="localization.user" var="loc" />
<fmt:message bundle="${loc}" key="user.title" var="title" />
<fmt:message bundle="${loc}" key="user.slogan" var="slogan" />
<fmt:message bundle="${loc}" key="user.logout" var="logout" />
<title>${title}</title>
</head>
<body>

<h1>${slogan}</h1>
<c:out value="${sessionScope.client.login}" />

<p>Bla. Bla. Bla </p>
<a href="Controller?command=logout">${logout}</a>
</body>
</html>