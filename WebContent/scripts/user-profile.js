var baseUrl = "http://localhost:8080";
var defaultOption, sideNav, musicApps, gamesApps, entertainmentApps, educationApps, politicsApps, otherApps, musicDiv, gamesDiv, entertainmentDiv, educationDiv, politicsDiv, otherDiv;
var changeName, changeUsername, changeEmail, changePassword, changeAccountType, changeNameOptions, changeUsernameOptions, changeEmailOptions, changePasswordOptions, changeAccountTypeOptions;
var allAppsOption, downloadsOption, wishlistOption, settingsOption, logOutOption;
var allAppsContainer, downloadsContainer, wishlistContainer, settingsContainer, appsContainer, devContainer;
var nameChangeButton, userNameChangeButton, emailChangeButton, passwordChangeButton, accountTypeChangeButtonYes, addAppButton;
var reviewClicked;
var wrongName = "Name can be 2-20 characters long and can have only alphabets";
var wrongUserName = "UserName can be 3-20 characters long and can have only alphabets and numbers";
var wrongEmail = "Enter a valid E-mail ID";
var wrongPassword = "Incorrect Password";
var wrongNewPassword = "Password should be 6-20 charactes long";
var mismatchNewPassword = "Password in confirm password mismatch";
var appReportedMessage = "You have reported this app. Please wait until our testing team process your request";
var loginToReflectChange = "Please Log-in to get the developer privileges";
window.onload = start;

function start() {
    defaultOption = document.getElementsByClassName("default-option")[0];
    sideNav = document.getElementsByClassName("side-navigation")[0];
    defaultOption.addEventListener("click", showOptionsMobile);
    musicDiv = document.getElementsByClassName("category__apps")[0];
    gamesDiv = document.getElementsByClassName("category__apps")[1];
    entertainmentDiv = document.getElementsByClassName("category__apps")[2];
    educationDiv = document.getElementsByClassName("category__apps")[3];
    politicsDiv = document.getElementsByClassName("category__apps")[4];
    otherDiv = document.getElementsByClassName("category__apps")[5];
    allAppsOption = document.getElementsByClassName("side-navigation__option")[0];
    downloadsOption = document.getElementsByClassName("side-navigation__option")[1];
    wishlistOption = document.getElementsByClassName("side-navigation__option")[2];
    settingsOption = document.getElementsByClassName("side-navigation__option")[3];
    logOutOption = document.getElementsByClassName("side-navigation__option")[4];
    changeName = document.getElementsByClassName("change__text")[0];
    changeUsername = document.getElementsByClassName("change__text")[1];
    changeEmail = document.getElementsByClassName("change__text")[2];
    changePassword = document.getElementsByClassName("change__text")[3];
    changeAccountType = document.getElementsByClassName("change__text")[4];
    changeNameOptions = document.getElementsByClassName("change__options")[0];
    changeUsernameOptions = document.getElementsByClassName("change__options")[1];
    changeEmailOptions = document.getElementsByClassName("change__options")[2];
    changePasswordOptions = document.getElementsByClassName("change__options")[3];
    changeAccountTypeOptions = document.getElementsByClassName("change__options")[4];
    addAppButton = document.querySelector(".add-app .app__text");
    allAppsContainer = document.getElementsByClassName("display-window-wrapper")[0];
    downloadsContainer = document.getElementsByClassName("display-window-wrapper")[1];
    wishlistContainer = document.getElementsByClassName("display-window-wrapper")[2];
    settingsContainer = document.getElementsByClassName("display-window-wrapper")[3];
    devContainer = document.getElementsByClassName("display-window-wrapper")[5];
    appsContainer = document.getElementsByClassName("display-window-wrapper")[4];
    changeName.addEventListener("click", showNameOptions);
    changeUsername.addEventListener("click", showUsernameOptions);
    changeEmail.addEventListener("click", showEmailOptions);
    changePassword.addEventListener("click", showPasswordOptions);
    changeAccountType.addEventListener("click", showAccountOptions);
    setAllAppsActive();
    allAppsOption.addEventListener("click", setAllAppsActive);
    downloadsOption.addEventListener("click", setDownloadsActive);
    wishlistOption.addEventListener("click", setWishListActive);
    settingsOption.addEventListener("click", setSettingsActive);
    nameChangeButton = document.getElementsByClassName("submit__button")[0];
    userNameChangeButton = document.getElementsByClassName("submit__button")[1];
    emailChangeButton = document.getElementsByClassName("submit__button")[2];
    passwordChangeButton = document.getElementsByClassName("submit__button")[3];
    accountTypeChangeButtonYes = document.getElementsByClassName("yes__button")[0];
    nameChangeButton.addEventListener("click", changeNameUtil);
    userNameChangeButton.addEventListener("click", changeUserNameUtil);
    emailChangeButton.addEventListener("click", changeEmailUtil);
    passwordChangeButton.addEventListener("click", changePasswordUtil);
    if(accountTypeChangeButtonYes !== undefined) {
    	accountTypeChangeButtonYes.addEventListener("click", changeAccountTypeYesUtil);
    }
    logOutOption.addEventListener("click", logOut);
    addAppButton.addEventListener("click", addApp);
    reviewClicked = false;
}

