package ru.wiktuar.testkotlin.dto

class ResultPollDTO(var id: Int, var header: String){
    var surveys: List<SurveyDTO> = listOf()
}
