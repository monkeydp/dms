package com.monkeydp.daios.dms.sdk.metadata.menu.item

import com.monkeydp.daios.dms.sdk.metadata.menu.def.MenuDef
import java.util.concurrent.CopyOnWriteArrayList

/**
 * @author iPotato
 * @date 2019/11/4
 */
class MenuPath : CopyOnWriteArrayList<MenuItem>() {
    
    companion object {
        fun of(vararg items: MenuItem): MenuPath {
            val path = MenuPath()
            path.addAll(items)
            return path
        }
        
        fun of(oldPath: MenuPath, vararg items: MenuItem): MenuPath {
            val path = MenuPath()
            path.addAll(oldPath)
            path.addAll(items)
            return path
        }
    }
    
    private val iterator by lazy { iterator() }
    
    fun recurFindNextDef(def: MenuDef): MenuDef? {
        if (!iterator.hasNext()) return def
        val item = iterator.next()
        var nextDef = def.items.firstOrNull() { it.info.instr == item.instr }?.menuDef
        if (iterator.hasNext() && nextDef != null) nextDef = recurFindNextDef(nextDef)
        return nextDef
    }
}