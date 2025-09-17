// функция, создающая вопрос теста
export function createQuestion(count){
    return ` <div class="question">
                <input type="hidden" id="qid" value="0">
                <img src="/img/cross.png" class="cross" alt="Удалить вопрос" title="Удалить вопрос">
                <h2>Заголовок вопроса</h2>
                 <textarea id="qHeader" name="question_header"></textarea>
                <div class="answer_block">
                    <input type="hidden" id="aid" value="0">
                    <input type="radio" name="question${count}" checked>
                    <input type="text" class="answer_input" name="answer" placeholder="Вариант ответа 1">
                </div>
                <div class="answer_block">
                    <input type="hidden" id="aid" value="0">
                    <input type="radio" name="question${count}">
                    <input type="text" class="answer_input" name="answer" placeholder="Вариант ответа 2">
                </div>
                <div class="answer_block">
                    <input type="hidden" id="aid" value="0">
                    <input type="radio" name="question${count}">
                    <input type="text" class="answer_input" name="answer" placeholder="Вариант ответа 3">
                </div>
                <div class="answer_block">
                    <input type="hidden" id="aid" value="0">
                    <input type="radio" name="question${count}">
                    <input type="text" class="answer_input" name="answer" placeholder="Вариант ответа 4">
                </div>
            </div>`;
}