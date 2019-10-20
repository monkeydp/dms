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
        private const val specificLibsPath = "libs/specific"
    }

    private val bundleClassLoader: BundleClassLoader
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
        bundleClassLoader = initBundleClassLoader()
        dm = initDm()
        impls = initAllImplClasses()
        dbDefMap = dm.dbDefs.map { it.version.id to it }.toMap()
        bundleClassLoader.specificClassLoaders = initSpecificClassLoaderMap()
    }

    val datasource: Datasource = dm.datasource

    private fun initBundleClassLoader(): BundleClassLoader {
        val classesUrl = File(deployDir, classesPath).toURI().toURL()
        val commonLibsUrls = commonLibsUrls()
        val urls = arrayOf<URL>(classesUrl, *commonLibsUrls)
        return BundleClassLoader(urls, Thread.currentThread().contextClassLoader)
    }

    private fun commonLibsUrls(): Array<URL> {
        val libsDir = File(deployDir, commonLibsPath)
        val jars = FileUtil.listFiles(libsDir, FileFilter { file ->
            file.isFile && file.name.endsWith(".jar")
        })
        return jars.map { it.toURI().toURL() }.toTypedArray()
    }

    private fun initSpecificClassLoaderMap(): Map<String, SpecificClassLoader> {
        val map = mutableMapOf<String, SpecificClassLoader>()
        dbDefMap.forEach { (dbVersionId, _) ->
            val specificLibsUrls = specificLibsUrls(dbVersionId)
            val loader = SpecificClassLoader(specificLibsUrls, bundleClassLoader)
            map[dbVersionId] = loader
        }
        return map
    }

    private fun specificLibsUrls(dbVersionId: String): Array<URL> {
        val libsDir = File(deployDir, "$specificLibsPath/$dbVersionId")
        val jars = FileUtil.listFiles(libsDir, FileFilter { file ->
            file.isFile && file.name.endsWith(".jar")
        })
        return jars.map { it.toURI().toURL() }.toTypedArray()
    }

    private fun initDm(): Dm {
        @Suppress("UNCHECKED_CAST")
        val dmClass: Class<out Dm> = bundleClassLoader.loadClass(dmClassname) as Class<out Dm>
        return ClassUtil.newInstance(dmClass)
    }

    @Suppress("UNCHECKED_CAST")
    private fun initAllImplClasses(): Impls {
        val implClassNames = dm.implClassNames
        val connectionFactoryClass = bundleClassLoader.loadClass(implClassNames.connectionFactory) as Class<ConnectionFactory>
        Impls.connectionFactory = ClassUtil.newInstance(connectionFactoryClass)
        return Impls
    }

    /**
     * @see DbVersion
     */
    fun setSpecificClassLoader(dbVersionId: String) {
        bundleClassLoader.setSpecificClassLoader(dbVersionId)
    }

    fun removeSpecificClassLoader() {
        bundleClassLoader.removeSpecificClassLoader()
    }

    private class BundleClassLoader(urls: Array<URL>, parent: ClassLoader) : URLClassLoader(urls, parent) {
        /**
         * DbVersion.id → SpecificClassLoader
         * @see DbVersion
         */
        lateinit var specificClassLoaders: Map<String, SpecificClassLoader>
        /**
         * Remember to remove the class loader after you set it,
         * otherwise it may cause a memory leak problem
         */
        private val sclThreadLocal = ThreadLocal<SpecificClassLoader>()

        override fun loadClass(name: String?): Class<*> {
            return try {
                super.loadClass(name)
            } catch (e: ClassNotFoundException) {
                val loader = sclThreadLocal.get()
                loader.loadClass(name)
            }
        }

        /**
         * @see DbVersion
         */
        fun setSpecificClassLoader(dbVersionId: String) {
            sclThreadLocal.set(specificClassLoaders[dbVersionId])
        }

        fun removeSpecificClassLoader() {
            sclThreadLocal.remove()
        }
    }

    private class SpecificClassLoader(urls: Array<URL>, parent: BundleClassLoader) : URLClassLoader(urls, parent) {

    }
}