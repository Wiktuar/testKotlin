// метод сохранения формы голосования
export async function savePoll(){
    await fetch("/savesurvey", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(getPoll())
    }).then(resp => {if(resp.status === 200){
        console.log("OK")
    } else {
        console.log("Что-то пошло не так...")
    }})
}

// метод получения из HTML объекта опроса и вариантов ответа
export function getPoll(){
    const pollDTO = {}
    pollDTO.depId = document.querySelector('input[name="depId"]').value
    const poll = {}
    poll.id = document.querySelector('input[name="poll_id"]').value
    poll.header = document.getElementById("header").value
    poll.status = (document.getElementById("published").checked) ? 'PUBLISHED' : 'SAVED'
    poll.personal = !!(document.getElementById("personal").checked)
    poll.surveys = getSurveyWithOpinions();
    pollDTO.poll = poll
    return pollDTO
}

function getSurveyWithOpinions(){
    const surveys = document.querySelectorAll(".survey")
    const survArr = []

    surveys.forEach(surv =>  {
        const survey = {}
        survey.id = surv.querySelector("input[name=survey_id]").value
        survey.topic = surv.querySelector(".survey_text").value

        const opinionBlocks = surv.querySelectorAll(".opinion_block")
        const opinions = []

        opinionBlocks.forEach(op => {
            const opinion = {}
            opinion.id = op.querySelector("input[type=hidden]").value
            opinion.text = op.querySelector(".opinion").value
            if(opinion.id !== 0 && opinion.text) opinion.survey = survey.id
            if(opinion.text.replace(/\s/g, '')) opinions.push(opinion)
        })

        survey.opinions = opinions
        survArr.push(survey)
    })

    return survArr
}

// функция удаления вопроса при нажатии на крестик
export function deleteSurvey(e){
    if(e.target.classList.contains("cross"))
        e.target.parentNode.remove();
}
