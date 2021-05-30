package com.mycrypt.mycrypt.services

import com.mycrypt.mycrypt.repositories.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
    private val userRepository: UserRepository
): UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        if (username != null) {
            return userRepository.findByUsername(username) ?: throw UsernameNotFoundException(username)
        }
        throw UsernameNotFoundException(username)
    }
}
