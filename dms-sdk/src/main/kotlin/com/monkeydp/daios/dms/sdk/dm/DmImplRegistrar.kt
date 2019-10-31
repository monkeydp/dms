package com.monkeydp.daios.dms.sdk.dm

import com.monkeydp.daios.dms.sdk.contract.Enumx
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon
import com.monkeydp.daios.dms.sdk.metadata.instruction.action.GlobalAction
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.GlobalTarget
import com.monkeydp.tools.ext.toPropListX
import kotlin.reflect.KClass

/**
 * @author iPotato
 * @date 2019/10/27
 */
object DmImplRegistrar {
    
    private val globalEnumClasses = listOf<KClass<out Enumx<*>>>(
            GlobalAction::class,
            GlobalTarget::class,
            GlobalIcon::class
    )
    
    fun registerAll(impl: DmImpl, datasource: Datasource) {
        registerClasses(impl.classes, datasource)
        registerEnums(impl.enumClasses, datasource)
    }
    
    private fun registerClasses(implClasses: DmImpl.Classes, datasource: Datasource) {
        val classes = implClasses.toPropListX<KClass<*>>()
        classes.forEach { clazz -> DmImplRegistry.registerClass(clazz, datasource) }
    }
    
    private fun registerEnums(implEnumClasses: DmImpl.EnumClasses, datasource: Datasource) {
        registerGlobalEnums(globalEnumClasses)
        registerLocalEnums(implEnumClasses, datasource)
    }
    
    private fun registerGlobalEnums(enumClasses: List<KClass<out Enumx<*>>>) {
        enumClasses.forEach { it.java.enumConstants.forEach { e -> DmImplRegistry.registerEnum(e) } }
    }
    
    private fun registerLocalEnums(implEnumClasses: DmImpl.EnumClasses, datasource: Datasource) {
        val enumClasses = implEnumClasses.toPropListX<KClass<Enumx<*>>>()
        enumClasses.forEach { it.java.enumConstants.forEach { e -> DmImplRegistry.registerEnum(e, datasource) } }
    }
}