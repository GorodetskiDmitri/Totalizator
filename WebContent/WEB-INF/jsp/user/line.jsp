<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<fmt:setLocale value="${sessionScope.locale}" />
	<fmt:setBundle basename="localization.user" var="user" />
	<fmt:message bundle="${user}" key="user.title.line" var="title" />
	<fmt:message bundle="${user}" key="user.slogan.line" var="slogan" />
	<fmt:message bundle="${user}" key="user.line.lineNotFound" var="lineNotFound" />
	<c:set var="sport" value="" scope="request"/> 
	
	<title>${title}</title>
</head>
<body>
	<%@ include file="user_menu.jsp" %>
	
	<!-- Контент страницы -->
	<div class="content">
		<div class="container">
			<div class="slogan" style="margin-bottom: 10px">
				<h1>${slogan}</h1>
			</div>
			<br/>
			<c:if test="${line.size() != 0}">
				<div class="table-responsive table-wrapper">
					<table id="lineTable" class="table table-style table-prop">
						<thead>
							<tr class="table-head">
								<th>Date</th>
								<th>Competition</th>
								<th>Event Name</th>
								<th>Win</th>
								<th>Draw</th>
								<th>Lose</th>
 								<%-- <th><c:out value="${betEvent}" /></th>
 								<th><c:out value="${betOutcome}" /></th>
 								<th><c:out value="${betCoefficient}" /></th>
 								<th><c:out value="${betAmount}" /></th>
 								<th><c:out value="${betResult}" /></th> 
 								<th><c:out value="${betCash}" /></th>  --%>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${line}" var="line">
								<tr>
									<c:if test="${!sport.equalsIgnoreCase(line.sport.name)}">
											<td colspan="6" align="left"><c:out value="${line.sport.name.toUpperCase()}" /></td></tr><tr>
											<c:set var="sport" value="${line.sport.name}" scope="request"/>
									</c:if>
										
									<td><fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${line.startDate}" /></td>
									<td align="left"><c:out value="${line.competition.name}" /></td>
									<td align="left"><c:out value="${line.eventName}" /></td>
									<td><a href="#"><c:out value="${line.winCoeff}" /></a></td>
									<td><c:if test="${line.drawCoeff > 0}">
											<c:out value="${line.drawCoeff}" />
										</c:if>
									</td>
									<td><c:out value="${line.loseCoeff}" /></td>
									<%-- <td align="left"><c:out value="${bet.line.sport.name.toUpperCase()}. ${bet.line.competition.name}. ${bet.line.eventName}" /></td>
									<td>
										<c:if test="${bet.outcome.equals('1')}">
											<c:out value="Win 1" />
										</c:if>
										<c:if test="${bet.outcome.equals('2')}">
											<c:out value="Draw" />
										</c:if>
										<c:if test="${bet.outcome.equals('3')}">
											<c:out value="Win 2" />
										</c:if>
									</td>
									<td>
										<c:if test="${bet.outcome.equals('1')}">
											<c:out value="${bet.line.winCoeff}" />
										</c:if>
										<c:if test="${bet.outcome.equals('2')}">
											<c:out value="${bet.line.drawCoeff}" />
										</c:if>
										<c:if test="${bet.outcome.equals('3')}">
											<c:out value="${bet.line.loseCoeff}" />
										</c:if>
									</td>
									<td><c:out value="${bet.amount}" /></td>
									<td>
										<c:choose>
											<c:when test="${bet.line.score1 == -1 || bet.line.score2 == -1 || !bet.line.fixedResult.equals('1')}">
											</c:when>
											<c:otherwise>
												<c:out value="${bet.line.score1} : ${bet.line.score2}" />
											</c:otherwise>
										</c:choose>
									</td>
									
									<c:if test="${bet.line.fixedResult.equals('1')}">
										<c:choose>
											<c:when test="${bet.outcome.equals('1') && (bet.line.score1 > bet.line.score2) }">
												<td style="color: DodgerBlue"><b>
													<fmt:formatNumber type="number" pattern=".##" value="${bet.amount * bet.line.winCoeff}" />
												</b></td>
											</c:when>
											<c:when test="${bet.outcome.equals('2') && (bet.line.score1 == bet.line.score2) }">
												<td style="color: DodgerBlue"><b>
													<fmt:formatNumber type="number" pattern=".##" value="${bet.amount * bet.line.drawCoeff}" />
												</b></td>
											</c:when>
											<c:when test="${bet.outcome.equals('3') && (bet.line.score1 < bet.line.score2) }">
												<td style="color: DodgerBlue"><b>
													<fmt:formatNumber type="number" pattern=".##"  value="${bet.amount * bet.line.loseCoeff}" />
												</b></td>
											</c:when>
											<c:otherwise>
												<td style="color: #d42819"><b><c:out value="0.00" /></b></td>
											</c:otherwise>
										</c:choose>
									</c:if>
									<c:if test="${!bet.line.fixedResult.equals('1')}">
										<td><c:out value="?" /></td>
									</c:if> --%>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>
			
			<c:if test="${line.isEmpty()}">
				<h3 align="center"><c:out value="${lineNotFound}" /></h3>
			</c:if>
			
			
	
		</div> 
	</div>

	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
	
</body>
</html>