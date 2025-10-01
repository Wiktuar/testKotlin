package ru.wiktuar.testkotlin.controllers.rest

import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.wiktuar.testkotlin.dto.PollDTO
import ru.wiktuar.testkotlin.dto.PublishPolDTO
import ru.wiktuar.testkotlin.dto.ResultPollDTO
import ru.wiktuar.testkotlin.entities.survey.Poll
import ru.wiktuar.testkotlin.entities.survey.Status
import ru.wiktuar.testkotlin.entities.test.Test
import ru.wiktuar.testkotlin.repository.VoterRepo
import ru.wiktuar.testkotlin.services.PollService
import ru.wiktuar.testkotlin.services.SurveyService
import java.security.Principal


@RestController
class PollJsonController {

    @Autowired
    private lateinit var surveyService: SurveyService

    @Autowired
    private lateinit var pollService: PollService

    @Autowired
    private lateinit var voterRepo: VoterRepo

//  метод сохранения опроса
    @PostMapping("/savesurvey")
    fun savePoll(@RequestBody pollDTO: PollDTO): HttpStatus {
        pollService.savePoll(pollDTO)
        return HttpStatus.OK;
    }

//  метод получения объекта опроса
    @GetMapping("/getsurvey/{header}")
    fun getSurvey(@PathVariable("header") header: String, principal: Principal): ResponseEntity<Poll> {
        val poll = pollService.findPollByHeader(header)
        return ResponseEntity(poll, HttpStatus.OK)
    }

//  метод сохранения голоса пользователя
    @PostMapping("/sendsurvey")
    fun saveVote(@RequestParam id: Int, principal: Principal): HttpStatus{
        surveyService.saveVote(principal.name, id)
        return HttpStatus.OK
    }

//  метод получения заголовков опросов
    @GetMapping("/getpublished")
    @ResponseBody
    fun getPublishedPollsHeaders(principal: Principal): List<PublishPolDTO> {
        val polls = pollService.getPollHeaders(Status.PUBLISHED)
        val filterPolls = polls.filter { !voterRepo.existsVoterByLoginAndPollId(principal.name, it.id) }
        return filterPolls
    }

    @GetMapping("/get-list-results/{id}")
    @ResponseBody
    fun getSurveysByPollId(@PathVariable("id") id: Int): ResultPollDTO {
        return pollService.getResultPollDTO(id);
    }

//  метод получения опроса для его обновления
    @GetMapping("/admin/update/poll/{id}")
    fun getTestForUpdate(@PathVariable("id") id: Int,
                         request: HttpServletRequest
    ): ResponseEntity<Poll> {
        val poll = pollService.getPollById(id)
        return ResponseEntity.ok().body(poll)
    }
}