<#import "../common.ftl" as c>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/css/reset.css">
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/tests.css">
    <title>Создание теста</title>
</head>
<body>
<div class="wrapper">
    <@c.header></@c.header>

    <div class="container">

        <div class="create_test_container">
            <input type="hidden" name="depId" value="<#if depId??>${depId}</#if>">
            <input type="hidden" name="id" value="0">
            <label for="header">Укажите заголовок теста</label>
            <input type="text" id="header" name="header">
        </div>

        <button type="button" class="create_question">Новый вопрос</button>
        <button type="button" class="save_test">Сохранить</button>

    </div>
    <@c.footer></@c.footer>
</div>
<script type="module" src="/js/testsConstructor/testsConstructor.js"></script>
</body>
</html>