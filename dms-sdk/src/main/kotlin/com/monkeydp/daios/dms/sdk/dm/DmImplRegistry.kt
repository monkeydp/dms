package com.monkeydp.daios.dms.sdk.dm

import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.datasource.DsVersion
import com.monkeydp.daios.dms.sdk.metadata.form.CpForm
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import com.monkeydp.daios.dms.sdk.metadata.instruction.action.Action
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.Target
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf

/**
 * @author iPotato
 * @date 2019/10/25
 */
object DmImplRegistry {
    
    private val dsVersions = mutableListOf<DsVersion<*>>()
    private val actions = mutableListOf<Action<*>>()
    private val targets = mutableListOf<Target<*>>()
    private val icons = mutableListOf<Icon<*>>()
    
    private val cpFormClassMap = mutableMapOf<Datasource, KClass<out CpForm>>()
    
    internal fun registerEnum(enum: Enum<*>, datasource: Datasource? = null) {
        datasource?.checkPrefix(enum.name)
        when (enum) {
            is DsVersion<*> -> dsVersions.add(enum)
            is Action<*>    -> actions.add(enum)
            is Target<*>    -> targets.add(enum)
            is Icon<*>      -> icons.add(enum)
        }
    }
    
    @Suppress("UNCHECKED_CAST")
    internal fun registerClass(clazz: KClass<*>, datasource: Datasource) {
        when {
            clazz.isSubclassOf(CpForm::class) -> cpFormClassMap.putIfAbsent(datasource, clazz as KClass<out CpForm>)
        }
    }
    
    fun getDsVersion(datasource: Datasource, dsVersionId: String) =
            dsVersions.first { it.datasource == datasource && it.id == dsVersionId }
    
    fun getAction(enumName: String) = actions.first { it.asEnum().name == enumName }
    fun getTarget(enumName: String) = targets.first { it.asEnum().name == enumName }
    fun getIcon(enumName: String) = icons.first { it.asEnum().name == enumName }
    
    fun getCpFormClass(datasource: Datasource) = cpFormClassMap.get(datasource)!!
}