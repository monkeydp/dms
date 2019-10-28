package com.monkeydp.daios.dms.sdk.metadata.icon

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.monkeydp.daios.dms.sdk.contract.Enumx
import com.monkeydp.daios.dms.sdk.dm.DmImplRegistry
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon.IconDeserializer

/**
 * @author iPotato
 * @date 2019/10/25
 */
@JsonDeserialize(using = IconDeserializer::class)
interface Icon<E> : Enumx<E>
        where E : Icon<E>, E : Enum<E> {
    
    val namex: String
    
    /**
     * Hex
     */
    val color: String
    
    companion object {
        val DEFAULT_COLOR = "#979BA4" // grey
    }
    
    class IconDeserializer : JsonDeserializer<Icon<*>>() {
        override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): Icon<*> {
            val name = p?.getValueAsString()!!
            return DmImplRegistry.getIcon(name)
        }
    }
}