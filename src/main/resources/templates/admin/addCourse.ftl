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
    <link rel="stylesheet" href="/css/course.css">
    <title>Добавление нового материала</title>
</head>
<body>

<div class="wrapper">
    <@c.header></@c.header>

    <div class="container">
        <h1 id="pageHeader">Добавление нового курса</h1>
        <form>
            <input type="hidden" name="depId" value="<#if depId??>${depId}</#if>">
            <input type="hidden" name="id" value="0">
            <label for="header">Добавьте заголовок материала</label>
            <input type="text" id="header" name="header">
            <div class="preview"></div>
            <label for="mytextarea">Текст материала</label>
            <textarea id="mytextarea"></textarea>
        </form>
        <button class="get-data">Отправить</button>
        <button class="preview_btn">Предпросмотр</button>
    </div>

    <@c.footer></@c.footer>
</div>

<script>
    let cid = ${courseId};
</script>

<script src="/js/tinymce/tinymce.min.js"></script>
<script src="/js/app.js"></script>
</body>
</html>