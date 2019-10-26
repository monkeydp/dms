package com.monkeydp.daios.dms.sdk.metadata.instruction.action

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.monkeydp.daios.dms.sdk.contract.Enumx
import com.monkeydp.daios.dms.sdk.dm.ImplContext

/**
 * @author iPotato
 * @date 2019/10/25
 */
@JsonDeserialize(using = ActionType.ActionTypeDeserializer::class)
interface ActionType<E> : Enumx<E>
        where E : ActionType<E>, E : Enum<E> {
    
    class ActionTypeDeserializer : JsonDeserializer<ActionType<*>>() {
        override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): ActionType<*> {
            return ImplContext.actionTypeMap.get(p?.getValueAsString())!!
        }
    }
}