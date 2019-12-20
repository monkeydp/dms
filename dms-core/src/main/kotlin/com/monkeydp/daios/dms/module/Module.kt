package com.monkeydp.daios.dms.module

import com.monkeydp.daios.dms.sdk.SdkDmApp
import com.monkeydp.daios.dms.sdk.config.PackageName
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.datasource.DsDef
import com.monkeydp.daios.dms.sdk.datasource.DsVersion
import com.monkeydp.daios.dms.sdk.share.kodein.DmKodeinHelper
import com.monkeydp.tools.ext.kotlin.findAnnot
import com.monkeydp.tools.ext.kotlin.matchOne
import com.monkeydp.tools.ext.reflections.getAnnotatedSingletons
import com.monkeydp.tools.ext.reflections.reflections
import com.monkeydp.tools.util.FileUtil
import java.io.File
import java.io.FileFilter
import java.net.URL
import java.net.URLClassLoader

/**
 * @author iPotato
 * @date 2019/10/14
 */
class Module(private val deployDir: File) {
    
    companion object {
        private const val classesPath = "classes"
        private const val commonLibsPath = "libs/common"
        private const val specificLibsPath = "libs/specific"
    }
    
    private val classLoader: ModuleClassLoader
    private val sdkDmApp: SdkDmApp
    val datasource: Datasource
    private val dsDefMap: Map<DsVersion<*>, DsDef>
    
    init {
        classLoader = initClassLoader()
        sdkDmApp = loadSdkDmApp()
        datasource = sdkDmApp.datasource
        val dsDefs = findImpl<Iterable<DsDef>>()
        dsDefMap = dsDefs.map { it.version to it }.toMap()
        classLoader.loaderMap = initSpecificClassLoaderMap()
    }
    
    private inline fun <reified T : Any> findImpl(tag: Any? = null) = DmKodeinHelper.findImpl<T>(datasource, tag)
    
    private fun initClassLoader(): ModuleClassLoader {
        val classesUrl = File(deployDir, classesPath).toURI().toURL()
        val commonLibsUrls = commonLibsUrls()
        val urls = arrayOf<URL>(classesUrl, *commonLibsUrls)
        return ModuleClassLoader(urls, Thread.currentThread().contextClassLoader)
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
            val loader = SpecificClassLoader(specificLibsUrls, classLoader)
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
    
    private fun loadSdkDmApp() =
            reflections(PackageName.dm, classLoader).run {
                getAnnotatedSingletons(SdkDmApp::class).matchOne { true }.run {
                    this.javaClass.kotlin.findAnnot<SdkDmApp>()
                }
            }
    
    fun findDsDef(dsVersion: DsVersion<*>) = dsDefMap.getValue(dsVersion)
    
    fun setSpecificClassLoader(dsVersion: DsVersion<*>) = classLoader.setSpecificClassLoader(dsVersion)
    
    fun removeSpecificClassLoader() = classLoader.removeSpecificClassLoader()
    
    private class ModuleClassLoader(urls: Array<URL>, parent: ClassLoader) : URLClassLoader(urls, parent) {
        
        lateinit var loaderMap: Map<DsVersion<*>, SpecificClassLoader>
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
        
        fun setSpecificClassLoader(dsVersion: DsVersion<*>): Unit = sclThreadLocal.set(loaderMap[dsVersion])
        
        fun removeSpecificClassLoader() {
            sclThreadLocal.remove()
        }
    }
    
    private class SpecificClassLoader(urls: Array<URL>, parent: ModuleClassLoader) : URLClassLoader(urls, parent)
}