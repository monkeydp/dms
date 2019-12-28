package com.monkeydp.daios.dms.sdk.metadata.node

/**
 * @author iPotato
 * @date 2019/11/29
 */
interface NodeDefStruct {
    /**
     * tree struct
     */
    val struct: List<NodeDef>
    
    /**
     * flatten struct
     */
    val flattenStruct: List<NodeDef>
    
    /**
     * @param id ↓
     * @see NodeDef.id
     */
    fun find(id: Int) = flattenStruct.first { it.id == id }
}

abstract class AbstractNdStruct(def: NodeDef) : NodeDefStruct {
    override val struct = listOf(def)
    override val flattenStruct = recurFlatten(struct)
    
    private fun recurFlatten(struct: List<NodeDef>): List<NodeDef> {
        val defs = mutableListOf<NodeDef>()
        struct.forEach {
            defs.add(it)
            defs.addAll(recurFlatten(it.children))
        }
        return defs.toList()
    }
}

inline fun <reified ND : NodeDef> NodeDefStruct.find(): ND =
        flattenStruct.first { it is ND } as ND