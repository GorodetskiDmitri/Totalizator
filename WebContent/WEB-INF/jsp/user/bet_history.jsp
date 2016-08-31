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
	<fmt:message bundle="${user}" key="user.betHistory.betAmount" var="betAmount" />
	<fmt:message bundle="${user}" key="user.betHistory.betCoefficient" var="betCoefficient" />
	<fmt:message bundle="${user}" key="user.betHistory.betResult" var="betResult" /> 
	<%-- <fmt:message bundle="${admin}" key="admin.userlist.slogan" var="slogan" />
	<fmt:message bundle="${admin}" key="admin.title.general" var="title" />
	<fmt:message bundle="${admin}" key="admin.userlist.search" var="search" />
	<fmt:message bundle="${admin}" key="admin.userlist.find" var="find" />
	<fmt:message bundle="${admin}" key="admin.userlist.login" var="userLogin" />
	<fmt:message bundle="${admin}" key="admin.userlist.name" var="userName" />
	<fmt:message bundle="${admin}" key="admin.userlist.surname" var="userSurname" />
	<fmt:message bundle="${admin}" key="admin.userlist.dateOfBirth" var="userDateOfBirth" />
	<fmt:message bundle="${admin}" key="admin.userlist.balance" var="userBalance" />
	<fmt:message bundle="${admin}" key="admin.userlist.betAllow" var="userBetAllow" />
	<fmt:message bundle="${admin}" key="admin.userlist.betAllow.yes" var="userBetAllowYes" />
	<fmt:message bundle="${admin}" key="admin.userlist.betAllow.no" var="userBetAllowNo" />
	<fmt:message bundle="${admin}" key="admin.userlist.email" var="userEmail" />
	<fmt:message bundle="${admin}" key="admin.userlist.passport" var="userPassport" />
	<fmt:message bundle="${admin}" key="admin.userlist.address" var="userAddress" />
	<fmt:message bundle="${admin}" key="admin.userlist.phone" var="userPhone" />
	<fmt:message bundle="${admin}" key="admin.userlist.notFound" var="userNotFound" />
	<fmt:message bundle="${admin}" key="admin.userlist.userInfo" var="userInfo" />
	<fmt:message bundle="${admin}" key="admin.button.removeUser" var="btnRemoveUser" />
	<fmt:message bundle="${admin}" key="admin.button.allowBet" var="btnAllowBet" />
	<fmt:message bundle="${admin}" key="admin.button.forbidBet" var="btnForbidBet" />
	
	<c:set var="totalPage" value="${totalPage}" scope="request"/>
	<c:set var="searchText" value="${param.searchText}"/>--%>
	<c:set var="totalPage" value="${totalPage}" scope="request"/>
	<c:set var="currentPage" value="${param.currentPage}"/> 
	
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
			 <div>
				<form method="POST" action="Controller" class="form-inline" name="searchForm" id="searchForm">
					<input type="hidden" name="command" value="show-bet-history"/>
					<input type="text" name="currentPage" id="currentPage" value="${currentPage}"/>
					
      				<input type="submit" id="submitButton" class="btn btn-primary" value="tttt"/>
				</form>
			</div>
			<br/>
			<c:if test="${betList.size() != 0}">
				<div class="table-responsive table-wrapper">
					<table id="betHistoryTable" class="table table-hover table-style">
						<thead>
							<tr class="table-head">
								<th><c:out value="${betDate}" /></th>
 								<th><c:out value="${betEvent}" /></th>
 								<th><c:out value="${betCoefficient}" /></th>
 								<th><c:out value="${betAmount}" /></th>
 								<th><c:out value="${betResult}" /></th> 
 								
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${betList}" var="bet">
								<tr>
									<td><c:out value="${bet.betDate}" /></td>
									<td><c:out value="${bet.betDate}" /></td>
									<td><c:out value="${bet.betDate}" /></td>
									<td><c:out value="${bet.betDate}" /></td>
									<td><c:out value="${bet.betDate}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>
			
			<c:if test="${betList.isEmpty()}">
				<h3 align="center">Not Found</h3>
			</c:if>
			<%--<c:if test="${userList.size() != 0}">
			<div class="table-responsive table-wrapper">
				<table id="userListTable" class="table table-hover table-style">
					
 					<tbody>
						<c:forEach items="${userList}" var="user">
						<tr>
							<td><a href="#myModal" class="userLink" id="${user.id}" data-toggle="modal">${user.login}</a></td>
							<td id="${user.id}_name"><c:out value="${user.name}" /></td>
							<td id="${user.id}_surname"><c:out value="${user.surname}" /></td>
							<td id="${user.id}_dateOfBirth"><c:out value="${user.dateOfBirth}" /></td>
							<td id="${user.id}_balance"><c:out value="${user.balance}" /></td>
							<td id="${user.id}_betAllow">
								<c:if test="${user.betAllow == 1}">
									<c:out value="${userBetAllowYes}" />
								</c:if>
								<c:if test="${user.betAllow == 0}">
									<c:out value="${userBetAllowNo}" />
								</c:if>
							</td>
							<input type="hidden" id="${user.id}_betAllowFlag" value="${user.betAllow}"/>
							<input type="hidden" id="${user.id}_email" value="${user.email}"/>
							<input type="hidden" id="${user.id}_address" value="${user.address}"/>
							<input type="hidden" id="${user.id}_phone" value="${user.phone}"/>
							<input type="hidden" id="${user.id}_passport" value="${user.passport}"/>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			</c:if>
			
			<c:if test="${userList.isEmpty()}">
				<h3 align="center"><c:out value="${userNotFound}" /></h3>
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
	</div>--%>
	
	
		
		</div> 
	</div>

	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
	
	<!-- Подключение jQuery и JavaScript-->
	<script src="resources/js/user-list.js"></script>
	
</body>
</html>