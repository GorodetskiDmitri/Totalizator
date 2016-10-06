<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<fmt:setLocale value="${sessionScope.locale}" />
	<fmt:setBundle basename="localization.locale" var="loc" />
	<fmt:message bundle="${loc}" key="user.title.result" var="title" />
	<fmt:message bundle="${loc}" key="user.slogan.result" var="slogan" />
	<fmt:message bundle="${loc}" key="user.line.sport" var="lineSport" />
	<fmt:message bundle="${loc}" key="user.line.competition" var="lineCompetition" />
	<fmt:message bundle="${loc}" key="user.line.event" var="lineEvent" />
	<fmt:message bundle="${loc}" key="user.line.score" var="lineScore" />
	<fmt:message bundle="${loc}" key="user.line.win" var="lineWin" />
	<fmt:message bundle="${loc}" key="user.line.draw" var="lineDraw" />
	<fmt:message bundle="${loc}" key="user.line.lose" var="lineLose" />
	<fmt:message bundle="${loc}" key="user.result.resultNotFound" var="resultNotFound" />
	<c:set var="totalPage" value="${totalPage}" scope="request"/>
	<c:set var="currentPage" value="${param.currentPage}"/> 
	<c:set var="dateOfEvent" value="" scope="request"/> 
	
	<title><c:out value="${title}"/></title>
</head>

<body>
	<c:if test="${sessionScope.client.status.equalsIgnoreCase(\"admin\")}">
		<%@ include file="../admin/admin_menu.jsp" %>
	</c:if>
	<c:if test="${!sessionScope.client.status.equalsIgnoreCase(\"admin\")}">
		<%@ include file="user_menu.jsp" %>
	</c:if>
	
	<!-- Page content -->
	<div class="content">
		<div class="container">
			<div class="slogan" style="margin-bottom: 10px">
				<h1><c:out value="${slogan}" /></h1>
			</div>
			<br/>
			<c:if test="${line.size() != 0}">
				<div class="table-responsive table-wrapper">
					<table id="lineTable" class="table table-style table-prop">
						<thead>
							<tr class="table-head">
								<th><c:out value="${lineSport}" /></th>
								<th><c:out value="${lineCompetition}" /></th>
								<th><c:out value="${lineEvent}" /></th>
								<th><c:out value="${lineScore}" /></th>
								<th><c:out value="${lineWin}" /></th>
								<th><c:out value="${lineDraw}" /></th>
								<th><c:out value="${lineLose}" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${line}" var="line">
								<tr>
									<c:if test="${dateOfEvent != line.startDate}" >
											<td colspan="7" class="table-sport"><c:out value="${line.startDate}" /></td>
											</tr><tr>
											<c:set var="dateOfEvent" value="${line.startDate}" scope="request"/>
									</c:if>	
									<td>
										<c:if test="${sessionScope.locale.equals(\"ru\")}">
											<c:out value="${line.sport.nameRu.toUpperCase()}"/>
										</c:if>
										<c:if test="${!sessionScope.locale.equals(\"ru\")}">
											<c:out value="${line.sport.name.toUpperCase()}"/>
										</c:if>
									</td>
									<td align="left">
										<c:if test="${sessionScope.locale.equals(\"ru\")}">
											<c:out value="${line.competition.nameRu}"/>
										</c:if>
										<c:if test="${!sessionScope.locale.equals(\"ru\")}">
											<c:out value="${line.competition.name}"/>
										</c:if>
									</td>
									<td align="left"><c:out value="${line.eventName}" /></td>
									<td style="color:yellow"><c:out value="${line.score1} : ${line.score2}" /></td>
									<td><c:out value="${line.winCoeff}" /></td>
									<td><c:if test="${line.drawCoeff > 0}">
											<c:out value="${line.drawCoeff}" />
										</c:if>
									</td>
									<td><c:out value="${line.loseCoeff}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>
			
			<c:if test="${line.isEmpty()}">
				<h3 align="center"><c:out value="${resultNotFound}" /></h3>
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
        	
        	<form method="POST" action="Controller" name="resultForm" id="resultForm">
				<input type="hidden" name="command" value="show-result"/>
				<input type="hidden" name="currentPage" id="currentPage" value="${currentPage}"/>
			</form>
			
		</div> 
	</div>

	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
	
	<!-- jQuery and JavaScript-->
	<script type="text/javascript"">
		$(document).ready(function() {
			$("#" + $("#currentPage").val() + "_li").addClass("active");
		});
	
		$(".page-link").click(function() {
			$("#currentPage").val($(this).text());
			$("#resultForm").submit();
		});
	</script>
	
</body>
</html>