<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<fmt:setLocale value="${sessionScope.locale}" />
	<fmt:setBundle basename="localization.user" var="user" />
	<fmt:message bundle="${user}" key="user.slogan" var="slogan" />
	<fmt:message bundle="${user}" key="user.title.general" var="title" />
	
	<title>${title}</title>
</head>
<body>
	<%@ include file="user_menu.jsp" %>
	
	<!-- Контент страницы -->
	<div class="content">
		<div class="container">
			<div class="slogan" style="margin-bottom: 120px">
				<h1>${slogan}</h1>
			</div>
			
		</div>
	</div>

	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
	
</body>
</html>