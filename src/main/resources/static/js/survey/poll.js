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

    getNextSurvey(){
        if (this._index === this._pollObj.surveys.length){
           this.sendResult()
        }
        return this._pollObj.surveys[this._index++]
    }

    getResultMap (){
        return this._resultMap
    }

    checkIndex(){
        console.log(this._pollObj.surveys.length)
        console.log(this._index)
        return this._pollObj.surveys.length - this._index === 1
    }

    sendResult(){
        const plainObject = Object.fromEntries(this.getResultMap());
        fetch("/savepollresult", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(plainObject)
        }).then(response => {
            if(response.status === 200){
                window.location.reload()
            }
        })
    }
}