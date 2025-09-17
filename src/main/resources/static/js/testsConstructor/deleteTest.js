const testsBlock = document.querySelector(".tests");
const listOfTestAnchors = testsBlock.querySelectorAll(".modify");
listOfTestAnchors.forEach(a => a.addEventListener("click", e=> modifyTest(e)));

//  функция обновления и удаления теста
async function modifyTest(e){
    e.preventDefault();
    let result = e.target.getAttribute("href");
    let testId = result.match(/\d+/i)[0];

    if(result.includes("test_delete")){
        let data = e.target.parentNode.firstElementChild.textContent;
        $.confirm({
            title: "Вы уверены?",
            content: `<p>Вы удаляете тест <br><span style="font-weight: bold;">&laquo;${data}&raquo;</span></p>`
        }).then(() => document.location = `/admin/tests/delete/${testId}`)
    } else {
        window.location.href = `/admin/update-test/${testId}`;
    }
}