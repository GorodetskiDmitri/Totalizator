<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<fmt:setLocale value="${sessionScope.locale}" />
	<fmt:setBundle basename="localization.admin" var="admin" />
	<fmt:setBundle basename="localization.user" var="user" />
	<fmt:message bundle="${admin}" key="admin.fix.slogan" var="slogan" />
	<fmt:message bundle="${admin}" key="admin.fix.notFound" var="fixNotFound" />
	<fmt:message bundle="${admin}" key="admin.fix.allResults" var="fixShowAllResults" />
	<fmt:message bundle="${admin}" key="admin.fix.scoreNaN" var="scoreNaN" />
	<fmt:message bundle="${admin}" key="admin.fix.scoreNegative" var="scoreNegative" />
	<fmt:message bundle="${admin}" key="admin.fix.scoreEmpty" var="scoreEmpty" />
	<fmt:message bundle="${admin}" key="admin.button.fixResult" var="fixResultBtn" />
	<fmt:message bundle="${admin}" key="admin.title.general" var="title" />
	<fmt:message bundle="${user}" key="user.line.startDate" var="lineDate" />
	<fmt:message bundle="${user}" key="user.line.sport" var="lineSport" />
	<fmt:message bundle="${user}" key="user.line.competition" var="lineCompetition" />
	<fmt:message bundle="${user}" key="user.line.event" var="lineEvent" />
	<fmt:message bundle="${user}" key="user.line.score" var="lineScore" />
	
	<title><c:out value="${title}"/></title>
</head>

<body>
	<%@ include file="admin_menu.jsp" %>
	
	<!-- Page content -->
	<div class="content">
		<div class="container">
			<div class="slogan" style="margin-bottom: 10px">
				<h1>${slogan}</h1>
			</div>
			<br/>
			
			<form method="POST" action="Controller" id="results-form">
				<input type="hidden" name="command" id="command" value="show-result"/>
				<input type="hidden" name="currentPage" value="1"/>
				<button type="submit" class="btn btn-success" ><c:out value="${fixShowAllResults}"/></button>
				<br/><br/>
			</form>
			
			<c:if test="${line.size() != 0}">
			<div class="table-responsive table-wrapper">
				<table id="userListTable" class="table table-style">
					<thead>
 						<tr class="table-head">
 							<th><c:out value="${lineDate}" /></th>
 							<th><c:out value="${lineSport}" /></th>
 							<th><c:out value="${lineCompetition}" /></th>
 							<th><c:out value="${lineEvent}" /></th>
 							<th><c:out value="${lineScore}" /></th>
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
								<input type="text" id="${line.id}_score1" name="score1" class="numOnly" value="" size="2" maxlength="3" style="background-color: Moccasin; color: black"/>
								 : 
								<input type="text" id="${line.id}_score2" name="score2" class="numOnly" value="" size="2" maxlength="3" style="background-color: Moccasin; color: black"/>
							</td>
							<td>
      							<button type="submit" id="${line.id}_fix" class="btn btn-primary btn-xs btn-fix" ><c:out value="${fixResultBtn}"/></button>
							</td>
							</form>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			</c:if>
			
			<input type="hidden" id="scoreNaN" value="${scoreNaN}" />
			<input type="hidden" id="scoreNegative" value="${scoreNegative}" />
			<input type="hidden" id="scoreEmpty" value="${scoreEmpty}" />
			
			<c:if test="${line.isEmpty()}">
				<h3 align="center"><c:out value="${fixNotFound}" /></h3>
			</c:if>
			
		</div>
	</div>

	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
	
	<!-- jQuery and JavaScript-->
	<script src="resources/js/util.js"></script> 
	<script src="resources/js/validation.js"></script>
	
</body>
</html>