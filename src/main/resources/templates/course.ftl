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
    <link rel="stylesheet" href="/css/course.css">
    <title>${course.header}</title>
</head>
<body>
<div class="wrapper">
    <@c.header></@c.header>

    <div class="container">
        <div class="material">
            <h1>${course.header}</h1>
            <div>${course.text}</div>
        </div>
        <div class="additional">
            <h2>Дополнительные материалы</h2>
            <#if course.uploads?has_content>
                <#list course.uploads as upload>
                <a href="${upload.url}" class="download_link" download>${upload.name}</a>
            </#list>
            <#else>
                <div class="empty_list">
                    Дополнительные материалы пока отсутствуют
                </div>
            </#if>
        </div>
        <div class="back"><a href="${request}">К списку всех курсов</a></div>
    </div>
    <@c.footer></@c.footer>
</div>
</body>
</html>