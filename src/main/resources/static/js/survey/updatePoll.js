const pollContainer = document.querySelector(".create_poll_container");
const depInput = document.querySelector('input[name="depId"]')
const pollIdInput = document.querySelector('input[name="poll_id"]')
const pollHeader = document.getElementById("header")
const status = document.getElementById("published")

if(pollId) getPollForUpdate(pollId);

async function getPollForUpdate(pollId){
    const result = await fetch(`/admin/update/poll/${pollId}`)
    const poll = await result.json()

    depInput.value = poll.department.id;
    pollIdInput.value = pollId
    pollHeader.value = poll.header
    if(poll.status === "PUBLISHED") status.checked = true;
    poll.surveys.forEach(survey => renderSurvey(survey))
}

function renderSurvey(survey){
    pollContainer.insertAdjacentHTML("beforeend",
        `
           <div class="survey">
                <input type="hidden" name="survey_id" value="${survey.id}">
                <img src="/img/cross.png" class="cross" alt="Удалить вопрос" title="Удалить вопрос">
                <h2>Задайте вопрос и добавьте от 2 до 5 вариантов ответа</h2>                   
                <textarea class="survey_text" name="topic" placeholder="Всего 500 символов" maxlength="500">${survey.topic}</textarea>
            </div>
        `)
   renderOpinions(survey.opinions)
}

function renderOpinions(opinions){
    let id = 0;
    let value = ""
    for(let i = 0; i < 5; i++){
        if(opinions[i]){
            id = opinions[i].id
            value = opinions[i].text
        } else {
            id = 0
            value = ""
        }
        pollContainer.lastElementChild.insertAdjacentHTML("beforeend",
        `<div class="opinion_block">
                <input type="hidden" name="id" value="${id}">
                <input type="text" class="opinion" name="opinion" value="${value}" placeholder=" Всего 80 символов">
            </div>
        `)
    }
}