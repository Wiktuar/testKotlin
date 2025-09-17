import {createSurvey} from "./addHTML.js";
import {deleteSurvey, savePoll} from "./saveSurvey.js";

const pollContainer = document.querySelector(".create_poll_container");
const createSurveyBtn = document.querySelector(".create_survey");
const savePollBtn = document.querySelector(".save_poll");

createSurveyBtn.addEventListener("click", create)

// функция устанавливающая события удаления вопроса по клику на крестик
pollContainer.addEventListener("click", deleteSurvey);

savePollBtn.addEventListener("click", savePoll)

// функция, создающая новый вопрос
function create(){
    let survey = createSurvey();
    pollContainer.insertAdjacentHTML("beforeend", survey);
}