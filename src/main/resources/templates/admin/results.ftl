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
    <link rel="stylesheet" href="/css/result.css">
    <title>Результаты тестов</title>
</head>
<body>
<div class="wrapper">
    <@c.header></@c.header>

    <div class="container">
        <h1>Результаты теста "${test.header}"</h1>
        <table>
            <tr>
                <th class="cell">
                    ФИО
                </th>
                <th>
                    Результат
                </th>
                <th>
                    Дата
                </th>
            </tr>
            <#list test.results as result>
                <tr>
                    <td class="cell">${result.firstName} ${result.surname} ${result.lastName}</td>
                    <td>${result.result}% ${result.message}</td>
                    <td>${result.timeStamp}</td>
                </tr>
            </#list>
        </table>
    </div>

    <@c.footer></@c.footer>
</div>
</body>
</html>