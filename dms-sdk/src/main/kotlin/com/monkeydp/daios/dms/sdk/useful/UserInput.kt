package com.monkeydp.daios.dms.sdk.useful

import com.monkeydp.tools.util.JsonUtil
import javax.persistence.AttributeConverter
import kotlin.reflect.KClass

/**
 * Parameters entered by the user
 *
 * @author iPotato
 * @date 2019/10/20
 */
class UserInput() : HashMap<String, String>() {
    
    inline fun <reified T> convertTo() = JsonUtil.convertTo<T>(this)
    fun <T : Any> convertTo(clazz: KClass<T>) = JsonUtil.convertTo<T>(this, clazz.java)
    
    class StringConverter : AttributeConverter<UserInput, String> {
        override fun convertToDatabaseColumn(attribute: UserInput): String = JsonUtil.toString(attribute)
        override fun convertToEntityAttribute(dbData: String): UserInput = JsonUtil.toObject<UserInput>(dbData)
    }
}