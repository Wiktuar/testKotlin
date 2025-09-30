// получение элементов страницы
const button = document.querySelector(".get-data");
const previewBtn = document.querySelector(".preview_btn");
const preloadFiles = document.querySelector(".preload_files")

// cписок потребуется для отправки имен удаленных файлов
const removeNamesList = []

const pageHeader = document.getElementById("pageHeader");
const depId = document.querySelector("input[name=depId]");
const id = document.querySelector("input[name=id]");
const header = document.getElementById("header");
const file = document.getElementById("file");

const previewDiv = document.querySelector(".preview");
let text = updateCourse(cid);

function MCEInit(){
    tinymce.init ({
        selector: '#mytextarea',
        setup: function (editor) {
            editor.on('init', function (e) {
                if(cid !== 0){
                    text.then(data => editor.setContent(data));
                }
            }); 
        },
        license_key: 'gpl',
        language: 'ru',
        height: 400,
        browser_spellcheck: true,
        plugins: 'link table image media lists advlist',
        toolbar: ' insertfile undo redo | styleselect | bold italic | forecolor backcolor emoticons | alignleft aligncenter alignright alignjustify | bullist numlist | link image',
        image_advtab: true,
        image_title: true,
        automatic_uploads: true,
        file_picker_types: 'image',
        images_reuse_filename: false,

        images_upload_handler: async function(blobinfo, succes, fail){
           const formData = new FormData();
           formData.append("file", blobinfo.blob());

           const result = await fetch("/upload", {
               method: 'POST',
               body: formData
           })
           await result.json().then(data => {
               console.log(data.location)
               succes(data.location)
           });
        },

        file_picker_callback: function(callback, value, meta){
            const input = document.createElement("input");
            input.setAttribute('type', 'file');
            input.setAttribute('accept', 'image/*');
            input.click();

            input.onchange = function(){
                let reader = new FileReader;
                reader.readAsDataURL(this.files[0]);
                reader.onload = () => {
                    let blobCache = tinymce.activeEditor.editorUpload.blobCache;
                    let base64 = reader.result.split(',')[1];
                    let blobinfo = blobCache.create(this.files[0].name, this.files[0], base64);
                    blobCache.add(blobinfo);
                    callback(blobinfo.blobUri(), {title: this.files[0].name});
                }
            }
        },
    });
}

MCEInit();

async function updateCourse(courseId){
    if(courseId !== 0){
        const res = await fetch(`/admin/courses/update/${cid}`)
        const course = await res.json();
        pageHeader.textContent = "Обновление курса";
        depId.value = course.department.id;
        id.value = course.id;
        header.value = course.header;
        if(course.uploads.length > 0){
            for (let i = 0; i < course.uploads.length; i++){
                preloadFiles.insertAdjacentHTML("beforeend",
            `<div class="preload_file">
                        <p class="preload_file_name">${course.uploads[i].name}</p>
                        <img src="/img/cross.png" class="cross" alt="крестик для удаления файлов">
                    </div>`
                )
            }
        }
        return course.text;
    }
}

// метод сохранения нового материала
button.addEventListener("click", (e) => {
    e.preventDefault()

    const formData = new FormData();
    formData.append("id", id.value);
    formData.append("header", header.value);
    formData.append("text", tinymce.activeEditor.getContent());
    formData.append("depId", depId.value);

    if(removeNamesList){
        for(let i = 0; i< removeNamesList.length; i++){
            // console.log(removeNamesList[i])
            formData.append("fileNames", removeNamesList[i])
        }
    }

    if(file.files){
        for(let i = 0; i< file.files.length; i++){
            formData.append("file", file.files[i])
        }
    }

    console.log(formData.getAll("fileNames"))
    fetch("/savecourse", {
        method: 'post',
        body: formData
    }).then(res => res.text())
        .then(data => window.location.href = data)
})

// метод предпросмотра сохраняемого текста статьи
previewBtn.addEventListener("click", () => {
    previewDiv.innerHTML = tinymce.activeEditor.getContent();
    previewDiv.scrollIntoView({
        behavior: 'smooth',
        block: 'start'
    });
})

// событие удаление файла из списка уже загруженных файлов
preloadFiles.addEventListener("click", (e) => {
    if(e.target.classList.contains("cross")){
        let data = e.target.parentNode.querySelector(".preload_file_name").textContent
        removeNamesList.push(data)
        preloadFiles.removeChild(e.target.parentNode);
    }
})