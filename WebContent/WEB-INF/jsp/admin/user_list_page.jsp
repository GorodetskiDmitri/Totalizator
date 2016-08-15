<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<fmt:setLocale value="${sessionScope.locale}" />
	<fmt:setBundle basename="localization.admin" var="admin" />
	<fmt:message bundle="${admin}" key="admin.userlist.slogan" var="slogan" />
	<fmt:message bundle="${admin}" key="admin.title.general" var="title" />
	<fmt:message bundle="${admin}" key="admin.userlist.search" var="search" />
	<fmt:message bundle="${admin}" key="admin.userlist.find" var="find" />
	
	<c:set var="totalPage" value="${totalPage}" scope="request"/>
	<c:set var="searchText" value="${param.searchText}"/>
	<c:set var="currentPage" value="${param.currentPage}"/>
	
	<title>${title}</title>
</head>
<body>
	<%@ include file="admin_menu.jsp" %>
	
	<!-- Контент страницы -->
	<div class="content">
		<div class="container">
			<div class="slogan" style="margin-bottom: 10px">
				<h1>${slogan}</h1>
			</div>
			<div>
				<form method="POST" action="Controller" class="form-inline" name="searchForm" id="searchForm">
					<input type="hidden" name="command" value="show-user-list"/>
					<input type="hidden" name="currentPage" id="currentPage" value="${currentPage}"/>
					<label class="control-label" for="searchText">${search}&nbsp;</label>
					<input type="text" name="searchText" value="${searchText}">
					&nbsp;&nbsp;
      				<input type="submit" id="submitButton" class="btn btn-primary" value="${find}"/>
				</form>
			</div>
		</div>
	</div>

	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
	
</body>
</html>