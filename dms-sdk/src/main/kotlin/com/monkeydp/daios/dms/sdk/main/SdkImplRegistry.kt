package com.monkeydp.daios.dms.sdk.main

import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.datasource.DsVersion
import com.monkeydp.daios.dms.sdk.enumx.Enumx
import com.monkeydp.daios.dms.sdk.request.RequestContext
import com.monkeydp.tools.ext.getFirstUpperBound
import com.monkeydp.tools.ext.ierror
import com.monkeydp.tools.ext.notNullSingleton
import com.monkeydp.tools.useful.Null
import kotlin.properties.Delegates
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties

/**
 * @author iPotato
 * @date 2019/10/25
 */
object SdkImplRegistry {
    
    /**
     * Pair<Datasource, contractKClass> → implKClass
     */
    var implClassesMap: Map<Pair<Datasource, KClass<*>>, KClass<*>> by Delegates.notNullSingleton()
    
    fun key(contract: KClass<*>, ds: Datasource) = Pair(ds, contract)
    
    internal fun registerClasses(implClasses: SdkImpl.Classes, ds: Datasource) {
        val implClassesMap =
                mutableMapOf<Pair<Datasource, KClass<*>>, KClass<*>>()
        implClasses::class.memberProperties.forEach {
            val contract = it.getFirstUpperBound()
            val implClass = it.getter.call(implClasses) as KClass<*>
            implClassesMap[key(contract, ds)] = implClass
        }
        SdkImplRegistry.implClassesMap = implClassesMap.toMap()
    }
    
    @Suppress("UNCHECKED_CAST")
    inline fun <reified C : Any> getImplKClass(ds: Datasource) =
            implClassesMap.getValue(key(C::class, ds)) as KClass<C>
    
    inline fun <reified I : Any> getKClass(ds: Datasource) =
            getImplKClass<I>(ds)
    
    @Suppress("UNCHECKED_CAST")
    inline fun <reified E : Enumx<out E>> getEnumKClass(ds: Datasource) =
            getImplKClass<E>(ds) as KClass<out E>
    
    inline fun <reified E : Enumx<out E>> getEnum(enumName: String, ds: Datasource): E? {
        val enumKClass = getEnumKClass<E>(ds)
        return getEnum<E>(enumKClass, enumName)
    }
    
    inline fun <reified E : Enumx<out E>> getEnum(enumKClass: KClass<out E>, enumName: String) =
            enumKClass.java.enumConstants.firstOrNull { it.asEnum().name == enumName.toUpperCase() }
    
    
    inline fun <reified E : Enumx<out E>> findEnum(enumName: String) =
            findEnum<E>(enumName, RequestContext.datasource!!)
    
    inline fun <reified E : Enumx<out E>> findEnum(enumName: String, ds: Datasource): E {
        val enumKClass = getEnumKClass<E>(ds)
        var enum = getEnum<E>(enumKClass, enumName)
        if (enum == null) {
            val parent = enumKClass.findAnnotation<SdkEnum>()!!.parent
            @Suppress("UNCHECKED_CAST")
            if (parent != Null::class)
                enum = getEnum<E>(parent as KClass<out E>, enumName)
        }
        if (enum == null) ierror("No such enum named ${enumName.toUpperCase()}, datasource is $ds")
        return enum
    }
    
    fun findDsVersion(datasource: Datasource, dsVersionId: String): DsVersion<*> {
        val enumKClass = getEnumKClass<DsVersion<*>>(datasource)
        return enumKClass.java.enumConstants.first { it.id == dsVersionId }
    }
}