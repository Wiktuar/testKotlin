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
    <link rel="stylesheet" href="/css/modal.css">
    <link rel="stylesheet" href="/css/departments.css">
    <title>Отделы Банка</title>
</head>
<body>
<div class="wrapper">
    <@c.header></@c.header>

    <div class="container">
    <#-- сюда буду загружаться результаты опроса       -->
        <div class="survey_results">

        </div>

        <h1>Подразделения Банка</h1>
        <#if departments?has_content>
            <#list departments as department>
                <div class="dep">
                    <div class="dep_name">
                        <a href="/admin/courses/${department.id}" class="dep_name_txt"> ${department.name} </a>
                    </div>
                    <a href="/dep_update/${department.id}" class="modify">Обновить</a>
                    <a href="/dep_delete/${department.id}" class="modify">Удалить</a>
                </div>
            </#list>
        <#else>
            <div class="empty_list">
                Список отделов пуст
            </div>
        </#if>

        <button type="button" class="add_dep_btn">Добавить подразделение</button>

        <form id="dep_form" method="post" action="/savedepartment">
            <input type="hidden" name="id" value="0">
            <label for="dep_name">Добавить новое подразделение:</label>
            <input type="text" id="dep_name" name="name">
            <button type="submit">Сохранить</button>
        </form>
    </div>

    <@c.footer></@c.footer>
</div>

<#-- Скрипты, относящиеся к модальному окну -->
<script src="/js/modal/base.js"></script>
<script src="/js/modal/plugins/modal.js"></script>
<script src="/js/modal/plugins/confirm.js"></script>

<script type="module" src="/js/departments.js"></script>
</body>
</html>