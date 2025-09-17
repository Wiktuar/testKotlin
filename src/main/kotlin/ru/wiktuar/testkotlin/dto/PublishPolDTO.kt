package ru.wiktuar.testkotlin.dto

import ru.wiktuar.testkotlin.entities.survey.Voter

class PublishPolDTO(var id: Int, var header: String) {
    val voters: MutableList<Voter> = ArrayList()
}