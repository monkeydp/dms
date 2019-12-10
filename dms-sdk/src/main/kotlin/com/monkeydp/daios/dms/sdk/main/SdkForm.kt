package com.monkeydp.daios.dms.sdk.main

import com.monkeydp.daios.dms.sdk.instruction.Instruction
import com.monkeydp.daios.dms.sdk.main.SdkImpl.Type
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.reflect.KClass

/**
 * @author iPotato
 * @date 2019/11/8
 */
@SdkImpl(Type.KClass)
@Target(CLASS)
annotation class SdkForm(val instrClass: KClass<out Instruction>)