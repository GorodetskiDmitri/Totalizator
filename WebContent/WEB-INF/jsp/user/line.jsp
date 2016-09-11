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
	<fmt:message bundle="${user}" key="user.line.startDate" var="lineStartDate" />
	<fmt:message bundle="${user}" key="user.line.competition" var="lineCompetition" />
	<fmt:message bundle="${user}" key="user.line.event" var="lineEvent" />
	<fmt:message bundle="${user}" key="user.line.win" var="lineWin" />
	<fmt:message bundle="${user}" key="user.line.draw" var="lineDraw" />
	<fmt:message bundle="${user}" key="user.line.lose" var="lineLose" />
	<fmt:message bundle="${user}" key="user.line.bet" var="bet" />
	<fmt:message bundle="${user}" key="user.line.betOutcome" var="betOutcome" />
	<fmt:message bundle="${user}" key="user.line.betCoefficient" var="betCoefficient" />
	<fmt:message bundle="${user}" key="user.line.betBalance" var="betBalance" />
	<fmt:message bundle="${user}" key="user.line.ban" var="ban" />
	<fmt:message bundle="${user}" key="user.line.banMessage" var="banMessage" />
	<fmt:message bundle="${user}" key="user.deposit.summa" var="betSumma" />
	<fmt:message bundle="${user}" key="user.deposit.error.summa" var="errorSumma1" />
	<fmt:message bundle="${user}" key="user.line.error.summa" var="errorSumma2" />
	<fmt:message bundle="${user}" key="user.line.lineNotFound" var="lineNotFound" />
	<fmt:message bundle="${user}" key="user.button.makeBet" var="makeBet" />
	<fmt:message bundle="${user}" key="user.button.cancel" var="cancel" />
	<c:set var="sport" value="" scope="request"/> 
	
	<title>${title}</title>
