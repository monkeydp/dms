package com.monkeydp.daios.dms.sdk.instruction

import com.monkeydp.tools.ext.kodein.component.KodeinComponent
import com.monkeydp.tools.ext.kodein.component.AbstractKodeinBuilderConfig
import com.monkeydp.tools.ext.kodein.component.KodeinKClassComp
import com.monkeydp.tools.ext.kodein.singletonX
import com.monkeydp.tools.ext.kotlin.singletonX
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import kotlin.reflect.KClass

/**
 * @author iPotato
 * @date 2019/11/1
 */
@Target(AnnotationTarget.CLASS)
@KodeinComponent(SdkInstrParser.KodeinBuilderConfig::class)
annotation class SdkInstrParser(val instrKClass: KClass<out Instruction> = Nothing::class) {
    object KodeinBuilderConfig : AbstractKodeinBuilderConfig<KodeinKClassComp>() {
        override fun Kodein.Builder.configOne(comp: KodeinKClassComp) {
            val sdkInstrParser = comp.annot as SdkInstrParser
            val instr = InstrHelper.getInstr(sdkInstrParser.instrKClass, comp.annotatedKClass)
            bind<InstrParser>(tag = instr) with singletonX { comp.annotatedKClass.singletonX() }
        }
    }
}