package com.monkeydp.daios.dms.sdk.metadata.menu

/**
 * @author iPotato
 * @date 2019/12/29
 */
interface MenuDefStruct {
    /**
     * every element is tree struct
     */
    val struct: List<MenuDef>
    
    /**
     * every element without sub element
     */
    val flattenDefs: List<MenuDef>
    
    fun find(id: Int): MenuDef
}

abstract class AbstractMenuDefStruct(override val struct: List<MenuDef>) : MenuDefStruct {
    
    override val flattenDefs get() = struct.map { recurFlatten(it) }.flatten()
    
    private fun recurFlatten(def: MenuDef): List<MenuDef> {
        val defs = mutableListOf<MenuDef>(def)
        def.items.forEach {
            if (it.hasMenuDef) defs.addAll(recurFlatten(it.menuDef!!))
        }
        return defs.toList()
    }
    
    override fun find(id: Int): MenuDef = flattenDefs.first { it.id == id }
}