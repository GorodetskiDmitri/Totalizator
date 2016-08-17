<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<fmt:setLocale value="${sessionScope.locale}" />
	<fmt:setBundle basename="localization.user" var="user" />
	<fmt:message bundle="${user}" key="user.title.deposit" var="title" />
	<fmt:message bundle="${user}" key="user.slogan.deposit" var="slogan" />
	<fmt:message bundle="${user}" key="user.deposit.current" var="current" />
	<fmt:message bundle="${user}" key="user.deposit.unresolved" var="unresolved" />
	<fmt:message bundle="${user}" key="user.deposit.ordered" var="ordered" />
	<fmt:message bundle="${user}" key="user.deposit.adjunction" var="adjunction" />
	<fmt:message bundle="${user}" key="user.button.deposit" var="btnDeposit" />
	<fmt:message bundle="${user}" key="user.button.depositAction" var="btnDepositAction" />
	
	<title>${title}</title>
</head>
<body>
	<%@ include file="user_menu.jsp" %>
	
	<!-- Контент страницы -->
	<div class="content">
		<div class="container">
			<div class="slogan" style="margin-bottom: 10px">
				<h1><c:out value="${slogan}"/></h1>
			</div>
			<div>
				<table class="simple-table">
					<tr>
						<td><c:out value="${current}"/></td>
						<td style="color: yellow"><c:out value="${sessionScope.client.balance}"/>&nbsp;$</td>
					</tr>
					<tr>
						<td><c:out value="${unresolved}"/></td>
						<td style="color: #d42819">0.00&nbsp;$</td>
					</tr>
					<tr>
						<td><c:out value="${ordered}"/></td>
						<td style="color: limegreen">0.00&nbsp;$</td>
					</tr>
				</table>
				<br/>
				<a href="#depositModal" data-toggle="modal"><input type="button" class="btn btn-primary" value="${btnDeposit}"></button></a>
				
	<!-- Modal -->
	<div class="modal fade" id="depositModal" role="dialog">
		<div class="modal-dialog">
		
		<!-- Modal content-->
    	<div class="modal-content">
      		<div class="modal-header">
      			<button type="button" class="close" data-dismiss="modal">&times;</button>
        		<h4 class="modal-title"><c:out value="${adjunction}"/></h4>
      		</div>
      		
      		<div class="modal-body">
				<div>
					<form id="deposit-form" name="deposit-form" method="POST" action="Controller" class="form-horizontal" > 
						<input type="hidden" name="command" value="make-deposit" />
						
						<input type="radio" name="card" value="webMoney"> WEB MONEY
						<br/>
						<input type="radio" name="card" value="visa"> VISA
						<br/>
						<input type="radio" name="card" value="masterCard"> MASTER CARD
						<br/>
						
						<br/>Card Number<br/>
						<input type="text" name="cardNumber" id="cardNumber" maxlength="12"/>
						
						<br/>Summa<br/>
						<input type="text" name="deposit" id="deposit" maxlength="7"/>
					</form>
				</div>
      		</div>
      		
      		<div class="modal-footer">
			  	<div align="left">
			  		<button type="submit" class="btn btn-primary"><c:out value="${btnDepositAction}"/></button>
				</div>
			</div>
		</div>
		</div>
	</div>
	
			</div>
		</div>
	</div>

	

	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
	
</body>
</html>