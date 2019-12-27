package com.monkeydp.daios.dms.sdk.conn

import com.monkeydp.tools.ext.kodein.component.AbstractKodeinBuilderConfig
import com.monkeydp.tools.ext.kodein.component.KodeinComponent
import com.monkeydp.tools.ext.kodein.component.KodeinFieldComp
import com.monkeydp.tools.ext.kodein.singletonX
import com.monkeydp.tools.useful.SourceSet
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import kotlin.annotation.AnnotationTarget.FIELD

/**
 * @author iPotato
 * @date 2019/12/14
 */
@Target(FIELD)
@KodeinComponent(SdkTestCp.KodeinBuilderConfig::class)
annotation class SdkTestCp {
    object KodeinBuilderConfig : AbstractKodeinBuilderConfig<KodeinFieldComp>() {
        override fun Kodein.Builder.config(comps: Collection<KodeinFieldComp>) {
            bind<Iterable<ConnProfile>>(SourceSet.TEST) with singletonX { comps.map { it.value } }
        }
    }
}