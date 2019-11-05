package com.monkeydp.daios.dms.sdk.metadata.menu.item

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
    
    val iterator by lazy { iterator() }
}