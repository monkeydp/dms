package com.monkeydp.daios.dms.sdk.annot

import com.monkeydp.daios.dms.sdk.instruction.InstrHelper
import com.monkeydp.daios.dms.sdk.instruction.InstrParser
import com.monkeydp.daios.dms.sdk.instruction.Instruction
import com.monkeydp.tools.ext.kodein.component.AbstractKodeinMapGenerator
import com.monkeydp.tools.ext.kodein.component.KodeinComponent
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation

/**
 * @author iPotato
 * @date 2019/11/1
 */
@KodeinComponent<InstrParser>(mapGeneratorKClass = SdkInstrParser.MapGenerator::class)
@Target(AnnotationTarget.CLASS)
annotation class SdkInstrParser(val instrKClass: KClass<out Instruction> = Nothing::class) {
    object MapGenerator : AbstractKodeinMapGenerator<Instruction, InstrParser>() {
        override fun generate(components: Iterable<InstrParser>) =
                components.map {
                    val instrParserImpl = it::class.findAnnotation<SdkInstrParser>()!!
                    val instr = InstrHelper.getInstr(instrParserImpl.instrKClass, it)
                    instr to it
                }.toMap()
    }
}