package com.monkeydp.daios.dms.module

import com.monkeydp.daios.dms.sdk.annot.SdkDsDef
import com.monkeydp.daios.dms.sdk.config.PackageName
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.datasource.DsDef
import com.monkeydp.daios.dms.sdk.datasource.DsVersion
import com.monkeydp.daios.dms.sdk.dm.DmApp
import com.monkeydp.daios.dms.sdk.dm.DmConfig
import com.monkeydp.daios.dms.sdk.dm.DmHelper
import com.monkeydp.tools.ext.java.newInstanceX
import com.monkeydp.tools.ext.kotlin.matchOne
import com.monkeydp.tools.ext.reflections.getSubTypesOf
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
class Module(private val config: DmConfig) {
    private val deployDir = config.deployDir
    
    companion object {
        private const val classesPath = "classes"
        private const val commonLibsPath = "libs/common"
        private const val specificLibsPath = "libs/specific"
    }
    
    private val classLoader: ModuleClassLoader
    val dmApp: DmApp
    val datasource: Datasource
    private val dsDefMap: Map<DsVersion<*>, DsDef>
    
    init {
        classLoader = initClassLoader()
        dmApp = loadDmApp()
        datasource = dmApp.datasource
        val dsDefSet = findImpl<Set<DsDef>>(SdkDsDef::class)
        dsDefMap = dsDefSet.map { it.version to it }.toMap()
        classLoader.loaderMap = initSpecificClassLoaderMap()
    }
    
    private inline fun <reified T : Any> findImpl(tag: Any? = null) = DmHelper.findImpl<T>(datasource, tag)
    
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
    
    private fun loadDmApp(): DmApp {
        val reflections = reflections(PackageName.dm, classLoader)
        val dmAppClass = reflections.getSubTypesOf<DmApp>().matchOne { !it.kotlin.isAbstract }
        return dmAppClass.newInstanceX(config)
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