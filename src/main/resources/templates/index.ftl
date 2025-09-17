<#import "common.ftl" as c>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/css/reset.css">
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/style.css">
    <title>Добро пожаловать!</title>
</head>
<body>
<div class="wrapper">
    <@c.header></@c.header>

    <div class="container">
        <h1 class="greeting">
            Добро пожаловать в образовательную систеvу SBI Learn
        </h1>
        <div class="authenticationWarning">
            <p class="authMessage"></p>
        </div>
        <form>
            <div class="fName">
                <label for="username">Ваша почта:</label>
                <input type="email" id="username" name="username" value="${username?has_content?then(username, "")}" autofocus/>
            </div>
            <div class="fName">
                <label for="password">Ваш пароль:</label>
                <input type="password" id="password" name="password" value="${password?has_content?then(password, "")}"/>
                <span class="showPassword" data-target="password"></span>
            </div>
            <button type="submit" id="sendLoginForm">Войти</button>
        </form>
    </div>

    <@c.footer></@c.footer>
</div>
<script src="/js/loginPage.js"></script>
</body>
</html>