package ru.wiktuar.testkotlin.controllers.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import ru.wiktuar.testkotlin.entities.Department
import ru.wiktuar.testkotlin.services.DepService

@Controller
class EditDepController {

    @Autowired
    private lateinit var depService: DepService

    //   метод сохранения банковского отдела
    @PostMapping("/savedepartment")
    fun saeDepartment(@ModelAttribute department: Department): String {
        println(department.id == 0)
        depService.save(department)
        println(department.id == 0)
        println(department.id)
        return "redirect:/admin/departments"
    }

    //  метод удаления банковского отдела
    @GetMapping("/admin/departments/delete/{id}")
    open fun deleteDepartment(@PathVariable("id") id: Int): String{
        depService.deleteDepartment(id);
        return "redirect:/admin/departments";
    }
}