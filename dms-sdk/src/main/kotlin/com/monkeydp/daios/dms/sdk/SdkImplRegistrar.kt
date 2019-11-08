package com.monkeydp.daios.dms.sdk

import com.monkeydp.daios.dms.sdk.config.GlobalConfig.globalEnumClasses
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.enumx.Enumx
import com.monkeydp.tools.ext.toPropListX
import kotlin.reflect.KClass

/**
 * @author iPotato
 * @date 2019/10/27
 */
object SdkImplRegistrar {
    
    fun registerAll(impl: SdkImpl, datasource: Datasource) {
        registerClasses(impl.classes, datasource)
        registerEnums(impl.enumClasses, datasource)
    }
    
    private fun registerClasses(implClasses: SdkImpl.Classes, datasource: Datasource) {
        val classes = implClasses.toPropListX<KClass<*>>()
        classes.forEach { clazz -> SdkImplRegistry.registerClass(clazz, datasource) }
    }
    
    private fun registerEnums(localEnumClasses: SdkImpl.EnumClasses, datasource: Datasource) {
        registerGlobalEnums(globalEnumClasses)
        registerLocalEnums(localEnumClasses, datasource)
    }
    
    private fun registerGlobalEnums(enumClasses: List<KClass<out Enumx<*>>>) {
        enumClasses.forEach { it.java.enumConstants.forEach { e -> SdkImplRegistry.registerEnum(e) } }
    }
    
    private fun registerLocalEnums(implEnumClasses: SdkImpl.EnumClasses, datasource: Datasource) {
        val enumClasses = implEnumClasses.toPropListX<KClass<Enumx<*>>>()
        enumClasses.forEach { it.java.enumConstants.forEach { e -> SdkImplRegistry.registerEnum(e, datasource) } }
    }
}