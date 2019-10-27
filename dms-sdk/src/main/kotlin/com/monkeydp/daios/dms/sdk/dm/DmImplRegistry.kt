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
    private val actionTypes = mutableListOf<ActionType<*>>()
    private val targetTypes = mutableListOf<TargetType<*>>()
    
    private val cpFormClassMap = mutableMapOf<Datasource, Class<out CpForm>>()
    
    internal fun registerEnum(enum: Enum<*>, datasource: Datasource? = null) {
        datasource?.checkPrefix(enum.name)
        when (enum) {
            is DsVersion<*>  -> dsVersions.add(enum)
            is ActionType<*> -> actionTypes.add(enum)
            is TargetType<*> -> targetTypes.add(enum)
        }
    }
    
    @Suppress("UNCHECKED_CAST")
    internal fun registerClass(clazz: Class<*>, datasource: Datasource) {
        if (CpForm::class.java.isAssignableFrom(clazz)) cpFormClassMap.putIfAbsent(datasource, clazz as Class<CpForm>)
    }
    
    fun getDsVersion(datasource: Datasource, dsVersionId: String) =
            dsVersions.first { it.datasource == datasource && it.id == dsVersionId }
    
    fun getActionType(enumName: String) = actionTypes.first { it.asEnum().name == enumName }
    fun getTargetType(enumName: String) = targetTypes.first { it.asEnum().name == enumName }
    
    fun getCpFormClass(datasource: Datasource) = cpFormClassMap.get(datasource)!!
}