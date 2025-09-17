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
    <title>Список курсов и тестов</title>
</head>
<body>
<div class="wrapper">
    <@c.header></@c.header>

    <div class="container">
        <h1>${department.name}</h1>
        <h2>Учебные материалы</h2>
        <#if department.courses?has_content>
            <#list department.courses as course>
                <div class="dep">
                    <div class="dep_name">
                        <a href="/sbi/course/${course.id}" class="dep_name_txt"> ${course.header}</a>
                    </div>
                </div>
            </#list>
        <#else>
            <div class="empty_list">
                Список материалов пуст
            </div>
        </#if>

        <h2>Проверочные материалы</h2>
        <div class="tests">
            <#if department.tests?has_content>
                <#list department.tests as test>
                    <div class="dep">
                        <div class="dep_name">
                            <a href="/sbi/test/${test.id}" class="dep_name_txt"> ${test.header}</a>
                        </div>
                    </div>
                </#list>
            <#else>
                <div class="empty_list">
                    Список тестов пуст
                </div>
            </#if>
        </div>
    </div>

    <@c.footer></@c.footer>
</div>
</body>
</html>