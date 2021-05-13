package com.mycrypt.mycrypt.models

data class EncryptionRequest(
    val password: String?,
    val extension: String?,
    val fileName: String?,
)
