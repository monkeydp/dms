package com.monkeydp.daios.dms.sdk.dm

import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.dm.Dm.Impl
import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon
import com.monkeydp.daios.dms.sdk.metadata.instruction.action.GlobalAction
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.GlobalTarget
import com.monkeydp.tools.util.FieldUtil
import kotlin.reflect.KClass

/**
 * @author iPotato
 * @date 2019/10/27
 */
object DmImplRegistrar {
    
    private val globalEnumClasses = listOf<Class<out Enum<*>>>(
            GlobalAction::class.java,
            GlobalTarget::class.java,
            GlobalIcon::class.java
    )
    
    fun registerAll(impl: Impl, datasource: Datasource) {
        registerClasses(impl.classes, datasource)
        registerEnums(impl.enumClasses, datasource)
    }
    
    private fun registerClasses(implClasses: Impl.Classes, datasource: Datasource) {
        val classes = FieldUtil.getNotnullValues<KClass<*>>(implClasses)
        classes.forEach { clazz -> DmImplRegistry.registerClass(clazz, datasource) }
    }
    
    private fun registerEnums(implEnumClasses: Impl.EnumClasses, datasource: Datasource) {
        registerGlobalEnums(globalEnumClasses)
        registerLocalEnums(implEnumClasses, datasource)
    }
    
    private fun registerGlobalEnums(enumClasses: List<Class<out Enum<*>>>) {
        enumClasses.forEach { it.enumConstants.forEach { e -> DmImplRegistry.registerEnum(e) } }
    }
    
    private fun registerLocalEnums(implEnumClasses: Impl.EnumClasses, datasource: Datasource) {
        @Suppress("UNCHECKED_CAST")
        val enumClasses = FieldUtil.getNotnullValues<Class<*>>(implEnumClasses) as List<Class<Enum<*>>>
        enumClasses.forEach { it.enumConstants.forEach { e -> DmImplRegistry.registerEnum(e, datasource) } }
    }
}