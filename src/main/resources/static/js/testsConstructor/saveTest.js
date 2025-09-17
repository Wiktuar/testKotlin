// функция, сохраняющая тест в базу данных.
export function saveTest(){
    const depId = document.querySelector("input[name=depId]");
    const testId = document.querySelector("input[name=id]");
    const testHeader = document.getElementById("header");

    let questions =  [];

    const questionsList = document.getElementsByClassName("question");
    for (const qElement of questionsList) {
        createQuestionObject(questions, qElement);
    }

    let testDTO = {};
    testDTO.depId = depId.value;

    let test = {};
    test.id = testId.value;
    test.header = testHeader.value;
    test.questions = questions;

    testDTO.test = test;
    fetch("/sbilearn/testjson", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(testDTO)
    }).then(res => res.text())
        .then(data => window.location.href = data)
}

// функция, возвращающая объект с данными, полученными из вопроса
function createQuestionObject(array, question){
    let qObj = {};
    let answers = [];
    qObj.id = question.querySelector("#qid").value;
    qObj.text = question.querySelector("#qHeader").value;
    const answerList = question.getElementsByClassName("answer_block");
    for (const aElement of answerList) {
        let answer = {};
        answer.id = aElement.querySelector("#aid").value;
        answer.text = aElement.querySelector(".answer_input").value;
        answer.correct = aElement.querySelector("input[type=radio]").matches(':checked');
        answers.push(answer);
    }
    qObj.answers = answers;
    array.push(qObj);
}

// функция удаления вопроса при нажатии на крестик
export function deleteQuestion(e){
    if(e.target.classList.contains("cross"))
        e.target.parentNode.remove();
}

