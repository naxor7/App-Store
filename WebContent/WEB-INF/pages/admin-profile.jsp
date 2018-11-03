<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>DizPie Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/admin-profile.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/grid.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ"
        crossorigin="anonymous">
</head>

<body>
    <div class="outer-container">
        <div class="top-bar col-sm-12">
            <div class="top-bar__logo">
                <img src="${pageContext.request.contextPath}/resources/logo.png" class="logo__img">
            </div>
            <div class="top-bar__text">
                <span>is Pie</span>
            </div>
        </div>
        <div class="default-option col-sm-12">
            <div class="option__icon">
                <i class="fas fa-bars"></i>
            </div>
            <div class="option__text">
                Options
            </div>
        </div>
        <div class="side-navigation">
            <div class="side-navigation__option col-sm-12">
                <div class="option__icon">
                    <i class="fas fa-flag"></i>
                </div>
                <div class="option__text">
                    Pending Apps
                </div>
            </div>
            <div class="side-navigation__option col-sm-12">
                <div class="option__icon">
                    <i class="fas fa-plus-square"></i>
                </div>
                <div class="option__text">
                    Add Tester
                </div>
            </div>
            <div class="side-navigation__option col-sm-12">
                <div class="option__icon">
                    <i class="fas fa-cog"></i>
                </div>
                <div class="option__text">
                    Settings
                </div>
            </div>
        </div>
        <div class="row display-window-wrapper">

        </div>
    </div>
    <script src="${pageContext.request.contextPath}/scripts/admin-profile.js"></script>
</body>

</html>