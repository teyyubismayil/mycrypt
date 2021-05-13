package com.mycrypt.mycrypt.controllers.rest

import com.mycrypt.mycrypt.models.DecryptionRequest
import com.mycrypt.mycrypt.models.DecryptionResponse
import com.mycrypt.mycrypt.models.User
import com.mycrypt.mycrypt.services.EncryptionService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/decryption")
class DecryptionController(
    private val encryptionService: EncryptionService
) {

    @PostMapping
    fun decryption(
        @RequestBody decryptionRequest: DecryptionRequest
    ): ResponseEntity<Any> {
        val user: User? = null
        if (user == null && decryptionRequest.password.isNullOrBlank()) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("No password provided")
        }
        val encryption = encryptionService.find(decryptionRequest.id)
            ?: return ResponseEntity
                .notFound()
                .build()

        if (decryptionRequest.password != encryption.password) {
            return ResponseEntity
                .badRequest()
                .body("Incorrect password")
        }

        return ResponseEntity
            .ok()
            .body(DecryptionResponse(encryption))
    }
}
