package com.mycrypt.mycrypt.services

import com.mycrypt.mycrypt.models.Key

interface KeyService {
    fun generateKey(): Key
}
