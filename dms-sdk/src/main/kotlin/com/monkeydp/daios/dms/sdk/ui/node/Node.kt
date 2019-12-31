package com.monkeydp.daios.dms.sdk.ui.node

import com.monkeydp.daios.dms.sdk.instruction.target.Target
import com.monkeydp.daios.dms.sdk.ui.icon.Icon

/**
 * @author iPotato
 * @date 2019/10/25
 */
interface Node {
    
    val defId: Int
    
    val target: Target<*>
    val name: String
    val icon: Icon<*>
    
    val menuDefId: Int?
    val hasMenu: Boolean get() = menuDefId != null
    
    companion object {
        operator fun invoke(
                defId: Int,
                
                target: Target<*>,
                name: String,
                icon: Icon<*>,
                
                menuDefId: Int?
        ): Node = StdNode(defId, target, name, icon, menuDefId)
    }
}

abstract class AbstractNode(
        override val defId: Int,
        
        override val target: Target<*>,
        override val name: String,
        override val icon: Icon<*>,
        
        override val menuDefId: Int?
) : Node {
    override fun toString() = "(target = $target, name = $name)"
}

class StdNode(
        defId: Int,
        
        target: Target<*>,
        name: String,
        icon: Icon<*>,
        
        menuDefId: Int?
) : AbstractNode(defId, target, name, icon, menuDefId)