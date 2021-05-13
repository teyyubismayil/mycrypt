package com.mycrypt.mycrypt.models

data class DecryptionResponse(
    val fileName: String?,
    val extension: String?,
    val decryptionKey: DecryptionKey,
) {
    constructor(encryption: Encryption): this(
        fileName = encryption.fileName,
        extension = encryption.extension,
        decryptionKey = DecryptionKey(encryption.keyD, encryption.keyN),
    )
}
