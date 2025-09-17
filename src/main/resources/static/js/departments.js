const addDepBtn = document.querySelector(".add_dep_btn");
const depForm = document.querySelector("#dep_form");
const addSurveyBtn = document.querySelector(".add_survey_btn");
const surveyForm = document.querySelector("#survey_form");

showHTMLBlock(addDepBtn, depForm)

// функция появления блока HTML кода
export function showHTMLBlock(btn, block){
    console.log(btn)
    btn.addEventListener("click", function () {
        if(+block.style.maxHeight === 0) {
            block.style.maxHeight = block.scrollHeight + 'px';
            block.style.marginTop = '20px';
        } else {
            block.style.maxHeight = "";
            block.style.marginTop = '';
        }
    });
}


const listOfAnchors = document.querySelectorAll(".modify");
listOfAnchors.forEach(a => a.addEventListener("click", e=> getDepartment(e)));

// метод получения подразделения для обновления
async function getDepartment(e){
    e.preventDefault();
    let result = e.target.getAttribute("href");
    let depId = result.match(/\d+/i)[0];

    if(result.includes("dep_delete")){
        let data = e.target.parentNode.firstElementChild.textContent;
        $.confirm({
            title: "Вы уверены?",
            content: `<p>Вы удаляете отдел <br><span style="font-weight: bold;">&laquo;${data}&raquo;</span></p>`
        }).then(() => document.location = `/admin/departments/delete/${depId}`)
    } else {
        const resp = await fetch(`/admin/departments/${depId}`);
        const department = await resp.json();

        const depForm = document.querySelector("#dep_form");
        depForm.style.maxHeight = depForm.scrollHeight + 'px';
        depForm.style.marginTop = '20px';
        document.querySelector("input[type=hidden]")
            .value = department.id;
        document.querySelector("input[type=text]")
            .value = department.name;
    }
}