function logOut() {
	window.location = baseUrl+"/App_Store";
	var url = new URL("/App_Store/LogOut", baseUrl);
	fetch(url).then(function(response) {
		return response.text();
	}).then(function(response) {
		
	}).catch(function(error) {
		console.log(error);
	});
}

function displayAppPageWishList(button) {
	displayAppsContainer();
	getApp(button.parentNode.parentNode.getElementsByTagName("div")[0].innerHTML, "Install", "Wishlist Remove", "installApp", "removeFromWishList");
}

function displayAppPageDownloads(button) {
	displayAppsContainer();
	getApp(button.parentNode.parentNode.getElementsByTagName("div")[0].innerHTML, "Un-Install", "Report", "uninstallApp", "reportApp", true);
}

function displayAppPageAllApps(button) {
	displayAppsContainer();
	checkDisplayOptionsWishlist(button, button.previousSibling.previousSibling.innerHTML);
}

function checkDisplayOptionsWishlist(button, appId) {
	var params = {
			id: parseInt(appId)
	}
	var url = new URL("/App_Store/HasAppInWishlist", baseUrl);
	Object.keys(params).forEach(key => url.searchParams.append(key, params[key]));
	fetch(url).then(function(response) {
		return response.text();
	}).then(function(response) {
		if(response === "yes") {
			getApp(button.previousSibling.previousSibling.innerHTML, "Install", "Wishlist Remove", "installApp", "removeFromWishList");
		} else {
			checkDisplayOptionsDownloads(button, appId);
		}
	}).catch(function(error) {
		console.log(error);
	});
}

function checkDisplayOptionsDownloads(button, appId) {
	var params = {
			id: parseInt(appId)
	}
	var url = new URL("/App_Store/HasAppInDownloads", baseUrl);
	Object.keys(params).forEach(key => url.searchParams.append(key, params[key]));
	fetch(url).then(function(response) {
		return response.text();
	}).then(function(response) {
		if(response === "yes") {
			getApp(button.previousSibling.previousSibling.innerHTML, "Un-Install", "Report", "uninstallApp", "reportApp", true);
		} else {
			getApp(button.previousSibling.previousSibling.innerHTML, "Install", "WishList", "installApp", "addAppToWishList");
		}
	}).catch(function(error) {
		console.log(error);
	});
}

function checkReportedByUser(appId) {
	var params = {
			id: parseInt(appId)
	}
	var url = new URL("/App_Store/IsReportedByUser", baseUrl);
	Object.keys(params).forEach(key => url.searchParams.append(key, params[key]));
	fetch(url).then(function(response) {
		return response.text();
	}).then(function(response) {
		if(response === "yes") {
			document.getElementsByClassName("wishlist__button")[0].innerHTML = "Reported";
			document.getElementsByClassName("wishlist__button")[0].style.boxShadow = "none";
			document.getElementsByClassName("wishlist__button")[0].setAttribute("title", appReportedMessage);
		}
	}).catch(function(error) {
		console.log(error);
	});
}

function installApp(button) {
	var appID = button.parentNode.parentNode.firstChild.nextSibling.innerHTML;
	downloadFile(appID);
}

function downloadFile(appID) {
	var params = {
			id: parseInt(appID)
	}
	var url = new URL("/App_Store/DownloadApp", baseUrl);
	Object.keys(params).forEach(key => url.searchParams.append(key, params[key]));
	open(url);
	addToInstalledApps(appID);
}

function addToInstalledApps(appID) {
	var params = {
			id: parseInt(appID)
	}
	var url = new URL("/App_Store/InstallApp", baseUrl);
	Object.keys(params).forEach(key => url.searchParams.append(key, params[key]));
	fetch(url).then(function(response) {
		return response.text();
	}).then(function(response) {
		if(response == "okay") {
			setDownloadsActive();
		}
	}).catch(function(error) {
		console.log(error);
	});
}

function removeFromWishList(button) {
	var appID = button.parentNode.parentNode.firstChild.nextSibling.innerHTML;
	var params = {
			id: parseInt(appID)
	}
	var url = new URL("/App_Store/DeleteFromWishlist", baseUrl);
	Object.keys(params).forEach(key => url.searchParams.append(key, params[key]));
	fetch(url).then(function(response) {
		return response.text();
	}).then(function(response) {
		if(response == "okay") {
			setWishListActive();
		}
	}).catch(function(error) {
		console.log(error);
	});
}

