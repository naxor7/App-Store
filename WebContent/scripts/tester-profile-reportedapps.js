var defaultOption, sideNav;
var pendingAppsButton, reportedAppsButton, logOutButton;
window.onload = start;

function start() {
    defaultOption = document.getElementsByClassName("default-option")[0];
    sideNav = document.getElementsByClassName("side-navigation")[0];
    defaultOption.addEventListener("click", showOptions);
    pendingAppsButton = document.getElementsByClassName("side-navigation__option")[0];
    reportedAppsButton = document.getElementsByClassName("side-navigation__option")[1];
    logOutButton = document.getElementsByClassName("side-navigation__option")[3];
    pendingAppsButton.addEventListener("click", fetchPendingApps);
    reportedAppsButton.addEventListener("click", fetchReportedApps);
    logOutButton.addEventListener("click", logOut);
}

function showOptions() {
    if(sideNav.style.display === "none" || sideNav.style.display === "") {
        sideNav.style.display = "block";
    }
    else {
        sideNav.style.display = "none";
    }
}

function removeApp(id) {
	open("http://localhost:8080/App_Store/RejectApp?window=reportedapps&id="+id, "_self")
}

function testerVerified(id) {
	open("http://localhost:8080/App_Store/TesterVerify?window=reportedapps&id="+id, "_self");
}

function fetchPendingApps() {
	open("http://localhost:8080/App_Store/TesterPendingApps", "_self");
}

function fetchReportedApps() {
	open("http://localhost:8080/App_Store/TesterReportedApps", "_self");
}

function logOut() {
	open("http://localhost:8080/App_Store/LogOut", "_self");
}

