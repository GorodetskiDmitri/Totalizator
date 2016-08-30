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
	<fmt:message bundle="${user}" key="user.deposit.cardNumber" var="cardNumber" />
	<fmt:message bundle="${user}" key="user.deposit.summa" var="summa" />
	<fmt:message bundle="${user}" key="user.deposit.validSumma" var="validSumma" />
	<fmt:message bundle="${user}" key="user.deposit.error.card" var="cardError" />
	<fmt:message bundle="${user}" key="user.deposit.error.cardNumber" var="cardNumberError" />
	<fmt:message bundle="${user}" key="user.deposit.error.summa" var="summaError" />
	<fmt:message bundle="${user}" key="user.button.deposit" var="btnDeposit" />
	<fmt:message bundle="${user}" key="user.button.depositAction" var="btnDepositAction" />
	<fmt:message bundle="${user}" key="user.button.cancel" var="btnCancel" />
	
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
						<td colspan="2" style="color: darkorange"><c:out value="${sessionScope.client.name}"/>&nbsp;<c:out value="${sessionScope.client.surname}"/></td>
					</tr>
					<tr>
						<td><c:out value="${current}"/></td>
						<td style="color: yellow"><c:out value="${sessionScope.client.balance}"/>&nbsp;$</td>
					</tr>
					<tr>
						<td><c:out value="${unresolved}"/></td>
						<td style="color: #d42819"><c:out value="${sessionScope.unresolvedMoney}"/>&nbsp;$</td>
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
      		
      		<form id="deposit-form" method="POST" action="Controller" accept-charset="UTF-8"> 
      		<div class="modal-body">
				<div>
						<input type="hidden" name="command" value="make-deposit" />
						<input type="hidden" name="login" value="${sessionScope.client.login}" />
						<input type="hidden" name="balance" value="${sessionScope.client.balance}" />
						<input type="hidden" id="cardLimit" value="${Math.random()*1500}"/>
						
						<div class="card-img">
							<img src="resources/img/cards/webmoney.png" height="24" width="36">&nbsp;&nbsp;
							<div><input type="radio" name="card" value="webMoney"> WEB MONEY</div>
						</div>
						<div class="card-img">
							<img src="resources/img/cards/visa.png">&nbsp;&nbsp;
							<div><input type="radio" name="card" value="visa"> VISA</div>
						</div>
						<div class="card-img">
							<img src="resources/img/cards/mastercard.png">&nbsp;&nbsp;
							<div><input type="radio" name="card" value="masterCard"> MASTER CARD</div>
						</div>
						<span id="span-card" class="help-inline error"><c:out value="${cardError}"/><br/></span>
						<br/>
						
						<div id="card-control-group" class="control-group">
							<div class="controls">
								<c:out value="${cardNumber}"/><br/>
								<input type="text" name="cardNumber" id="cardNumber" maxlength="12" onkeypress="delSpan()"/>
								<span id="span-cardNumber" class="help-inline error"><c:out value="${cardNumberError}"/></span>
							</div>
						</div>
						
						<div id="summa-control-group" class="control-group">
							<div class="controls">
								<c:out value="${summa}"/><br/>
								<input type="text" name="summa" id="summa" maxlength="7" onkeypress="delSpan()"/>
								<span id="span-summa" class="help-inline error"><c:out value="${summaError}"/></span>
								<br/>
								<span style="color: olive"><c:out value="${validSumma}"/></span>
							</div>
						</div>
						<span id="span-limit" class="help-inline error"><br/>Недостаточно средств на карте</span>
				</div>
      		</div>
      		
      		<div class="modal-footer">
			  	<div align="left">
			  		<button type="submit" class="btn btn-primary"><c:out value="${btnDepositAction}"/></button>
			  		&nbsp;&nbsp;
			  		<a href="Controller?command=show-deposit"><input id="cancelBtn" type="button" value="${btnCancel}" class="btn btn-danger"/></a>
				</div>
			</div>
			</form>
		</div>
		</div>
	</div>
	
			</div>
		</div>
	</div>

	

	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
	
	<!-- Подключение jQuery и JavaScript-->
	<script src="resources/js/deposit.js"></script>
	
</body>
</html>