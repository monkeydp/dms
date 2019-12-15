package com.monkeydp.daios.dms.sdk.share.kodein

import com.monkeydp.daios.dms.sdk.annot.SdkEnum
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.datasource.DsVersion
import com.monkeydp.daios.dms.sdk.share.request.RequestContext
import com.monkeydp.tools.enumx.Enumx
import com.monkeydp.tools.ext.kotlin.enumSet
import com.monkeydp.tools.ext.kotlin.matchOne
import com.monkeydp.tools.ext.kotlin.transformEnumName
import com.monkeydp.tools.ext.main.ierror
import com.monkeydp.tools.ext.main.valueOfOrNullX
import org.kodein.di.generic.instance
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation

/**
 * @author iPotato
 * @date 2019/12/10
 */
object DmShareKodeinHelper {
    
    val defaultDs get() = RequestContext.datasource
    
    /**
     * @param E enum contract
     */
    inline fun <E : Enumx<out E>, reified K : KClass<out E>> findEnum(
            enumName: String,
            caseSensitive: Boolean = false,
            datasource: Datasource = defaultDs
    ): E {
        val enumKClass: K =
                findImpl(datasource)
        
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
        if (parent == null || parent == Nothing::class) return null
        
        @Suppress("UNCHECKED_CAST")
        return (parent as K).recurFindEnumOrNull(enumName, caseSensitive)
    }
    
    inline fun <reified T : Any> findImpl(
            datasource: Datasource = defaultDs,
            tag: Any? = null
    ): T {
        val dmShareKodein = getDmShareKodein(datasource)
        val instance: T by dmShareKodein.instance(tag)
        return instance
    }
    
    inline fun <reified T : Any> finalAllImpls(tag: Any? = null): List<T> =
            dmShareKodeinMap.keys.map { findImpl<T>(it, tag) }.toList()
    
    fun findDsVersion(datasource: Datasource, dsVersionId: String): DsVersion<*> {
        val enumKClass =
                findImpl<KClass<out DsVersion<*>>>(datasource)
        return enumKClass.enumSet().matchOne { it.id == dsVersionId }
    }
}