package com.monkeydp.daios.dms.sdk.api.annot

import com.monkeydp.daios.dms.sdk.api.InstrApi
import com.monkeydp.tools.ext.kodein.component.AbstractKodeinBuilderConfig
import com.monkeydp.tools.ext.kodein.component.KodeinComponent
import com.monkeydp.tools.ext.kodein.component.KodeinKClassComp
import com.monkeydp.tools.ext.kodein.singletonX
import com.monkeydp.tools.ext.kotlin.singletonX
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import kotlin.annotation.AnnotationTarget.CLASS

/**
 * @author iPotato
 * @date 2019/12/19
 */
@Target(CLASS)
@KodeinComponent(SdkInstrApi.KodeinBuilderConfig::class)
annotation class SdkInstrApi {
    object KodeinBuilderConfig : AbstractKodeinBuilderConfig<KodeinKClassComp>() {
        override fun Kodein.Builder.configOne(comp: KodeinKClassComp) {
            bind<InstrApi>() with singletonX { comp.annotatedKClass.singletonX() }
        }
    }
}