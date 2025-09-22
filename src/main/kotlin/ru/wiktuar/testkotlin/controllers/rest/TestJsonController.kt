package ru.wiktuar.testkotlin.controllers.rest

import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.wiktuar.testkotlin.dto.TestDTO
import ru.wiktuar.testkotlin.entities.test.Test
import ru.wiktuar.testkotlin.services.DepService
import ru.wiktuar.testkotlin.services.TestService

@RestController
class TestJsonController {

    @Autowired
    lateinit var testService: TestService

    @PostMapping("/testjson")
    fun sendData(@RequestBody testDTO: TestDTO): String {
        testService.saveTest(testDTO);
        return "/admin/courses/${testDTO.depId}"
    }

//  метод получения теста для его обновления
    @GetMapping(value = ["/admin/update/test/{id}", "/sbi/gettest/{id}"])
    fun getTestForUpdate(@PathVariable("id") id: Int,
                         request: HttpServletRequest): ResponseEntity<Test> {
        val test = testService.getTest(id)
        return ResponseEntity.ok()
            .header("X-Total-Count", test.questions.size.toString())
            .body(test)
    }
}