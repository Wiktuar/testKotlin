window.addEventListener("load", () => getTest(testId));

// получение элементов вопроса
const answerBlock = document.querySelector(".answer_buttons");
const question = document.getElementById("question");
const answers = document.getElementsByClassName("answer_btn");
const resultBlock = document.querySelector(".result");

// получение кнопок
const begin = document.getElementById("begin");
const nextBtn = document.querySelector(".next_btn");
const finalBtn = document.querySelector(".final_btn");

// объект полученного теста
let currentTest = null;

// необходимые переменные
let countOfQuestions = 0;
let currentQuestionIndex = 0;
let score = 0;

//  слушатели событий
begin.addEventListener("click", beginTest);

// функция получения объекта теста из базы данных
async function getTest(testId){
    const res = await fetch(`/sbi/gettest/${testId}`);
    countOfQuestions = res.headers.get("X-Total-Count");
    currentTest = await res.json();
    document.querySelector(".test_header")
            .textContent = currentTest.header;
}

function beginTest(e){
    e.target.style.display = "none";
    document.querySelector(".app")
        .style.display = "block";
    insertQuestion(currentQuestionIndex);
}

function insertQuestion(){
    if(currentQuestionIndex === countOfQuestions-1){
        nextBtn.style.display = "none";
        finalBtn.style.display = "block";
        finalBtn.addEventListener("click", completeTest);
    }
    let questionObj = currentTest.questions[currentQuestionIndex];
    question.textContent = questionObj.text;
    for(let i = 0; i < answers.length; i++){
       answers[i].style.backgroundColor = "#fff";
       answers[i].textContent = questionObj.answers[i].text;
       if(questionObj.answers[i].correct){
            answers[i].dataset.correct = "true";
       }
    }
    blockElement(nextBtn, insertQuestion);
    blockElement(answerBlock, getAnswer);
    ++currentQuestionIndex;
}

function getAnswer(e){
    if(e.target.classList.contains("answer_btn")){
       if(e.target.dataset.correct === "true"){
         e.target.style.backgroundColor = "#63e663";
         score++;
      } else {
         e.target.style.backgroundColor = "#E88E8EFF";
         const elem = e.target.parentNode.querySelector('[data-correct="true"]');
         elem.style.backgroundColor = "#63e663";
      }
        document.querySelectorAll(".answer_btn")
            .forEach(a => a.dataset.correct = "false");
        blockElement(answerBlock, getAnswer);
        blockElement(nextBtn, insertQuestion);
    }
}

function completeTest(){
    document.querySelector(".app")
        .style.display = "none";
    resultBlock.style.display = "block";
    let totalScore = Math.round(score / countOfQuestions * 100);
    if(totalScore < 75){
        resultBlock.insertAdjacentHTML("afterbegin", `
        <h2 class="result_header">К сожалению, Вы не прошли тест</h2>
            <p class="result_text">Ваш результат - ${totalScore}%</p>`);
    } else {
        resultBlock.insertAdjacentHTML("afterbegin", `
        <h2 class="result_header">Поздравляе! Вы прошли тест</h2>
            <p class="result_text">Ваш результат - ${totalScore}%</p>`);
    }
    saveResult(totalScore);
}

// функция блокировки и разблокировки элемента
function blockElement(element, func){
    if(element.classList.toggle("blocked")){
        element.removeEventListener("click", func);
    } else {
        element.addEventListener("click", func);
    }
}

// функция отправки результатов теста
function saveResult(totalScore){
    let message = (totalScore < 75) ? "Тест не пройден" : "Тест пройден";

    let result = {
        result: totalScore,
        message: message,
        testId: testId
    };

    fetch("/saveresult", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(result)
    }).then(res => res.text())
        .then(data => console.log(data));
}

