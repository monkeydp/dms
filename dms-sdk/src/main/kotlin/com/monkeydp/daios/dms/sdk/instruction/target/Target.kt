package com.monkeydp.daios.dms.sdk.instruction.target

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.monkeydp.daios.dms.sdk.SdkImplRegistry
import com.monkeydp.daios.dms.sdk.enumx.Enumx
import com.monkeydp.daios.dms.sdk.enumx.SdkEnumContract
import com.monkeydp.daios.dms.sdk.instruction.target.Target.TargetDeserializer
import com.monkeydp.tools.ext.toUpperCamelCase
import io.swagger.annotations.ApiModelProperty

/**
 * @author iPotato
 * @date 2019/10/25
 */
@SdkEnumContract
@JsonDeserialize(using = TargetDeserializer::class)
interface Target<E> : Enumx<E>
        where E : Target<E>, E : Enum<E> {
    
    val defaultFullName
        @ApiModelProperty(hidden = true)
        get() = asEnum().name.toUpperCamelCase()
    val fullName
        @ApiModelProperty(hidden = true)
        get() = defaultFullName
    
    class TargetDeserializer : JsonDeserializer<Target<*>>() {
        override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): Target<*> {
            val name = p?.getValueAsString()!!
            return SdkImplRegistry.getEnumByDsThreadLocal(name)
        }
    }
}