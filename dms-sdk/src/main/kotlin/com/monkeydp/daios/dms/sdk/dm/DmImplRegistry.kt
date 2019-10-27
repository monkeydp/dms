package com.monkeydp.daios.dms.sdk.dm

import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.datasource.DsVersion
import com.monkeydp.daios.dms.sdk.metadata.form.CpForm
import com.monkeydp.daios.dms.sdk.metadata.instruction.action.ActionType
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.TargetType

/**
 * @author iPotato
 * @date 2019/10/25
 */
object DmImplRegistry {
    
    private val dsVersions = mutableListOf<DsVersion<*>>()
    // TODO toList
    internal val actionTypeMap = mutableMapOf<String, ActionType<*>>()
    internal val targetTypeMap = mutableMapOf<String, TargetType<*>>()
    
    internal val cpFormClassMap = mutableMapOf<Datasource, Class<out CpForm>>()
    
    internal fun registerEnum(enum: Enum<*>, datasource: Datasource? = null) {
        val name = enum.name
        datasource?.checkPrefix(name)
        when (enum) {
            is DsVersion<*>  -> dsVersions.add(enum)
            is ActionType<*> -> actionTypeMap.putIfAbsent(name, enum)
            is TargetType<*> -> targetTypeMap.putIfAbsent(name, enum)
        }
    }
    
    @Suppress("UNCHECKED_CAST")
    internal fun registerClass(clazz: Class<*>, datasource: Datasource) {
        if (CpForm::class.java.isAssignableFrom(clazz)) cpFormClassMap.putIfAbsent(datasource, clazz as Class<CpForm>)
    }
    
    fun getDsVersion(datasource: Datasource, dsVersionId: String) =
            dsVersions.first { it.datasource == datasource && it.id == dsVersionId }
}