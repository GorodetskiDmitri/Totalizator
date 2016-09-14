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
	<fmt:message bundle="${user}" key="user.line.titleWin" var="titleWin" />
	<fmt:message bundle="${user}" key="user.line.titleDraw" var="titleDraw" />
	<fmt:message bundle="${user}" key="user.line.titleLose" var="titleLose" />
	<fmt:message bundle="${user}" key="user.line.ban" var="ban" />
	<fmt:message bundle="${user}" key="user.line.banMessage" var="banMessage" />
	<fmt:message bundle="${user}" key="user.deposit.summa" var="betSumma" />
	<fmt:message bundle="${user}" key="user.deposit.error.summa" var="errorSumma1" />
	<fmt:message bundle="${user}" key="user.line.error.summa" var="errorSumma2" />
	<fmt:message bundle="${user}" key="user.line.lineNotFound" var="lineNotFound" />
	<fmt:message bundle="${user}" key="user.button.makeBet" var="makeBet" />
	<fmt:message bundle="${user}" key="user.button.cancel" var="cancel" />
	<c:set var="sport" value="" scope="request"/> 
	<c:set var="link1" value="football" scope="request"/>
	<c:set var="link2" value="hockey" scope="request"/>
	<c:set var="link3" value="tennis" scope="request"/>
	<c:set var="link4" value="box" scope="request"/>
	
	<title><c:out value="${title}"/></title>
</head>

<body>
	<c:if test="${sessionScope.client.id != null}">
		<%@ include file="user_menu.jsp" %>
	</c:if>
	<c:if test="${sessionScope.client.id == null}">
		<%@ include file="../header.jsp" %>
	</c:if>
	
	<!-- Page content -->
	<div class="content">
		<div class="container">
			<div class="slogan" style="margin-bottom: 10px">
				<h1><c:out value="${slogan}" /></h1>
			</div>
			<br/>
			
			<a href="#link${param.link}"><input type="hidden" id="link" /></a>
			
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
								<c:if test="${!sport.equalsIgnoreCase(line.sport.name)}">
										<tr><td colspan="6" class="table-sport">
											<c:if test="${sessionScope.locale.equals(\"ru\")}">
												<c:out value="${line.sport.nameRu.toUpperCase()}"/>
											</c:if>
											<c:if test="${!sessionScope.locale.equals(\"ru\")}">
												<c:out value="${line.sport.name.toUpperCase()}"/>
											</c:if>
										
										<c:if test="${param.link != null}">
											<c:choose>
												<c:when test="${param.link.equals('1')}">
													<c:if test="${line.sport.name.indexOf(link1) >= 0 }">
														<div><a id="link1"></a></div>
													</c:if>
												</c:when>
												<c:when test="${param.link.equals('2')}">
													<c:if test="${line.sport.name.indexOf(link2) >= 0 }">
														<div><a id="link2"></a></div>
													</c:if>
												</c:when>
												<c:when test="${param.link.equals('3')}">
													<c:if test="${line.sport.name.indexOf(link3) >= 0 }">
														<div><a id="link3"></a></div>
													</c:if>
												</c:when>
												<c:when test="${param.link.equals('4')}">
													<c:if test="${line.sport.name.indexOf(link4) >= 0 }">
														<div><a id="link4"></a></div>
													</c:if>
												</c:when>
												<c:otherwise>
												</c:otherwise>
											</c:choose>
										</c:if>
										
										</td>
										</tr>
										<c:set var="sport" value="${line.sport.name}" scope="request"/>
								</c:if>
									
								<tr>
									<td id="${line.id}_startDate"><fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${line.startDate}" /></td>
									<td id="${line.id}_competition" align="left">
										<c:if test="${sessionScope.locale.equals(\"ru\")}">
											<c:out value="${line.competition.nameRu}"/>
										</c:if>
										<c:if test="${!sessionScope.locale.equals(\"ru\")}">
											<c:out value="${line.competition.name}"/>
										</c:if>
									</td>
									<td id="${line.id}_event" align="left"><c:out value="${line.eventName}" /></td>
									
									<c:if test="${sessionScope.client.id == null}">
										<td>
											<c:if test="${line.winCoeff > 1}">
												<a href="#myModal" class="betLink" id="${line.id}_win" data-toggle="modal" title="${titleWin}"><c:out value="${line.winCoeff}" /></a>
											</c:if>
										</td>
										<td>
											<c:if test="${line.drawCoeff > 1}">
												<a href="#myModal" class="betLink" id="${line.id}_draw" data-toggle="modal" title="${titleDraw}"><c:out value="${line.drawCoeff}" /></a>
											</c:if>	
										</td>
										<td>
											<c:if test="${line.loseCoeff > 1}">
												<a href="#myModal" class="betLink" id="${line.id}_lose" data-toggle="modal" title="${titleLose}"><c:out value="${line.loseCoeff}" /></a>
											</c:if>	
										</td>
									</c:if>
									
									<c:if test="${sessionScope.client.id != null}">
										<td>
											<c:if test="${line.winCoeff > 1}">
												<a href="#betModal" class="betLink" id="${line.id}_win" data-toggle="modal"><c:out value="${line.winCoeff}" /></a>
											</c:if>
										</td>
										<td>
											<c:if test="${line.drawCoeff > 1}">
												<a href="#betModal" class="betLink" id="${line.id}_draw" data-toggle="modal"><c:out value="${line.drawCoeff}" /></a>
											</c:if>
										</td>
										<td>
											<c:if test="${line.loseCoeff > 1}">
												<a href="#betModal" class="betLink" id="${line.id}_lose" data-toggle="modal"><c:out value="${line.loseCoeff}" /></a>
											</c:if>
										</td>
									</c:if>
										
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
			
	<!-- Modal Bet -->
	<div id="betModal" class="modal fade" role="dialog">
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
							<h5><span id="startDate"></span></h5>
							<h5><span id="sport"></span></h5>
							<h5><span id="event"></span></h5>
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
												<input type="text" name="summa" id="summa" class="decimalOnly" maxlength="7" size="14" style="background-color: lightgray" onkeypress="delSpan()"/>
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
	<!-- End modal bet -->
	
	
	<!-- Modal Login -->
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
						
						<button class="btn btn-primary" type="submit">${signin}</button>
						&nbsp;&nbsp;
						<a href="Controller?command=logout"><input id="cancelBtn" type="button" value="${cancel}" class="btn btn-danger"/></a>
					</form>
      			</div>
    		</div>

  		</div>
	</div>
	<!-- End modal login-->
	
	
			<c:if test="${line.isEmpty()}">
				<h3 align="center"><c:out value="${lineNotFound}" /></h3>
			</c:if>
			
		</div> 
	</div>

	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
	
	<!-- jQuery and JavaScript-->
	<script src="resources/js/util.js"></script> 
	<script src="resources/js/line.js"></script> 
	
	<c:if test="${param.link != null}">
		<script type="text/javascript">
			$(document).ready(function () {
				$("#link").click();
			});
		</script>
	</c:if>
	
</body>
</html>