package ru.wiktuar.testkotlin.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.wiktuar.testkotlin.dto.OpinionDTO
import ru.wiktuar.testkotlin.entities.survey.Opinion

@Repository
interface OpinionRepo : JpaRepository<Opinion, Int>{

    @Query("select new ru.wiktuar.testkotlin.dto.OpinionDTO(op.id, op.text, size(op.likes)) from Opinion op left join op.likes group by op having op.survey = :id")
    fun getOpinionsWithLikes(@Param("id") id: Int): MutableList<OpinionDTO>
}