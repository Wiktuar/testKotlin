(function showFileHeader(){
    const fileName = document.querySelector(".file_name")
    document.getElementById("file")
        .addEventListener('change', e => {
            let files = e.target.files;
            if (files.length > 0) {
                let value = ""
                for(let i = 0; i< files.length; i++){
                    value += files[i].name + "\n"
                }
                fileName.textContent = value
            } else {
                fileName.textContent = 'Файл не выбран';
            }
        })
})()