function uninstallApp(button) {
	var appID = button.parentNode.parentNode.firstChild.nextSibling.innerHTML;
	var params = {
			id: parseInt(appID)
	}
	var url = new URL("/App_Store/DeleteAppFromDownloads", baseUrl);
	Object.keys(params).forEach(key => url.searchParams.append(key, params[key]));
	fetch(url).then(function(response) {
		return response.text();
	}).then(function(response) {
		if(response == "okay") {
			setDownloadsActive();
		}
	}).catch(function(error) {
		console.log(error);
	});
}

function addAppToWishList(button) {
	var appID = button.parentNode.parentNode.firstChild.nextSibling.innerHTML;
	var params = {
			id: parseInt(appID)
	}
	var url = new URL("/App_Store/AddToWishlist", baseUrl);
	Object.keys(params).forEach(key => url.searchParams.append(key, params[key]));
	fetch(url).then(function(response) {
		return response.text();
	}).then(function(response) {
		if(response == "okay") {
			setWishListActive();
		}
	}).catch(function(error) {
		console.log(error);
	});
}

function reportApp(button) {
	var appID = button.parentNode.parentNode.firstChild.nextSibling.innerHTML;
	var params = {
			id: parseInt(appID)
	}
	var url = new URL("/App_Store/ReportApp", baseUrl);
	Object.keys(params).forEach(key => url.searchParams.append(key, params[key]));
	fetch(url).then(function(response) {
		return response.text();
	}).then(function(response) {
		if(response == "okay") {
			getApp(appID, "Un-Install", "Reported", "uninstallApp", "reportApp", true);
		}
	}).catch(function(error) {
		console.log(error);
	});
}

function getApp(appId, buttonValue1, buttonValue2, listener1, listener2, reportCheck) {
	var params = {
			id: parseInt(appId)
	}
	var url = new URL("/App_Store/GetAppInfo", baseUrl);
	Object.keys(params).forEach(key => url.searchParams.append(key, params[key]));
	fetch(url).then(function(response) {
		return response.json();
	}).then(function(response) {
		displayAppInfo(response, buttonValue1, buttonValue2, listener1, listener2);
		getReview(appId);
		displayComments(response);
		document.getElementsByClassName("rating-info__rate")[0].addEventListener("onmouseleave", getReview(document.getElementsByClassName("rating-info__rate")[0].parentNode.parentNode.parentNode.firstChild.innerHTML));
		if(reportCheck) {
			checkReportedByUser(appId);
		}
	}).catch(function(error) {
		console.log(error);
	});
}

function displayAppInfo(appJson, buttonValue1, buttonValue2, listener1, listener2) {
	appsContainer.innerHTML = `<div class="hidden-div" style="display:none !important">`+appJson.appID+`</div>
                <div class="row application-wrapper__app-info">
                    <div class="app-info__image-container">
                        <img src="`+baseUrl+`/App_Store/GetImages?id=`+appJson.appID+`" alt="call of duty" class="image-container__image">
                    </div>
                    <div class="app-info__text-container">
                        <div class="text-container__app-name">
                            `+appJson.appName+`
                        </div>
                        <div class="text-container__app-release">
                            August 2018
                        </div>
                        <div class="text-container__app-category">
                            `+appJson.category.categoryName+`
                        </div>
                        <div class="text-container__app-cost">
                            $`+appJson.cost+`
                        </div>
                        <div class="text-container__app-buttons">
                        <div style="display:none !important;" class="hidden-info">
	                            `+appJson.appID+`
	                        </div>
                            <div class="app-buttons__install">
                                <button class="install__button" onclick="`+listener1+`(this)">`+buttonValue1+`</button>
                            </div>
                            <div class="app-buttons__wishlist">
                                <button class="wishlist__button" onclick="`+listener2+`(this)">`+buttonValue2+`</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row application-wrapper__rating-info">
                    <div class="rating-info__text">
                        Rating :
                    </div>
                    <div class="rating-info__number">
                        `+appJson.review+`
                    </div>
                    <div class="rating-info__rate">
                            <i class="fas fa-star" onmouseover="highLightPreviousStars(this)" onmouseleave="removeHighLightPreviousStars(this)" onclick="sendReviews(this)"></i>
                            <i class="fas fa-star" onmouseover="highLightPreviousStars(this)" onmouseleave="removeHighLightPreviousStars(this)" onclick="sendReviews(this)"></i>
                            <i class="fas fa-star" onmouseover="highLightPreviousStars(this)" onmouseleave="removeHighLightPreviousStars(this)" onclick="sendReviews(this)"></i>
                            <i class="fas fa-star" onmouseover="highLightPreviousStars(this)" onmouseleave="removeHighLightPreviousStars(this)" onclick="sendReviews(this)"></i>
                            <i class="fas fa-star" onmouseover="highLightPreviousStars(this)" onmouseleave="removeHighLightPreviousStars(this)" onclick="sendReviews(this)"></i>
                    </div>
                </div>
                <div class="row application-wrapper__rating-info">
                    <div class="rating-info__text">
                        Description :
                    </div>
                    <div class="rating-info__number">
                        `+appJson.description+`
                    </div>
                </div>`;
}

