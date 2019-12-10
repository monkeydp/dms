package com.monkeydp.daios.dms.sdk.dm

import com.monkeydp.daios.dms.sdk.config.getDmKodein
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.main.SdkEnum
import com.monkeydp.daios.dms.sdk.request.RequestContext
import com.monkeydp.tools.enumx.Enumx
import com.monkeydp.tools.enumx.NullEnumx
import com.monkeydp.tools.ext.ierror
import com.monkeydp.tools.ext.transformEnumName
import com.monkeydp.tools.ext.valueOfOrNullX
import org.kodein.di.generic.instance
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation

/**
 * @author iPotato
 * @date 2019/12/10
 */
object DmHelper {
    
    val defaultDs get() = RequestContext.datasource
    
    /**
     * @param E enum contract
     */
    inline fun <E : Enumx<out E>, reified K : KClass<out E>> findEnum(
            enumName: String,
            caseSensitive: Boolean = false,
            datasource: Datasource = defaultDs
    ): E {
        val enumKClass: K = findImpl(datasource)
        
        val enumNameX = transformEnumName(enumName, caseSensitive)
        var enum = enumKClass.recurFindEnumOrNull(enumNameX)
        if (enum == null) ierror("No such enum named $enumNameX, datasource is $datasource")
        return enum
    }
    
    tailrec fun <E : Enumx<out E>> KClass<out E>.recurFindEnumOrNull(
            enumName: String,
            caseSensitive: Boolean = false
    ): E? {
        var enum = valueOfOrNullX(enumName, caseSensitive)
        if (enum != null) return enum
        
        val parent = findAnnotation<SdkEnum>()?.parent
        if (parent == null || parent == NullEnumx::class) return null
        
        @Suppress("UNCHECKED_CAST")
        return (parent as KClass<out E>).recurFindEnumOrNull(enumName, caseSensitive)
    }
    
    inline fun <reified T : Any> findImpl(datasource: Datasource = defaultDs): T {
        val dmKodein = getDmKodein(datasource)
        val api: T by dmKodein.instance()
        return api
    }
}