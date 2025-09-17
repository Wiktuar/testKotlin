import {createQuestion} from "./addHTML.js";
import {deleteQuestion, saveTest} from "./saveTest.js";

window.addEventListener("load", () => getTest(testID));

const testContainer = document.querySelector(".create_test_container");
const createQuesBtn = document.querySelector(".create_question");
const saeTestBtn = document.querySelector(".save_test");
let total = 1;

createQuesBtn.addEventListener("click", createNewQuestion);
saeTestBtn.addEventListener("click", saveTest);
testContainer.addEventListener("click", deleteQuestion);

// функция создания нового вопроса из шаблона
function createNewQuestion(){
    let question = createQuestion(total);
    testContainer.insertAdjacentHTML("beforeend", question);
    return ++total;
}

// функция получения объекта теста из базы данных
function getTest(testId){
    fetch(`/admin/update/test/${testId}`)
        .then(res => res.json())
        .then(test => {
            parseTestObject(test);
        });
}

// функция разбора текста вопроса теста
function parseTestObject(test) {
    const depId = document.querySelector("input[name=depId]");
    const testId = document.querySelector("input[name=id]");
    const testHeader = document.getElementById("header");

    depId.value = test.department.id;
    testId.value = test.id;
    testHeader.value = test.header;
    test.questions.forEach(q => parseQuestion(q));
}

// функция разбора текста вопроса
function parseQuestion(question){
    const qHtml = `<div class="question ${question.id}">
                            <input type="hidden" id="qid" value="${question.id}">
                            <img src="/img/cross.png" class="cross" alt="Удалить вопрос" title="Удалить вопрос">
                            <h2>Заголовок вопроса</h2>
                            <textarea id="qHeader" name="question_header">${question.text}</textarea>
                          </div>`
    testContainer.insertAdjacentHTML("beforeend", qHtml);
    question.answers.forEach(a => parseAnswer(a, question.id, total));
    return ++total;
}

// функция разбора вариантов ответа
function parseAnswer(answer, id, count){
    const ques = document.getElementsByClassName(`question ${id}`)[0];
    let correct = (answer.correct) ? "checked" : "";
    const aHtml = `<div class="answer_block">
                            <input type="hidden" id="aid" value="${answer.id}">
                            <input type="radio" name="question${count}" ${correct}>
                            <input type="text" class="answer_input" name="answer" value="${answer.text}">
                        </div>`;
    ques.insertAdjacentHTML("beforeend", aHtml);
}