// функция, создающая новый вопрос опрос
export function createSurvey(){
    return `<div class="survey">
                    <input type="hidden" name="survey_id" value="0">
                    <img src="/img/cross.png" class="cross" alt="Удалить вопрос" title="Удалить вопрос">
                    <h2>Задайте вопрос и добавьте от 2 до 5 вариантов ответа</h2>                   
                    <textarea class="survey_text" name="topic" placeholder="Всего 500 символов" maxlength="500"></textarea>
                    <div class="opinion_block">
                        <input type="hidden" name="id" value="0">
                        <input type="text" class="opinion" name="opinion" placeholder=" Всего 80 символов">
                    </div>
                    <div class="opinion_block">
                        <input type="hidden" name="id" value="0">
                        <input type="text" class="opinion" name="opinion" placeholder=" Всего 80 символов">
                    </div>
                    <div class="opinion_block">
                        <input type="hidden" name="id" value="0">
                        <input type="text" class="opinion" name="opinion" placeholder=" Всего 80 символов">
                    </div>
                    <div class="opinion_block">
                        <input type="hidden" name="id" value="0">
                        <input type="text" class="opinion" name="opinion" placeholder=" Всего 80 символов">
                    </div>
                    <div class="opinion_block">
                        <input type="hidden" name="id" value="0">
                        <input type="text" class="opinion" name="opinion" placeholder=" Всего 80 символов">
                    </div>
            </div>`
}