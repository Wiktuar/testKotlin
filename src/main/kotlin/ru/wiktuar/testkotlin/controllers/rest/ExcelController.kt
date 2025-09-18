package ru.wiktuar.testkotlin.controllers.rest

import jakarta.servlet.http.HttpServletResponse
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFCellStyle
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import ru.wiktuar.testkotlin.services.resultServices.ResultService
import kotlin.reflect.KProperty
import kotlin.reflect.full.memberProperties


@RestController
class ExcelController {

    @Autowired
    lateinit var resultService: ResultService

    @GetMapping("/convert_test_to_excel/{id}")
    fun getTestExcelFile(@PathVariable("id") testId: Int,
                     response: HttpServletResponse): String {
        resultService.getExcelTestResults(testId, response)
        return "ok"
    }

    @GetMapping("/convert_poll_to_excel/{id}")
    fun getPollExcelFile(@PathVariable("id") pollId: Int,
                         response: HttpServletResponse) {
        resultService.getExcelPollResults(pollId, response)
//        return ResponseEntity("Файл загружен", HttpStatus.OK)
    }
}
