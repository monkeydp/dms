package com.monkeydp.daios.dms.config

import com.monkeydp.daios.dms.sdk.config.sdkKodeinModule
import com.monkeydp.tools.gradle.wrapper.GradleWrapperExecutor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

/**
 * @author iPotato
 * @date 2019/12/8
 */
internal val kodein = Kodein {
    import(sdkKodeinModule)
    bind<GradleWrapperExecutor>() with singleton { GradleWrapperExecutor(rootDirpath = DmsDirpath.root) }
}