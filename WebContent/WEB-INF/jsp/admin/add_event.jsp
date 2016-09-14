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
	<fmt:message bundle="${admin}" key="admin.title.general" var="title" />
	<fmt:message bundle="${admin}" key="admin.addEvent.slogan" var="slogan" />
	<fmt:message bundle="${admin}" key="admin.addEvent.eventName" var="eventName" />
	<fmt:message bundle="${admin}" key="admin.addEvent.coefficients" var="eventCoefficients" />
	<fmt:message bundle="${admin}" key="admin.addEvent.betAmount" var="eventBetAmount" />
	<fmt:message bundle="${admin}" key="admin.addEvent.lastAddedEvents" var="lastAddedEvents" />
	<fmt:message bundle="${admin}" key="admin.addEvent.eventNameNotValid" var="eventNameNotValid" />
	<fmt:message bundle="${admin}" key="admin.addEvent.dateTimeNotValid" var="dateTimeNotValid" />
	<fmt:message bundle="${admin}" key="admin.button.addEvent" var="addEventBtn" />
	<fmt:message bundle="${user}" key="user.line.id" var="lineId" />
	<fmt:message bundle="${user}" key="user.line.startDate" var="lineStartDate" />
	<fmt:message bundle="${user}" key="user.line.sport" var="lineSport" />
	<fmt:message bundle="${user}" key="user.line.competition" var="lineCompetition" />
	<fmt:message bundle="${user}" key="user.line.event" var="lineEvent" />
	<fmt:message bundle="${user}" key="user.line.win" var="lineWin" />
	<fmt:message bundle="${user}" key="user.line.draw" var="lineDraw" />
	<fmt:message bundle="${user}" key="user.line.lose" var="lineLose" />
	<fmt:message bundle="${user}" key="user.line.minBet" var="lineMinBet" />
	<fmt:message bundle="${user}" key="user.line.maxBet" var="lineMaxBet" />
	
	<title><c:out value="${title}"/></title>
</head>

<body>
	<%@ include file="admin_menu.jsp" %>
	
	<!-- Page content -->
	<div class="content" >
		<div class="container">
			<div class="slogan" style="margin-bottom: 10px">
				<h1><c:out value="${slogan}"/></h1>
			</div>
			<br/>
			
			<div>
			<form id="add-event-form" method="POST" action="Controller" accept-charset="UTF-8">
				<input type="hidden" name="command" value="add-event" />
				<input type="hidden" id="eventNameNotValid" value="${eventNameNotValid}" />
				<input type="hidden" id="dateTimeNotValid" value="${dateTimeNotValid}" />
			
			<div class="table-responsive table-wrapper">
			<table class="simple-padding">
				<tr>
					<td><c:out value="${lineSport}"/></td>
					<td><select name="sportId" style="color: black">
						<c:forEach items="${sport}" var="sport">
							<option value="${sport.id}" <c:if test='${sport.name.equalsIgnoreCase("football")}'>selected</c:if> ><c:out value="${sport.name}"/></option>
						</c:forEach>
						</select>
					</td>
					
					<td><c:out value="${lineCompetition}"/></td>
					<td><select name="competitionId" style="color: black">
						<c:forEach items="${competition}" var="competition">
							<option value="${competition.id}" <c:if test='${competition.name.equalsIgnoreCase("champions league")}'>selected</c:if> ><c:out value="${competition.name}"/></option>
						</c:forEach>
						</select>
					</td>
					
					<td><c:out value="${eventName}"/></td>
					<td><input type="text" id="eventName" name="eventName" size="50" maxLength="100" style="color: black"/></td>
				</tr>
			</table>
			
			<table align="left" class="simple-padding">
				<tr>
					<td><c:out value="${lineStartDate}"/></td>
					<td><input type="text" id="eventDate" name="eventDate" size="14" maxLength="16" value="2016-MM-dd 18:00" style="color: black"/></td>
					
					<td style="color: gold"><c:out value="${eventCoefficients}:"/></td>
					<td><c:out value="${lineWin}"/></td>
					<td><input type="text" id="eventWinCoeff" name="eventWinCoeff" class="decimalOnly" size="4" maxLength="6" style="color: black"/></td>
					
					<td><c:out value="${lineDraw}"/></td>
					<td><input type="text" id="eventDrawCoeff" name="eventDrawCoeff" class="decimalOnly" size="4" maxLength="6" style="color: black"/></td>
					
					<td><c:out value="${lineLose}"/></td>
					<td><input type="text" id="eventLoseCoeff" name="eventLoseCoeff" class="decimalOnly" size="4" maxLength="6" style="color: black"/></td>
					
					<td style="color: gold"><c:out value="${eventBetAmount}:"/></td>
					<td><c:out value="Min"/></td>
					<td><input type="text" id="eventMinBet" name="eventMinBet" class="decimalOnly" size="4" maxLength="6" style="color: black"/></td>
					
					<td><c:out value="Max"/></td>
					<td><input type="text" id="eventMaxBet" name="eventMaxBet" class="decimalOnly" size="4" maxLength="6" style="color: black"/></td>
					
					<td></td>
					<td><button type="submit" class="btn btn-primary"><c:out value="${addEventBtn}"/></button></td>
				</tr>
			</table>
			</div>	
			
			</form>
			</div>
			
			<br/>
			<h4 style="color: #d42819"><c:out value="${lastAddedEvents}"/></h4>
			
			<div>
			<c:if test="${line.size() != 0}">
				<div class="table-responsive table-wrapper">
					<table id="lineTable" class="table table-style table-prop">
						<thead>
							<tr class="table-head">
								<th><c:out value="${lineId}" /></th>
								<th><c:out value="${lineStartDate}" /></th>
								<th><c:out value="${lineSport}" /></th>
								<th><c:out value="${lineCompetition}" /></th>
								<th><c:out value="${lineEvent}" /></th>
								<th><c:out value="${lineWin}" /></th>
								<th><c:out value="${lineDraw}" /></th>
								<th><c:out value="${lineLose}" /></th>
								<th><c:out value="${lineMinBet}" /></th>
								<th><c:out value="${lineMaxBet}" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${line}" var="line">
								<tr>
									<td style="color:gold"><c:out value="${line.id}" /></td>
									<td><fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${line.startDate}" /></td>
									<td align="left"><c:out value="${line.sport.name}" /></td>
									<td align="left"><c:out value="${line.competition.name}" /></td>
									<td align="left"><c:out value="${line.eventName}" /></td>
									<td><c:if test="${line.winCoeff > 1}">
											<c:out value="${line.winCoeff}" />
										</c:if>
									</td>
									<td><c:if test="${line.drawCoeff > 1}">
											<c:out value="${line.drawCoeff}" />
										</c:if>
									</td>
									<td><c:if test="${line.loseCoeff > 1}">
											<c:out value="${line.loseCoeff}" />
										</c:if>
									</td>
									<td><c:out value="${line.minBet}" /></td>
									<td><c:out value="${line.maxBet}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>
			</div>
			
		</div>
	</div>
	
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
	
	<!-- jQuery and JavaScript-->
	<script src="resources/js/util.js"></script> 
	<script src="resources/js/validation.js"></script> 

</body>
</html>