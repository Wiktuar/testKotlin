package ru.wiktuar.testkotlin.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.wiktuar.testkotlin.dto.PollDTO
import ru.wiktuar.testkotlin.dto.PublishPolDTO
import ru.wiktuar.testkotlin.dto.ResultPollDTO
import ru.wiktuar.testkotlin.dto.SurveyDTO
import ru.wiktuar.testkotlin.entities.Department
import ru.wiktuar.testkotlin.entities.survey.Poll
import ru.wiktuar.testkotlin.entities.survey.Status
import ru.wiktuar.testkotlin.repository.OpinionRepo
import ru.wiktuar.testkotlin.repository.PollRepo

@Service
class PollService {

    @Autowired
    private lateinit var pollRepo: PollRepo

    @Autowired
    private lateinit var depService: DepService

    @Autowired
    private lateinit var surveyService: SurveyService

    fun getPollByDepartmentId(department: Department): MutableList<Poll> {
        return pollRepo.findPollByDepartment(department)
    }

    fun savePoll(pollDTO: PollDTO){
        val poll = pollDTO.poll
        poll.department = depService.getDepartmentById(pollDTO.depId)
        pollRepo.save(poll)
    }

//  метод получения опроса по его заголовку со всеми вопросами и мнениями
    fun findPollByHeader(header: String): Poll {
        return pollRepo.getPollByHeader(header)
    }

//  метод получения заголовков опроса по определенному статусу
    fun getPollHeaders(status: Status): List<PublishPolDTO>{
        return pollRepo.getPollHeaders(status)
    }

    fun getResultPollDTO(id: Int): ResultPollDTO {
        val pollDTO = pollRepo.getResultPollDTO(id)
        pollDTO.surveys = pollRepo.getSurveysByPollId(pollDTO.id)
        pollDTO.surveys.forEach{
            val opinions = surveyService.getResultsSurvey(it.id)
            it.opinions = opinions
        }
        return pollDTO
    }

}