</head>
<body>
	<c:if test="${sessionScope.client.id != null}">
		<%@ include file="user_menu.jsp" %>
	</c:if>
	<c:if test="${sessionScope.client.id == null}">
		<%@ include file="../header.jsp" %>
	</c:if>
	
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
								<th><c:out value="${lineStartDate}" /></th>
								<th><c:out value="${lineCompetition}" /></th>
								<th><c:out value="${lineEvent}" /></th>
								<th><c:out value="${lineWin}" /></th>
								<th><c:out value="${lineDraw}" /></th>
								<th><c:out value="${lineLose}" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${line}" var="line">
								<tr>
									<c:if test="${!sport.equalsIgnoreCase(line.sport.name)}">
										<td colspan="6" class="table-sport"><c:out value="${line.sport.name.toUpperCase()}" /></td>
										</tr><tr>
										<c:set var="sport" value="${line.sport.name}" scope="request"/>
									</c:if>
										
									<td id="${line.id}_startDate"><fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${line.startDate}" /></td>
									<td id="${line.id}_competition" align="left"><c:out value="${line.competition.name}" /></td>
									<td id="${line.id}_event" align="left"><c:out value="${line.eventName}" /></td>
									<c:if test="${sessionScope.client.id == null}">
										<td><a href="#myModal" class="betLink" id="modalLogin1" data-toggle="modal"><c:out value="${line.winCoeff}" /></a></td>
									</c:if>
									<c:if test="${sessionScope.client.id != null}">
										<td><a href="#betModal" class="betLink" id="${line.id}_win" data-toggle="modal"><c:out value="${line.winCoeff}" /></a></td>
									</c:if>
									<td>
										<c:if test="${line.drawCoeff > 0}">
											<a href="#betModal" class="betLink" id="${line.id}_draw" data-toggle="modal"><c:out value="${line.drawCoeff}" /></a>
										</c:if>
									</td>
									<td><a href="#betModal" class="betLink" id="${line.id}_lose" data-toggle="modal"><c:out value="${line.loseCoeff}" /></a></td>
									
									<input type="hidden" id="${line.id}_id" value="${line.id}"/>
									<input type="hidden" id="${line.id}_sport" value="${line.sport.name}"/>
									<input type="hidden" id="${line.id}_minBet" value="${line.minBet}"/>
									<input type="hidden" id="${line.id}_maxBet" value="${line.maxBet}"/>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>
			
	<!-- Modal -->
	<div class="modal fade" id="betModal" role="dialog">
		<div class="modal-dialog">
		
			<!-- Modal content-->
    		<div class="modal-content" style="color: black">
    			<c:if test="${sessionScope.client.betAllow == 0}">
    				<div class="modal-header">
      					<button type="button" class="close" data-dismiss="modal">&times;</button>
        				<h4 class="modal-title"><c:out value="${ban}"/></h4>
      				</div>
      				<div class="modal-body" style="background-color: orange">
						<div>
							<h5><c:out value="${banMessage}"/>.</h5>
							<br/>
						</div>
      				</div>
      					<div class="modal-footer">
						</div>
					</div>
    			</c:if>
		
			<c:if test="${sessionScope.client.betAllow != 0}">
      			<div class="modal-header">
      				<button type="button" class="close" data-dismiss="modal">&times;</button>
        			<h4 class="modal-title"><c:out value="${bet}"/></h4>
      			</div>
      		
      		<form id="bet-form" method="POST" action="Controller" accept-charset="UTF-8"> 
      			<input type="hidden" name="command" value="make-bet" />
      			<input type="hidden" name="userId" value="${sessionScope.client.id}" />
      			<input type="hidden" name="lineId" value="" />
      			<input type="hidden" name="outcome" value="" /> 
      			
      			<div class="modal-body" style="background-color: orange">
					<div>
						<h5><span id="startDate" /></h5>
						<h5><span id="sport" /></h5>
						<h5><span id="event" /></h5>
						<br/>
						
						<table>
							<tr>
								<td style="padding-right: 15px">
									<h5><c:out value="${betOutcome}"/>:</h5>
									<h5><c:out value="${betCoefficient}"/>:</h5>
									<h5><c:out value="${betBalance}"/>, $:</h5>
								</td>
								<td style="padding-right: 30px">
									<h5><b><span id="outcome" /></b></h5>
									<h5><b><span id="coefficient" /></b></h5>
									<h5><c:out value="${sessionScope.client.balance}"/></h5>
									
									<input type="hidden" id="balance" value="${sessionScope.client.balance }"/>
									<input type="hidden" id="outcomeWin" value="${lineWin}"/>
									<input type="hidden" id="outcomeDraw" value="${lineDraw}"/>
									<input type="hidden" id="outcomeLose" value="${lineLose}"/>
								</td>
								<td>
									<h5><c:out value="${betSumma}" /></h5>
									<div id="summa-control-group" class="control-group">
										<div class="controls">
											<input type="text" name="summa" id="summa" maxlength="7" size="14" style="background-color: lightgray" onkeypress="delSpan()"/>
											<span id="span-summa1" class="help-inline error" style="color: red"><c:out value="${errorSumma1}"/></span>
											<span id="span-summa2" class="help-inline error" style="color: red"><c:out value="${errorSumma2}"/></span>
											<br/>
											<span>From <span id="minBet"></span> to <span id="maxBet"></span> $.</span>
										</div>
									</div>
								</td>
							</tr>
						</table>
						<br/>
				</div>
      		</div>
      		
      		<div class="modal-footer">
			  	<div align="left">
					<button type="submit" class="btn btn-primary"><c:out value="${makeBet}"/></button>
			  		&nbsp;&nbsp;
			  		<a href="Controller?command=show-line"><input id="cancelBtn" type="button" value="${cancel}" class="btn btn-danger"/></a>
				</div>
			</div>
			</form>
			</c:if>
		</div>
		</div>
	</div>
	<!-- End modal content-->
	
	<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title"><c:out value="${legend}"/></h4>
      </div>
      
      <div class="modal-body">
       <c:if test="${accessDenied == true}">
			<div class="alert alert-danger">
    			<h5><c:out value="${errorAccess}"/></h5>
  			</div>
		</c:if>
       
        <form id="loginFromLineForm" method="POST" action="Controller" accept-charset="UTF-8">
			<input type="hidden" name="command" value="login" />
			<div id="login-control-group" class="control-group">
				<div class="controls">
					<input type="text" name="login" id="login" value="${param.login}" placeholder="${login}" size="25" maxlength="20" onkeypress="delSpan('login')">
					<span id="span-login" class="help-inline error">${errorLogin}</span>
					<br/><br/>
				</div>
			</div>
			<div id="password-control-group" class="control-group">
				<div class="controls">
					<input type="password" name="password" id="password" value="${param.password}" placeholder="${password}" size="25" maxlength="20" onkeypress="delSpan('password')">
					<span id="span-password" class="help-inline error">${errorPassword}</span>
					<br/><br/>
				</div>
			</div>
			<button class="btn btn-primary" type="submit">${signin}</button>&nbsp;&nbsp;
			<a href="Controller?command=logout"><input id="cancelBtn" type="button" value="${cancel}" class="btn btn-danger"/></a>
		</form>
      </div>
    </div>

  </div>
