package com.monkeydp.daios.dms.sdk.share.kodein

import com.monkeydp.daios.dms.sdk.config.kodein
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.datasource.DsVersion
import com.monkeydp.daios.dms.sdk.share.conn.ConnContext
import com.monkeydp.tools.enumx.Enumx
import com.monkeydp.tools.enumx.recurFindEnumOrNull
import com.monkeydp.tools.ext.kotlin.enumSet
import com.monkeydp.tools.ext.kotlin.matchOne
import com.monkeydp.tools.ext.kotlin.transformEnumName
import com.monkeydp.tools.ext.main.ierror
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import kotlin.reflect.KClass

/**
 * @author iPotato
 * @date 2019/12/10
 */
object DmKodeinHelper {
    
    val connContext: () -> ConnContext by kodein.provider()
    val defaultDs get() = connContext().datasource
    
    /**
     * @param E enum contract
     */
    inline fun <E : Enumx<out E>, reified K : KClass<out E>> findEnum(
            enumName: String,
            caseSensitive: Boolean = false,
            datasource: Datasource = defaultDs
    ): E =
            transformEnumName(enumName, caseSensitive).let {
                val enumKClass: K = findImpl(datasource)
                var enum = enumKClass.recurFindEnumOrNull(it)
                if (enum == null) ierror("No such enum named `$it`, datasource is `$datasource`")
                enum
            }
    
    inline fun <reified T : Any> findImpl(
            datasource: Datasource = defaultDs,
            tag: Any? = null
    ): T {
        val dmKodein = getDmKodein(datasource)
        val instance: T by dmKodein.instance(tag)
        return instance
    }
    
    inline fun <reified T : Any> finalAllImpls(tag: Any? = null): List<T> =
            dmKodeinMap.keys.map { findImpl<T>(it, tag) }.toList()
    
    fun findDsVersion(datasource: Datasource, dsVersionId: String): DsVersion<*> {
        val enumKClass =
                findImpl<KClass<out DsVersion<*>>>(datasource)
        return enumKClass.enumSet().matchOne { it.id == dsVersionId }
    }
}