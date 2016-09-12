<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<fmt:setLocale value="${sessionScope.locale}" />
	<fmt:setBundle basename="localization.admin" var="admin" />
	<fmt:message bundle="${admin}" key="admin.fix.slogan" var="slogan" />
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
			<c:if test="${line.size() != 0}">
			<div class="table-responsive table-wrapper">
				<table id="userListTable" class="table table-style">
					<thead>
 						<tr class="table-head">
 							<th><c:out value="Date" /></th>
 							<th><c:out value="Sport" /></th>
 							<th><c:out value="Competition" /></th>
 							<th><c:out value="Event" /></th>
 							<th><c:out value="Score" /></th>
 							<th></th>
 						</tr>
 					</thead>
 					<tbody>
						<c:forEach items="${line}" var="line">
						<tr>
							<form id="line${line.id}-form" method="POST" action="Controller" accept-charset="UTF-8">
									<input type="hidden" name="command" value="fix-result" />
      								<input type="hidden" name="lineId" value="${line.id}" />
      								
							<td id="${line.id}_date"><fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${line.startDate}" /></td>
							<td id="${line.id}_sport"><c:out value="${line.sport.name.toUpperCase()}" /></td>
							<td id="${line.id}_competition"><c:out value="${line.competition.name}" /></td>
							<td id="${line.id}_event"><c:out value="${line.eventName}" /></td>
							<td>
								<input type="text" id="${line.id}_score1" name="score1" class="score" value="" size="2" maxlength="3" style="background-color: Moccasin; color: black"/>
								 : 
								<input type="text" id="${line.id}_score2" name="score2" class="score" value="" size="2" maxlength="3" style="background-color: Moccasin; color: black"/>
							</td>
							<td>
      								<button type="submit" class="btn btn-primary btn-xs" ><c:out value="Fix Result"/></button>
							</td>
							
							</form>
						</tr>
						</c:forEach>
					</tbody>
				</table>
				
			</div>
			</c:if>
			
			<c:if test="${line.isEmpty()}">
				<h3 align="center"><c:out value="Not Found" /></h3>
			</c:if>
			
		</div>
	</div>
	
		</div>
	</div> 

	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
	
	<!-- Подключение jQuery и JavaScript-->
	<script type="text/javascript">
	// Allow enter numbers only
	$('.score').keypress(function(event) {
		if (event.which != 8) {
			if (event.which < 48 || event.which > 57) {
				return false;
			}
		}
	});
	</script>
</body>
</html>