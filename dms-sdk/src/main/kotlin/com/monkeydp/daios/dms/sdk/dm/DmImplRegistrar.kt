package com.monkeydp.daios.dms.sdk.dm

import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.dm.Dm.Impl
import com.monkeydp.daios.dms.sdk.metadata.instruction.action.GlobalActionType
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.GlobalTargetType
import com.monkeydp.tools.util.FieldUtil

/**
 * @author iPotato
 * @date 2019/10/27
 */
object DmImplRegistrar {
    
    fun registerAll(impl: Impl, datasource: Datasource) {
        registerAllClasses(impl.classes, datasource)
        registerAllEnums(impl.enumClasses, datasource)
    }
    
    private fun registerAllClasses(implClasses: Impl.Classes, datasource: Datasource) {
        val classes = FieldUtil.getNotnullValues<Class<*>>(implClasses)
        classes.forEach { clazz -> DmImplRegistry.registerClass(clazz, datasource) }
    }
    
    private fun registerAllEnums(implEnumClasses: Impl.EnumClasses, datasource: Datasource) {
        registerAllGlobalEnums()
        registerAllLocalEnums(implEnumClasses, datasource)
    }
    
    private fun registerAllGlobalEnums() {
        val enumClasses = listOf<Class<out Enum<*>>>(
                GlobalActionType::class.java,
                GlobalTargetType::class.java
        )
        enumClasses.forEach { it.enumConstants.forEach { e -> DmImplRegistry.registerEnum(e) } }
    }
    
    private fun registerAllLocalEnums(implEnumClasses: Impl.EnumClasses, datasource: Datasource) {
        @Suppress("UNCHECKED_CAST")
        val enumClasses = FieldUtil.getNotnullValues<Class<*>>(implEnumClasses) as List<Class<Enum<*>>>
        enumClasses.forEach { it.enumConstants.forEach { e -> DmImplRegistry.registerEnum(e, datasource) } }
    }
}