function displayComments(appJson) {
	var params = {
			id: appJson.appID
	}
	var url = new URL("/App_Store/GetAllComments", baseUrl);
	Object.keys(params).forEach(key => url.searchParams.append(key, params[key]));
	fetch(url, {
		headers: {
			"Accept" : "application/json"
		}
	}).then(function(response) {
		return response.json();
	}).then(function(response) {
		fillComments(response);
	}).catch(function(error) {
		console.log(error);
	});
}

function fillComments(commentsJson) {
	if(commentsJson.length > 0) {
		var commentsBody = `<div class="row application-wrapper__comments-wrapper">`;
		for(var i=0; i<commentsJson.length; i++) {
			commentsBody += `<div class="comments-wrapper__comment">
                        <div class="comment__user">
                            <i class="fas fa-user"></i>
                            <span class="user__name">`+commentsJson[i].userName+`</span>
                        </div>
                        <div class="comment__text">
                            `+commentsJson[i].commentText+`
                        </div>
                    </div>`;
		}
		commentsBody += `</div>`
		appsContainer.innerHTML += commentsBody;
	}
	else {
		appsContainer.innerHTML += `<div class="row application-wrapper__comments-wrapper">
									<div class="no-comments">No Comments Yet</div>
									<div>`;
	}
		appsContainer.innerHTML += `<div class="row application-wrapper__comment-wrapper">
					                    <textarea name="comment" class="comment-textarea" rows=3></textarea>
					                    <div class="comment-wrapper__submit">
					                        <button class="submit-button" onclick="submitComment(this)">Submit</button>
					                    </div>
					                </div>`;
}

function submitComment(element) {
	var commenttext = document.getElementsByClassName("comment-textarea")[0].value;
	var appId = element.parentNode.parentNode.parentNode.firstChild.innerHTML;
	if(commenttext.trim().length > 0) {
		var params = {
				id: parseInt(appId),
				comment: commenttext
		}
		var url = new URL("/App_Store/AddComment", baseUrl);
		Object.keys(params).forEach(key => url.searchParams.append(key, params[key]));
		fetch(url).then(function(response) {
			return response.text();
		}).then(function(response) {
			console.log(response);
			if(response === "okay") {
				document.getElementsByClassName("comment-wrapper__submit")[0].innerHTML = `<span class="wrapper__submit__thank-you-message">Thank You for commenting</span>`+ document.getElementsByClassName("comment-wrapper__submit")[0].innerHTML;
			} else {
				document.getElementsByClassName("comment-wrapper__submit")[0].innerHTML = `<span class="wrapper__submit__thank-you-message">Something's wrong. Try again later</span>`+ document.getElementsByClassName("comment-wrapper__submit")[0].innerHTML;
			}
			var eventListener1 = document.getElementsByClassName("install__button")[0].getAttribute("onclick");
			var eventListener2 = document.getElementsByClassName("wishlist__button")[0].getAttribute("onclick");
			eventListener1 = eventListener1.split("(")[0];
			eventListener2 = eventListener2.split("(")[0];
			setTimeout(function() {
				getApp(appId, document.getElementsByClassName("install__button")[0].innerHTML, document.getElementsByClassName("wishlist__button")[0].innerHTML, eventListener1, eventListener2);
			}, 2000);
		}).catch(function(error) {
			console.log(error);
		});
	}
}

function changeNameUtil() {
	var fullName = document.getElementsByClassName("name__text")[0];
	var password = document.querySelector(".change__options__name+.change__options__verify-pass .name__text");
	var message = document.querySelector(".fullname__change .change__options .change__options__messages");
	message.innerHTML = "";
	fullName.style.border = "3px solid #4f4e4e";
	password.style.border = "3px solid #4f4e4e";
	if(!validateName(fullName.value)) {
		message.innerHTML = wrongName;
		fullName.style.border = "3px solid red";
		return false;
	}
	if(!validatePassword(password.value)) {
		password.style.border = "3px solid red";
		message.innerHTML = wrongPassword;
		return false;
	}
	changeInfoUtil("fullname", fullName.value, password.value, message);
}

function changeUserNameUtil() {
	var userName = document.getElementsByClassName("username__text")[0];
	var password = document.querySelector(".change__options__username+.change__options__verify-pass .name__text");
	var message = document.querySelector(".username__change .change__options .change__options__messages");
	message.innerHTML = "";
	userName.style.border = "3px solid #4f4e4e";
	password.style.border = "3px solid #4f4e4e";
	if(!validateUsername(userName.value)) {
		message.innerHTML = wrongUserName;
		userName.style.border = "3px solid red";
		return false;
	}
	if(!validatePassword(password.value)) {
		password.style.border = "3px solid red";
		message.innerHTML = wrongPassword;
		return false;
	}
	changeInfoUtil("username", userName.value, password.value, message);
}

