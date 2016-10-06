<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<fmt:setLocale value="${sessionScope.locale}" />
	<fmt:setBundle basename="localization.locale" var="loc" />
	<fmt:message bundle="${loc}" key="registration.title" var="title" />
	<fmt:message bundle="${loc}" key="registration.slogan" var="slogan" />
	<fmt:message bundle="${loc}" key="registration.name" var="regName" />
	<fmt:message bundle="${loc}" key="registration.placeholder.name" var="placeholder_name" />
	<fmt:message bundle="${loc}" key="registration.surname" var="regSurname" />
	<fmt:message bundle="${loc}" key="registration.placeholder.surname" var="placeholder_surname" />
	<fmt:message bundle="${loc}" key="registration.birthday" var="regBirthday" />
	<fmt:message bundle="${loc}" key="registration.placeholder.birthday" var="placeholder_birthday" />
	<fmt:message bundle="${loc}" key="registration.login" var="regLogin" />
	<fmt:message bundle="${loc}" key="registration.placeholder.login" var="placeholder_login" />
	<fmt:message bundle="${loc}" key="registration.password" var="regPassword" />
	<fmt:message bundle="${loc}" key="registration.placeholder.password" var="placeholder_password" />
	<fmt:message bundle="${loc}" key="registration.confirm" var="regConfirmPassword" />
	<fmt:message bundle="${loc}" key="registration.placeholder.confirm" var="placeholder_confirm" />
	<fmt:message bundle="${loc}" key="registration.email" var="regEmail" />
	<fmt:message bundle="${loc}" key="registration.placeholder.email" var="placeholder_email" />
	<fmt:message bundle="${loc}" key="registration.passport" var="regPassport" />
	<fmt:message bundle="${loc}" key="registration.placeholder.passport" var="placeholder_passport" />
	<fmt:message bundle="${loc}" key="registration.phone" var="regPhone" />
	<fmt:message bundle="${loc}" key="registration.placeholder.phone" var="placeholder_phone" />
	<fmt:message bundle="${loc}" key="registration.address" var="regAddress" />
	<fmt:message bundle="${loc}" key="registration.placeholder.address" var="placeholder_address" />
	<fmt:message bundle="${loc}" key="registration.agree" var="agree" />
	<fmt:message bundle="${loc}" key="registration.conditions" var="conditions" />
	<fmt:message bundle="${loc}" key="registration.validation.input" var="inputValid" />
	<fmt:message bundle="${loc}" key="registration.validation.password" var="passwordValid" />
	<fmt:message bundle="${loc}" key="registration.validation.checkbox" var="checkboxValid" />
	<fmt:message bundle="${loc}" key="registration.validation.slogan1" var="validSlogan1" />
	<fmt:message bundle="${loc}" key="registration.validation.slogan2" var="validSlogan2" />
	<fmt:message bundle="${loc}" key="registration.reset" var="reset" />

	<title><c:out value="${title}"/></title>

	<!-- Link jQuery library -->
  	<script type="text/javascript" src="resources/js/jquery-1.11.1.min.js"></script>
 	
 	<!-- Link script moment-with-locales.min.js for work with Date -->
  	<script type="text/javascript" src="resources/js/moment-with-locales.min.js"></script>
  
  	<!-- Link script Twitter Bootstrap 3 -->
  	<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
    
    <!-- Link script widget "Bootstrap datetimepicker" -->
  	<script type="text/javascript" src="resources/js/bootstrap-datetimepicker.min.js"></script>
    
    <!-- Link css widget "Bootstrap datetimepicker" -->  
  	<link rel="stylesheet" href="resources/css/bootstrap-datetimepicker.min.css" />
</head>

