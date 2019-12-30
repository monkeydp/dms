package com.monkeydp.daios.dms.module

import com.monkeydp.daios.dms.config.kodein
import com.monkeydp.daios.dms.sdk.config.PackageName
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.datasource.DsDef
import com.monkeydp.daios.dms.sdk.datasource.DsVersion
import com.monkeydp.daios.dms.sdk.dm.SdkDmApp
import com.monkeydp.daios.dms.sdk.helper.ScopeHelper
import com.monkeydp.daios.dms.sdk.share.conn.ConnContext
import com.monkeydp.daios.dms.sdk.dm.dmKodeinRepo
import com.monkeydp.daios.dms.sdk.dm.findImpl
import com.monkeydp.tools.ext.kotlin.findAnnot
import com.monkeydp.tools.ext.kotlin.matchOne
import com.monkeydp.tools.ext.kotlin.singleton
import com.monkeydp.tools.ext.reflections.getAnnotatedSingletons
import com.monkeydp.tools.ext.reflections.reflections
import com.monkeydp.tools.util.FileUtil
import org.kodein.di.generic.instance
import java.io.File
import java.io.FileFilter
import java.net.URL
import java.net.URLClassLoader
import kotlin.properties.Delegates

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
    
    private val moduleClassLoader: ModuleClassLoader
    private val sdkDmApp: SdkDmApp
    val datasource: Datasource
    private val dsDefMap: Map<DsVersion<*>, DsDef>
    
    init {
        moduleClassLoader = initClassLoader()
        sdkDmApp = loadSdkDmApp()
        datasource = sdkDmApp.datasource
        val dsDefs = findImpl<Iterable<DsDef>>()
        dsDefMap = dsDefs.map { it.version to it }.toMap()
        moduleClassLoader.specClassloaderMap = initSpecificClassLoaderMap()
    }
    
    private inline fun <reified T : Any> findImpl(tag: Any? = null) =
            dmKodeinRepo.findImpl<T>(datasource, tag)
    
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
            val loader = SpecificClassLoader(specificLibsUrls, moduleClassLoader)
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
            reflections(PackageName.dm, moduleClassLoader).run {
                getAnnotatedSingletons(SdkDmApp::class).matchOne { true }.run {
                    this.javaClass.kotlin.findAnnot<SdkDmApp>()
                }
            }
    
    fun findDsDef(dsVersion: DsVersion<*>) = dsDefMap.getValue(dsVersion)
    
    private class ModuleClassLoader(urls: Array<URL>, parent: ClassLoader) : URLClassLoader(urls, parent) {
        
        var specClassloaderMap: Map<DsVersion<*>, SpecificClassLoader> by Delegates.singleton(emptyMap())
        
        private val specClassLoaderOrNull
            get() =
                when {
                    ScopeHelper.inRequestScope -> {
                        val connContext: ConnContext by kodein.instance()
                        specClassloaderMap[connContext.cp.dsVersion]
                    }
                    else -> null
                }
        
        override fun loadClass(name: String): Class<*>? =
                try {
                    super.loadClass(name)
                } catch (e: ClassNotFoundException) {
                    specClassLoaderOrNull?.loadClass(name)
                }
    }
    
    private class SpecificClassLoader(urls: Array<URL>, parent: ModuleClassLoader) : URLClassLoader(urls, parent)
}