<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<fmt:setLocale value="${sessionScope.locale}" />
	<fmt:setBundle basename="localization.locale" var="loc" />
	<fmt:message bundle="${loc}" key="menu.home" var="home" />
	<fmt:message bundle="${loc}" key="menu.bet" var="bet" />
	<fmt:message bundle="${loc}" key="menu.registration" var="registration" />
	<fmt:message bundle="${loc}" key="menu.contacts" var="contacts" />
	<fmt:message bundle="${loc}" key="menu.language.en" var="en" />
	<fmt:message bundle="${loc}" key="menu.language.ru" var="ru" />
	<fmt:message bundle="${loc}" key="login.legend" var="legend" />
	<fmt:message bundle="${loc}" key="login.login" var="login" />
	<fmt:message bundle="${loc}" key="login.errorLogin" var="errorLogin" />
	<fmt:message bundle="${loc}" key="login.password" var="password" />
	<fmt:message bundle="${loc}" key="login.errorPassword" var="errorPassword" />
	<fmt:message bundle="${loc}" key="login.errorAccess" var="errorAccess" />
	<fmt:message bundle="${loc}" key="login.cancel" var="cancel" />
	<fmt:message bundle="${loc}" key="login.signin" var="signin" />
	
	<!-- Page icon -->
	<link type="image/x-icon" href="resources/img/logo.ico" rel="shortcut icon">

	<!-- For compatibility with the greatest possible version IE -->
	<meta http-equiv="X-UA-Compatible" content="IE=edge">

	<!-- For adaptability to any device -->
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<!-- Link font from Google Fonts -->
	<link href='https://fonts.googleapis.com/css?family=Open+Sans&subset=latin,cyrillic' rel='stylesheet' type='text/css'>
	
	<!-- Link bootstrap mini version -->
	<link rel="stylesheet" href="resources/css/bootstrap.min.css">
	
	<!-- Link own styles -->
	<link rel="stylesheet" href="resources/css/style.css">
	
	<!--[if lt IE 9]>
	  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>

<body>

	<!-- Header navigation panel. Bootstrap template -->
	<header>
		<nav class="navbar navbar-default menu">
			<div class="container">
			
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="Controller?command=home"><img src="resources/img/logo3.png" height="100" width="100"></a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li><a href="Controller?command=home">${home}</a></li>
						<li><a href="Controller?command=show-line">${bet}</a></li>
						<li><a href="Controller?command=registration">${registration}</a></li>
						<li><a href="Controller?command=contacts">${contacts}</a></li>
					</ul>
					
					<ul class="nav navbar-nav navbar-right icons">
						<li><a id="modalLogin1" href="#myModal" title="SIGN IN" role="button" class="btn" data-toggle="modal"><img src="resources/img/login.png" height="26" width="27"></a></li>
						
   	
	<!-- Modal -->
	<div id="myModal" class="modal fade modal-color" role="dialog">
 		 <div class="modal-dialog">

    		<!-- Modal content-->
    		<div class="modal-content">
      			<div class="modal-header">
        			<button type="button" class="close" data-dismiss="modal">&times;</button>
        			<h4 class="modal-title">${legend}</h4>
      			</div>
      
	      		<div class="modal-body">
	       			<c:if test="${accessDenied == true}">
						<div class="alert alert-danger">
	    					<h5><c:out value="${errorAccess}"/></h5>
	  					</div>
					</c:if>
	       
		        	<form id="login-form" method="POST" action="Controller" accept-charset="UTF-8">
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
						
						<li><a href="Controller?command=change-locale&locale=ru">${ru}</a></li>
						<li><a href="Controller?command=change-locale&locale=en">${en}</a></li>
					</ul>
				</div><!-- /.navbar-collapse -->
				<i class="glyphicon glyphicon-log-in globe"></i>
			</div><!-- /.container -->
		</nav>
	</header>
	
</body>
</html>