package ru.wiktuar.testkotlin.services

import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import ru.wiktuar.testkotlin.entities.courses.Course
import ru.wiktuar.testkotlin.entities.courses.Upload
import ru.wiktuar.testkotlin.repository.CourseRepo
import ru.wiktuar.testkotlin.repository.UploadRepo
import java.io.File

@Service
class CourseService {

    @Value("\${upload.pathW}")
    private lateinit var writePath: String

    @Autowired
    private lateinit var courseRepo: CourseRepo

    @Autowired
    private lateinit var uploadRepo: UploadRepo

    @Autowired
    private lateinit var depService: DepService

    // метод сохранения и обновления курса
    @Transactional
    fun saveCourse(course: Course, depId: Int, files: Array<MultipartFile>?, fileNames: Array<String>?) {
        if(course.id != 0){
            course.uploads = uploadRepo.getUploadsByCourseId(course.id)
            if(fileNames != null) {
                for (fileName in fileNames){
                    if(course.uploads.removeIf { fileNames.contains(it.name) }){
                        File(writePath + fileName).delete()
                    }
                }
            }
        }

        if(files != null){
            for(file in files){
                val upload = Upload()
                val destFile = File(writePath + file.originalFilename)
                upload.url = "/uploads/" + file.originalFilename
                upload.name = file.originalFilename.toString()
                file.transferTo(destFile.toPath())
                course.addUploads(upload)
            }
        }

        course.department = depService.getDepartmentById(depId)
        courseRepo.save(course)
    }

    //  метод получения курса по его ID
    fun getCourseById(id: Int): Course {
        return courseRepo.findById(id).get()
    }

    //  метод получения курса по его ID вместе с отделом
    fun getCourseWithDepartment(id: Int): Course {
        return courseRepo.getCourseWithDepartment(id)
    }

    //  метод удаления курса по его id
    fun deleteCourseById(id: Int) {
        val uploads = uploadRepo.getUploadsByCourseId(id)
        uploads
            .map { it.url.replace("/uploads/", writePath) }
            .forEach {
                val file = File(it)
                if(file.delete()) println("Файл $it успешно удален")
                else println("Файл $it не удален")
            }
        courseRepo.deleteById(id)
    }
}