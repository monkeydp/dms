package com.monkeydp.daios.dms.sdk.dm

import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.tools.ext.kodein.component.KodeinComponent
import com.monkeydp.tools.ext.kodein.component.abstr.AbstractKodeinBuilderConfig
import com.monkeydp.tools.ext.kodein.component.contract.KodeinKClassComp
import com.monkeydp.tools.ext.kodein.singletonX
import com.monkeydp.tools.ext.kotlin.singletonX
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import kotlin.annotation.AnnotationTarget.CLASS

/**
 * @author iPotato
 * @date 2019/12/15
 */
@Target(CLASS)
@KodeinComponent(SdkDmApp.KodeinBuilderConfig::class)
annotation class SdkDmApp(val datasource: Datasource) {
    object KodeinBuilderConfig : AbstractKodeinBuilderConfig<KodeinKClassComp>() {
        override fun Kodein.Builder.configOne(comp: KodeinKClassComp) {
            bind<DmApp>() with singletonX { comp.annotatedKClass.singletonX() }
        }
    }
}