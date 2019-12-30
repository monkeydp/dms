package com.monkeydp.daios.dms.sdk.ui.node

import com.monkeydp.daios.dms.sdk.share.kodein.dmKodeinRepo
import com.monkeydp.daios.dms.sdk.share.kodein.findImpl
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
    
    private val ndStruct: NodeDefStruct get() = dmKodeinRepo.findImpl()
    
    fun getLastNodeDef(): NodeDef = ndStruct.find(last().defId)
}