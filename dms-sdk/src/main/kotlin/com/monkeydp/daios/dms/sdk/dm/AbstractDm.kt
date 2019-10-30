package com.monkeydp.daios.dms.sdk.dm

import com.monkeydp.daios.dms.sdk.metadata.node.NodeStruct
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

//    abstract val nodeData: NodeData
    
    interface NodeData {
        val defs: List<NodeDef>
        val struct: NodeStruct
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
    private fun initNodeStruct() {
        // TODO
    }
}