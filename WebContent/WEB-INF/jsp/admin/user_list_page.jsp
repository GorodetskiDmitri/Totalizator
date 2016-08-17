<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<fmt:setLocale value="${sessionScope.locale}" />
	<fmt:setBundle basename="localization.admin" var="admin" />
	<fmt:message bundle="${admin}" key="admin.userlist.slogan" var="slogan" />
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
			<br/>
			<c:if test="${userList.size() != 0}">
			<div class="table-responsive table-wrapper">
				<table id="userListTable" class="table table-hover table-style">
					<thead>
 						<tr class="table-head">
 							<th><c:out value="${userLogin}" /></th>
 							<th><c:out value="${userName}" /></th>
 							<th><c:out value="${userSurname}" /></th>
 							<th><c:out value="${userDateOfBirth}" /></th>
 							<th><c:out value="${userBalance}" /></th>
 							<th><c:out value="${userBetAllow}" /></th>
 						</tr>
 					</thead>
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
	</div>
	
	<!-- Modal -->
	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">
		
		<!-- Modal content-->
    	<div class="modal-content">
      		<div class="modal-header">
      			<button type="button" class="close" data-dismiss="modal">&times;</button>
        		<h4 class="modal-title"><c:out value="${userInfo}"/></h4>
      		</div>
      		
      		<div class="modal-body">
				<div>
					<h5 class="text-center"><c:out value="${userLogin}"/>: <span id="userLogin" /></h5>
					<h5 class="text-center"><c:out value="${userName}"/>: <span id="userName" /></h5>
					<h5 class="text-center"><c:out value="${userSurname}"/>: <span id="userSurname" /></h5>
					<h5 class="text-center"><c:out value="${userBalance}"/>: <span id="userBalance" /></h5>
					<h5 class="text-center"><c:out value="${userEmail}"/>: <span id="userEmail" /></h5>
					<h5 class="text-center"><c:out value="${userDateOfBirth}"/>: <span id="userDateOfBirth" /></h5>
					<h5 class="text-center"><c:out value="${userPassport}"/>: <span id="userPassport" /></h5>
					<h5 class="text-center"><c:out value="${userPhone}"/>: <span id="userPhone" /></h5>
					<h5 class="text-center"><c:out value="${userAddress}"/>: <span id="userAddress" /></h5>
					<h5 class="text-center"><c:out value="${userBetAllow}"/>: <span id="userBetAllow" /></h5>
				</div>
      		</div>
      		
      		<div class="modal-footer">
			  <div>
				<div class="float-style">
					<form id="removeUser-form" method="POST" action="Controller" accept-charset="UTF-8">
						<input type="hidden" name="command" value="remove-user" />
						<input type="hidden" name="userId" id="userId" value=""/>
						<button type="submit" class="btn btn-primary"><c:out value="${btnRemoveUser}"/></button>
					</form>
				</div>
				<div class="float-style">
					<form id="allowBet-form" method="POST" action="Controller" accept-charset="UTF-8">
						<input type="hidden" name="command" value="allow-bet" />
						<input type="hidden" name="userId" value=""/>
						<input type="hidden" name="allowBet" id="allowBet" value="1"/>
						<button type="submit" class="btn btn-success"><c:out value="${btnAllowBet}"/></button>
					</form>
				</div>
				<div class="float-style">
					<form id="forbidBet-form" method="POST" action="Controller" accept-charset="UTF-8">
						<input type="hidden" name="command" value="allow-bet" />
						<input type="hidden" name="userId" value=""/>
						<input type="hidden" name="allowBet" id="allowBet" value="0"/>
						<button type="submit" class="btn btn-danger"><c:out value="${btnForbidBet}"/></button>
					</form>
				</div>
			  </div>
			</div>
		</div>
		
		</div>
	</div>

	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
	
	<!-- Подключение jQuery и JavaScript-->
	<script src="resources/js/user-list.js"></script>
	
</body>
</html>