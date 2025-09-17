// изменение типа инпута пароля и подтверждения пароля для текстового отображения введенных данных
const $inputPass = document.getElementById("password");
const $btnShowPassword = document.querySelector(".showPassword");

$btnShowPassword.addEventListener("click", () => {

    if($inputPass.getAttribute('type') === 'password'){
        $inputPass.setAttribute('type', 'text');
        $btnShowPassword.classList.add('view');
    } else {
        $inputPass.setAttribute('type', 'password');
        $btnShowPassword.classList.remove('view');
    }
})

let sendFormBtn = document.getElementById("sendLoginForm");
sendFormBtn.addEventListener("click", e => loginUser(e));

// функция отправки формы аутентификации на сервер
async function loginUser(e){
    e.preventDefault();
    let username = document.getElementById("username");
    let password = document.getElementById("password");

    const formData = new FormData();
    formData.append("username", username.value);
    formData.append("password", password.value);
    const result = await fetch("/login", {
        method: 'POST',
        body: formData
    });

    if(result.status === 200){
        result.json().then(data => {
            console.log(data)
            window.location.href = data
        })
    } else {
        result.json().then(data => {
            let authenticationWarning = document.querySelector(".authenticationWarning");
            let authMessage = document.querySelector(".authMessage");
            authenticationWarning.style.display = 'flex';
            authMessage.textContent = `${data}`;
        })
    }

}


// функция обработки результата запроса на логгирование
function handleJsonResult(status, result){
    console.log(status)
    let authenticationWarning = document.querySelector(".authenticationWarning");
    let authMessage = document.querySelector(".authMessage");
    if(status === 401){
        if(result.includes("Bad credentials")){
            console.log(result)
            authenticationWarning.style.display = 'flex';
            authMessage.textContent = "Неверный пароль";
        }
        else if(result.includes("not exists")){
            authenticationWarning.style.display = 'flex';
            authMessage.textContent ="Пользователь с таким логином не найден";
        }
    } else {
        window.location.href = result;
    }
}