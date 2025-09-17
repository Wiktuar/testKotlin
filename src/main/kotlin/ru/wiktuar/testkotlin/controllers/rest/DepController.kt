package ru.wiktuar.testkotlin.controllers.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import ru.wiktuar.testkotlin.entities.Department
import ru.wiktuar.testkotlin.services.DepService

@RestController
class DepController {

    @Autowired
    private lateinit var depService: DepService

    //  метод получения банковского отдела по его ID
    @GetMapping("/admin/departments/{id}")
    fun getDepartmentById(@PathVariable("id") id: Int): ResponseEntity<Department?> {
        val department: Department? = depService.getDepartmentById(id)
        return ResponseEntity<Department?>(department, HttpStatus.OK)
    }
}