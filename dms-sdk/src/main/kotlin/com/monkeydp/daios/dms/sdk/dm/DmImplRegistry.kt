package com.monkeydp.daios.dms.sdk.dm

import com.monkeydp.daios.dms.sdk.enumeration.Enumx
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.datasource.DsVersion
import com.monkeydp.daios.dms.sdk.metadata.form.CpForm
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf

/**
 * @author iPotato
 * @date 2019/10/25
 */
object DmImplRegistry {
    
    val enumListMap =
            mutableMapOf<KClass<out Enumx<*>>, List<Enumx<*>>>()
    
    private val cpFormClassMap = mutableMapOf<Datasource, KClass<out CpForm>>()
    
    internal fun registerEnum(enum: Enumx<*>, datasource: Datasource? = null) {
        val contract = Enumx.getContract(enum)
        datasource?.checkPrefix(enum.asEnum().name)
        val oldEnumList = enumListMap[contract]
        val newEnumList = mutableListOf<Enumx<*>>(enum)
        if (oldEnumList != null) newEnumList.addAll(oldEnumList)
        enumListMap[contract] = newEnumList.toList()
    }
    
    @Suppress("UNCHECKED_CAST")
    fun <E : Enumx<out E>> getEnumList(clazz: KClass<E>) =
            enumListMap.getValue(Enumx.getContract(clazz)) as List<E>
    
    inline fun <reified E : Enumx<out E>> getEnum(enumName: String): E {
        val enumList = getEnumList(E::class)
        return enumList.first { it.asEnum().name == enumName.toUpperCase() }
    }
    
    internal fun registerClass(clazz: KClass<*>, datasource: Datasource) {
        @Suppress("UNCHECKED_CAST")
        when {
            clazz.isSubclassOf(CpForm::class) -> cpFormClassMap.putIfAbsent(datasource, clazz as KClass<out CpForm>)
        }
    }
    
    fun getDsVersion(datasource: Datasource, dsVersionId: String): DsVersion<*> {
        val enumList = getEnumList(DsVersion::class)
        return enumList.first { it.datasource == datasource && it.id == dsVersionId }
    }
    
    fun getCpFormClass(datasource: Datasource) = cpFormClassMap.get(datasource)!!
}