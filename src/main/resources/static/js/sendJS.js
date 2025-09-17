const firstName = document.querySelector("input[name=firstname]");
const lastName = document.querySelector("input[name=surname]");
const sendBtn = document.getElementById("sendButton");

sendBtn.addEventListener("click", sendData);

function sendData(){
    const data = {
        name: firstName.value,
        surname: lastName.value,
        interests: ['chess', 'football', 'sky']
    }

    fetch("/testjson", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
}