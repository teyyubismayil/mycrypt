package com.mycrypt.mycrypt

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MycryptApplication

fun main(args: Array<String>) {
    runApplication<MycryptApplication>(*args)
}
