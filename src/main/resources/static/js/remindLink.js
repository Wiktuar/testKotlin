const remindlinkBtn = document.getElementById("remindLink");

remindlinkBtn.addEventListener("click", remindLink);

// функция отправки формы аутентификации на сервер
async function remindLink(e){
    e.preventDefault();
    let username = document.getElementById("username");

    const formData = new FormData();
    formData.append("email", username.value);
    const result = await fetch("/message", {
        method: 'POST',
        body: formData
    });

    handleResult(result.status);
}

function handleResult(status){
    console.log(status);
    let authenticationWarning = document.querySelector(".authenticationWarning");
    let authMessage = document.querySelector(".authMessage");
    authenticationWarning.style.display = 'flex';

    if(status === 200){
        authMessage.textContent = "Письмо для входа в SBIlearn отправлено на указанную Вами почту";
    } else {
        authMessage.textContent = "Вы указали неверную почту";
    }

}