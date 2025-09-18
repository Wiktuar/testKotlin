package ru.wiktuar.testkotlin.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.wiktuar.testkotlin.dto.PublishPolDTO
import ru.wiktuar.testkotlin.dto.ResultPollDTO
import ru.wiktuar.testkotlin.dto.SurveyDTO
import ru.wiktuar.testkotlin.entities.Department
import ru.wiktuar.testkotlin.entities.survey.Poll
import ru.wiktuar.testkotlin.entities.survey.Status

@Repository
interface PollRepo : JpaRepository<Poll, Int> {

    @Query("select distinct new ru.wiktuar.testkotlin.dto.ResultPollDTO(p.id, p.header, size(p.voters)) from Poll p inner join p.voters where p.id = :id")
    fun getResultPollDTO(@Param("id")id : Int): ResultPollDTO

    @Query("select new ru.wiktuar.testkotlin.dto.PublishPolDTO(p.id, p.header) from Poll p  where p.status = :status")
    fun getPollHeaders(@Param("status") status: Status): List<PublishPolDTO>

    @Query("from Poll p join fetch p.surveys where p.header = :header")
    fun getPollByHeader(@Param("header") header: String): Poll

    @Query("select new ru.wiktuar.testkotlin.dto.SurveyDTO(s.id, s.topic) from Poll p join p.surveys as s where p.id = :id")
    fun getSurveysByPollId(@Param("id") id: Int): List<SurveyDTO>

    abstract fun findPollByDepartment(department: Department): MutableList<Poll>
}