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
    <link rel="stylesheet" href="/css/departments.css">
    <title>Отделы Банка</title>
</head>
<body>
<div class="wrapper">
    <@c.header></@c.header>

        <div class="container">

            <div class="poll_blocks">
                <img src="/img/survey.jpg" class="survey_img" alt="картинка опроса">

            </div>
            
            <h1>Подразделения Банка</h1>
            <#if departments?has_content>
                <#list departments as department>
                    <ol class="dep">
                        <li class="dep_name">
                            <a href="/sbi/courses/${department.id}" class="dep_name_txt">${department.name}</a>
                        </li>
                    </ol>
                </#list>
            <#else>
                <div class="empty_list">
                    Список отделов пуст
                </div>
            </#if>
        </div>

    <@c.footer></@c.footer>
</div>

<script type="module" src="/js/survey/survey.js"></script>
</body>
</html>