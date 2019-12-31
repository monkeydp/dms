package com.monkeydp.daios.dms.sdk.mocker

import com.monkeydp.tools.ext.kodein.component.AbstractKodeinBuilderConfig
import com.monkeydp.tools.ext.kodein.component.KodeinComponent
import com.monkeydp.tools.ext.kodein.component.KodeinKClassComp
import com.monkeydp.tools.ext.kodein.singletonX
import com.monkeydp.tools.ext.kotlin.singletonX
import com.monkeydp.tools.useful.SourceSet.TEST
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import kotlin.annotation.AnnotationTarget.CLASS

/**
 * @author iPotato
 * @date 2019/12/30
 */
@Target(CLASS)
@KodeinComponent(SdkNodePathMocker.KodeinBuilderConfig::class)
annotation class SdkNodePathMocker {
    object KodeinBuilderConfig : AbstractKodeinBuilderConfig<KodeinKClassComp>() {
        override fun Kodein.Builder.configOne(comp: KodeinKClassComp) {
            bind<NodePathMocker>(tag = TEST) with singletonX { comp.annotatedKClass.singletonX() }
        }
    }
}