function changeEmailUtil() {
	var email = document.getElementsByClassName("email__text")[0];
	var password = document.querySelector(".change__options__email+.change__options__verify-pass .name__text");
	var message = document.querySelector(".email__change .change__options .change__options__messages");
	message.innerHTML = "";
	email.style.border = "3px solid #4f4e4e";
	password.style.border = "3px solid #4f4e4e";
	if(!validateEmail(email.value)) {
		message.innerHTML = wrongEmail;
		email.style.border = "3px solid red";
		return false;
	}
	if(!validatePassword(password.value)) {
		password.style.border = "3px solid red";
		message.innerHTML = wrongPassword;
		return false;
	}
	changeInfoUtil("email", email.value, password.value, message);
}

function changePasswordUtil() {
	var newpassword = document.getElementsByClassName("password__text")[0];
	var newpasswordAgain = document.getElementsByClassName("password__text")[1];
	var password = document.querySelector(".change__options__password+.change__options__verify-pass .name__text");
	var message = document.querySelector(".password__change .change__options .change__options__messages");
	message.innerHTML = "";
	newpassword.style.border = "3px solid #4f4e4e";
	password.style.border = "3px solid #4f4e4e";
	newpasswordAgain.style.border = "3px solid #4f4e4e";
	if(!validatePassword(newpassword.value)) {
		message.innerHTML = wrongNewPassword;
		newpassword.style.border = "3px solid red";
		return false;
	}
	if(newpassword.value !== newpasswordAgain.value) {
		message.innerHTML = mismatchNewPassword;
		newpasswordAgain.style.border = "3px solid red";
		return false;
	}
	if(!validatePassword(password.value)) {
		password.style.border = "3px solid red";
		message.innerHTML = wrongPassword;
		return false;
	}
	changeInfoUtil("password", newpassword.value, password.value, message);
}

function changeAccountTypeYesUtil() {
	var message = document.querySelector(".account-type__change .change__options .change__options__messages");
	changeInfoUtil("accounttype", "d", "", message);
	
}

function changeInfoUtil(fieldText, valueText, passwordText, message) {
	var jsonObj = {
		field: fieldText,
		value: valueText,
		password: passwordText
	};
	fetch(baseUrl+"/App_Store/ChangeInfo", {
		method: "POST",
		headers: {
			"Accept" : "text/plain",
			"Content-Type" : "application/json"
		},
		body: JSON.stringify(jsonObj)
	}).then(function(response) {
		return response.text();
	}).then(function(responseText) {
		message.innerHTML = responseText;
		if(responseText == "Congradulations! You're a developer now") {
			setTimeout(function() {
				message.innerHTML = loginToReflectChange;
				setTimeout(function() {
					open(baseUrl+"/App_Store", "_self");
				}, 2000);
			}, 2000);
		} 
		deleteContent(responseText);
	}).catch(function(error) {
		console.log(error);
	});
}

function deleteContent(responseText) {
	if(responseText == "You're Name has been changed successfully") {
		document.getElementsByClassName("name__text")[0].value = "";
		document.querySelector(".change__options__name+.change__options__verify-pass .name__text").value = "";
	}
	else if(responseText == "You're Username has been changed successfully") {
		document.getElementsByClassName("username__text")[0].value = "";
		document.querySelector(".change__options__username+.change__options__verify-pass .name__text").value = "";
	}
	else if(responseText == "You're Email-ID has been changed successfully") {
		document.getElementsByClassName("email__text")[0].value = "";
		document.querySelector(".change__options__email+.change__options__verify-pass .name__text").value = "";
	}
	else if(responseText == "You're Password has been changed successfully") {
		document.getElementsByClassName("password__text")[0].value = "";
		document.getElementsByClassName("password__text")[1].value = "";
		document.querySelector(".change__options__password+.change__options__verify-pass .name__text").value = "";
		
	}
}

function validateName(fullName) {
    var nameRegex = /^[a-zA-Z ]{2,30}$/;
    return nameRegex.test(fullName);
}

function validateUsername(userName) {
    var usernameRegex = /^[a-zA-Z0-9]{3,20}$/;
    return usernameRegex.test(userName);
}

function validatePassword(password) {
    var passwordRegex = /^.{6,20}$/;
    return passwordRegex.test(password);
}

function validateEmail(email) {
    var emailRegex = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    return emailRegex.test(email);
}

function hideAllContainers() {
	allAppsContainer.style.display = "none";
	settingsContainer.style.display = "none";
	downloadsContainer.style.display = "none";
	wishlistContainer.style.display = "none";
	appsContainer.style.display = "none";
	devContainer.style.display = "none";
}

function displayAllAppsContainer() {
	hideAllContainers();
	allAppsContainer.style.display = "block";
}

