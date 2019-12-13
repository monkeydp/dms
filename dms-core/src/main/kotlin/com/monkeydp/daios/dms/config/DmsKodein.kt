package com.monkeydp.daios.dms.config

import com.monkeydp.tools.gradle.wrapper.GradleWrapperExecutor
import com.monkeydp.tools.gradle.wrapper.StdGradleWrapperExecutor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

/**
 * @author iPotato
 * @date 2019/12/8
 */
internal val kodein = Kodein {
    bind<GradleWrapperExecutor>() with singleton { StdGradleWrapperExecutor(DmsDirpath.root) }
}