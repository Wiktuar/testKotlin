
const warning = document.querySelector(".warning")
let isWarning = false

// function getFiles(e, isWarning){
//     const files = e.target.files;
//     for(let i = 0; i< files.length; i++) {
//         exist(files[i].name)
//             .then(data => {
//                 console.log("data " + data)
//                 if (data) {
//                     isWarning = true
//                 }
//             })
//         console.log("Выполнено раньше")
//         console.log(isWarning)
//     }
// }

document.getElementById("file")
        .addEventListener('change', e => exist(e))

// (function showFileHeader(){
//     const fileName = document.querySelector(".file_name")
//     const warning = document.querySelector(".warning")
//     let isWarning = false
//     document.getElementById("file")
//         .addEventListener('change', e => function

            // let files = e.target.files;
            // if (files.length > 0) {
            //     let value = ""
            //     var isWarning = false
            //     for(let i = 0; i< files.length; i++){
            //         exist(files[i].name)
            //             .then(data => {
            //                console.log("data " + data)
            //                if (data) {
            //                    isWarning = true
            //                }
            //             })
            //         console.log("isWarning is " + isWarning)
                    // if(isWarning){
                    //
                    //     e.target.files = null
                    // }
                    // value += files[i].name + "\n"
                // }
                // fileName.textContent = value
            // } else {
                // fileName.textContent = 'Файл не выбран';
            // }
        // })
// })()

async function exist(e){
    const fileName = document.querySelector(".file_name")
    const warning = document.querySelector(".warning");
    const fileInput = document.getElementById("file");
    let files = e.target.files;
    let isActive = false;
    warning.style.display = "none";
    let value = "";

    for(let i = 0; i< files.length; i++){
        const formData = new FormData();
        formData.append("fileName", files[i].name)
        await fetch("/is_file_exists", {
                method: 'POST',
                body: formData
            }
        ).then(response => response.json())
            .then(data => {
                if(data){
                    warning.style.display = "block"
                    warning.textContent = `Файл \"${files[i].name}\" уже существует! Исключите его из списка загружаемых файлов`
                    value = 'Файл не выбран';
                    fileInput.value = ""
                    isActive = true
                } else {
                    value += files[i].name + "\n"
                }
                fileName.textContent = value
            })
        if(isActive) break
    }


    // for(let i = 0; i< files.length; i++){
        //
        // value
        // }
        // fileName.textContent = value
        // } else {
        // fileName.textContent = 'Файл не выбран';
        // }

}