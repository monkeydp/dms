package com.monkeydp.daios.dms.sdk.main

import com.monkeydp.daios.dms.sdk.instruction.Instruction
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.reflect.KClass

/**
 * @author iPotato
 * @date 2019/11/8
 */
@Target(CLASS)
annotation class SdkForm(val instrClass: KClass<out Instruction>)