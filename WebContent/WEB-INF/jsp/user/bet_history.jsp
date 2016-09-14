<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<fmt:setLocale value="${sessionScope.locale}" />
	<fmt:setBundle basename="localization.user" var="user" />
	<fmt:message bundle="${user}" key="user.title.betHistory" var="title" />
	<fmt:message bundle="${user}" key="user.slogan.betHistory" var="slogan" />
	<fmt:message bundle="${user}" key="user.betHistory.betDate" var="betDate" />
	<fmt:message bundle="${user}" key="user.betHistory.betEvent" var="betEvent" />
	<fmt:message bundle="${user}" key="user.betHistory.betOutcome" var="betOutcome" />
	<fmt:message bundle="${user}" key="user.betHistory.betAmount" var="betAmount" />
	<fmt:message bundle="${user}" key="user.betHistory.betCoefficient" var="betCoefficient" />
	<fmt:message bundle="${user}" key="user.betHistory.betResult" var="betResult" /> 
	<fmt:message bundle="${user}" key="user.betHistory.betCash" var="betCash" /> 
	<fmt:message bundle="${user}" key="user.betHistory.betNotFound" var="betNotFound" /> 
	<c:set var="totalPage" value="${totalPage}" scope="request"/>
	<c:set var="currentPage" value="${param.currentPage}"/> 
	
	<title><c:out value="${title}"/></title>
</head>

<body>
	<%@ include file="user_menu.jsp" %>
	
	<!-- Page content -->
	<div class="content">
		<div class="container">
			<div class="slogan" style="margin-bottom: 10px">
				<h1><c:out value="${slogan}" /></h1>
			</div>
			<br/>
			<c:if test="${betList.size() != 0}">
				<div class="table-responsive table-wrapper">
					<table id="betHistoryTable" class="table table-style">
						<thead>
							<tr class="table-head">
								<th><c:out value="${betDate}" /></th>
 								<th><c:out value="${betEvent}" /></th>
 								<th><c:out value="${betOutcome}" /></th>
 								<th><c:out value="${betCoefficient}" /></th>
 								<th><c:out value="${betAmount}" /></th>
 								<th><c:out value="${betResult}" /></th> 
 								<th><c:out value="${betCash}" /></th> 
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${betList}" var="bet">
								<tr>
									<td><c:out value="${bet.betDate}" /></td>
									<td align="left">
										<c:if test="${sessionScope.locale.equals(\"ru\")}">
											<c:out value="${bet.line.sport.nameRu.toUpperCase()}. ${bet.line.competition.nameRu}. ${bet.line.eventName}"/>
										</c:if>
										<c:if test="${!sessionScope.locale.equals(\"ru\")}">
											<c:out value="${bet.line.sport.name.toUpperCase()}. ${bet.line.competition.name}. ${bet.line.eventName}"/>
										</c:if>
									</td>
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
											<c:when test="${!bet.line.fixedResult.equals('1')}">
											</c:when>
											<c:otherwise>
												<c:out value="${bet.line.score1} : ${bet.line.score2}" />
											</c:otherwise>
										</c:choose>
									</td>
									
									<c:if test="${bet.line.fixedResult.equals('1')}">
										<c:choose>
											<c:when test="${bet.outcome.equals('1') && (bet.line.score1 > bet.line.score2) }">
												<td style="color: DodgerBlue">
													<b><fmt:formatNumber type="number" pattern=".##" value="${bet.amount * bet.line.winCoeff}" /></b>
												</td>
											</c:when>
											<c:when test="${bet.outcome.equals('2') && (bet.line.score1 == bet.line.score2) }">
												<td style="color: DodgerBlue">
													<b><fmt:formatNumber type="number" pattern=".##" value="${bet.amount * bet.line.drawCoeff}" /></b>
												</td>
											</c:when>
											<c:when test="${bet.outcome.equals('3') && (bet.line.score1 < bet.line.score2) }">
												<td style="color: DodgerBlue">
													<b><fmt:formatNumber type="number" pattern=".##"  value="${bet.amount * bet.line.loseCoeff}" /></b>
												</td>
											</c:when>
											<c:otherwise>
												<td style="color: #d42819">
													<b><c:out value="0.00" /></b>
												</td>
											</c:otherwise>
										</c:choose>
									</c:if>
									<c:if test="${!bet.line.fixedResult.equals('1')}">
										<td><c:out value="?" /></td>
									</c:if>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>
			
			<c:if test="${betList.isEmpty()}">
				<h3 align="center"><c:out value="${betNotFound}" /></h3>
			</c:if>
			
			<c:if test="${totalPage != 1}"> 
			<div class="container" align="center">
          		<ul class="pagination pagination-centered">
          			<c:forEach items="${pageList}" var="page">
            			<li id="${page}_li"><a class="page-link" href="#">${page}</a></li>
          			</c:forEach>
          		</ul>
        	</div>
        	</c:if>
		
		</div>
	</div>
	
	<form method="POST" action="Controller" class="form-inline" name="betHistoryForm" id="betHistoryForm">
		<input type="hidden" name="command" value="show-bet-history"/>
		<input type="hidden" name="currentPage" id="currentPage" value="${currentPage}"/>
	</form>

	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
	
	<!-- jQuery and JavaScript-->
	<script type="text/javascript">
		$(document).ready(function() {
			$("#" + $("#currentPage").val() + "_li").addClass("active");
		});
	
		$(".page-link").click(function() {
			$("#currentPage").val($(this).text());
			$("#betHistoryForm").submit();
		});
	</script>
	
</body>
</html>