function displayDownloadsContainer() {
	hideAllContainers();
	downloadsContainer.style.display = "block";
}

function displayWishlistContainer() {
	hideAllContainers();
	wishlistContainer.style.display = "block";
}

function displaySettingsContainer() {
	hideAllContainers();
	settingsContainer.style.display = "block";
}

function displayDevContainer() {
	hideAllContainers();
	devContainer.style.display = "block";
}

function displayAppsContainer() {
	hideAllContainers();
	appsContainer.style.display = "block";
}

function resetSideNavOptions() {
	allAppsOption.style.removeProperty("background-color");
	downloadsOption.style.removeProperty("background-color");
	wishlistOption.style.removeProperty("background-color");
	settingsOption.style.removeProperty("background-color");
}

function setAllAppsActive() {
	resetSideNavOptions();
	allAppsOption.style.background = "#689F38";
	displayAllAppsContainer();
	getAllApps();
}

function setDownloadsActive() {
	resetSideNavOptions();
	downloadsOption.style.background = "#ED3B3B";
	displayDownloadsContainer();
	getDownloadsApps();
}

function setWishListActive() {
	resetSideNavOptions();
	wishlistOption.style.background = "#EF6C00";
	displayWishlistContainer();
	getWishListApps();
}

function setSettingsActive() {
	resetSideNavOptions();
	settingsOption.style.background = "#039BE5";
	hideAllSettingsBlocks();
	displaySettingsContainer();
}

function showOptionsMobile() {
    if(sideNav.style.display === "none" || sideNav.style.display === "") {
        sideNav.style.display = "block";
    }
    else {
        sideNav.style.display = "none";
    }
}

function getAllApps() {
	fetch(baseUrl+"/App_Store/GetAllApps" , {
		method: "GET",
		headers: {
			"Accept" : "application/json",
			"Content-Type" : "application/json"
		}
	}).then(function(response) {
		return response.json();
	}).then(function(data) {
		fillAllApps(data);
	}).catch(function(error) {
		console.log(error);
	});
}

function fillAllApps(allAppsJson) {
	musicApps = allAppsJson[0];
	musicStoredApps = musicApps;
	gamesApps = allAppsJson[1];
	gamesStoredApps = gamesApps;
	entertainmentApps = allAppsJson[2];
	entertainmentStoredApps = entertainmentApps;
	educationApps = allAppsJson[3];
	educationStoredApps =  educationApps;
	politicsApps = allAppsJson[4];
	politicsStoredApps = politicsApps;
	otherApps = allAppsJson[5];
	otherStoredApps = otherApps;
	fillMusicApps(musicApps);
	fillGamesApps(gamesApps);
	fillEntertainmentApps(entertainmentApps);
	fillEducationApps(educationApps);
	fillPoliticsApps(politicsApps);
	fillOtherApps(otherApps);
}

function getWishListApps() {
	fetch(baseUrl+"/App_Store/GetWishList" , {
		method: "GET",
	}).then(function(response) {
		return response.json();
	}).then(function(data) {
		fillWishListApps(data);
	}).catch(function(error) {
		console.log(error);
	});
}

function fillWishListApps(wishListAppsJson) {
	wishlistContainer.innerHTML = "";
	if(wishListAppsJson.length == 0) {
		wishlistContainer.innerHTML	= `<div class="no-apps">
                You've not added any apps to your Wishlist
            </div>`;
	}
	for(var i=0; i<wishListAppsJson.length; i++) {
		wishlistContainer.innerHTML += `<div class="wishlist-wrapper__app">
                    <div class="app__dp">
                        <img src="`+baseUrl+`/App_Store/GetImages?id=`+wishListAppsJson[i].appID+`" alt="tomb raider" class="dp__picture">
                    </div>
                    <div class="app__info">
                        <div class="info__name">
                            `+wishListAppsJson[i].appName+`
                        </div>
                        <div class="info__rating">
                            Ratings: `+wishListAppsJson[i].review+`
                        </div>
                        <div class="info__options">
	                        <div style="display:none !important;" class="hidden-info">
	                            `+wishListAppsJson[i].appID+`
	                        </div>
                            <div class="options__remove">
                                <button class="remove__button" onclick="removeFromWishList(this)">Remove</button>
                            </div>
                            <div class="options__get-it">
                                <button class="get-it__button" onclick="displayAppPageWishList(this)">Get It</button>
                            </div>
                        </div>
                    </div>
                </div>`;
	}
}

function getDownloadsApps() {
	fetch(baseUrl+"/App_Store/GetDownloads" , {
		method: "GET",
	}).then(function(response) {
		return response.json();
	}).then(function(data) {
		fillDownloadsApps(data);
	}).catch(function(error) {
		console.log(error);
	});
}

