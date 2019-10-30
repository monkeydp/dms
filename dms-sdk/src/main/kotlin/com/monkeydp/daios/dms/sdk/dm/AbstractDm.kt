package com.monkeydp.daios.dms.sdk.dm

import com.fasterxml.jackson.databind.JsonNode
import com.monkeydp.daios.dms.sdk.metadata.node.NodeStructWrapper
import com.monkeydp.daios.dms.sdk.metadata.node.def.NodeDef
import com.monkeydp.tools.ext.getLogger

/**
 * @author iPotato
 * @date 2019/10/27
 */
abstract class AbstractDm : Dm {
    companion object {
        val log = getLogger()
    }
    
    abstract val nodeData: NodeData
    private var isNodeStructInitialized = false
    
    interface NodeData {
        val defMap: Map<String, NodeDef>
        val structWrapper: NodeStructWrapper
    }
    
    override fun initialize() {
        registerStaticComponents()
    }
    
    private fun registerStaticComponents() {
        log.info("Begin to register all dm static components!")
        initNodeStruct()
        DmImplRegistrar.registerAll(impl, datasource)
        DmTestdataRegistrar.registerAll(testdata)
        log.info("End to register all dm static components!")
    }
    
    /**
     * Initialize node structure
     */
    @Synchronized
    private fun initNodeStruct() {
        if (isNodeStructInitialized) return
        val struct = nodeData.structWrapper.structure
        recurAssignNodeDefChildren(struct)
        isNodeStructInitialized = true
    }
    
    private fun recurAssignNodeDefChildren(struct: JsonNode) {
        val defMap = nodeData.defMap
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
}