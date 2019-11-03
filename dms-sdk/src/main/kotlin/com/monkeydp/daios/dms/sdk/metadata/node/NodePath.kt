package com.monkeydp.daios.dms.sdk.metadata.node

import com.monkeydp.daios.dms.sdk.metadata.node.def.NodeDef
import com.monkeydp.daios.dms.sdk.metadata.node.main.Node
import com.monkeydp.tools.ext.ierror
import kotlin.reflect.full.createInstance

/**
 * @author iPotato
 * @date 2019/10/25
 */
open class NodePath : ArrayList<Node>() {
    companion object {
        fun of(vararg nodes: Node): NodePath {
            val path = NodePath()
            path.addAll(nodes)
            return path
        }
    
        fun of(oldPath: NodePath, vararg nodes: Node): NodePath {
            val path = NodePath()
            path.addAll(oldPath)
            path.addAll(nodes)
            return path
        }
    }
    
    inline fun <reified P : NodePath> toSub(): P {
        val path = P::class.createInstance()
        path.addAll(this)
        return path
    }
    
    open fun getLastNodeDef(): NodeDef {
        throw ierror("You have to implement this fun in subclass!")
    }
}