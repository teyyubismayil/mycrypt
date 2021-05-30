package com.mycrypt.mycrypt.dtos

import com.mycrypt.mycrypt.models.EncryptionKey

data class EncryptionResponse(
    val id: String,
    val encryptionKey: EncryptionKey
)
