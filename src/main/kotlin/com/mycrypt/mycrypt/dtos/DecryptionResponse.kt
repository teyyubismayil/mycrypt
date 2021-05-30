package com.mycrypt.mycrypt.dtos

import com.mycrypt.mycrypt.models.DecryptionKey
import com.mycrypt.mycrypt.models.Encryption

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
