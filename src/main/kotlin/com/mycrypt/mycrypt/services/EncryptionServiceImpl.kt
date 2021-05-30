package com.mycrypt.mycrypt.services

import com.mycrypt.mycrypt.models.Encryption
import com.mycrypt.mycrypt.repositories.EncryptionRepository
import org.springframework.stereotype.Service

@Service
class EncryptionServiceImpl(
    private val encryptionRepository: EncryptionRepository
): EncryptionService {

    override fun find(id: String): Encryption? {
        return encryptionRepository.findById(id).orElse(null)
    }

    override fun save(encryption: Encryption): Encryption {
        return encryptionRepository.save(encryption)
    }
}
