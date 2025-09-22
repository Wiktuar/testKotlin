// import {showHTMLBlock} from "../departments.js";

window.addEventListener("DOMContentLoaded", getResults)
// window.addEventListener("DOMContentLoaded", getPollPeople)

const resultsBlock = document.querySelector(".poll_results")

async function getResults(){
    const data = await fetch(`/get-list-results/${pollId}`)
    const result = await data.json()
    renderPoll(result)
    addHTML(result.countVoters)
    const btn = document.querySelector(".download_results")
    btn.addEventListener("click", () => downloadPollResults(result.id, result.header, btn))
}

function renderPoll(result){
    resultsBlock.insertAdjacentHTML('afterbegin', `<h1>${result.header}</h1>`)
    result.surveys.forEach(survey => renderSurvey(survey))
}

function renderSurvey(survey){
    const surveyBlock = document.createElement("div")
    surveyBlock.classList.add("survey_results_block")
    surveyBlock.insertAdjacentHTML("afterbegin", `<h2 class="survey_header">${survey.topic}</h2>`)
    resultsBlock.appendChild(surveyBlock)
    survey.opinions.forEach(opininion => renderOpinion(surveyBlock, opininion))
}

function renderOpinion(elem, opinion){
    const op = document.createElement("div")
    op.classList.add("opinion_result_block")
    op.insertAdjacentHTML("afterbegin",
        `<div class="opinion_result_outer">
                  <div class="opinion_result_inner" style="background-color: ${opinion.color}; width: ${opinion.percent};"></div>
                  <div class="opinion_result_inner2">${opinion.text}</div>
              </div>
              <div class="percent">${opinion.percent}</div>`)
    elem.appendChild(op)
}

// метод получения проголосовавших
// function getPollPeople(){
//     const btn = document.querySelector('.poll_people_btn')
//     const block = document.querySelector(".poll_people")
//
//     btn.addEventListener("click",  async () => {
//         block.innerHTML = ""
//         const res = await fetch(`/voters/${pollId}`)
//         const voters = await res.json()
//         voters.forEach(v => {
//             block.insertAdjacentHTML("afterbegin",
//                 `<h3>${v.login}</h3>`)
//         })
//     })
//
// }
//
// getPollPeople()

const block = document.querySelector(".poll_people")
const btn = document.querySelector(".poll_people_btn")

async function getPollPeople(){
    const res = await fetch(`/voters/${pollId}`)
    const voters = await res.json()
    voters.forEach(v => {
        block.insertAdjacentHTML("afterbegin",
            `<h3>${v.login}</h3>`)
    })
}

// showHTMLBlock(btn, block)

// функция добавляет в конец блока результатов опроса HTML код
function addHTML(data, pollId){
    resultsBlock.insertAdjacentHTML("beforeend",
        `
                <p class="countVoters">В опросе приняло участие ${data} человек</p>
                <button type="button" class="download_results">Выгрузить результаты</button>              
            `)
}

async function downloadPollResults(pollId, header, btn){
    const response = await fetch(`/convert_poll_to_excel/${pollId}`)
    const blob = await response.blob()
    const blobUrl = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = blobUrl;
    a.download = `${header}.xlsx`;
    document.body.appendChild(a);
    a.click();
    document.body.removeChild(a);
    window.URL.revokeObjectURL(blobUrl);

    const message = document.createElement("p")
    message.classList.add("message")
    message.textContent = "Файл результатов успешно загружен"
    btn.before(message)
    btn.classList.add("clicked")
    btn.disabled = true
}

