package com.monkeydp.daios.dms.sdk.dm

import com.monkeydp.daios.dms.sdk.annot.SdkEnum
import com.monkeydp.daios.dms.sdk.config.dmKodeinMap
import com.monkeydp.daios.dms.sdk.config.getDmKodein
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.datasource.DsVersion
import com.monkeydp.daios.dms.sdk.request.RequestContext
import com.monkeydp.tools.enumx.Enumx
import com.monkeydp.tools.enumx.NullEnumx
import com.monkeydp.tools.ext.*
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
    
    tailrec fun <E : Enumx<out E>, K : KClass<out E>> K.recurFindEnumOrNull(
            enumName: String,
            caseSensitive: Boolean = false
    ): E? {
        var enum = valueOfOrNullX(enumName, caseSensitive)
        if (enum != null) return enum
        
        val parent = findAnnotation<SdkEnum>()?.parent
        if (parent == null || parent == NullEnumx::class) return null
        
        @Suppress("UNCHECKED_CAST")
        return (parent as K).recurFindEnumOrNull(enumName, caseSensitive)
    }
    
    inline fun <reified T : Any> findImpl(
            datasource: Datasource = defaultDs,
            tag: KodeinTag? = null
    ): T {
        val dmKodein = getDmKodein(datasource)
        val instance: T by dmKodein.instance(tag)
        return instance
    }
    
    inline fun <reified T : Any> finalAllImpls(tag: KodeinTag? = null): List<T> =
            dmKodeinMap.keys.map { findImpl<T>(it, tag) }.toList()
    
    fun findDsVersion(datasource: Datasource, dsVersionId: String): DsVersion<*> {
        val enumKClass = findImpl<KClass<out DsVersion<*>>>(datasource)
        return enumKClass.enumSet().matchOne { it.id == dsVersionId }
    }
}