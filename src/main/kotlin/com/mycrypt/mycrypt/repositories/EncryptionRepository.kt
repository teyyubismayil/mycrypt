package com.mycrypt.mycrypt.repositories

import com.mycrypt.mycrypt.models.Encryption
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EncryptionRepository: CrudRepository<Encryption, Int>