function fillDownloadsApps(downloadsAppsJson) {
	downloadsContainer.innerHTML = "";
	if(downloadsAppsJson.length == 0) {
		downloadsContainer.innerHTML	= `<div class="no-apps">
                You've not downloaded any apps yet
            </div>`;
	}
	for(var i=0; i<downloadsAppsJson.length; i++) {
		downloadsContainer.innerHTML += `<div class="downloads-wrapper__app">
                    <div class="app__dp">
                        <img src="`+baseUrl+`/App_Store/GetImages?id=`+downloadsAppsJson[i].appID+`" alt="tomb raider" class="dp__picture">
                    </div>
                    <div class="app__info">
                        <div class="info__name">
                            `+downloadsAppsJson[i].appName+`
                        </div>
                        <div class="info__rating">
                            Ratings: `+downloadsAppsJson[i].review+`
                        </div>
                        <div class="info__options">
                            <div style="display:none !important;" class="hidden-info">
	                            `+downloadsAppsJson[i].appID+`
	                        </div>
                            <div class="options__remove">
                                <button class="remove__button" onclick="uninstallApp(this)">Un-Install</button>
                            </div>
                            <div class="options__get-it">
                                <button class="get-it__button" onclick="displayAppPageDownloads(this)">View App</button>
                            </div>
                        </div>
                    </div>
                </div>`;
	}
}

function printErrorObject(error) {
	console.log(error);
}

function fillMusicApps(musicApps) {
	musicDiv.innerHTML = "";
	for(var i=0; i<musicApps.length; i++) {
		fillDiv(musicDiv, musicApps[i]);
	}
}

function fillGamesApps(gamesApps) {
	gamesDiv.innerHTML = "";
	for(var i=0; i<gamesApps.length; i++) {
		fillDiv(gamesDiv, gamesApps[i]);
	}
}

function fillEntertainmentApps(entertainmentApps) {
	entertainmentDiv.innerHTML = "";
	for(var i=0; i<entertainmentApps.length; i++) {
		fillDiv(entertainmentDiv, entertainmentApps[i]);
	}
}

function fillEducationApps(educationApps) {
	educationDiv.innerHTML = "";
	for(var i=0; i<educationApps.length; i++) {
		fillDiv(educationDiv, educationApps[i]);
	}
}

function fillPoliticsApps(politicsApps) {
	politicsDiv.innerHTML = "";
	for(var i=0; i<politicsApps.length; i++) {
		fillDiv(politicsDiv, politicsApps[i]);
	}
}

function fillOtherApps(otherApps) {
	otherDiv.innerHTML = "";
	for(var i=0; i<otherApps.length; i++) {
		fillDiv(otherDiv, otherApps[i]);
	}
}

function fillDiv(divName, divObj) {
	divName.innerHTML += `<div class="apps__app">
        <img src="`+baseUrl+`/App_Store/GetImages?id=`+divObj.appID+`" alt="app image" class="app__image">
        <div class="app__name">
            `+divObj.appName+`
        </div>
        <div class="app__price">
            $ `+divObj.cost+`
        </div>
        <div class="app__ratings">
            Ratings: `+divObj.review+`
        </div>
        <div style="display:none !important;" class="hidden-info">
	                            `+divObj.appID+`
	                        </div>
        <input type="button" value="View" class="app__install" onclick="displayAppPageAllApps(this);">
    </div>`;	
}

function hideAllSettingsBlocks() {
	changeNameOptions.style.display = "none";
	changeUsernameOptions.style.display = "none";
	changeEmailOptions.style.display = "none";
	changePasswordOptions.style.display = "none";
	if(changeAccountTypeOptions !== undefined) {
		changeAccountTypeOptions.style.display = "none";
	}
}

function showNameOptions() {
	var displayProp = changeNameOptions.style.display;
	hideAllSettingsBlocks();
	document.getElementsByClassName("name__text")[0].value = "";
	document.querySelector(".change__options__name+.change__options__verify-pass .name__text").value = "";
	document.querySelector(".fullname__change .change__options .change__options__messages").innerHTML = "";
    if(displayProp === "none" || displayProp === "") {
        changeNameOptions.style.display = "block";
    }
    else {
        changeNameOptions.style.display = "none";
    }
}

function showUsernameOptions() {
	var displayProp = changeUsernameOptions.style.display;
	hideAllSettingsBlocks();
	document.getElementsByClassName("username__text")[0].value = "";
	document.querySelector(".change__options__username+.change__options__verify-pass .name__text").value = "";
	document.querySelector(".username__change .change__options .change__options__messages").innerHTML = "";
    if(displayProp === "none" || displayProp === "") {
        changeUsernameOptions.style.display = "block";
    }
    else {
        changeUsernameOptions.style.display = "none";
    }
}

