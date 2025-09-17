package ru.wiktuar.testkotlin.dto

data class OpinionDTO(var id: Int, var text: String, var likes: Int){
    var color: String = ""
    var percent: String = ""

    class ExcelDTO(var percent: String, var likes: Int, var atext: String)

    fun getExcelDTO(): ExcelDTO{
        return ExcelDTO(this.percent, this.likes, this.text)
    }
}
