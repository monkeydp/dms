package com.monkeydp.daios.dms.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder


/**
 * @author iPotato
 * @date 2019/10/21
 */
@Configuration
class JacksonConfig {

    /**
     * Auto register module jackson-module-kotlin,
     * it's the module that adds support for serialization/deserialization of Kotlin classes and data classes.
     */
    @Autowired
    fun objectMapper(builder: Jackson2ObjectMapperBuilder): ObjectMapper {
        return builder.build()
    }
}