package ru.wiktuar.testkotlin.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import ru.wiktuar.testkotlin.entities.courses.Upload

interface UploadRepo : JpaRepository<Upload, Int> {
    fun getUploadsByCourseId(courseId: Int): MutableList<Upload>

    @Query("select u.url from Upload u where u.name = :name")
    fun getUrlByName(@Param("name") name: String): String

    @Query("select u.name from Upload u where u.courseId = :id")
    fun getUploadsNamesByCourseId(@Param("id") id : Int): MutableList<String>

    @Modifying
    @Query("delete from Upload u where u.name = :name")
    fun deleteByName(@Param("name") name: String): Unit

    fun existsUploadByName(name: String): Boolean
}