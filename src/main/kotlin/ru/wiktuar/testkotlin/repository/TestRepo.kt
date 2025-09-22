package ru.wiktuar.testkotlin.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.wiktuar.testkotlin.entities.test.Test

@Repository
interface TestRepo : JpaRepository<Test, Int> {
    fun getTestByDepartmentId(id: Int): MutableList<Test>;

    @Query("from Test t join fetch t.questions join fetch t.department where t.id = :id")
    fun getTest(@Param("id") id: Int): Test;

    @Query("from Test t left join fetch t.results where t.id = :id")
    fun getTestWithResults(@Param("id") id: Int): Test;

    @Query("select t.header from Test t where t.id = :id")
    fun getTestHeaderByTestId(@Param("id")testId: Int): String
}