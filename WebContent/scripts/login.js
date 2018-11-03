var registerNowButton, registerBlock, loginBlock, loginNowButton, userName, password, confirmPassword, email, fullName, carousalElements, carousalIndex, loginMessageDiv, registerMessageDiv;
window.onload = start;
var usernameMessage = "Username must be 3-20 characters long and must contain only alphabets and numbers.";
var passwordMessage = "Password must be 6-20 characters long";
var nameMessage = "Name can be 2-30 characters long and can have only characters and spaces.";
var emailMessage = "Please enter a valid Email-ID";
var confirmPasswordMessage = "Password mismatch in Confirm password";
var anotherUserNameMessage = "Username already exists. Please choose a different Username";

function start() {
    registerNowButton = document.getElementsByClassName("register-now__link")[0];
    loginNowButton = document.getElementsByClassName("login__link")[0];
    registerBlock = document.getElementsByClassName("user-register-wrapper")[0];
    loginBlock = document.getElementsByClassName("login-wrapper")[0];
    loginMessageDiv = document.getElementsByClassName("credentials__message")[0];
    registerMessageDiv = document.getElementsByClassName("credentials__message")[1];
    carousalElements = document.getElementsByClassName("carousal-container");
    carousalIndex = 0;
    setInterval(carousalIterator, 6000);
    registerNowButton.addEventListener("click", displayRegister);
    loginNowButton.addEventListener("click", displayLogin);
}

function displayRegister() {
    registerBlock.style.display = "block";
    loginBlock.style.display = "none";
}

function displayLogin() {
    registerBlock.style.display = "none";
    loginBlock.style.display = "block";
}

function validateLogin() {
    return loginUsernameValidator()&&loginPasswordValidator();
}

function loginUsernameValidator() {
    userName = document.querySelector("input[name=l-username]");
    userName.style.borderColor = "#B4B1B1";
    if (!validateUsername(userName.value)) {
        userName.style.borderColor = "red";
        loginMessageDiv.innerHTML = usernameMessage;
        return false;
    }
    else {
        if (loginMessageDiv.innerHTML === usernameMessage)
            loginMessageDiv.innerHTML = "";
    }
    return true;
}

function loginPasswordValidator() {
    password = document.querySelector("input[name=l-password]");
    password.style.borderColor = "#B4B1B1";
    if (!validatePassword(password.value)) {
        password.style.borderColor = "red";
        loginMessageDiv.innerHTML = passwordMessage;
        return false;
    }
    else {
        if (loginMessageDiv.innerHTML === passwordMessage)
            loginMessageDiv.innerHTML = "";
    }
    return true;
}

function validateRegister() {
    return nameValidator() && emailValidator() && usernameValidator() && passwordValidator() && confirmPasswordValidator();
}

function nameValidator() {
    fullName = document.querySelector("input[name=r-fullname]");
    fullName.style.borderColor = "#B4B1B1";
    if (!validateName(fullName.value)) {
        registerMessageDiv.innerHTML = nameMessage;
        fullName.style.borderColor = "red";
        return false;
    }
    else {
        if (registerMessageDiv.innerHTML === nameMessage)
            registerMessageDiv.innerHTML = "";
    }
    return true;
}

function emailValidator() {
    email = document.querySelector("input[name=r-email]");
    email.style.borderColor = "#B4B1B1";
    if (!validateEmail(email.value)) {
        registerMessageDiv.innerHTML = emailMessage;
        email.style.borderColor = "red";
        return false;
    }
    else {
        if (registerMessageDiv.innerHTML === emailMessage)
            registerMessageDiv.innerHTML = "";
    }
    return true;
}

function usernameValidator() {
    userName = document.querySelector("input[name=r-username]");
    userName.style.borderColor = "#B4B1B1";
    if (!validateUsername(userName.value)) {
        registerMessageDiv.innerHTML = usernameMessage;
        userName.style.borderColor = "red";
        return false;
    }
    else {
        if (registerMessageDiv.innerHTML === usernameMessage)
            registerMessageDiv.innerHTML = "";
        checkUserName(userName);
    }
    return true;
}

function checkUserNameUtil(responseText) {
    if (responseText === "yes") {
        registerMessageDiv.innerHTML = anotherUserNameMessage;
        userName.style.borderColor = "red";
        return false;
    }
    else {
        if (registerMessageDiv.innerHTML === anotherUserNameMessage)
            registerMessageDiv.innerHTML = "";
    }
    return true;
}

function checkUserName(userName) {
	var params = {
			username: userName.value
	}
	var url = new URL("http://localhost:8080/App_Store/UserNameChecker");
	Object.keys(params).forEach(key => url.searchParams.append(key, params[key]));
	fetch(url).then(function(response) {
		return response.text();
	}).then(function(response) {
		checkUserNameUtil(response);
	}).catch(function(error) {
		console.log(error);
	});
}

function passwordValidator() {
    password = document.querySelector("input[name=r-password]");
    password.style.borderColor = "#B4B1B1";
    if (!validatePassword(password.value)) {
        registerMessageDiv.innerHTML = "Password must be 6-20 characters long";
        password.style.borderColor = "red";
        return false;
    }
    else {
        if (registerMessageDiv.innerHTML === passwordMessage)
            registerMessageDiv.innerHTML = "";
    }
    return true;
}

function confirmPasswordValidator() {
    confirmPassword = document.querySelector("input[name=r-confirm-password]");
    confirmPassword.style.borderColor = "#B4B1B1";
    if (!validateConfirmPassword(password.value, confirmPassword.value)) {
        registerMessageDiv.innerHTML = confirmPasswordMessage;
        confirmPassword.style.borderColor = "red";
        return false;
    }
    else {
        if (registerMessageDiv.innerHTML === confirmPasswordMessage)
            registerMessageDiv.innerHTML = "";
    }
    return true;
}

function validateName(fullName) {
    var nameRegex = /^[a-zA-Z ]{2,30}$/;
    return nameRegex.test(fullName);
}

function validateUsername(userName) {
    var usernameRegex = /^[a-zA-Z0-9]{3,20}$/;
    return usernameRegex.test(userName);
}

function validateConfirmPassword(password, confirmPassword) {
    if (password.localeCompare(confirmPassword) === 0)
        return true;
    return false;
}

function validatePassword(password) {
    var passwordRegex = /^.{6,20}$/;
    return passwordRegex.test(password);
}

function validateEmail(email) {
    var emailRegex = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    return emailRegex.test(email);
}

function carousalIterator() {
    setCarousalActive(carousalIndex);
    carousalIndex = (carousalIndex + 1) % carousalElements.length;
}

function setCarousalActive(carousalIndex) {
    for (var index = 0; index < carousalElements.length; index++) {
        carousalElements[index].style.zIndex = 1;
    }
    if (carousalIndex == 0) {
        carousalElements[carousalElements.length - 1].style.zIndex = 2;
    }
    else {
        carousalElements[carousalIndex - 1].style.zIndex = 2;
    }
    carousalElements[carousalIndex].style.zIndex = 3;
    carousalElements[carousalIndex].classList.remove("animator-slide-in");
    setTimeout(() => {
        carousalElements[carousalIndex].classList.add("animator-slide-in");
    }, 10);
}
