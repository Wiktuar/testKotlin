package ru.wiktuar.testkotlin.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.wiktuar.testkotlin.dto.ExcelResultDTO
import ru.wiktuar.testkotlin.entities.test.Result

@Repository
interface ResultRepo : JpaRepository<Result, Int> {

    @Query("select new ru.wiktuar.testkotlin.dto.ExcelResultDTO(" +
            "r.firstName, r.result, r.message, r.timeStamp) from Result r where r.testId = :id")
    fun getExcelResultDTO(@Param("id") id: Int): List<ExcelResultDTO>

//  метод получения результатов теста по его ID. Необхоим для обновления теста
    fun getResultsByTestId(id:Int): MutableList<Result>
}