<body>

	<%@ include file="header.jsp" %>
	
	<!-- Page content -->
	<div class="content">
		<div class="container">
			<div class="slogan">
				<h1><span><c:out value="${slogan}"/></span></h1>
			</div>
			
			<form id="registration-form" name="registration-form" method="POST" action="Controller" class="form-horizontal  label-slyle" > 
				<input type="hidden" name="command" value="register-user" />
				<input type="hidden" id="inputValid" value="${inputValid}" />
				<input type="hidden" id="passwordValid" value="${passwordValid}" />
				<input type="hidden" id="checkboxValid" value="${checkboxValid}" />	
				
				<div class="form-group">
				    <div class="col-xs-3"></div>
				    <div class="col-xs-6">
				      	<c:if test="${concurenseLogin == true}">
							<div class="alert alert-danger">
    							<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    							<h4>${validSlogan1} <strong>${param.regLogin}</strong> ${validSlogan2}</h4>
  							</div>
						</c:if>
				    </div>
				</div>
				<div class="form-group">
				    <label class="control-label col-xs-3" for="regName"><c:out value="${regName}"/></label>
				    <div class="col-xs-6">
				      	<input type="text" class="form-control reg" id="regName" name="regName" value="${param.regName}" maxlength="30" placeholder="${placeholder_name}">
				    </div>
				</div>
				<div class="form-group">
				    <label class="control-label col-xs-3" for="regSurname"><c:out value="${regSurname}"/></label>
				    <div class="col-xs-6">
				    	<input type="text" class="form-control reg" id="regSurname" name="regSurname" value="${param.regSurname}" maxlength="40" placeholder="${placeholder_surname}">
				    </div>
				</div>
				<div class="form-group">
					<label class="control-label col-xs-3" for="datetimepicker"><c:out value="${regBirthday}"/></label>
					<div class="date col-xs-6" >
    					<input type="text" class="form-control reg" id="datetimepicker" name="regBirthday" value="${param.regBirthday}"  placeholder="${placeholder_birthday}"/>
  					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-xs-3" for="regLogin"><c:out value="${regLogin}"/></label>
					<div class="col-xs-6">
				 		<input type="text" class="form-control reg" id="regLogin" name="regLogin" value="${param.regLogin}" maxlength="20" placeholder="${placeholder_login}">
					</div>
				</div>
				<div class="form-group">
				    <label class="control-label col-xs-3" for="regPassword"><c:out value="${regPassword}"/></label>
				    <div class="col-xs-6">
				      	<input type="password" class="form-control reg" id="regPassword" name="regPassword" value="${param.regPassword}" maxlength="20" placeholder="${placeholder_password}">
				    </div>
				</div>
				<div class="form-group">
				    <label class="control-label col-xs-3" for="regConfirmPassword"><c:out value="${regConfirmPassword}"/></label>
				    <div class="col-xs-6">
				    	<input type="password" class="form-control reg" id="regConfirmPassword" name="regConfirmPassword" value="${param.regConfirmPassword}" maxlength="20" placeholder="${placeholder_confirm}">
				    </div>
				</div>
				<div class="form-group">
				    <label class="control-label col-xs-3" for="regEmail"><c:out value="${regEmail}"/></label>
				    <div class="col-xs-6">
				      	<input type="email" class="form-control reg" id="regEmail" name="regEmail" value="${param.regEmail}" maxlength="40" placeholder="${placeholder_email}">
				    </div>
				</div>
				<div class="form-group">
					<label class="control-label col-xs-3" for="regPassport"><c:out value="${regPassport}"/></label>
					<div class="col-xs-6">
				 		<input type="text" class="form-control reg" id="regPassport" name="regPassport" value="${param.regPassport}" maxlength="9" placeholder="${placeholder_passport}">
					</div>
				</div>
				<div class="form-group">
				    <label class="control-label col-xs-3" for="regPhone"><c:out value="${regPhone}"/></label>
				    <div class="col-xs-6">
				      	<input type="tel" class="form-control reg" id="regPhone" name="regPhone" value="${param.regPhone}" maxlength="20" placeholder="${placeholder_phone}">
				    </div>
				</div>
				<div class="form-group">
				    <label class="control-label col-xs-3" for="regAddress"><c:out value="${regAddress}"/></label>
				    <div class="col-xs-6">
				      	<textarea rows="2" class="form-control reg" id="regAddress" name="regAddress" maxlength="70" placeholder="${placeholder_address}">${param.regAddress}</textarea>
				    </div>
				</div>
				
				<div class="form-group">
				    <div class="col-xs-offset-3 col-xs-6">
				      	<label class="checkbox-inline">
				        	<input type="checkbox" id="agree" name="agree">  <c:out value="${agree}"/> 
				        	<c:if test="${sessionScope.locale.equalsIgnoreCase(\"ru\")}" >
				        		<a href="resources/html/conditions_ru.html" onclick="javascript:newWindow(this.href); return false;">
				        	</c:if>
				        	<c:if test="${sessionScope.locale.equalsIgnoreCase(\"en\")}" >
				        		<a href="resources/html/conditions_en.html" onclick="javascript:newWindow(this.href); return false;">
				        	</c:if>
				        	<c:out value="${conditions}"/></a>.
				      	</label>
				    </div>
				</div>
				<br />
				
				<div class="form-group">
				    <div class="col-xs-offset-3 col-xs-6">
				      	<button type="submit" class="btn btn-primary"><c:out value="${slogan}"/></button>
				      	&nbsp;&nbsp;
				      	<input type="button" class="btn btn-default" id="resetBtn" value="${reset}" />
				    </div>
				</div> 
			</form>
				
		</div>
	</div>
	
	<footer>
		<div class="container">
			<hr>
			<p class="pull-right">Design by Gorodetski Dmitri 2016</p>		
		</div>
	</footer>
	 
	<!-- jQuery and JavaScript-->
	<script src="resources/js/main.js"></script>
	<script src="resources/js/validation.js"></script>
	
	<script type="text/javascript">
		// Identify HTML-element, for which we want to initialize widget "Bootstrap datetimepicker"
		$(function () {
			$('#datetimepicker').datetimepicker({pickTime:false, language: 'ru'});
		});

		// Reset all fields on Registration form
		$('#resetBtn').click(function() {
			$(".form-control").val("");
			$("#agree").attr('checked', false);
		});
	</script>

</body>
</html>