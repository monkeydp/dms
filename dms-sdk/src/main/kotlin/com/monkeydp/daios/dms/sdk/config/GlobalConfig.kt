package com.monkeydp.daios.dms.sdk.config

import com.monkeydp.daios.dms.sdk.enumx.Enumx
import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon
import com.monkeydp.daios.dms.sdk.instruction.action.GlobalAction
import com.monkeydp.daios.dms.sdk.instruction.target.GlobalTarget
import com.monkeydp.tools.ext.toPropListX
import kotlin.reflect.KClass

/**
 * @author iPotato
 * @date 2019/11/5
 */
object GlobalConfig {
    val globalActionClass = GlobalAction::class
    val globalTargetClass = GlobalTarget::class
    val globalIconClass = GlobalIcon::class
    
    val globalEnumClasses: List<KClass<out Enumx<*>>>
    
    init {
        globalEnumClasses = toPropListX<KClass<out Enumx<*>>>()
    }
}