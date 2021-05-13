package com.mycrypt.mycrypt.services

import com.mycrypt.mycrypt.models.Encryption

interface EncryptionService {
    fun find(id: Int): Encryption?
    fun save(encryption: Encryption): Encryption
}
