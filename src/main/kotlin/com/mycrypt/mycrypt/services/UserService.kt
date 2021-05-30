package com.mycrypt.mycrypt.services

import com.mycrypt.mycrypt.dtos.RegistrationRequest
import com.mycrypt.mycrypt.models.User

interface UserService {
    fun register(registrationRequest: RegistrationRequest): User?
}
