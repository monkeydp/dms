package com.monkeydp.daios.dms.sdk.metadata.node

import com.monkeydp.tools.ext.ierror
import com.monkeydp.tools.ext.replaceLast
import kotlin.reflect.full.createInstance

/**
 * @author iPotato
 * @date 2019/10/25
 */
open class NodePath : ArrayList<Node>() {
    companion object {
        fun of(vararg nodes: Node, name: String? = null): NodePath {
            val path = NodePath()
            path.addAll(nodes)
            path.replaceLastNodeName(name)
            return path
        }
        
        fun of(oldPath: NodePath, vararg nodes: Node, name: String? = null): NodePath {
            val path = NodePath()
            path.addAll(oldPath)
            path.addAll(nodes)
            path.replaceLastNodeName(name)
            return path
        }
    }
    
    inline fun <reified P : NodePath> toSub(): P {
        val path = P::class.createInstance()
        path.addAll(this)
        return path
    }
    
    fun replaceLastNodeName(name: String?) {
        if (name != null) this.replaceLast(this.last().create<Node>(name))
    }
    
    open fun getLastNode(): Node {
        throw ierror("You have to implement this fun in subclass!")
    }
}