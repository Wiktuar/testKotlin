package ru.wiktuar.testkotlin.controllers

import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import ru.wiktuar.testkotlin.services.PollService

@Controller
class PollController {

    @Autowired
    lateinit var pollService: PollService

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

//  метод получения ID опроса для страницы обновления
    @GetMapping("/admin/update-poll/{id}")
    fun getUpdatePollPage(
        @PathVariable("id") id: Int,
        model: Model
    ): String {
        model.addAttribute("pollID", id)
        return "admin/createPoll"
    }

//  метод удаления опроса по его ID
    @GetMapping("/admin/polls/delete/{id}")
    fun deletePollById(
        @PathVariable("id") id: Int,
        request: HttpServletRequest
    ): String {
        pollService.deletePollById(id)
        return String.format("redirect:%s", request.getHeader("referer"))
    }
}