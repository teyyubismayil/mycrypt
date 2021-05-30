package com.mycrypt.mycrypt.security

import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthenticationSuccessHandlerImpl: AuthenticationSuccessHandler {

    override fun onAuthenticationSuccess(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authentication: Authentication?
    ) {
        val username: String? = authentication?.name
        if (username != null) {
            request?.session?.setAttribute("user", username)
        }
        response?.sendRedirect("/home")
    }
}
