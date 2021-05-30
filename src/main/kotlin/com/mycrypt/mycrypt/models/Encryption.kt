package com.mycrypt.mycrypt.models

import com.mycrypt.mycrypt.dtos.EncryptionRequest
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class Encryption(
    @Id
    val id: ObjectId = ObjectId.get(),
    val regDate: LocalDateTime = LocalDateTime.now(),
    val user: User?,
    val password: String?,
    val extension: String?,
    val fileName: String?,
    val keyD: Int,
    val keyN: Int
) {
    constructor(
        user: User?,
        encryptionRequest: EncryptionRequest,
        decryptionKey: DecryptionKey
    ): this(
        regDate = LocalDateTime.now(),
        user = user,
        password = encryptionRequest.password,
        extension = encryptionRequest.extension,
        fileName = encryptionRequest.fileName,
        keyD = decryptionKey.d,
        keyN = decryptionKey.n
    )
}
