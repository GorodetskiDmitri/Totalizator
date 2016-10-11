<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<fmt:setLocale value="${sessionScope.locale}" />
	<fmt:setBundle basename="localization.locale" var="loc" />
	<fmt:message bundle="${loc}" key="menu.home" var="home" />
	<fmt:message bundle="${loc}" key="main.title" var="title" />
	<fmt:message bundle="${loc}" key="main.slogan.part1" var="part1" />
	<fmt:message bundle="${loc}" key="main.slogan.part2" var="part2" />
	<fmt:message bundle="${loc}" key="main.slogan.part3" var="part3" />
	
	<title><c:out value="${title}"/></title>
</head>

<body>

	<%@ include file="WEB-INF/jsp/header.jsp" %>
	
	<!-- Page content -->
	<div class="content">
		<div class="container">
			<div class="slogan" style="margin-bottom: 120px">
				<h1><c:out value="${part1}"/>&nbsp;<span><c:out value="${part2}"/></span>&nbsp;&nbsp;<c:out value="${part3}"/></h1>
			</div>
			<div class="row">
				<div class="card-row clearfix">
					<div class="col-md-3 col-sm-6">
						<div class="card">
							<h4>Naftan - Krumkachy</h4>
							<p>Football. Belarus. Day 25</p>
							<a href="Controller?command=show-line&link=1"><img src="resources/img/photo/bet2.jpg" height="173" width="218"></a>
						</div>
					</div>
					<div class="col-md-3 col-sm-6">
						<div class="card">
							<h4>CSKA - Dinamo Mn</h4>
							<p>KHL. Regular season</p>
							<a href="Controller?command=show-line&link=2"><img src="resources/img/photo/bet7.jpg" height="173" width="218"></a>
						</div>
					</div>
					<div class="col-md-3 col-sm-6">
						<div class="card">
							<h4>S.Williams - S.Halep</h4>
							<p>US Open. Quater-finals</p>
							<a href="Controller?command=show-line&link=3"><img src="resources/img/photo/bet8.jpg" height="173" width="218"></a>
						</div>
					</div>
					<div class="col-md-3 col-sm-6">
						<div class="card">
							<h4>Tyson Fury - Vl. Klitschko</h4>
							<p>Box. Battle for the titul WBA/WBO </p>
							<a href="Controller?command=show-line&link=4"><img src="resources/img/photo/bet4.jpg" height="173" width="218"></a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="/WEB-INF/jsp/footer.jsp" />

	<!-- jQuery and JavaScript-->
	<c:if test="${accessDenied == true}">
		<script type="text/javascript">
			$(document).ready(function() {
				$("#myModal").modal();
			});
		</script>
	</c:if>
	
</body>
</html>