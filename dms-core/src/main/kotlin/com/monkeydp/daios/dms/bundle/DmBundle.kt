package com.monkeydp.daios.dms.bundle

import com.monkeydp.daios.dms.sdk.connection.ConnFactory
import com.monkeydp.daios.dms.sdk.datasource.Datasource
import com.monkeydp.daios.dms.sdk.datasource.Datasource.DsVersion
import com.monkeydp.daios.dms.sdk.dm.Dm
import com.monkeydp.daios.dms.sdk.dm.Dm.DsDef
import com.monkeydp.daios.dms.sdk.dm.ImplContext.registerDataClass
import com.monkeydp.daios.dms.sdk.dm.ImplContext.registerEnum
import com.monkeydp.daios.dms.sdk.metadata.form.CpForm
import com.monkeydp.daios.dms.sdk.metadata.instruction.action.ActionType
import com.monkeydp.daios.dms.sdk.metadata.instruction.action.GlobalActionType
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.GlobalTargetType
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.TargetType
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
    
    private val dsDefMap: Map<DsVersion, DsDef>
    
    object Impls {
        lateinit var connFactory: ConnFactory
    }
    
    init {
        bundleClassLoader = initBundleClassLoader()
        dm = initDm()
        impls = initAllImplClasses()
        registerAllEnums()
        registerAllDataClass()
        dsDefMap = dm.dsDefs.map { it.version to it }.toMap()
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
    
    private fun initSpecificClassLoaderMap(): Map<DsVersion, SpecificClassLoader> {
        val map = mutableMapOf<DsVersion, SpecificClassLoader>()
        dsDefMap.forEach { (dsVersion, _) ->
            val specificLibsUrls = specificLibsUrls(dsVersion)
            val loader = SpecificClassLoader(specificLibsUrls, bundleClassLoader)
            map[dsVersion] = loader
        }
        return map
    }
    
    private fun specificLibsUrls(dsVersion: DsVersion): Array<URL> {
        val libsDir = File(deployDir, "$specificLibsPath/${dsVersion.id}")
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
    
    private fun initAllImplClasses(): Impls {
        val classnames = dm.impl.apiClassnames
        @Suppress("UNCHECKED_CAST")
        val connectionFactoryClass =
                bundleClassLoader.loadClass(classnames.connFactory) as Class<ConnFactory>
        Impls.connFactory = ClassUtil.newInstance(connectionFactoryClass)
        return Impls
    }
    
    private fun registerAllEnums() {
        registerAllGlobalEnums()
        registerAllLocalEnums()
    }
    
    private fun registerAllGlobalEnums() {
        GlobalActionType.values().forEach { registerEnum(it) }
        GlobalTargetType.values().forEach { registerEnum(it) }
    }
    
    @Suppress("UNCHECKED_CAST")
    private fun registerAllLocalEnums() {
        val classnames = dm.impl.enumClassnames
        val datasource = dm.datasource
        
        val actionTypeClass = bundleClassLoader.loadClass(classnames.actionType) as Class<ActionType<*>>
        actionTypeClass.enumConstants.forEach { registerEnum(it, datasource) }
        
        val targetTypeClass = bundleClassLoader.loadClass(classnames.targetType) as Class<TargetType<*>>
        targetTypeClass.enumConstants.forEach { registerEnum(it, datasource) }
    }
    
    @Suppress("UNCHECKED_CAST")
    private fun registerAllDataClass() {
        val classnames = dm.impl.dataClassnames
        val cpFormClass = bundleClassLoader.loadClass(classnames.cpForm) as Class<CpForm>
        registerDataClass(cpFormClass, dm.datasource)
    }
    
    fun getDsDriverClassname(dsVersion: DsVersion) = dsDefMap[dsVersion]?.driver?.classname!!
    
    fun setSpecificClassLoader(dsVersion: DsVersion) {
        bundleClassLoader.setSpecificClassLoader(dsVersion)
    }
    
    fun removeSpecificClassLoader() {
        bundleClassLoader.removeSpecificClassLoader()
    }
    
    private class BundleClassLoader(urls: Array<URL>, parent: ClassLoader) : URLClassLoader(urls, parent) {
        
        lateinit var specificClassLoaders: Map<DsVersion, SpecificClassLoader>
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
        
        fun setSpecificClassLoader(dsVersion: DsVersion) {
            sclThreadLocal.set(specificClassLoaders[dsVersion])
        }
        
        fun removeSpecificClassLoader() {
            sclThreadLocal.remove()
        }
    }
    
    private class SpecificClassLoader(urls: Array<URL>, parent: BundleClassLoader) : URLClassLoader(urls, parent) {
    }
}