<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>DisPie Add App</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheets/appupload.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheets/grid.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.2.0/css/all.css"
	integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ"
	crossorigin="anonymous">
</head>
<body>
	<div class="outer-container">
	<div class="logo">
		<img src="${pageContext.request.contextPath}/resources/logo.png"
						alt="dizpie logo" class="logo__picture"> <span
						class="logo__text"> isPie </span>
	</div>
		<div class="row display-window-wrapper">
			<form name="app-app-form"
				action="http://localhost:8080/App_Store/AppUploadServlet"
				method="POST" enctype="multipart/form-data" onsubmit="return validateFields();">
				<div class="app-name">
					<span class="name__info-text">App Name :</span> <input type="text"
						name="appname" class="name__text">
				</div>
				<div class="app-category"></div>
				<div class="app-description">
					<span class="desciption__info-text">Description :</span> <input type="text"
						name="appdescription" class="desc__text">
				</div>
				<div class="app-cost">
					<span class="cost__info-text">App Cost :</span> <input type="text"
						name="appcost" class="cost__text">
				</div>
				<div class="app-picture">
					<span class="picture__info-text">App Logo :</span> <input type="file"
						name="picture" class="applogo">
				</div>
				<div class="app-file">
					<span class="file__info-text">App File :</span> <input type="file"
						name="appfile" class="appfile">
				</div>
				<div class="app-submit-button">
					<input type="submit" value="Submit" class="submit-button">
				</div>
			</form>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/scripts/appupload.js"></script>
</body>
</html>