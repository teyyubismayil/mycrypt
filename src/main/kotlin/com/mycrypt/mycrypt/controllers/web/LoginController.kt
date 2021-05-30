package com.mycrypt.mycrypt.controllers.web

import com.mycrypt.mycrypt.dtos.RegistrationRequest
import com.mycrypt.mycrypt.services.UserService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpSession

@Controller
@RequestMapping("/")
class LoginController(
    private val userService: UserService
) {

    @GetMapping("/login")
    fun login(authentication: Authentication?): String {
        return "login"
    }

    @PostMapping("/register")
    fun register(
        @RequestBody registrationRequest: RegistrationRequest,
        httpSession: HttpSession
    ): String {
        val user = userService.register(registrationRequest)
        if(user != null) {
            val authReq = UsernamePasswordAuthenticationToken(user.username, registrationRequest.password)
            SecurityContextHolder.getContext().authentication = authReq
            httpSession.setAttribute("user", user.username)
        }
        return "home"
    }
}
