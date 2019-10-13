package com.monkeydp.daios.dms.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 * @author iPotato
 * @date 2019/10/12
 */
@Component
@ConfigurationProperties("dms")
class DmsConfig {

    val module: Module = Module()

    class Module {
        lateinit var dirPath: String
    }
}