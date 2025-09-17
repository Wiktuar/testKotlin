package ru.wiktuar.testkotlin.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import ru.wiktuar.testkotlin.services.resultServices.ResultService
import ru.wiktuar.testkotlin.services.TestService

@Controller
class ResultPageController {

    @Autowired
    lateinit var resultService: ResultService

    @Autowired
    lateinit var testService: TestService

    @GetMapping("/results/{id}")
    fun getResultsPage(@PathVariable("id") id: Int,
                       model: Model): String {
        val test = testService.getTestWithResults(id);
        model.addAttribute("test", test)
        return "admin/results"
    }
}