package com.monkeydp.daios.dms.sdk.dm

import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.datasource.DsVersion
import com.monkeydp.daios.dms.sdk.datasource.dsThreadLocal
import com.monkeydp.daios.dms.sdk.enumeration.Enumx
import com.monkeydp.daios.dms.sdk.conn.NewConnForm
import com.monkeydp.tools.exception.inner.AbstractInnerException
import com.monkeydp.tools.ext.firstOfSnackCase
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf

/**
 * @author iPotato
 * @date 2019/10/25
 */
object DmImplRegistry {
    
    private val enumsMap: MutableMap<Pair<Datasource?, KClass<out Enumx<*>>>, List<Enumx<*>>> = mutableMapOf()
    
    private val newConnFormClassMap = mutableMapOf<Datasource, KClass<out NewConnForm>>()
    
    private fun key(enum: Enumx<*>, datasource: Datasource? = null) = key(enum.javaClass.kotlin, datasource)
    fun key(kClass: KClass<out Enumx<*>>, datasource: Datasource? = null) =
            Pair(datasource, Enumx.getContract(kClass))
    
    internal fun registerEnum(enum: Enumx<*>, datasource: Datasource? = null) {
        val key = key(enum, datasource)
        val oldEnums = enumsMap[key]
        val newEnums = mutableListOf<Enumx<*>>(enum)
        if (oldEnums != null) newEnums.addAll(oldEnums)
        enumsMap[key] = newEnums.toList()
    }
    
    @Suppress("UNCHECKED_CAST")
    fun <E : Enumx<out E>> getEnums(kClass: KClass<E>, datasource: Datasource? = null) =
            enumsMap[key(kClass, datasource)] as List<E>?
    
    private fun <E : Enumx<out E>> getEnumsX(kClass: KClass<E>, datasource: Datasource) = getEnums(kClass, datasource)!!
    
    inline fun <reified E : Enumx<out E>> getEnumByDsThreadLocal(enumName: String): E =
            getEnum(enumName, dsThreadLocal.get())
    
    inline fun <reified E : Enumx<out E>> getEnumByPrefix(enumName: String): E {
        val dsName = enumName.firstOfSnackCase().toUpperCase()
        val ds = Datasource.valueOfOrNull(dsName)
        return getEnum(enumName, ds)
    }
    
    inline fun <reified E : Enumx<out E>> getEnum(enumName: String, datasource: Datasource? = null): E {
        val enumNameUpper = enumName.toUpperCase()
        var enum: E? =
                if (datasource == null) getEnumFromGlobal<E>(enumNameUpper)
                else getEnumFromLocal<E>(enumNameUpper, datasource)
        // If enum cannot found from local, try to find from global
        if (enum == null && datasource != null) enum = getEnumFromGlobal<E>(enumNameUpper)
        if (enum == null) throw NoSuchEnumException(enumNameUpper)
        return enum
    }
    
    inline fun <reified E : Enumx<out E>> getEnumFromGlobal(enumName: String): E? {
        val enums = getEnums(E::class, null)
        return enums?.firstOrNull() { it.asEnum().name == enumName }
    }
    
    inline fun <reified E : Enumx<out E>> getEnumFromLocal(enumName: String, datasource: Datasource): E? {
        val enums = getEnums(E::class, datasource)
        return enums?.firstOrNull() { it.asEnum().name == enumName }
    }
    
    internal fun registerClass(clazz: KClass<*>, datasource: Datasource) {
        @Suppress("UNCHECKED_CAST")
        when {
            clazz.isSubclassOf(NewConnForm::class) -> newConnFormClassMap.putIfAbsent(datasource, clazz as KClass<out NewConnForm>)
        }
    }
    
    fun getDsVersion(datasource: Datasource, dsVersionId: String): DsVersion<*> {
        val enums = getEnumsX(DsVersion::class, datasource)
        return enums.first { it.id == dsVersionId }
    }
    
    fun getNewConnFormClass(datasource: Datasource) = newConnFormClassMap.get(datasource)!!
    
    class NoSuchEnumException(enumName: String) : AbstractInnerException("No such enum named $enumName")
}