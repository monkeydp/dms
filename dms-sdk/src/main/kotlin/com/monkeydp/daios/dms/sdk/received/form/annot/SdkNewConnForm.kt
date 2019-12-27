package com.monkeydp.daios.dms.sdk.received.form.annot

import com.monkeydp.daios.dms.sdk.conn.NewConnForm
import com.monkeydp.daios.dms.sdk.instruction.Instruction
import com.monkeydp.daios.dms.sdk.received.form.ReceivedForm
import com.monkeydp.tools.ext.kodein.component.AbstractKodeinBuilderConfig
import com.monkeydp.tools.ext.kodein.component.KodeinComponent
import com.monkeydp.tools.ext.kodein.component.KodeinKClassComp
import com.monkeydp.tools.ext.kodein.singletonX
import com.monkeydp.tools.ext.kotlin.singletonX
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.reflect.KClass

/**
 * @author iPotato
 * @date 2019/11/8
 */
@Target(CLASS)
@KodeinComponent(SdkNewConnForm.KodeinBuilderConfig::class)
annotation class SdkNewConnForm(val instrKClass: KClass<out Instruction>) {
    object KodeinBuilderConfig : AbstractKodeinBuilderConfig<KodeinKClassComp>() {
        override fun Kodein.Builder.configOne(comp: KodeinKClassComp) {
            bind<KClass<out NewConnForm>>() with singletonX { comp.annotatedKClass }
            val instr = (comp.annot as SdkNewConnForm).instrKClass.singletonX()
            bind<KClass<out ReceivedForm>>(tag = instr) with singletonX { comp.annotatedKClass }
        }
    }
}