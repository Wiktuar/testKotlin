package ru.wiktuar.testkotlin.controllers.rest

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm")

//  метод, переводящий в строку текущее время. (в базе данных время хранится в виде строки)
fun convertTimeToString(): String {
    return LocalDateTime.now().format(formatter)
}