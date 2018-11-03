var defaultOption, sideNav;
window.onload = start;

function start() {
    defaultOption = document.getElementsByClassName("default-option")[0];
    sideNav = document.getElementsByClassName("side-navigation")[0];
    defaultOption.addEventListener("click", showOptions);
}

function showOptions() {
    if(sideNav.style.display === "none" || sideNav.style.display === "") {
        sideNav.style.display = "block";
    }
    else {
        sideNav.style.display = "none";
    }
}