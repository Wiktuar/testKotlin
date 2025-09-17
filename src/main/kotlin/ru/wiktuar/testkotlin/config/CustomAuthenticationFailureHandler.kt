package ru.wiktuar.testkotlin.config

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import java.io.IOException

class CustomAuthenticationFailureHandler : AuthenticationFailureHandler {
    private val objectMapper: ObjectMapper = ObjectMapper();

    @Throws(IOException::class, ServletException::class)
    public override fun onAuthenticationFailure(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        e: AuthenticationException
    ) {
        response.status = HttpStatus.UNAUTHORIZED.value()
        response.contentType = "application/json; charset=utf-8"
        response.writer.println(objectMapper.writeValueAsString(e.message))
    }
}