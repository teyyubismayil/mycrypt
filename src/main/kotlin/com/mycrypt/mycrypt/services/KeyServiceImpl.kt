package com.mycrypt.mycrypt.services

import com.mycrypt.mycrypt.models.DecryptionKey
import com.mycrypt.mycrypt.models.EncryptionKey
import com.mycrypt.mycrypt.models.Key
import com.mycrypt.mycrypt.utils.*
import org.springframework.stereotype.Service

@Service
class KeyServiceImpl: KeyService {

    override fun generateKey(): Key {
        val (p1, p2) = generateTwoRandomPrimeNumbers(1000, 10000)
        val n = p1 * p2
        val f = euler(p1, p2)
        val e = generateRandomExponent(f)
        val d = inverse(e, f)
        return Key(EncryptionKey(e, n), DecryptionKey(d, n))
    }
}
