package ru.wiktuar.testkotlin.controllers

import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import ru.wiktuar.testkotlin.entities.Department
import ru.wiktuar.testkotlin.services.DepService
import java.security.Principal

@Controller
class PageController {

    private val logger = LoggerFactory.getLogger(PageController::class.java)

    @Autowired
    private lateinit var depService: DepService

    @GetMapping
    fun getIndexPage(): String = "index"

//  получение списка всех отделов
    @GetMapping(value = ["/sbi/departments", "/admin/departments"])
    open fun fetAllDepartments( model: Model, request:
                               HttpServletRequest,
                                principal: Principal): String {
        val depList: List<Department> = depService.getAllDepartments();
        model.addAttribute("departments", depList);
        return if (request.requestURL.toString().contains("/admin")) "admin/departments" else "sbi/departments";
    }

    //    метод, помогающий обраотать случай, когда пользователь аутентифицировался, перешел куда, и ему нужно нажать "Назад"
    //    и при этом не попасть снова на форму логирования
    @GetMapping("/login")
    fun showLoginForm(
        model: Model,
        request: HttpServletRequest
    ): String {
        val authentication = SecurityContextHolder.getContext().authentication
        if (authentication == null || authentication is AnonymousAuthenticationToken) {
            return "/"
        }
        return "redirect:/sbi/departments"
    }
}