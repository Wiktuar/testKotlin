export class Poll {

    _pollObj = {}
    _index = 0
    _resultMap = new Map()

    static async fetchDataAndCreate(url) {
        try {
            const response = await fetch(url);
            const data = await response.json();
            // Возвращаем новый экземпляр класса, инициализированный данными
            return new Poll(data);
        } catch (error) {
            console.error("Ошибка при получении данных:", error);
            throw error;
        }
    }

    constructor(data) {
        this._pollObj = data
    }

    getId(){
        return this._pollObj.id
    }

    getNextSurvey(node){
        if (this._index === this._pollObj.surveys.length){
           this.sendResult(node)
        }
        return this._pollObj.surveys[this._index++]
    }

    getResultMap (){
        return this._resultMap
    }

    checkIndex(){
        return this._pollObj.surveys.length - this._index === 1
    }

    sendResult(node){
        const plainObject = Object.fromEntries(this.getResultMap());
        fetch("/savepollresult", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(plainObject)
        }).then(response => {
            if(response.status === 200){
                node.innerHTML = `
                    <div class="poll_result">
                       <img src="/img/check mark.jpg" alt="Картинка опроса">
                       <p>Благодарим за участие! Ваш голос будет услышан</p> 
                    </div> 
                `;
            }
        })
    }
}