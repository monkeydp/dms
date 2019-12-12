package com.monkeydp.daios.dms.sdk.annot

import com.monkeydp.daios.dms.sdk.instruction.Instruction
import com.monkeydp.tools.ext.kodein.KodeinComponent
import com.monkeydp.tools.ext.kodein.KodeinComponent.Type
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.reflect.KClass

/**
 * @author iPotato
 * @date 2019/11/8
 */
@KodeinComponent(Type.K_CLASS)
@Target(CLASS)
annotation class SdkForm(val instrClass: KClass<out Instruction>)