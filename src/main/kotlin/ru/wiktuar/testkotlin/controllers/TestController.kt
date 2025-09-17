package ru.wiktuar.testkotlin.controllers

import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import ru.wiktuar.testkotlin.services.TestService

@Controller
class TestController {
    @Autowired
    lateinit var testService: TestService;

    //  метод получения страницы создания тестов с ID банковского отдела, создающего тест
    @GetMapping("/admin/create-test/{id}")
    fun getAddTestPage(
        @PathVariable("id") id: String,
        model: Model
    ): String {
        model.addAttribute("depId", id)
        return "admin/createTest"
    }

    //  метод получения ID теста для страницы обновления
    @GetMapping("/admin/update-test/{id}")
    fun getUpdateTestPage(
        @PathVariable("id") id: Long,
        model: Model
    ): String {
        model.addAttribute("testID", id)
        return "admin/updateTest"
    }

    //  метод удаления теста по его ID
    @GetMapping("/admin/tests/delete/{id}")
    fun deleteTestById(
        @PathVariable("id") id: Int,
        request: HttpServletRequest
    ): String {
        testService.deleteTestById(id)
        return String.format("redirect:%s", request.getHeader("referer"))
    }

    //  метод получения страницы решения теста
    @GetMapping("/sbi/test/{id}")
    fun getSimpleTestPage(
        @PathVariable("id") id: Int,
        model: Model,
        request: HttpServletRequest
    ): String {
        println(request.getHeader("referer"))
        model.addAttribute("request", request.getHeader("referer"))
        model.addAttribute("testId", id)
        return "sbi/test"
    }
}