package com.monkeydp.daios.dms.module

import com.monkeydp.daios.dms.sdk.api.ConnApi
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.datasource.DsDef
import com.monkeydp.daios.dms.sdk.datasource.DsVersion
import com.monkeydp.daios.dms.sdk.dm.Dm
import com.monkeydp.daios.dms.sdk.main.SdkImpl
import com.monkeydp.daios.dms.sdk.dm.DmOpenConfig
import com.monkeydp.tools.ext.newInstanceX
import com.monkeydp.tools.util.FileUtil
import java.io.File
import java.io.FileFilter
import java.net.URL
import java.net.URLClassLoader

/**
 * @author iPotato
 * @date 2019/10/14
 */
class ModuleBundle(private val openConfig: DmOpenConfig, private val dmClassname: String) {
    
    private val deployDir = openConfig.deployDir
    
    companion object {
        private const val classesPath = "classes"
        private const val commonLibsPath = "libs/common"
        private const val specificLibsPath = "libs/specific"
    }
    
    private val bundleClassLoader: BundleClassLoader
    private val dm: Dm
    private val impl: SdkImpl
    val datasource: Datasource
    val apis: SdkImpl.Apis
    
    private val dsDefMap: Map<DsVersion<*>, DsDef>
    
    object Impls {
        lateinit var connApi: ConnApi
    }
    
    init {
        bundleClassLoader = initBundleClassLoader()
        dm = loadDm()
        impl = dm.impl
        datasource = dm.datasource
        apis = impl.apis
        dsDefMap = dm.dsDefs.map { it.version to it }.toMap()
        bundleClassLoader.specificClassLoaders = initSpecificClassLoaderMap()
    }
    
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
    
    private fun initSpecificClassLoaderMap(): Map<DsVersion<*>, SpecificClassLoader> {
        val map = mutableMapOf<DsVersion<*>, SpecificClassLoader>()
        dsDefMap.forEach { (dsVersion, _) ->
            val specificLibsUrls = specificLibsUrls(dsVersion)
            val loader = SpecificClassLoader(specificLibsUrls, bundleClassLoader)
            map[dsVersion] = loader
        }
        return map
    }
    
    private fun specificLibsUrls(dsVersion: DsVersion<*>): Array<URL> {
        val libsDir = File(deployDir, "$specificLibsPath/${dsVersion.id}")
        val jars = FileUtil.listFiles(libsDir, FileFilter { file ->
            file.isFile && file.name.endsWith(".jar")
        })
        return jars.map { it.toURI().toURL() }.toTypedArray()
    }
    
    private fun loadDm(): Dm {
        @Suppress("UNCHECKED_CAST")
        val dmClass: Class<Dm> = bundleClassLoader.loadClass(dmClassname) as Class<Dm>
        return dmClass.newInstanceX(openConfig)
    }
    
    fun getDriverClassname(dsVersion: DsVersion<*>) = dsDefMap[dsVersion]?.driver?.classname!!
    
    fun setSpecificClassLoader(dsVersion: DsVersion<*>) {
        bundleClassLoader.setSpecificClassLoader(dsVersion)
    }
    
    fun removeSpecificClassLoader() {
        bundleClassLoader.removeSpecificClassLoader()
    }
    
    private class BundleClassLoader(urls: Array<URL>, parent: ClassLoader) : URLClassLoader(urls, parent) {
    
        lateinit var specificClassLoaders: Map<DsVersion<*>, SpecificClassLoader>
        /**
         * Remember to remove the class loader after you set it,
         * otherwise it may cause a memory leak problem
         */
        private val sclThreadLocal = ThreadLocal<SpecificClassLoader?>()
    
        override fun loadClass(name: String): Class<*>? {
            return try {
                super.loadClass(name)
            } catch (e: ClassNotFoundException) {
                val loader = sclThreadLocal.get()
                loader?.loadClass(name)
            }
        }
    
        fun setSpecificClassLoader(dsVersion: DsVersion<*>) {
            sclThreadLocal.set(specificClassLoaders[dsVersion])
        }
        
        fun removeSpecificClassLoader() {
            sclThreadLocal.remove()
        }
    }
    
    private class SpecificClassLoader(urls: Array<URL>, parent: BundleClassLoader) : URLClassLoader(urls, parent)
}