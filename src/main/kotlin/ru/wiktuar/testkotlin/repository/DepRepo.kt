package ru.wiktuar.testkotlin.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.wiktuar.testkotlin.entities.Department

@Repository
interface DepRepo : CrudRepository<Department, Int> {
    @Query("select d from Department d left join fetch d.courses where d.id = :id")
    fun getDepartmentWithCourses(@Param("id") id: Int): Department;
}