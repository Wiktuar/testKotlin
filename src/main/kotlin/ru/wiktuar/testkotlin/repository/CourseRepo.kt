package ru.wiktuar.testkotlin.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.wiktuar.testkotlin.entities.Course

@Repository
interface CourseRepo : JpaRepository<Course, Int>{
    @Query("select c from Course c join fetch c.department where c.id = :id")
    fun getCourseWithDepartment(@Param("id") id: Int): Course;
}