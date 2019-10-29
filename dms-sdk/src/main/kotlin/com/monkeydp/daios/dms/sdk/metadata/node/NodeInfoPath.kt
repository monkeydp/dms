package com.monkeydp.daios.dms.sdk.metadata.node

import com.monkeydp.daios.dms.sdk.metadata.instruction.target.Target
import com.monkeydp.daios.dms.sdk.metadata.node.def.NodeDef
import com.monkeydp.tools.function.ierror
import com.monkeydp.tools.function.replaceLast
import kotlin.reflect.full.createInstance

/**
 * @author iPotato
 * @date 2019/10/25
 */
open class NodeInfoPath : ArrayList<NodeInfo>() {
    companion object {
        fun of(vararg infos: NodeInfo, name: String? = null): NodeInfoPath {
            val path = NodeInfoPath()
            path.addAll(infos)
            path.replaceLastNodeName(name)
            return path
        }
    
        fun of(oldPath: NodeInfoPath, vararg infos: NodeInfo, name: String? = null): NodeInfoPath {
            val path = NodeInfoPath()
            path.addAll(oldPath)
            path.addAll(infos)
            path.replaceLastNodeName(name)
            return path
        }
    
        fun of(vararg defs: NodeDef, name: String? = null): NodeInfoPath {
            val path = NodeInfoPath()
            defs.forEach { path.add(it.info) }
            path.replaceLastNodeName(name)
            return path
        }
    
        fun of(oldPath: NodeInfoPath, vararg defs: NodeDef, name: String? = null): NodeInfoPath {
            val path = NodeInfoPath()
            path.addAll(oldPath)
            defs.forEach { path.add(it.info) }
            path.replaceLastNodeName(name)
            return path
        }
    }
    
    inline fun <reified P : NodeInfoPath> toSub(): P {
        val path = P::class.createInstance()
        path.addAll(this)
        return path
    }
    
    fun replaceLastNodeName(name: String?) {
        if (name != null) this.replaceLast(this.last().copy(name = name))
    }
    
    open fun getLastNodeDef(): NodeDef {
        throw ierror("You have to implement this fun in subclass!")
    }
}