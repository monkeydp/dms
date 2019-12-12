package com.monkeydp.daios.dms.sdk.useful

import com.monkeydp.tools.ext.kotlin.toJson
import com.monkeydp.tools.ext.kotlin.toPropMapX
import com.monkeydp.tools.util.JsonUtil
import javax.persistence.AttributeConverter

/**
 * Parameters entered by the user
 *
 * @author iPotato
 * @date 2019/10/20
 */
class UserInput() : HashMap<String, Any>() {
    
    constructor(any: Any) : this() {
        this.putAll(any.toPropMapX<String, Any>())
    }
    
    class StringConverter : AttributeConverter<UserInput, String> {
        override fun convertToDatabaseColumn(attribute: UserInput): String = attribute.toJson()
        override fun convertToEntityAttribute(dbData: String): UserInput = JsonUtil.toObject<UserInput>(dbData)
    }
}