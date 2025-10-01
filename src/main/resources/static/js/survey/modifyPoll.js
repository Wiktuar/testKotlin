const pollsBlock = document.querySelector(".polls");
const listOfPollAnchors = pollsBlock.querySelectorAll(".modify");
listOfPollAnchors.forEach(a => a.addEventListener("click", e=> modifyPoll(e)));

//  функция обновления и удаления теста
async function modifyPoll(e){
    e.preventDefault();
    let result = e.target.getAttribute("href");
    let pollId = result.match(/\d+/i)[0];

    if(result.includes("poll_delete")){
        let data = e.target.parentNode.firstElementChild.textContent;
        $.confirm({
            title: "Вы уверены?",
            content: `<p>Вы удаляете опрос <br><span style="font-weight: bold;">&laquo;${data}&raquo;</span></p>`
        }).then(() => document.location = `/admin/polls/delete/${pollId}`)
    } else {
        window.location.href = `/admin/update-poll/${pollId}`;
    }
}