package ru.wiktuar.testkotlin.controllers.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.wiktuar.testkotlin.dto.ResultDTO
import ru.wiktuar.testkotlin.entities.survey.Like
import ru.wiktuar.testkotlin.entities.survey.Voter
import ru.wiktuar.testkotlin.entities.test.Result
import ru.wiktuar.testkotlin.repository.LikeRepo
import ru.wiktuar.testkotlin.repository.VoterRepo
import ru.wiktuar.testkotlin.services.resultServices.ResultService
import java.security.Principal

@RestController
class ResultController {
    @Autowired
    private lateinit var resultService: ResultService

    @Autowired
    private lateinit var voterRepo: VoterRepo

//  метод сохранения результатов голосования
    @PostMapping("/savepollresult")
    fun savePollResult(@RequestBody data: Map<Int, Int>, principal: Principal): String{
        resultService.savePollResult(data, principal)
        return "OK"
    }

    @GetMapping("/voters/{id}")
    fun getVoters(@PathVariable("id") id: Int): List<Voter>{
        return voterRepo.getVotersByPollId(id);
    }

//  метод сохранения результатов теста
    @PostMapping("/saveresult")
    fun saveResult(@RequestBody resultDTO: ResultDTO,
                   principal: Principal): String {
        val result = Result()
        result.firstName = "Viktor"
        result.surname = "Alexeevich"
        result.lastName = "Gisev"

        val (result1, message, testId) = resultDTO
        result.result = result1
        result.message = message
        result.testId = testId
        result.timeStamp = convertTimeToString();
        resultService.saveResult(result)
        return "correct"
    }
}