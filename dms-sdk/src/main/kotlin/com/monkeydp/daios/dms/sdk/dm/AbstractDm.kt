package com.monkeydp.daios.dms.sdk.dm

import com.fasterxml.jackson.databind.JsonNode
import com.monkeydp.daios.dms.sdk.metadata.node.NodeStructWrapper
import com.monkeydp.daios.dms.sdk.metadata.node.def.NodeDef
import com.monkeydp.tools.ext.getClassname
import com.monkeydp.tools.ext.getLogger
import com.monkeydp.tools.ext.singletonInstance
import com.monkeydp.tools.util.FileUtil

/**
 * @author iPotato
 * @date 2019/10/27
 */
abstract class AbstractDm(config: DmShareConfig? = null) : Dm {
    
    companion object {
        private val log = getLogger()
        @Volatile
        private var isNodeStructInitialized = false
    }
    
    private var classLoader = Thread.currentThread().contextClassLoader
    protected abstract val config: LocalConfig
    
    
    init {
        if (config != null) {
            classLoader = config.classLoader
            updateConfig(config)
        }
    }
    
    protected abstract fun updateConfig(config: DmShareConfig)
    
    protected interface LocalConfig {
        val classesDirpath: String
        val node: Node
        
        interface Node {
            val structWrapper: NodeStructWrapper
            val defDirpath: String
        }
    }
    
    protected fun registerStaticComponents() {
        log.info("Begin to register all dm static components!")
        initNodeStruct()
        DmImplRegistrar.registerAll(impl, datasource)
        DmTestdataRegistrar.registerAll(testdata)
        log.info("End to register all dm static components!")
    }
    
    /**
     * Initialize node structure
     */
    private fun initNodeStruct() {
        if (isNodeStructInitialized) return
        val struct = config.node.structWrapper.structure
        recurAssignNodeDefChildren(struct)
        isNodeStructInitialized = true
    }
    
    private fun recurAssignNodeDefChildren(struct: JsonNode) {
        val defMap = nodeDefMap()
        val fields = struct.fields()
        fields.forEach { (structName, subStruct) ->
            val def = defMap.getValue(structName)
            val children = mutableListOf<NodeDef>()
            subStruct.fields().forEach { (subStructName, _) ->
                children.add(defMap.getValue(subStructName))
            }
            def.children = children.toList()
            recurAssignNodeDefChildren(subStruct)
        }
    }
    
    private fun nodeDefMap(): Map<String, NodeDef> {
        val files = FileUtil.listFiles(config.node.defDirpath)
        return files.map { file ->
            val classname = file.getClassname(config.classesDirpath)
            val nd = classLoader.loadClass(classname).singletonInstance() as NodeDef
            nd.structName to nd
        }.toMap()
    }
}