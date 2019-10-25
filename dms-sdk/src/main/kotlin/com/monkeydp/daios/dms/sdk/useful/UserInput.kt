package com.monkeydp.daios.dms.sdk.useful

import com.monkeydp.tools.util.JsonUtil
import org.jetbrains.annotations.TestOnly
import javax.persistence.AttributeConverter
import javax.persistence.Convert

/**
 * Parameters entered by the user
 *
 * @author iPotato
 * @date 2019/10/20
 */
@Convert(converter = UserInput.StringConverter::class)
class UserInput() : HashMap<String, String>() {
    
    @TestOnly
    internal constructor(map: Map<String, String>) : this() {
        this.putAll(map)
    }
    
    inline fun <reified T> convertTo(): T {
        return JsonUtil.convertTo<T>(this)
    }
    
    class StringConverter : AttributeConverter<UserInput, String> {
        override fun convertToDatabaseColumn(attribute: UserInput): String = JsonUtil.toString(attribute)
        override fun convertToEntityAttribute(dbData: String): UserInput = JsonUtil.toObject<UserInput>(dbData)
    }
}