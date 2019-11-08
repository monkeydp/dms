package com.monkeydp.daios.dms.sdk.metadata.form

import com.monkeydp.daios.dms.sdk.instruction.Instruction
import kotlin.reflect.KClass

/**
 * @author iPotato
 * @date 2019/11/8
 */
@Target(AnnotationTarget.CLASS)
annotation class FormImpl(val instrClass: KClass<out Instruction>)