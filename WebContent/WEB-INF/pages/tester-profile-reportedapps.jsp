<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>
<%@page import="io.zilker.appstore.beans.*" %>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>DizPie Tester</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheets/tester-profile-reportedapps.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheets/grid.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.2.0/css/all.css"
	integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ"
	crossorigin="anonymous">
<link href='https://fonts.googleapis.com/css?family=Lato:100'
	rel='stylesheet' type='text/css'>
</head>

<body>
	<div class="outer-container">
		<div class="top-bar col-sm-12">
			<div class="top-bar__logo">
				<img src="${pageContext.request.contextPath}/resources/logo.png"
					class="logo__img">
			</div>
			<div class="top-bar__text">
				<span>is Pie</span>
			</div>
			<div class="top-bar__top-name">
				<div class="top-name__text">${user.getFullName()}</div>
			</div>
		</div>
		<div class="default-option col-sm-12">
			<div class="option__icon">
				<i class="fas fa-bars"></i>
			</div>
			<div class="option__text">Options</div>
		</div>
		<div class="side-navigation">
			<div class="side-navigation__option col-sm-12">
				<div class="option__icon">
					<i class="fas fa-flag"></i>
				</div>
				<div class="option__text">Pending Apps</div>
			</div>
			<div class="side-navigation__option col-sm-12">
				<div class="option__icon">
					<i class="fas fa-bug"></i>
				</div>
				<div class="option__text">Reported Apps</div>
			</div>
			<div class="side-navigation__option col-sm-12">
				<div class="option__icon">
					<i class="fas fa-cog"></i>
				</div>
				<div class="option__text">Settings</div>
			</div>
			<div class="side-navigation__option col-sm-12">
				<div class="option__icon">
					<i class="fas fa-sign-out-alt"></i>
				</div>
				<div class="option__text">Log Out</div>
			</div>
		</div>
		<div class="row display-window-wrapper">
			
			<%
				Applications[] reportedApps = (Applications[])request.getAttribute("reportedApps");
				if(reportedApps != null) {
				int[] sameName = (int[])request.getAttribute("sameName");
				int[] userApps = (int[])request.getAttribute("userApps");
				int[] sameCategoryApps = (int[])request.getAttribute("sameCategoryApps");
				int[] sameDescription = (int[])request.getAttribute("sameDescription");
				float[] userRating = (float[])request.getAttribute("userRating");
				for(int i=0; i<reportedApps.length; i++) {
			%>
			<div class="pending-apps__app">
				<div class="app__dp">
					<img src="http://localhost:8080/App_Store/GetImages?id=<%= reportedApps[i].getAppID() %>"
						alt="tomb raider" class="dp__picture">
				</div>
				<div class="app__info">
					<div class="info__name"><%= reportedApps[i].getAppName() %></div>
					<div class="info__name">Category: <%= reportedApps[i].getCategory().getCategoryName() %></div>
					<div class="info__rating">User Ratings:<%=  userRating[i]%></div>
					<div class="info__rating">User published apps:<%=  userApps[i]%></div>
					<div class="info__rating">Apps with same Name:<%=  sameName[i]%></div>
					<div class="info__rating">Apps with same Name and same Category:<%=  sameCategoryApps[i]%></div>
					<div class="info__rating">Found same description:<%=  sameDescription[i]%></div>
					<div class="info__options">
						<div class="options__remove">
							<button class="remove__button" onclick="removeApp(<%= reportedApps[i].getAppID()%>)">Remove</button>
						</div>
						<div class="options__get-it">
							<button class="get-it__button"
								onclick="testerVerified(<%= reportedApps[i].getAppID()%>)">Accept</button>
						</div>
					</div>
				</div>
			</div>
			<%} } else {
				out.print("No reported apps");
			}%>
		</div>
	</div>
	<script
		src="${pageContext.request.contextPath}/scripts/tester-profile-reportedapps.js"></script>
</body>

</html>