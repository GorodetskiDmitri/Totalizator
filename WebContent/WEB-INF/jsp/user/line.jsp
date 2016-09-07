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
									<td><a href="#betModal" class="betLink" id="${line.id}_win" data-toggle="modal"><c:out value="${line.winCoeff}" /></a></td>
									<td><c:if test="${line.drawCoeff > 0}">
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
    	<div class="modal-content">
      		<div class="modal-header" style="color: black">
      			<button type="button" class="close" data-dismiss="modal">&times;</button>
        		<h4 class="modal-title">Bet</h4>
      		</div>
      		
      		<form id="bet-form" method="POST" action="Controller" accept-charset="UTF-8"> 
      		<div class="modal-body" style="background-color: #d42819; color: black">
				<div>
						<h5><span id="startDate" /></h5>
						<h5><span id="sport" /></h5>
						<h5><span id="event" /></h5>
						<br/>
						<table>
							<tr>
								<td>
									<h5><c:out value="Outcome"/>: <b><span id="outcome" /></b></h5>
									<h5><c:out value="Coefficient"/>: <b><span id="coefficient" /></b></h5>
									<h5><c:out value="Balance"/>: <b><c:out value="${sessionScope.client.balance }"/></b></h5>
								</td>
								<td width="30px"></td>
								<td rowspan="3">
									<h5><c:out value="Summa"/></h5>
									<div id="summa-control-group" class="control-group">
										<div class="controls">
											<input type="text" name="summa" id="summa" maxlength="7" size="15" style="background-color: lightgray" onkeypress="delSpan()"/>
											<span id="span-summa" class="help-inline error" style="color: gold"><c:out value="invalid"/></span>
											<br/>
											<span style="color: white">From <span id="minBet"></span> to <span id="maxBet"></span> $.</span>
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
			  		<button type="submit" class="btn btn-primary">Make Bet</button>
			  		&nbsp;&nbsp;
			  		<a href="Controller?command=show-line"><input id="cancelBtn" type="button" value="Cancel" class="btn btn-danger"/></a>
				</div>
			</div>
			</form>
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
			alert("par = " + this.id);
			var separateIndex = (this.id).indexOf("_");
			var idLine = (this.id).substr(0,separateIndex);
			var outcome = (this.id).substr(separateIndex+1);
			
			$(".btn-success").prop('disabled', false);
			$(".btn-danger").prop('disabled', false);
			//$("input[name='lineId']").val(this.id);
			
			$("#startDate").text($("#"+idLine+"_startDate").text());
			$("#sport").text($("#"+idLine+"_sport").val().toUpperCase() + ". " + $("#"+idLine+"_competition").text());
			$("#event").text($("#"+idLine+"_event").text());
			if (outcome == "win")
				$("#outcome").text("Win1");
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
			$("#span-summa").hide();
		}
		
		$(function() {
			$("#span-summa").hide();
		});
		
		$("#bet-form").submit(function() {
			if ($("#summa").val() == "") {
				$("#span-summa").show();
				$("#summa").focus();
				return false;
			}
			
			if (parseFloat($("#summa").val()) < parseFloat($("#minBet").text()) 
					|| parseFloat($("#summa").val()) > parseFloat($("#maxBet").text())) {
					$("#span-summa").show();
					$("#summa").focus();
					return false;
			}
			return false;
		});
	</script>
</body>
</html>