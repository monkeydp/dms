package com.monkeydp.daios.dms.sdk.metadata.instruction.target

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.monkeydp.daios.dms.sdk.contract.Enumx
import com.monkeydp.daios.dms.sdk.dm.DmImplRegistry
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.Target.TargetDeserializer

/**
 * @author iPotato
 * @date 2019/10/25
 */
@JsonDeserialize(using = TargetDeserializer::class)
interface Target<E> : Enumx<E>
        where E : Target<E>, E : Enum<E> {
    
    class TargetDeserializer : JsonDeserializer<Target<*>>() {
        override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): Target<*> {
            val name = p?.getValueAsString()!!
            return DmImplRegistry.getEnum(name)
        }
    }
}