</div>
<!-- End modal content-->
	
			<c:if test="${line.isEmpty()}">
				<h3 align="center"><c:out value="${lineNotFound}" /></h3>
			</c:if>
			
		</div> 
	</div>

	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
	
	<!-- Подключение jQuery и JavaScript-->
	<script type="text/javascript">
		$(".betLink").click(function() {
			var separateIndex = (this.id).indexOf("_");
			var idLine = (this.id).substr(0,separateIndex);
			var outcome = (this.id).substr(separateIndex+1);
			
			$("#startDate").text($("#"+idLine+"_startDate").text());
			$("#sport").text($("#"+idLine+"_sport").val().toUpperCase() + ". " + $("#"+idLine+"_competition").text());
			$("#event").text($("#"+idLine+"_event").text());
			$("[name=lineId]").val(idLine);
			if (outcome == "win") {
				$("#outcome").text($("#outcomeWin").val());
				$("[name=outcome]").val("1");
			}
			if (outcome == "draw") {
				$("#outcome").text($("#outcomeDraw").val());
				$("[name=outcome]").val("2");
			}
			if (outcome == "lose") {
				$("#outcome").text($("#outcomeLose").val());
				$("[name=outcome]").val("3");
			}
			$("#coefficient").text($("#"+this.id).text());
			$("#minBet").text($("#"+idLine+"_minBet").val());
			$("#maxBet").text($("#"+idLine+"_maxBet").val()); 
			$("#summa").val("");
		}); 
		
		//Allow enter only numbers and point
		$('#summa').keypress(function(event) {
			
			if (event.which != 8) {
				if ((event.which < 46) || (event.which > 57)) return false;
				if (event.which == 47) return false;
			}
			var val = event.target.value;
			if (event.which == 46) {
	            return val.indexOf(".") < 0;
	        }
		});
		
		// Hide span messages
		function delSpan() {
			$("#span-summa1").hide();
			$("#span-summa2").hide();
		}
		
		$(function() {
			$("#span-summa1").hide();
			$("#span-summa2").hide();
		});
		
		$("#bet-form").submit(function() {
			if ($("#summa").val() == "") {
				$("#span-summa1").show();
				$("#summa").focus();
				return false;
			}
			
			if (parseFloat($("#summa").val()) < parseFloat($("#minBet").text()) 
					|| parseFloat($("#summa").val()) > parseFloat($("#maxBet").text())) {
					$("#span-summa1").show();
					$("#summa").focus();
					return false;
			}
			
			if (parseFloat($("#summa").val()) > parseFloat($("#balance").val())) {
					$("#span-summa2").show();
					$("#summa").focus();
					return false;
			}
		});
	</script>
</body>
</html>