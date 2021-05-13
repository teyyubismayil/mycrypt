package com.mycrypt.mycrypt.models

import java.time.LocalDateTime

data class User(
    val id: Int,
    val regDate: LocalDateTime?,
    val userName: String,
    val password: String,
    val email: String
)
