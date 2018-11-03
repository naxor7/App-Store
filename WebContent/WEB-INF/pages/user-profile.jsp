<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>DisPie Store</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheets/user-profile.css">
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
					<i class="fab fa-untappd"></i>
				</div>
				<div class="option__text">All Apps</div>
			</div>
			<div class="side-navigation__option col-sm-12">
				<div class="option__icon">
					<i class="fas fa-arrow-circle-down"></i>
				</div>
				<div class="option__text">Downloads</div>
			</div>
			<div class="side-navigation__option col-sm-12">
				<div class="option__icon">
					<i class="fas fa-smile-beam"></i>
				</div>
				<div class="option__text">Wish List</div>
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
			<div class="row display-window-wrapper__category">
				<div class="row category__name">
					<span>Music</span>
				</div>
				<div class="row category__apps"></div>
			</div>
			<div class="row display-window-wrapper__category">
				<div class="row category__name">
					<span>Games</span>
				</div>
				<div class="row category__apps"></div>
			</div>
			<div class="row display-window-wrapper__category">
				<div class="row category__name">
					<span>Entertainment</span>
				</div>
				<div class="row category__apps"></div>
			</div>
			<div class="row display-window-wrapper__category">
				<div class="row category__name">
					<span>Education</span>
				</div>
				<div class="row category__apps"></div>
			</div>
			<div class="row display-window-wrapper__category">
				<div class="row category__name">
					<span>Politics</span>
				</div>
				<div class="row category__apps"></div>
			</div>
			<div class="row display-window-wrapper__category">
				<div class="row category__name">
					<span>Other</span>
				</div>
				<div class="row category__apps"></div>
			</div>
		</div>
		<div class="row display-window-wrapper">
			<div class="row downloads-wrapper"></div>
		</div>
		<div class="row display-window-wrapper">
			<div class="row wishlist-wrapper"></div>
		</div>
		<div class="row display-window-wrapper">
			<div class="row fullname__change">
				<div class="change__text">Change Name</div>
				<div class="change__options">
					<div class="change__options__name">
						<input type="name" placeholder="Enter your new Name"
							class="name__text">
					</div>
					<div class="change__options__verify-pass">
						<input type="password" placeholder="Enter your Password"
							class="name__text">
					</div>
					<div class="change__options__submit">
						<button class="submit__button">CHANGE</button>
					</div>
					<div class="change__options__messages"></div>
				</div>
			</div>
			<div class="row username__change">
				<div class="change__text">Change Username</div>
				<div class="change__options">
					<div class="change__options__username">
						<input type="name" placeholder="Enter your new Username"
							class="username__text">
					</div>
					<div class="change__options__verify-pass">
						<input type="password" placeholder="Enter your Password"
							class="name__text">
					</div>
					<div class="change__options__submit">
						<button class="submit__button">CHANGE</button>
					</div>
					<div class="change__options__messages"></div>
				</div>
			</div>
			<div class="row email__change">
				<div class="change__text">Change Email</div>
				<div class="change__options">
					<div class="change__options__email">
						<input type="email" placeholder="Enter your new Email ID"
							class="email__text">
					</div>
					<div class="change__options__verify-pass">
						<input type="password" placeholder="Enter your current Password"
							class="name__text">
					</div>
					<div class="change__options__submit">
						<button class="submit__button">CHANGE</button>
					</div>
					<div class="change__options__messages"></div>
				</div>
			</div>
			<div class="row password__change">
				<div class="change__text">Change Password</div>
				<div class="change__options">
					<div class="change__options__password">
						<input type="password" placeholder="Enter your new Password"
							class="password__text">
					</div>
					<div class="change__options__password">
						<input type="password" placeholder="Enter your new Password Again"
							class="password__text">
					</div>
					<div class="change__options__verify-pass">
						<input type="password" placeholder="Enter your old Password"
							class="name__text">
					</div>
					<div class="change__options__submit">
						<button class="submit__button">CHANGE</button>
					</div>
					<div class="change__options__messages"></div>
				</div>
			</div>
				<c:if test="${user.getUserPrivilege() == 'u'}">
					<div class="row account-type__change">
						<div class="change__text">Change Account Type</div>
						<div class="change__options">
							<div class="options__text">Do you want a Developer Account ?
							</div>
							<div class="change__options__yes-no">
								<button class="yes__button">YES</button>
							</div>
							<div class="change__options__messages"></div>
						</div>
					</div>
				</c:if>
				<c:if test="${user.getUserPrivilege() == 'd'}">
					<div class="row dev-options__change">
		                <div class="change__text">
		                    Developer Options
		                </div>
            		</div>
				</c:if>
			
		</div>
		<div class="row display-window-wrapper">
			<div class="row application-wrapper"></div>
		</div>
		<div class="row display-window-wrapper">
            <div class="row add-app">
                <div class="app__text">
                    Add a new App
                </div>
            </div>
        </div>
	</div>
	<script
		src="${pageContext.request.contextPath}/scripts/user-profile.js"></script>
</body>

</html>