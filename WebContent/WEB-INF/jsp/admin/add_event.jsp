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
	<div class="content" >
		<div class="container">
			<div class="slogan" style="margin-bottom: 10px">
				<h1>${slogan}</h1>
			</div>
			<br/>
			
			<div>
			<form id="add-event-form" method="POST" action="Controller" accept-charset="UTF-8">
				Sport <select name="sport" style="color: black">
					<c:forEach items="${sport}" var="sport">
						<option value="${sport.id}" <c:if test='${sport.name.equalsIgnoreCase("football")}'>selected</c:if> ><c:out value="${sport.name}"/></option>
					</c:forEach>
				</select>
				
				Competition <select name="competition" style="color: black">
					<c:forEach items="${competition}" var="competition">
						<option value="${competition.id}" <c:if test='${competition.name.equalsIgnoreCase("champions league")}'>selected</c:if> ><c:out value="${competition.name}"/></option>
					</c:forEach>
				</select>
				
				Event 
				<input type="text" size="40" maxLength="100" style="color: black"/>
				
				DateTime 
				<input type="text" size="14" maxLength="16" style="color: black"/>
				
				Win 1 
				<input type="text" size="3" maxLength="6" style="color: black"/>
				
				Draw 
				<input type="text" size="3" maxLength="6" style="color: black"/>
				
				Win 2 
				<input type="text" size="3" maxLength="6" style="color: black"/>
				
				Amount From
				<input type="text" size="3" maxLength="6" style="color: black"/>
				&nbsp;to&nbsp;
				<input type="text" size="3" maxLength="6" style="color: black"/> 
				
				<button type="submit" class="btn btn-primary btn-xs" ><c:out value="Add Event"/></button>
				</form>
			</div>
			
		</div>
	</div>
	
		</div>
	</div> 

	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
	
	
</body>
</html>