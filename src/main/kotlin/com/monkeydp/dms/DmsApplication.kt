package com.monkeydp.dms

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DmsApplication

fun main(args: Array<String>) {
    runApplication<DmsApplication>(*args)
}
