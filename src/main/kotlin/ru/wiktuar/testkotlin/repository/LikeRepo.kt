package ru.wiktuar.testkotlin.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.wiktuar.testkotlin.entities.survey.Like

@Repository
interface LikeRepo : JpaRepository<Like, Int> {

    @Query("select count(l.survey) from Like l group by l.survey having l.survey = :id")
    fun gettotalCountOfLikesForSurvey(@Param("id") id: Int): Int
}