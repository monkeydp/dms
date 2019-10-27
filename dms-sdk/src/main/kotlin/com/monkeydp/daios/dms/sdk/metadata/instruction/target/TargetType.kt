package com.monkeydp.daios.dms.sdk.metadata.instruction.target

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.monkeydp.daios.dms.sdk.contract.Enumx
import com.monkeydp.daios.dms.sdk.dm.DmImplRegistry
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.TargetType.TargetTypeDeserializer

/**
 * @author iPotato
 * @date 2019/10/25
 */
@JsonDeserialize(using = TargetTypeDeserializer::class)
interface TargetType<E> : Enumx<E>
        where E : TargetType<E>, E : Enum<E> {
    
    class TargetTypeDeserializer : JsonDeserializer<TargetType<*>>() {
        override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): TargetType<*> {
            val name = p?.getValueAsString()!!
            return DmImplRegistry.getTargetType(name)
        }
    }
}