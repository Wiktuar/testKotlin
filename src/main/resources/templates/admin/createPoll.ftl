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
    <link rel="stylesheet" href="/css/polls.css">
    <title>Создание опроса</title>
</head>
<body>
<div class="wrapper">
    <@c.header></@c.header>

    <div class="container">
        <div class="create_poll_container">
            <input type="hidden" name="depId" value="<#if depId??>${depId}</#if>">
            <input type="hidden" name="poll_id" value="0">
            <label for="header">Укажите заголовок опроса</label>
            <input type="text" id="header" name="header">
        </div>

        <button type="button" class="create_survey">Новый вопрос</button>

        <div class="publish">
            <input type="checkbox" id="published" value="true">
            <label for="published">Опубликовать опрос</label>
        </div>

        <div class="personal">
            <input type="checkbox" id="personal" value="true">
            <label for="personal">Персонифицировать</label>
        </div>

        <button type="button" class="save_poll">Сохранить</button>
    </div>

    <@c.footer></@c.footer>
</div>
<script>
    let pollId = <#if pollID??>${pollID}<#else>0</#if>;
</script>
<script type="module" src="/js/survey/pollConstructor.js"></script>
<script type="module" src="/js/survey/updatePoll.js"></script>
</body>
</html>