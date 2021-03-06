package com.monkeydp.daios.dms.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleAbstractTypeResolver
import com.fasterxml.jackson.databind.module.SimpleModule
import com.monkeydp.daios.dms.sdk.instruction.Instruction
import com.monkeydp.daios.dms.sdk.instruction.StdInstr
import com.monkeydp.daios.dms.sdk.ui.menu.MenuItem
import com.monkeydp.daios.dms.sdk.ui.menu.StdMi
import com.monkeydp.daios.dms.sdk.ui.node.Node
import com.monkeydp.daios.dms.sdk.ui.node.StdNode
import com.monkeydp.tools.config.ToolsKodeinModules
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder


/**
 * @author iPotato
 * @date 2019/10/21
 */
@Configuration
class DmsJackson {

    /**
     * Auto register module jackson-module-kotlin,
     * it's the module that adds support for serialization/deserialization of Kotlin classes and data classes.
     */
    @Autowired
    fun objectMapper(builder: Jackson2ObjectMapperBuilder) = builder.build<ObjectMapper>()

    @Autowired
    fun configureObjectMapper(objectMapper: ObjectMapper) {
        objectMapper.registerModule(object : SimpleModule("CustomModule") {
            override fun setupModule(context: SetupContext) {
                val nodeResolver = SimpleAbstractTypeResolver()
                        .addMapping(Node::class.java, StdNode::class.java)
                        .addMapping(MenuItem::class.java, StdMi::class.java)
                        .addMapping(Instruction::class.java, StdInstr::class.java)
                context.addAbstractTypeResolver(nodeResolver)
            }
        })
        ToolsKodeinModules.addModule("jacksonModule") {
            bind<ObjectMapper>(overrides = true) with singleton { objectMapper }
        }
    }
}