function showEmailOptions() {
	var displayProp = changeEmailOptions.style.display;
	hideAllSettingsBlocks();
	document.getElementsByClassName("email__text")[0].value = "";
	document.querySelector(".change__options__email+.change__options__verify-pass .name__text").value = "";
	document.querySelector(".email__change .change__options .change__options__messages").innerHTML = "";
    if(displayProp === "none" || displayProp === "") {
        changeEmailOptions.style.display = "block";
    }
    else {
        changeEmailOptions.style.display = "none";
    }
}

function showPasswordOptions() {
	var displayProp = changePasswordOptions.style.display;
	hideAllSettingsBlocks();
	document.getElementsByClassName("password__text")[0].value = "";
	document.getElementsByClassName("password__text")[1].value = "";
	document.querySelector(".change__options__password+.change__options__verify-pass .name__text").value = "";
	document.querySelector(".password__change .change__options .change__options__messages").innerHTML = "";
    if(displayProp === "none" || displayProp === "") {
        changePasswordOptions.style.display = "block";
    }
    else {
        changePasswordOptions.style.display = "none";
    }
}

function showAccountOptions() {
	if(changeAccountTypeOptions === undefined) {
		displayDevContainer();
	}
	else {
		var displayProp = changeAccountTypeOptions.style.display; 
		hideAllSettingsBlocks();
	    if(displayProp === "none" || displayProp === "") {
	    	changeAccountTypeOptions.style.display = "block";
	    }
	    else {
	        changeAccountTypeOptions.style.display = "none";
	    }
	}
}

function showOptions() {
    document.querySelector(".account-type__change .change__options .change__options__messages").innerHTML = "";
    if (sideNav.style.display === "none" || sideNav.style.display === "") {
        sideNav.style.display = "block";
    }
    else {
        sideNav.style.display = "none";
    }
}

function highLightPreviousStars(element) {
	var reviewStars = document.getElementsByClassName("fas fa-star");
    for(var i=0; i<reviewStars.length; i++) {
    	reviewStars[i].style.color = "black";
    }
    while(element != undefined) {
        if(element.attributes != undefined) {
            element.style.color = "#039BE5";
        }
        element = element.previousSibling;
    }
}

function removeHighLightPreviousStars(element) {
    var reviewStars = document.getElementsByClassName("fas fa-star");
    for(var i=0; i<reviewStars.length; i++) {
    	reviewStars[i].style.color = "black";
    }
}

function sendReviews(element) {
	if(!reviewClicked) {
		console.log("in send reviews");
		reviewClicked = true;
	    var ratingsCount = 0;
	    var appId = element.parentNode.parentNode.parentNode.firstChild.innerHTML;
	    while(element != undefined) {
	        if(element.attributes != undefined) {
	            ratingsCount++;
	        }
	        element = element.previousSibling;
	    }
	    var params = {
				id: parseInt(appId),
				review: parseInt(ratingsCount)
		}
		var url = new URL("/App_Store/AddReview", baseUrl);
		Object.keys(params).forEach(key => url.searchParams.append(key, params[key]));
		fetch(url).then(function(response) {
			return response.text();
		}).then(function(response) {
			if(response === "okay") {
				document.getElementsByClassName("rating-info__rate")[0].innerHTML = `<span style="color:red; padding-right: 25px;">Thank you for reviewing the app</span>`+document.getElementsByClassName("rating-info__rate")[0].innerHTML;
			} else {
				document.getElementsByClassName("rating-info__rate")[0].innerHTML = `<span style="color:red; padding-right: 25px;">Internal Server Error. Try Later</span>`+document.getElementsByClassName("rating-info__rate")[0].innerHTML;
			}
			var eventListener1 = document.getElementsByClassName("install__button")[0].getAttribute("onclick");
			var eventListener2 = document.getElementsByClassName("wishlist__button")[0].getAttribute("onclick");
			eventListener1 = eventListener1.split("(")[0];
			eventListener2 = eventListener2.split("(")[0];
			setTimeout(function() {
				getApp(appId, document.getElementsByClassName("install__button")[0].innerHTML, document.getElementsByClassName("wishlist__button")[0].innerHTML, eventListener1, eventListener2);
				reviewClicked = false;
			}, 2000);
		}).catch(function(error) {
			console.log(error);
		});
	}
}

function getReview(appId) {
	var params = {
			id: parseInt(appId)
	}
	var url = new URL("/App_Store/GetReview", baseUrl);
	Object.keys(params).forEach(key => url.searchParams.append(key, params[key]));
	fetch(url).then(function(response) {
		return response.text();
	}).then(function(response) {
		fillReview(parseInt(response));
	}).catch(function(error) {
		console.log(error);
	});
}

function fillReview(review) {
	var reviewStars = document.getElementsByClassName("fas fa-star");
	for(var i=0; i<review; i++) {
		reviewStars[i].style.color = "#039BE5";
	}
}

function addApp() {
	params = `scrollbars=no,resizable=no,status=no,location=no,toolbar=no,menubar=no,
		width=800,height=1000`;
	open(baseUrl+'/App_Store/AppUpload', 'Add App', params);
}




