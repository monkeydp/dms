package com.monkeydp.daios.dms.sdk.instruction.action

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.monkeydp.daios.dms.sdk.share.kodein.DmKodeinHelper
import com.monkeydp.daios.dms.sdk.instruction.action.Action.ActionDeserializer
import com.monkeydp.tools.enumx.Enumx
import com.monkeydp.tools.ext.kotlin.toUpperCamelCase
import io.swagger.annotations.ApiModelProperty

/**
 * @author iPotato
 * @date 2019/10/25
 */
@JsonDeserialize(using = ActionDeserializer::class)
interface Action<E> : Enumx<E>
        where E : Action<E>, E : Enum<E> {
    
    val defaultFullName
        @ApiModelProperty(hidden = true)
        get() = asEnum().name.toUpperCamelCase()
    val fullName
        @ApiModelProperty(hidden = true)
        get() = defaultFullName
    
    class ActionDeserializer : JsonDeserializer<Action<*>>() {
        override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): Action<*> {
            val name = p?.getValueAsString()!!
            return DmKodeinHelper.findEnum(name)
        }
    }
}