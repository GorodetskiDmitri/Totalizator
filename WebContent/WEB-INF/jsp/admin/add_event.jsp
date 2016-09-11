<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<fmt:setLocale value="${sessionScope.locale}" />
	<fmt:setBundle basename="localization.admin" var="admin" />
	<fmt:message bundle="${admin}" key="admin.addEvent.slogan" var="slogan" />
	<fmt:message bundle="${admin}" key="admin.title.general" var="title" />

	
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
			<br/>
			
			<div>
				Sport <select name="sport">
					
				</select>
				
				Competition <select name="competition">
				
				</select>
				
				Event 
				<input type="text" size="40" maxLength="100" />
			</div>
			
		</div>
	</div>
	
		</div>
	</div> 

	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
	
	
</body>
</html>