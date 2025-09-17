package ru.wiktuar.testkotlin.services

import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.wiktuar.testkotlin.dto.OpinionDTO
import ru.wiktuar.testkotlin.entities.survey.Like
import ru.wiktuar.testkotlin.entities.survey.Survey
import ru.wiktuar.testkotlin.entities.survey.Voter
import ru.wiktuar.testkotlin.repository.LikeRepo
import ru.wiktuar.testkotlin.repository.OpinionRepo
import ru.wiktuar.testkotlin.repository.SurveyRepo
import ru.wiktuar.testkotlin.repository.VoterRepo
import java.util.*

@Service
class SurveyService {

    @Autowired
    private lateinit var surveyRepo: SurveyRepo

    @Autowired
    private lateinit var voterRepo: VoterRepo

    @Autowired
    private lateinit var likeRepo: LikeRepo

    @Autowired
    private lateinit var opinionRepo: OpinionRepo

//  метод сохранения голоса при опросе
    @Transactional
    fun saveVote(login: String, id: Int){
        likeRepo.save(Like(id))
        voterRepo.save(Voter(login))
    }

//  метод получения результатов теста
    fun getResultsSurvey(id: Int): MutableList<OpinionDTO>{
        val colorArray = arrayOf("#00cc00", "yellow", "blue", "#538cc6", "#ff3399")
        val resultList = mutableListOf<OpinionDTO>();
        var percent = ""

        val count = likeRepo.gettotalCountOfLikesForSurvey(id);
        val opinions = opinionRepo.getOpinionsWithLikes(id)
        opinions.sortByDescending { it.likes }
        val rows = opinions.size
        val table: Array<Array<OpinionDTO?>> = Array(rows, { i -> Array(rows - i) {null} })

        var outer = 0
        var inner = 0
        for (i in 0 until opinions.size){
            if (i == 0 ) {
                opinions[i].color = colorArray[outer]
                table[outer][inner] = opinions[i]
            }
            else {
                if(opinions[i].likes < opinions[i-1].likes){
                    inner = 0;
                    opinions[i].color = colorArray[++outer]
                    table[outer][inner] = opinions[i]
                } else {
                    opinions[i].color = colorArray[outer]
                    table[outer][++inner] = opinions[i]
                }
            }
        }
        for (arr in table){
            for (opinion in arr){
                if(opinion == null) continue
                percent = String.format(Locale.US, "%.2f",(opinion.likes.toDouble() / count) * 100)
                opinion.percent = "${percent.replace(".00", "")}%"
                resultList.add(opinion);
            }
        }
        return resultList
    }
}