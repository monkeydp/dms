package com.monkeydp.daios.dms.sdk.ui.icon

import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.monkeydp.daios.dms.sdk.dm.dmKodeinRepo
import com.monkeydp.daios.dms.sdk.dm.findEnum
import com.monkeydp.daios.dms.sdk.ui.icon.Icon.IconDeserializer
import com.monkeydp.tools.enumx.Enumx
import com.monkeydp.tools.ext.kotlin.toPropMap


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
        const val DEFAULT_COLOR = "#979BA4" // grey
    }
    
    class IconDeserializer : JsonDeserializer<Icon<*>>() {
        override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): Icon<*> {
            val jsonNode = p?.readValueAsTree<JsonNode>()!!
            val name = jsonNode.get(Enum<*>::name.name).asText()
            return dmKodeinRepo.findEnum(enumName = name)
        }
    }
    
    @JsonValue
    fun jsonValue(): Map<String, Any?> {
        val map = mutableMapOf<String, Any?>()
        map.putAll(toPropMap({ it.name }))
        map.remove(Enum<*>::ordinal.name)
        return map
    }
}