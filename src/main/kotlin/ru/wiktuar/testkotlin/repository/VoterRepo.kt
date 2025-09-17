package ru.wiktuar.testkotlin.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.wiktuar.testkotlin.entities.survey.Voter

interface VoterRepo : JpaRepository<Voter, Int>{

    fun existsVoterByLoginAndPollId(login: String, pollid: Int): Boolean

    fun getVotersByPollId(id: Int): List<Voter>
}