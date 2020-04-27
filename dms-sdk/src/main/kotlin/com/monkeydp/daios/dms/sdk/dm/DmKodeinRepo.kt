package com.monkeydp.daios.dms.sdk.dm

import com.monkeydp.daios.dms.sdk.config.kodein
import com.monkeydp.daios.dms.sdk.context.ConnContext
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.datasource.DefaultDatasource
import com.monkeydp.daios.dms.sdk.datasource.DsVersion
import com.monkeydp.tools.enumx.Enumx
import com.monkeydp.tools.enumx.recurFindEnumOrNull
import com.monkeydp.tools.ext.kodein.providerX
import com.monkeydp.tools.ext.kotlin.enumSet
import com.monkeydp.tools.ext.kotlin.transformEnumName
import com.monkeydp.tools.ext.main.ierror
import org.kodein.di.generic.instance
import kotlin.reflect.KClass

/**
 * @author iPotato
 * @date 2019/12/23
 */
val dmKodeinRepo = DmKodeinRepo()

interface DmKodeinRepo {
    companion object {
        operator fun invoke(): DmKodeinRepo =
                DmKodeinRepoImpl()
    }

    val defaultDs: Datasource get() = DefaultDatasource.get()

    val dmKodeinMap: Map<Datasource, DmKodein>
    fun putDmKodein(datasource: Datasource, dmKodein: DmKodein)
    fun getDmKodein(datasource: Datasource): DmKodein

    fun findDsVersion(datasource: Datasource, dsVersionId: String): DsVersion<*>
}

private abstract class AbstractDmKodeinRepo : DmKodeinRepo {

    private val connContext: ConnContext get() = kodein.providerX()

    private val _dmKodeinMap = mutableMapOf<Datasource, DmKodein>()
    override val dmKodeinMap get() = _dmKodeinMap.toMap()

    override fun putDmKodein(datasource: Datasource, dmKodein: DmKodein) {
        _dmKodeinMap[datasource] = dmKodein
    }

    override fun getDmKodein(datasource: Datasource) = _dmKodeinMap.getValue(datasource)

    override fun findDsVersion(datasource: Datasource, dsVersionId: String): DsVersion<*> =
            findImpl<KClass<out DsVersion<*>>>(datasource).enumSet().single() { it.id == dsVersionId }
}

private class DmKodeinRepoImpl : AbstractDmKodeinRepo()


inline fun <reified T : Any> DmKodeinRepo.findImpl(
        datasource: Datasource = defaultDs,
        tag: Any? = null
): T {
    val instance by getDmKodein(datasource).instance<T>(tag)
    return instance
}

inline fun <reified T : Any> DmKodeinRepo.findAllImpls(tag: Any? = null): Iterable<T> =
        dmKodeinMap.keys.map { findImpl<T>(it, tag) }.toList()

inline fun <reified I : Iterable<Any>> DmKodeinRepo.findFlatAllImpls(tag: Any? = null): I =
        findAllImpls<I>(tag).flatten() as I

/**
 * @param E enum contract
 */
inline fun <E : Enumx<out E>, reified K : KClass<out E>> DmKodeinRepo.findEnum(
        datasource: Datasource = defaultDs,
        enumName: String,
        caseSensitive: Boolean = false
): E = transformEnumName(enumName, caseSensitive).let {
    val enumKClass: K = findImpl(datasource)
    var enum = enumKClass.recurFindEnumOrNull(it)
    if (enum == null) ierror("No such enum named `$it`, datasource is `$datasource`")
    enum
}