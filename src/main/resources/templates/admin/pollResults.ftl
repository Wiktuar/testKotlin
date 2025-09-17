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
    <title>Результаты опроса</title>
</head>
<body>
<div class="wrapper">
    <@c.header></@c.header>

    <div class="container">
        <div class="survey_results">
            <button type="button" class="poll_people_btn">Проголосовавшие</button>
            <div class="poll_people">

            </div>
        </div>
    </div>

    <@c.footer></@c.footer>
</div>

<script>
    let pollId = ${pollId}
</script>
<script src="/js/survey/results.js"></script>
</body>
</html>