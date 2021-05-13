package com.mycrypt.mycrypt.controllers.rest

import com.mycrypt.mycrypt.models.Encryption
import com.mycrypt.mycrypt.models.EncryptionRequest
import com.mycrypt.mycrypt.models.EncryptionResponse
import com.mycrypt.mycrypt.models.User
import com.mycrypt.mycrypt.services.EncryptionService
import com.mycrypt.mycrypt.services.KeyService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/encryption")
class EncryptionController(
    private val keyService: KeyService,
    private val encryptionService: EncryptionService
) {

    @PostMapping
    fun encryption(
        @RequestBody encryptionRequest: EncryptionRequest
    ): ResponseEntity<Any> {
        val user: User? = null
        if (user == null && encryptionRequest.password.isNullOrBlank()) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("No password provided")
        }

        val (encryptionKey, decryptionKey) = keyService.generateKey()
        val encryption = encryptionService.save(Encryption(user, encryptionRequest, decryptionKey))
        return ResponseEntity
            .ok()
            .body(EncryptionResponse(encryption.id, encryptionKey))
    }
}
