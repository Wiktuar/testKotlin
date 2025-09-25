package ru.wiktuar.testkotlin.controllers.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import ru.wiktuar.testkotlin.entities.courses.Course
import ru.wiktuar.testkotlin.entities.courses.Upload
import ru.wiktuar.testkotlin.repository.UploadRepo
import ru.wiktuar.testkotlin.services.CourseService
import ru.wiktuar.testkotlin.services.DepService
import java.io.File

@RestController
class KursController {

    @Value("\${upload.pathW}")
    private lateinit var writePath: String

    @Autowired
    private lateinit var uploadRepo: UploadRepo

    @Autowired
    private lateinit var courseService: CourseService

    @Autowired
    private lateinit var depService: DepService

    //  метод сохранения нового курса
    @PostMapping("/savecourse")
    fun saveCourse(
        @ModelAttribute course: Course,
        @RequestParam("depId") depId: Int,
        @RequestParam("file", required = false) files: Array<MultipartFile>?
    ): String {
        if(files != null){
            for(file in files){
                val upload = Upload()
                val destFile: File = File(writePath + file.originalFilename)
                upload.url = "/upload/" + file.originalFilename
                upload.name = file.originalFilename.toString()
                file.transferTo(destFile.toPath())
                course.addUploads(upload)
            }
        }
        course.setDepartment(depService.getDepartmentById(depId))
        courseService.saveCourse(course)
        return String.format("/admin/courses/%d", depId)
    }

//  метод получения курса для его обновления
    @ResponseBody
    @GetMapping("/admin/courses/update/{id}")
    fun getCourseById(@PathVariable("id") id: Int): Course {
        return courseService.getCourseWithDepartment(id)
    }
}

