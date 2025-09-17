package ru.wiktuar.testkotlin.config

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import jakarta.servlet.http.HttpSession
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.savedrequest.SavedRequest
import java.io.IOException

class CustomAuthenticationSuccessHandler : AuthenticationSuccessHandler {

    private val objectMapper: ObjectMapper = ObjectMapper();

    @Throws(IOException::class, ServletException::class)
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val session: HttpSession = request.session;
        var targetUrl = ""
        if (session.getAttribute("SPRING_SECURITY_SAVED_REQUEST") != null) {
            targetUrl = (session.getAttribute("SPRING_SECURITY_SAVED_REQUEST") as SavedRequest).redirectUrl
        } else {
//            targetUrl = "http://192.168.114.130:8040/sbi/departments"
            targetUrl = "http://localhost:8040/sbi/departments"
        }

        println("authentication success")
        response.status = 200;
        response.outputStream
            .println(objectMapper.writeValueAsString(targetUrl))
    }
}