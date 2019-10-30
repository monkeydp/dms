package com.monkeydp.daios.dms.sdk.metadata.node.def.impl

import com.monkeydp.daios.dms.sdk.metadata.node.def.NodeDef
import com.monkeydp.tools.ext.camelCase2List
import com.monkeydp.tools.ext.lastOf

/**
 * @author iPotato
 * @date 2019/10/27
 */
abstract class AbstractNd : NodeDef {
    override val structureName: String
        get() {
            val classname = this.javaClass.simpleName
            if (classname == "abstract") return "<no name for abstract>"
            return classname.camelCase2List().lastOf(1).toLowerCase()
        }
    override val parent: NodeDef? = null
    override val children = emptyList<NodeDef>()
}