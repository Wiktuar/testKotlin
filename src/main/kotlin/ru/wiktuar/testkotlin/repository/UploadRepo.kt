package ru.wiktuar.testkotlin.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.wiktuar.testkotlin.entities.courses.Upload

interface UploadRepo : JpaRepository<Upload, Int> {
}