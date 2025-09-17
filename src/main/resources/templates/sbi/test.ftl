<#import "../common.ftl" as c>
<!DOCTYPE html>
<html lang="en">
<head>
    meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/css/reset.css">
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/simpleTest.css">
    <title>Проверочный тест</title>
</head>
<body>
<div class="wrapper">
    <@c.header></@c.header>

    <div class="container">
        <h1 class="test_header">Заголовок теста</h1>
        <div class="app">
            <h2 id="question">Заголовок вопроса теста</h2>
            <div class="answer_buttons blocked">
                <button class="answer_btn" data-correct="false">Answer 1</button>
                <button class="answer_btn" data-correct="false">Answer 2</button>
                <button class="answer_btn" data-correct="false">Answer 3</button>
                <button class="answer_btn" data-correct="false">Answer 4</button>
            </div>
            <button class="next_btn">Следующий вопрос</button>
            <button class="final_btn">Завершить тест</button>
        </div>

        <div class="result">
            <a href="${request}" class="back_btn">К списку курсов и тестов</a>
        </div>

        <button id="begin">Начать тест</button>
    </div>

    <@c.footer></@c.footer>
</div>
<script>
    let testId = ${testId};
</script>
<script src="/js/testsConstructor/simpleTest.js"></script>
</body>
</html>