package com.mycrypt.mycrypt.controllers.rest

import com.mycrypt.mycrypt.models.Encryption
import com.mycrypt.mycrypt.dtos.EncryptionRequest
import com.mycrypt.mycrypt.dtos.EncryptionResponse
import com.mycrypt.mycrypt.models.User
import com.mycrypt.mycrypt.services.EncryptionService
import com.mycrypt.mycrypt.services.KeyService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/api/encryption")
class EncryptionController(
    private val keyService: KeyService,
    private val encryptionService: EncryptionService
) {

    @PostMapping
    fun encryption(
        @RequestBody encryptionRequest: EncryptionRequest,
        principal: Principal?
    ): ResponseEntity<Any> {
        val user: User? = (principal as? UsernamePasswordAuthenticationToken)?.principal as? User
        if (user == null && encryptionRequest.password.isNullOrBlank()) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("No password provided")
        }

        val (encryptionKey, decryptionKey) = keyService.generateKey()
        val encryption = encryptionService.save(Encryption(user, encryptionRequest, decryptionKey))
        return ResponseEntity
            .ok()
            .body(EncryptionResponse(encryption.id.toString(), encryptionKey))
    }
}
