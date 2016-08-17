<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<fmt:setLocale value="${sessionScope.locale}" />
	<fmt:setBundle basename="localization.menu" var="menu" />
	<fmt:message bundle="${menu}" key="menu.contacts.title" var="title" />
	<fmt:message bundle="${menu}" key="menu.contacts.slogan.part1" var="part1" />
	<fmt:message bundle="${menu}" key="menu.contacts.slogan.part2" var="part2" />
	<fmt:message bundle="${menu}" key="menu.contacts.main-office" var="main" />
	<fmt:message bundle="${menu}" key="menu.contacts.regional-offices" var="regional" />
	<fmt:message bundle="${menu}" key="menu.contacts.international-offices" var="international" />
	
	<title>${title}</title>

	<!-- Иконка страницы -->
	<link type="image/x-icon" href="resources/img/logo.ico" rel="shortcut icon">
	
	<!-- Для совместимости с максимально возможной версией IE -->
	<meta http-equiv="X-UA-Compatible" content="IE=edge">

	<!-- Для адаптивности с любыми устройствами -->
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<!-- Подключение шрифта из Google Fonts -->
	<link href='https://fonts.googleapis.com/css?family=Open+Sans&subset=latin,cyrillic' rel='stylesheet' type='text/css'>
	
	<!-- Подключение mini версии bootstrap -->
	<link rel="stylesheet" href="resources/css/bootstrap.min.css">
	
	<!-- Подключение файла собственных стилей -->
	<link rel="stylesheet" href="resources/css/style.css">
	
	<!-- Скрипты подключатся, если пользователь будет просматривать страницу в браузере IE ранее 9 версии-->
	<!--[if lt IE 9]>
	  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body >

	<jsp:include page="/WEB-INF/jsp/common-header.jsp" />

	<!-- Контент страницы -->
	<div class="content">
		<div class="container">
			<div class="slogan">
				<h1>${part1} <span>${part2}</span></h1>
			</div>
			<div class="row">
				<div class="card-row clearfix">
					<div class="col-md-4 col-sm-6">
						<div class="contacts">
							<h4>${main}</h4>
							<p>Minsk. Nezavisimosti, 133</br>
							Tel.: +375-29-717-44-20</br>
							Fax.: +375-29-717-44-22</br>
							e-mail: t-one@gmail.com</p>
						</div>
					</div>
					<div class="col-md-4 col-sm-6">
						<div class="contacts">
							<h4>${regional}</h4>
							<h5>Minsk</h5>
							<p>Minsk. Nezavisimosti, 56</br>
							Tel. +375-29-717-44-26</p>
							<p>Minsk. Kamennogorskaja, 3</br>
							Tel. +375-29-717-44-25</p>
							<p>Minsk. Nemiga, 8</br>
							Tel. +375-29-717-44-27</p>
							<p>Minsk. Kulman, 15</br>
							Tel. +375-29-717-44-28</p>
							<h5>Gomel</h5>
							<p>Gomel. Sovetskaja, 32</br>
							Tel. +375-29-772-35-15</p>
							<p>Gomel. Lenina, 9</br>
							Tel. +375-29-772-35-16</p>
							<h5>Grodno</h5>
							<p>Grodno. Kruglaja pl., 1</br>
							Tel. +375-29-733-16-06</p>
							<h5>Vitebsk</h5>
							<p>Vitebsk. Krasnoarmejskaja, 19</br>
							Tel. +375-29-704-92-12</p>
							<h5>Pinsk</h5>
							<p>Pinsk. Polesskaja, 7a</br>
							Tel. +375-29-781-24-24</p>
						</div>
					</div>
					<div class="col-md-4 col-sm-6">
						<div class="contacts">
							<h4>${international}</h4>
							<h5>Russia</h5>
							<p>Moscow. Turgeneva, 44</br>
							Tel. +395-48-587-41-00</p>
							<h5>Kazakhstan</h5>
							<p>Astana. Barmalyke, 10</br>
							Tel. +345-60-811-77-40</p>
							<h5>Ukraine</h5>
							<p>Kiev. Solomennaja, 18</br>
							Tel. +371-59-218-87-61</p>
							<h5>Poland</h5>
							<p>Krakow. Malinova, 17</br>
							Tel. +323-42-114-14-18</p>
							<h5>Slovakia</h5>
							<p>Presov. Dubnice, 3</br>
							Tel. +303-74-650-22-92</p>
						</div>
					</div>
				</div>
			</div>
				
		</div>
	</div>

	
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
	
</body>
</html>