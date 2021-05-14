package com.mycrypt.mycrypt.repositories

import com.mycrypt.mycrypt.models.Encryption
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface EncryptionRepository: MongoRepository<Encryption, String>
