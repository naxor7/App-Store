<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>DizPie Store</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/stylesheets/login.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheets/grid.css">
</head>

<body>
	<div class="outer-container col-sm-12">
		<div class="row login-wrapper">
			<form name="login-form" method="POST"
				action="http://localhost:8080/App_Store/Login" onsubmit="return validateLogin();">
				<div class="login-wrapper__credentials col-sm-12">
					<div class="credentials__message">
						<% if(request.getAttribute("LOGIN_MESSAGE") != null)
							out.print(request.getAttribute("LOGIN_MESSAGE"));
							%>
					</div>
					<div class="credentials__username">
						<input type="name" name="l-username" placeholder="Username"
							class="username__text" onfocusout="loginUsernameValidator()">
					</div>
					<div class="credentials__password">
						<input type="password" name="l-password" placeholder="Password"
							class="password__text" onfocusout="loginPasswordValidator()">
					</div>
				</div>
				<div class="login-wrapper__login col-sm-12">
					<input type="submit" class="login__button" value="LOGIN">
				</div>
				<div class="login-wrapper__register-now">
					<span class="register-now__text">Haven't DizPie App Store
						yet ?</span> <a class="register-now__link"> Register Now </a>
				</div>
				<div class="login-wrapper__logo">
					<img src="${pageContext.request.contextPath}/resources/logo.png"
						alt="dizpie logo" class="logo__picture"> <span
						class="logo__text"> isPie </span>
				</div>
			</form>
		</div>
		<div class="row user-register-wrapper">
			<form name="register-form"
				action="http://localhost:8080/App_Store/Register" method="POST"
				onsubmit="return validateRegister();">
				<div class="user-register-wrapper__credentials col-sm-12">
					<div class="credentials__message">
						<%= request.getAttribute("REGISTER_MESSAGE") %>
					</div>
					<div class="credentials__name">
						<input type="name" name="r-fullname" placeholder="Full Name"
							class="name__text" onfocusout="nameValidator()">
					</div>
					<div class="credentials__email">
						<input type="email" name="r-email" placeholder="Email ID"
							class="email__text" onfocusout="emailValidator()">
					</div>
					<div class="credentials__username">
						<input type="name" name="r-username" placeholder="Username"
							class="username__text" onfocusout="usernameValidator()">
					</div>
					<div class="credentials__password">
						<input type="password" name="r-password" placeholder="Password"
							class="password__text" onfocusout="passwordValidator()">
					</div>
					<div class="credentials__password">
						<input type="password" name="r-confirm-password"
							placeholder="Confirm Password" class="password__text"
							onfocusout="confirmPasswordValidator()">
					</div>
				</div>
				<div class="user-register-wrapper__register col-sm-12">
					<input type="submit" value="REGISTER" class="register__button">
				</div>
				<div class="user-register-wrapper__login col sm-12">
					<span class="login__text">Already a member of DizPie App
						Store ?</span> <a class="login__link"> Login Now </a>
				</div>
			</form>
		</div>
		<div class="carousals-wrapper col-sm-12">
			<div class="carousal-container col-sm-12 animator-slide-in"></div>
			<div class="carousal-container col-sm-12 animator-slide-in"></div>
			<div class="carousal-container col-sm-12 animator-slide-in"></div>
			<div class="carousal-container col-sm-12 animator-slide-in"></div>
			<div class="carousal-container col-sm-12 animator-slide-in"></div>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/scripts/login.js"></script>
</body>

</html>