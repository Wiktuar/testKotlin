package ru.wiktuar.testkotlin.services.resultServices

import jakarta.servlet.http.HttpServletResponse
import jakarta.transaction.Transactional
import org.apache.poi.ss.usermodel.BorderStyle
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.util.RegionUtil
import org.apache.poi.xssf.usermodel.XSSFCellStyle
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.wiktuar.testkotlin.entities.survey.Like
import ru.wiktuar.testkotlin.entities.survey.Voter
import ru.wiktuar.testkotlin.entities.test.Result
import ru.wiktuar.testkotlin.repository.LikeRepo
import ru.wiktuar.testkotlin.repository.ResultRepo
import ru.wiktuar.testkotlin.repository.VoterRepo
import ru.wiktuar.testkotlin.services.PollService
import ru.wiktuar.testkotlin.services.TestService
import java.security.Principal

@Service
class ResultService {
    @Autowired
    lateinit var resultRepo: ResultRepo

    @Autowired
    lateinit var testService: TestService

    @Autowired
    lateinit var pollService: PollService

    @Autowired
    private lateinit var likeRepo: LikeRepo

    @Autowired
    private lateinit var voterRepo: VoterRepo

    val listOfTestHeader = listOf("Имя", "Результат", "Процент", "Дата")
    val listOfPollHeader = listOf("Мнение", "Кол-во человек", "Процент")

    fun saveResult(result: Result) = resultRepo.save(result)

//  метод получения excel файла результатов теста
    fun getExcelTestResults(testId: Int, response: HttpServletResponse){
        val listExcelDto = resultRepo.getExcelResultDTO(testId)
        val testHeader = testService.getTestHeaderByTestId(testId)
        val workbook = XSSFWorkbook()
        val sheet = workbook.createSheet("Результаты теста")

        val cellStyle = createDefaultStyle(workbook)
        val headerStyle: XSSFCellStyle = cellStyle.copy()
        headerStyle.setFont(createFont(workbook, fontSize = 16, bold = true))

//      создание объединенной верхней строки
        mergeCells(sheet, listOfTestHeader.size, "Результаты теста \"$testHeader\"", headerStyle, 0, 0, 0, 3, 0)

//      создание заголовка для таблицы
        createHeaderRow(sheet, listOfTestHeader, headerStyle, 2)

        var rowNum = 2
        for (dto in listExcelDto){
            val row = sheet.createRow(rowNum++)
            getPropertyValues(dto, row, cellStyle)
        }

        sheet.setColumnWidth(0, 20*256)
        sheet.setColumnWidth(1, 18*256)
        sheet.setColumnWidth(2, 14*256)
        sheet.setColumnWidth(3, 20*256)

        val fileName = "MyFirstExcel.xlsx"
        response.setHeader("Content-disposition", "attachment; filename=$fileName");
        response.contentType = "APPLICATION/OCTET-STREAM"
        workbook.write(response.outputStream)
        response.outputStream.flush()
    }

    fun getExcelPollResults(pollId: Int, response: HttpServletResponse){
        val resultPollDTO = pollService.getResultPollDTO(pollId)
        val workbook = XSSFWorkbook()
        val sheet = workbook.createSheet("Результаты опроса")
        val cellStyle = createDefaultStyle(workbook)
        val headerStyle = cellStyle.copy()
        val totalStyle = cellStyle.copy()
        headerStyle.setFont(createFont(workbook, fontSize = 16, bold = true))
        totalStyle.setFont(createFont(workbook, italic = true))

//      создание объединенной верхней строки
        mergeCells(sheet, listOfPollHeader.size, "Результаты опроса \"${resultPollDTO.header}\"", headerStyle, 0, 0, 0, 2, 0)

//      создание заголовка для таблицы
        createHeaderRow(sheet, listOfPollHeader, headerStyle, 3)

        resultPollDTO.surveys.forEach {
            mergeCells(sheet, listOfPollHeader.size, it.topic, headerStyle, sheet.lastRowNum+1, sheet.lastRowNum+1, 0, 2, sheet.lastRowNum+1)
            it.opinions.forEach {
                val row1 = sheet.createRow(sheet.lastRowNum + 1)
                getPropertyValues(it.getExcelDTO(), row1, cellStyle)
            }
        }

        createRowTotalVoters(sheet, arrayOf("Всего проголосовало", resultPollDTO.countVoters.toString()), totalStyle, sheet.lastRowNum+1, sheet.lastRowNum+1, 1, 2, sheet.lastRowNum+1)

        sheet.setColumnWidth(0, 60*256)
        sheet.setColumnWidth(1, 20*256)
        sheet.setColumnWidth(2, 14*256)

        val mergedRegions = sheet.mergedRegions
        mergedRegions.forEach {
            RegionUtil.setBorderTop(BorderStyle.THIN, it, sheet);
            RegionUtil.setBorderLeft(BorderStyle.THIN, it, sheet);
            RegionUtil.setBorderRight(BorderStyle.THIN, it, sheet);
            RegionUtil.setBorderBottom(BorderStyle.THIN, it, sheet);
        }

        val fileName = "234.xlsx"
        response.setHeader("Content-disposition", "attachment; filename=$fileName");
        response.contentType = "APPLICATION/OCTET-STREAM"
        workbook.write(response.outputStream)
        response.outputStream.flush()
    }

    @Transactional
    fun savePollResult(data: Map<Int, Int>, principal: Principal) {
        data.forEach { (k, v) ->
            run {
                if (k == 0) {
                    val voter = Voter()
                    voter.login = principal.name
                    voter.pollId = v
                    voterRepo.save(voter)
                } else {
                    val like = Like()
                    like.like = v
                    like.survey = k
                    likeRepo.save(like)
                }
            }
        }
    }
}