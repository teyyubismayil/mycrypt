package com.mycrypt.mycrypt.dtos

data class EncryptionRequest(
    val password: String?,
    val extension: String?,
    val fileName: String?,
)
