package com.monkeydp.daios.dms.config

import com.monkeydp.daios.dms.sdk.config.KodeinModuleRepo
import com.monkeydp.tools.gradle.wrapper.GradleWrapperExecutor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

/**
 * @author iPotato
 * @date 2019/12/8
 */
internal val kodein = Kodein {
    importAll(*KodeinModuleRepo.modules())
    bind<GradleWrapperExecutor>() with singleton { GradleWrapperExecutor(rootDirpath = DmsDirpath.root) }
}