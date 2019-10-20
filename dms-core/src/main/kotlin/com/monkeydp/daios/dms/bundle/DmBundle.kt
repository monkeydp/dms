package com.monkeydp.daios.dms.bundle

import com.monkeydp.daios.dms.sdk.connection.ConnectionFactory
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.dm.Dm
import com.monkeydp.daios.dms.sdk.dm.Dm.DbDef
import com.monkeydp.daios.dms.sdk.dm.Dm.DbVersion
import com.monkeydp.tools.util.ClassUtil
import com.monkeydp.tools.util.FileUtil
import java.io.File
import java.io.FileFilter
import java.net.URL
import java.net.URLClassLoader

/**
 * @author iPotato
 * @date 2019/10/14
 */
class DmBundle(private val deployDir: File, private val dmClassname: String) {

    companion object {
        private const val classesPath = "classes"
        private const val commonLibsPath = "libs/common"
    }

    private val classLoader: BundleClassLoader
    private val dm: Dm
    val impls: Impls

    /**
     * DbVersion.id → DbDef
     * @see DbVersion
     */
    private val dbDefMap: Map<String, DbDef>

    object Impls {
        lateinit var connectionFactory: ConnectionFactory
    }

    init {
        classLoader = initBundleClassLoader()
        dm = initDm()
        impls = initAllImplClasses()
        dbDefMap = dm.dbDefs.map { it.version.id to it }.toMap()
    }

    val datasource: Datasource = dm.datasource

    private fun initBundleClassLoader(): BundleClassLoader {
        val classesUrl = File(deployDir, classesPath).toURI().toURL()
        val commonLibsUrls = commonLibsUrls()
        val urls = arrayOf<URL>(classesUrl, *commonLibsUrls)
        return BundleClassLoader(urls, Thread.currentThread().contextClassLoader)
    }

    private fun commonLibsUrls(): Array<URL> {
        val commonLibsDir = File(deployDir, commonLibsPath)
        val jars = FileUtil.listFiles(commonLibsDir, FileFilter { file ->
            file.isFile && file.endsWith(".jar")
        })
        return jars.map { it.toURI().toURL() }.toTypedArray()
    }

    private fun initDm(): Dm {
        @Suppress("UNCHECKED_CAST")
        val dmClass: Class<out Dm> = classLoader.loadClass(dmClassname) as Class<out Dm>
        return ClassUtil.newInstance(dmClass)
    }

    @Suppress("UNCHECKED_CAST")
    private fun initAllImplClasses(): Impls {
        val implClassNames = dm.implClassNames
        val connectionFactoryClass = classLoader.loadClass(implClassNames.connectionFactory) as Class<ConnectionFactory>
        Impls.connectionFactory = ClassUtil.newInstance(connectionFactoryClass)
        return Impls
    }

    private class BundleClassLoader(urls: Array<URL>, parent: ClassLoader) : URLClassLoader(urls, parent) {

    }
}