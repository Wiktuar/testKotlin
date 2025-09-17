import {createQuestion} from "./addHTML.js";
import {deleteQuestion, saveTest} from "./saveTest.js";

const testContainer = document.querySelector(".create_test_container");
const createQuestionBtn = document.querySelector(".create_question");
const saeTestBtn = document.querySelector(".save_test");

let count = 0;

createQuestionBtn.addEventListener("click", create);
saeTestBtn.addEventListener("click", saveTest);

// функция устанавливающая события удаления вопроса по клику на крестик
testContainer.addEventListener("click", deleteQuestion);

// функция, создающая новый вопрос
function create(){
    let question = createQuestion(++count);
    testContainer.insertAdjacentHTML("beforeend", question);
    return ++count;
}


