package com.mycrypt.mycrypt.controllers.web

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/tools")
class ToolsController {

    @GetMapping
    fun tools() = "tools"

    @GetMapping("/fileEncryptor")
    fun encryptFile()= "fileEncryptor"

    @GetMapping("/fileDecryptor")
    fun decryptFile()= "fileDecryptor"
}
