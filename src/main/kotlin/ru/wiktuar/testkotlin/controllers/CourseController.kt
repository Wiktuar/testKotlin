package ru.wiktuar.testkotlin.controllers

import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import ru.wiktuar.testkotlin.entities.courses.Course
import ru.wiktuar.testkotlin.entities.Department
import ru.wiktuar.testkotlin.entities.survey.Poll
import ru.wiktuar.testkotlin.entities.test.Test
import ru.wiktuar.testkotlin.services.CourseService
import ru.wiktuar.testkotlin.services.DepService
import ru.wiktuar.testkotlin.services.PollService
import ru.wiktuar.testkotlin.services.TestService

@Controller
class CourseController {

    @Autowired
    private lateinit var depService: DepService;

    @Autowired
    private lateinit var testService: TestService;

    @Autowired
    private lateinit var courseService: CourseService;

    @Autowired
    private lateinit var pollService: PollService

    //  метод получения страницы добавления нового курса
    @GetMapping("/admin/create-course/{id}")
    fun getAddCoursePage(@PathVariable("id") id: String?,
                              model: Model): String {
        model.addAttribute("depId", id)
        model.addAttribute("courseId", 0)
        return "admin/addCourse"
    }

    //   метод получения отдела с его курсами и тестами
    @GetMapping(value = ["/admin/courses/{id}", "/sbi/courses/{id}"])
    fun getDepartmentWithCourses(@PathVariable("id") id: Int,
                                      model: Model,
                                      request: HttpServletRequest
    ): String {
        val department: Department = depService.getDepartmentWithCourses(id)
        val tests: MutableList<Test> = testService.getTestByDepartmentId(id)
        val polls: MutableList<Poll> = pollService.getPollByDepartmentId(department)
        println(polls.size)
        department.tests = tests
        department.polls = polls
        model.addAttribute("department", department)
        return if (request.requestURL.toString().contains("/admin")) "admin/courses" else "sbi/courses"
    }

    //  метод получения курса по его ID
    @GetMapping("/sbi/course/{id}")
    fun getCourseById(@PathVariable("id") id: Int,
                            model: Model,
                            request: HttpServletRequest): String {
        val course: Course = courseService.getCourseById(id)
        model.addAttribute("course", course)
        model.addAttribute("request", request.getHeader("referer"))
        return "course"
    }

    //   метод удаления курса по его ID
    @GetMapping("/admin/courses/delete/{id}")
    fun deleteCourseById( @PathVariable("id") id: Int,
                              request: HttpServletRequest): String {
        courseService.deleteCourseById(id)
        return String.format("redirect:%s", request.getHeader("referer"))
    }

    //   метод обновления курса по его ID
    @GetMapping("/admin/update-course/{id}")
    fun getUpdatePage(@PathVariable("id") id: Int, model: Model): String {
        model.addAttribute("courseId", id)
        return "admin/addCourse"
    }
}