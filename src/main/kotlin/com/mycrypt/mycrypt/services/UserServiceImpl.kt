package com.mycrypt.mycrypt.services

import com.mycrypt.mycrypt.dtos.RegistrationRequest
import com.mycrypt.mycrypt.models.User
import com.mycrypt.mycrypt.repositories.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
): UserService {

    override fun register(registrationRequest: RegistrationRequest): User? {
        return try {
            userRepository.save(User(registrationRequest, passwordEncoder))
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
