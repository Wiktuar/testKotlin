package ru.wiktuar.testkotlin.entities.survey

enum class Status(val value: String) {
    SAVED("Сохранен"), UPDATED("Обновлен"), PUBLISHED("Опубликован"), STOPPED("Остановлен")
}