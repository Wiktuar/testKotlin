package ru.wiktuar.testkotlin.dto

class ResultPollDTO(var id: Int, var header: String, val countVoters: Int){
    var surveys: List<SurveyDTO> = listOf()
}
