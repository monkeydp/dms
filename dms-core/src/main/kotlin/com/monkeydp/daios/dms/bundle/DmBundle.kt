package com.monkeydp.daios.dms.bundle

import com.monkeydp.daios.dms.sdk.connection.ConnectionFactory
import com.monkeydp.daios.dms.sdk.dm.Dm
import com.monkeydp.tools.util.ClassUtil
import java.io.File
import java.net.URL
import java.net.URLClassLoader

/**
 * @author iPotato
 * @date 2019/10/14
 */
class DmBundle(private val deployDir: File, private val dmClassname: String) {

    companion object {
        private const val classesDirname = "classes"
    }

    private val classLoader: BundleClassLoader
    private val dm: Dm
    private val impls = Impls()

    private class Impls {
        lateinit var connectionFactory: ConnectionFactory
    }

    init {
        classLoader = initBundleClassLoader()
        dm = initDm()
        initAllImplClasses()
    }

    private fun initBundleClassLoader(): BundleClassLoader {
        val urls = arrayOf<URL>(File(deployDir, classesDirname).toURI().toURL())
        return BundleClassLoader(urls, Thread.currentThread().contextClassLoader)
    }

    private fun initDm(): Dm {
        @Suppress("UNCHECKED_CAST")
        val dmClass: Class<out Dm> = classLoader.loadClass(dmClassname) as Class<out Dm>
        return ClassUtil.newInstance(dmClass)
    }

    @Suppress("UNCHECKED_CAST")
    private fun initAllImplClasses() {
        val implClassNames = dm.implClassNames()
        val connectionFactoryClass = classLoader.loadClass(implClassNames.connectionFactory()) as Class<ConnectionFactory>
        impls.connectionFactory = ClassUtil.newInstance(connectionFactoryClass)
    }

    private class BundleClassLoader(urls: Array<URL>, parent: ClassLoader) : URLClassLoader(urls, parent) {

    }
}