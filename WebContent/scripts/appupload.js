var appName, description, appCost, appLogo, appFile;
window.onload = start;

function start() {
	appName = document.getElementsByClassName("name__text")[0];
	description = document.getElementsByClassName("desc__text")[0];
	appCost = document.getElementsByClassName("cost__text")[0];
	appLogo = document.getElementsByClassName("applogo")[0];
	appFile = document.getElementsByClassName("appfile")[0];
}

function validateFields() {
	if(!validateName(appName.value)) {
		alert("Enter a valid app Name. It can have only alphabets and numbers and length can be between 2 and 20");
		return false;
	}
	if(!validateDescription(description.value)) {
		alert("Description should be less than 100 characters long")
		return false;
	}
	if(!validateCost(appCost.value)) {
		alert("Cost should be between 0 and 1000. Only integers are allowed");
		return false;
	}
	if(appLogo.value == "") {
		alert("Please upload your app logo to continue");
		return false;
	}
	if(!(appLogo.value.endsWith(".jpg") || appLogo.value.endsWith(".jpeg") || appLogo.value.endsWith(".png"))) {
		alert("Please upload logo in .png or .jpeg or .jpg format");
		return false;
	}
	if(appFile.value == "") {
		alert("Please upload your app to continue");
		return false;
	}
	return true;
}


function validateCost(cost) {
	console.log(cost);
	console.log(Number.isInteger(cost));
	if(!isNaN(cost)) {
		if(parseInt(cost) >= 0 && parseInt(cost) < 1001) {
			return true;
		}
	}
	return false;
}

function validateName(fullName) {
    var nameRegex = /^[a-zA-Z0-9 ]{2,30}$/;
    return nameRegex.test(fullName);
}

function validateDescription(description) {
    var nameRegex = /^.{0,100}$/;
    return nameRegex.test(description);
}
