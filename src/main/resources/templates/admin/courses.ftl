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
    <title>Список курсов и тестов</title>
</head>
<body>
<div class="wrapper">
    <@c.header></@c.header>

    <div class="container">

        <h1>${department.name}</h1>
        <h2>Учебные материалы</h2>
        <div class="materials">
            <#if department.courses?has_content>
                <#list department.courses as course>
                    <div class="dep">
                        <div class="dep_name">
                            <a href="/sbi/course/${course.id}" class="dep_name_txt"> ${course.header}</a>
                        </div>
                        <a href="/course_update/${course.id}" class="modify">Обновить</a>
                        <a href="/course_delete/${course.id}" class="modify">Удалить</a>
                    </div>
                </#list>
            <#else>
                <div class="empty_list">
                    Список материалов пуст
                </div>
            </#if>

            <a href="/admin/create-course/${department.id}" class="add_new_material">Добавить материал</a>
        </div>

        <h2>Проверочные материалы</h2>
        <div class="tests">
            <#if department.tests?has_content>
                <#list department.tests as test>
                    <div class="dep">
                        <div class="dep_name">
                            <a href="/sbi/test/${test.id}" class="dep_name_txt"> ${test.header}</a>
                        </div>
                        <a href="/results/${test.id}" class="test_result">Результаты</a>
                        <a href="/test_update/${test.id}" class="modify">Обновить</a>
                        <a href="/test_delete/${test.id}" class="modify">Удалить</a>
                    </div>
                </#list>
            <#else>
                <div class="empty_list">
                    Список тестов пуст
                </div>
            </#if>

            <a href="/admin/create-test/${department.id}" class="add_new_material">Добавить тест</a>
        </div>
        <h2>Опросы</h2>
        <div class="polls">
            <#if department.polls?has_content>
                <#list department.polls as poll>
                    <div class="dep">
                        <div class="dep_name">
                            <span class="dep_name_txt"> ${poll.header}</span>
                        </div>
                        <span class="test_result">${poll.status.value}</span>
                        <a href="/poll_results/${poll.id}" class="test_result">Результаты</a>
                        <a href="/poll_update/${poll.id}" class="modify">Обновить</a>
                        <a href="/poll_delete/${poll.id}" class="modify">Удалить</a>
                    </div>
                </#list>
            <#else>
                <div class="empty_list">
                    Список опросов пуст
                </div>
            </#if>

            <a href="/admin/create-poll/${department.id}" class="add_new_material">Добавить опрос</a>
        </div>

    </div>

    <@c.footer></@c.footer>
</div>

<#-- Скрипты, относящиеся к модальному окну -->
<script src="/js/modal/base.js"></script>
<script src="/js/modal/plugins/modal.js"></script>
<script src="/js/modal/plugins/confirm.js"></script>

<script src="/js/courses.js"></script>
<script src="/js/testsConstructor/deleteTest.js"></script>
<script src="/js/survey/modifyPoll.js"></script>

</body>
</html>