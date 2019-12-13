package com.monkeydp.daios.dms.sdk.annot

import com.monkeydp.daios.dms.sdk.instruction.Instruction
import com.monkeydp.tools.ext.java.singletonX
import com.monkeydp.tools.ext.kodein.component.AbstractKodeinMapGenerator
import com.monkeydp.tools.ext.kodein.component.KodeinComponent
import com.monkeydp.tools.ext.kodein.component.KodeinComponent.Type
import com.monkeydp.tools.ext.kodein.component.KodeinMapGenerator
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation

/**
 * @author iPotato
 * @date 2019/11/8
 */
@KodeinComponent<KClass<*>>(Type.K_CLASS, SdkForm.MapGenerator::class)
@Target(CLASS)
annotation class SdkForm(val instrClass: KClass<out Instruction>) {
    object MapGenerator : AbstractKodeinMapGenerator<Instruction, KClass<*>>() {
        override fun generate(components: Iterable<KClass<*>>) =
                components.map {
                    val sdkForm = it.java.kotlin.findAnnotation<SdkForm>()!!
                    sdkForm.instrClass.java.singletonX() to it
                }.toMap()
    }
}