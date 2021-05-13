package com.mycrypt.mycrypt.models

data class Key(
    val encryptionKey: EncryptionKey,
    val decryptionKey: DecryptionKey
)

data class EncryptionKey(
    val e: Int,
    val n: Int
)

data class DecryptionKey(
    val d: Int,
    val n: Int
)
