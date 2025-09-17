package ru.wiktuar.testkotlin.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.wiktuar.testkotlin.entities.test.Answer

@Repository
interface AnswerRepo : JpaRepository<Answer, Int> {
}