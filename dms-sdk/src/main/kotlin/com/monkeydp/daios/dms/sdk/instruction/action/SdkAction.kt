package com.monkeydp.daios.dms.sdk.instruction.action

import com.monkeydp.tools.ext.kodein.component.KodeinComponent
import com.monkeydp.tools.ext.kodein.component.abstr.AbstractKodeinBuilderConfig
import com.monkeydp.tools.ext.kodein.component.contract.KodeinKClassComp
import com.monkeydp.tools.ext.kodein.singletonX
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.reflect.KClass

/**
 * @author iPotato
 * @date 2019/12/18
 */
@Target(CLASS)
@KodeinComponent(SdkAction.KodeinBuilderConfig::class)
annotation class SdkAction {
    object KodeinBuilderConfig : AbstractKodeinBuilderConfig<KodeinKClassComp>() {
        override fun Kodein.Builder.configOne(comp: KodeinKClassComp) {
            bind<KClass<out Action<*>>>() with singletonX { comp.annotatedKClass }
        }
    }
}