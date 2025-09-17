// удаление и обновление материала по его id
const materialsBlock = document.querySelector(".materials");
const listOfAnchors = materialsBlock.querySelectorAll(".modify");
listOfAnchors.forEach(a => a.addEventListener("click", e=> modifyCourse(e)));

async function modifyCourse(e){
    e.preventDefault();
    let result = e.target.getAttribute("href");
    let courseId = result.match(/\d+/i)[0];

    if(result.includes("course_delete")){
        let data = e.target.parentNode.firstElementChild.textContent;
        $.confirm({
            title: "Вы уверены?",
            content: `<p>Вы удаляете отдел <br><span style="font-weight: bold;">&laquo;${data}&raquo;</span></p>`
        }).then(() => document.location = `/admin/courses/delete/${courseId}`)
    } else {
       window.location.href = `/admin/update-course/${courseId}`;
    }
}
