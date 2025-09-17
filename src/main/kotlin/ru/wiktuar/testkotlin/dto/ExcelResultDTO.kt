package ru.wiktuar.testkotlin.dto

import jakarta.persistence.Column

data class ExcelResultDTO(val firstName: String,
                          val result: String,
                          val message: String,
                          val timeStamp: String)