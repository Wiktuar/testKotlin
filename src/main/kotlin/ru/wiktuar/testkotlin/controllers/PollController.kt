package ru.wiktuar.testkotlin.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class PollController {

    //  метод получения страницы создания опроса с ID банковского отдела, создающего опрос
    @GetMapping("/admin/create-poll/{id}")
    fun getAddPollPage(
        @PathVariable("id") id: String,
        model: Model
    ): String {
        model.addAttribute("depId", id)
        return "admin/createPoll"
    }

//   метод получения страницы результатов опроса
    @GetMapping("/poll_results/{id}")
    fun getPollResultsPage(@PathVariable("id") id: Int, model: Model): String {
        model.addAttribute("pollId", id)
        return "admin/pollResults"
    }
}