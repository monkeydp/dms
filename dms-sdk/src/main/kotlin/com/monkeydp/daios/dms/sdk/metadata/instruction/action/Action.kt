package com.monkeydp.daios.dms.sdk.metadata.instruction.action

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.monkeydp.daios.dms.sdk.dm.DmImplRegistry
import com.monkeydp.daios.dms.sdk.enumeration.Enumx
import com.monkeydp.daios.dms.sdk.metadata.instruction.action.Action.ActionDeserializer
import com.monkeydp.tools.ext.toUpperCamelCase

/**
 * @author iPotato
 * @date 2019/10/25
 */
@JsonDeserialize(using = ActionDeserializer::class)
interface Action<E> : Enumx<E>
        where E : Action<E>, E : Enum<E> {
    
    val defaultFullName
        get() = asEnum().name.toUpperCamelCase()
    val fullName
        get() = defaultFullName
    
    class ActionDeserializer : JsonDeserializer<Action<*>>() {
        override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): Action<*> {
            val name = p?.getValueAsString()!!
            return DmImplRegistry.getEnumByDsThreadLocal(name)
        }
    }
}