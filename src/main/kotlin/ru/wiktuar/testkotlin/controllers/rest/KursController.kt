package ru.wiktuar.testkotlin.controllers.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.wiktuar.testkotlin.entities.Course
import ru.wiktuar.testkotlin.services.CourseService
import ru.wiktuar.testkotlin.services.DepService

@RestController
class KursController {

    @Autowired
    private lateinit var courseService: CourseService

    @Autowired
    private lateinit var depService: DepService

    //  метод сохранения нового курса
    @PostMapping("/savecourse")
    fun saveCourse(
        @ModelAttribute course: Course,
        @RequestParam("depId") depId: Int
    ): String {
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

