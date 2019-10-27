package com.monkeydp.daios.dms.sdk.dm

import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.datasource.DsVersion
import com.monkeydp.daios.dms.sdk.metadata.form.CpForm
import com.monkeydp.daios.dms.sdk.metadata.instruction.action.Action
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.Target

/**
 * @author iPotato
 * @date 2019/10/25
 */
object DmImplRegistry {
    
    private val dsVersions = mutableListOf<DsVersion<*>>()
    private val actions = mutableListOf<Action<*>>()
    private val targets = mutableListOf<Target<*>>()
    
    private val cpFormClassMap = mutableMapOf<Datasource, Class<out CpForm>>()
    
    internal fun registerEnum(enum: Enum<*>, datasource: Datasource? = null) {
        datasource?.checkPrefix(enum.name)
        when (enum) {
            is DsVersion<*> -> dsVersions.add(enum)
            is Action<*>    -> actions.add(enum)
            is Target<*>    -> targets.add(enum)
        }
    }
    
    @Suppress("UNCHECKED_CAST")
    internal fun registerClass(clazz: Class<*>, datasource: Datasource) {
        if (CpForm::class.java.isAssignableFrom(clazz)) cpFormClassMap.putIfAbsent(datasource, clazz as Class<CpForm>)
    }
    
    fun getDsVersion(datasource: Datasource, dsVersionId: String) =
            dsVersions.first { it.datasource == datasource && it.id == dsVersionId }
    
    fun getAction(enumName: String) = actions.first { it.asEnum().name == enumName }
    fun getTarget(enumName: String) = targets.first { it.asEnum().name == enumName }
    
    fun getCpFormClass(datasource: Datasource) = cpFormClassMap.get(datasource)!!
}