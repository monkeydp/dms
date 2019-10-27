package com.monkeydp.daios.dms.sdk.useful

import com.monkeydp.daios.dms.sdk.metadata.form.CpForm
import com.monkeydp.tools.util.FieldUtil
import com.monkeydp.tools.util.JsonUtil
import org.jetbrains.annotations.TestOnly
import javax.persistence.AttributeConverter

/**
 * Parameters entered by the user
 *
 * @author iPotato
 * @date 2019/10/20
 */
class UserInput() : HashMap<String, String>() {
    
    @TestOnly
    constructor(cpForm: CpForm) : this() {
        FieldUtil.getFields(cpForm).forEach { this[it.name] = FieldUtil.getNotnullValue(cpForm, it) }
    }
    
    inline fun <reified T> convertTo(): T {
        return JsonUtil.convertTo<T>(this)
    }
    
    class StringConverter : AttributeConverter<UserInput, String> {
        override fun convertToDatabaseColumn(attribute: UserInput): String = JsonUtil.toString(attribute)
        override fun convertToEntityAttribute(dbData: String): UserInput = JsonUtil.toObject<UserInput>(dbData)
    }
}