package com.mycrypt.mycrypt.models

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "Encryptions")
data class Encryption(
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int,
    val regDate: LocalDateTime?,
    //val user: User?,
    val password: String?,
    val extension: String?,
    val fileName: String?,
    @Column(nullable = false)
    val keyD: Int,
    @Column(nullable = false)
    val keyN: Int
) {
    constructor(
        user: User?,
        encryptionRequest: EncryptionRequest,
        decryptionKey: DecryptionKey
    ): this(
        id = 0,
        regDate = LocalDateTime.now(),
        //user = user,
        password = encryptionRequest.password,
        extension = encryptionRequest.extension,
        fileName = encryptionRequest.fileName,
        keyD = decryptionKey.d,
        keyN = decryptionKey.n
    )
    constructor(): this(
        id = 0,
        regDate = null,
        password = null,
        extension = null,
        fileName = null,
        keyD = 0,
        keyN = 0
    )
}
