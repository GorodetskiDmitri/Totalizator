<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<fmt:setLocale value="${sessionScope.locale}" />
	<fmt:setBundle basename="localization.locale" var="loc" />
	<fmt:message bundle="${loc}" key="errorPage.slogan" var="slogan" />
	<fmt:message bundle="${loc}" key="errorPage.title" var="title" />
	
	<title><c:out value="${title}"/></title>
</head>

<body>
	<%@ include file="WEB-INF/jsp/header.jsp" %>
	
	<div class="content">
		<div class="container">
			<div class="slogan" style="margin-bottom: 120px">
				<h1><c:out value="${slogan}"/></h1>
			</div>
		</div>
	</div>

	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
	
	
</body>
</html>