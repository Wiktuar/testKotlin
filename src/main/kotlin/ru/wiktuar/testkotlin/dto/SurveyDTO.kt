package ru.wiktuar.testkotlin.dto

data class SurveyDTO(var id: Int, var topic: String) {
    var opinions: List<OpinionDTO> = mutableListOf()
}