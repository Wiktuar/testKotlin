import {Poll} from "./poll.js";

window.addEventListener("DOMContentLoaded", getPollsHeaders)
const pollBlocks = document.querySelector(".poll_blocks")
getSurveyFromDBByName()

const helpObject = {}

const resultMap = new Map();

// метод получения из БД заголовком опросов для главной страницы
async function getPollsHeaders(){
    const result = await fetch("/getpublished")
    const polls = await result.json()
    if(polls.length > 0){
        renderPollsHeaders(polls)
    }
}

// метод отображения заголовков опроса
function renderPollsHeaders(array){
    array.forEach(arr => {
        pollBlocks.insertAdjacentHTML("beforeend",
            `<div class="poll_block">
                    <h2 class="poll_header" data-poll="${arr.id}">${arr.header}</h2>
                 </div>`)
    })
    pollBlocks.style.display = "block"
}

// метод получения из БД опроса и вариантов ответа
function getSurveyFromDBByName(){
    pollBlocks.addEventListener("click", async e => {
        if(e.target.classList.contains("poll_header")){
           await createSurveyBlock(e)

            //
            // const pBlock = document.createElement("div")
            // pBlock.classList.add("poll")
            //
            // const nextBtn = document.createElement("button")
            // nextBtn.classList.add("next")
            // nextBtn.type = "button"
            // nextBtn.textContent = "Далее"
            //
            // if(e.target.classList.contains("poll_header")){
            //     e.target.after(nextBtn)
            //     e.target.after(pBlock)
            // }
            // renderSurvey(pBlock, helpObject.poll.surveys)
            // nextBtn.addEventListener("click", nextSurvey)
        }
    })
}

// функция, отображающая в HTML полученное с сервера голосование
let sid = 0
let index = 0
function renderSurvey(elem, surveys){
    if(+helpObject.poll.surveys.length === +index){
        const next = document.querySelector(".next")
        next.textContent = "Отправить опрос"
        next.removeEventListener("click", nextSurvey)
        next.addEventListener("click", () => {
            const plainObject = Object.fromEntries(resultMap);
            fetch("/savepollresult", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(plainObject)
            }).then(response => {
                if(response.status === 200){
                    window.location.reload()
                }
            })
        })
    } else {
        const survey = surveys[index]
        sid = survey.id

        elem.innerHTML = `<h1>${survey.topic}</h1>`;
        survey.opinions.forEach(op => {
            const opinion = document.createElement("div")
            elem.insertAdjacentHTML("beforeend",
                `<div class="survey_op" data-id="${op.id}">${op.text}</div>`)
        })
        // selectOpinion();
        index++
    }
}

function nextSurvey(e){
    e.preventDefault()
    resultMap.set(oid, sid)
    const pBlock = document.querySelector(".poll")
    pBlock.innerHTML = ""
    renderSurvey(pBlock, helpObject.poll.surveys)
}

//  функция создания блока теста, который будет появляться и исчезать по клику на заголовок
async function createSurveyBlock(e){
    if(!e.target.nextElementSibling){
        const wrapper = document.createElement("div")
        wrapper.classList.add("surv_wrap")
        wrapper.classList.add("clicked")
        e.target.parentNode.appendChild(wrapper)

        const surveyBlock = document.createElement("div")
        surveyBlock.classList.add("survey_block")
        wrapper.appendChild(surveyBlock)

        // создание объекта опроса и получение его данных
        const poll = await Poll.fetchDataAndCreate(`/getsurvey/${e.target.textContent}`);
        const resultMap = poll.getResultMap()
        resultMap.set(0, poll.getId())

        // добавление кнопки "Далее"
        const nextBtn = document.createElement("button")
        nextBtn.classList.add("next")
        nextBtn.type = "button"
        nextBtn.textContent = "Далее"
        nextBtn.disabled = true
        nextBtn.addEventListener("click", () => render(poll.checkIndex(), surveyBlock, poll.getNextSurvey(nextBtn.parentNode), resultMap))
        wrapper.appendChild(nextBtn)

        render(poll.checkIndex(), surveyBlock, poll.getNextSurvey(), resultMap)

    } else {
        e.target.nextElementSibling.classList.toggle("clicked")
    }
}

function render(isLast, elem, survey, resultMap){
    let sid = 0
    const btn = elem.parentNode.querySelector(".next")

    // блокируем кнопку "Далее"
    if(btn.classList.contains("clicked")){
        btn.classList.remove("clicked")
        btn.disabled = true
    }

    if(isLast){
        btn.textContent = "Отправить"
    }

    if(survey){
        elem.innerHTML = `<h1>${survey.topic}</h1>`;
        sid = `${survey.id}`
        survey.opinions.forEach(op => {
            const opinion = document.createElement("div")
            opinion.classList.add("survey_op")
            opinion.dataset.id = `${op.id}`
            opinion.textContent = `>${op.text}`
            opinion.addEventListener("click", e=> selectOpinion(e, elem, btn, sid, resultMap))
            elem.appendChild(opinion)
        })
    }

}

function selectOpinion(e, elem, btn, sid, resultMap){
    let oid = 0
    const opinions = elem.querySelectorAll(".survey_op");
    opinions.forEach(op1 => {
        if(op1 !== e.target){
            if(op1.classList.contains("clicked")){
                op1.classList.remove("clicked")
            }
            oid = e.target.dataset.id
        }
    })
    e.target.classList.add("clicked")

    // разблокировка кнопки "Далее"
    if(!btn.classList.contains("clicked")){
        btn.classList.add("clicked")
        btn.disabled = false
    }

    // Установка выбранного значения
    resultMap.set(sid, oid)
}

