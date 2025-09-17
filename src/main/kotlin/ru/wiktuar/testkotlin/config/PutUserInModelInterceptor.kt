package ru.wiktuar.testkotlin.config

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView

class PutUserInModelInterceptor : HandlerInterceptor {
    override fun preHandle(
        aRequest: HttpServletRequest,
        aResponse: HttpServletResponse,
        aHandler: Any
    ): Boolean = true

    @Throws(Exception::class)
    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        aHandler: Any,
        modelAndView: ModelAndView?
    ) {
        if(modelAndView != null) {
            if(request.isUserInRole("ROLE_ADMIN"))
                modelAndView.addObject("isAdmin", "data");
        }
    }
}