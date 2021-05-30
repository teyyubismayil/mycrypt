package com.mycrypt.mycrypt.configs

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.AuthenticationSuccessHandler

@Configuration
@EnableWebSecurity
class SecurityConfig(
    @Qualifier("userDetailsServiceImpl")
    private val userDetailsService: UserDetailsService,
    private val authenticationSuccessHandler: AuthenticationSuccessHandler
) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userDetailsService)
    }

    override fun configure(http: HttpSecurity?) {
        http
            ?.csrf()
                ?.disable()
            ?.authorizeRequests()
                ?.anyRequest()
                    ?.permitAll()
            ?.and()
            ?.formLogin()
                ?.loginPage("/signIn")
                ?.loginProcessingUrl("/authenticate")
                ?.successHandler(authenticationSuccessHandler)
    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
}
