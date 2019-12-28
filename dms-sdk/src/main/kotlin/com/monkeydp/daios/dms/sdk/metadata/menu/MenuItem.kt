package com.monkeydp.daios.dms.sdk.metadata.menu

import com.monkeydp.daios.dms.sdk.instruction.Instruction
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon

/**
 * @author iPotato
 * @date 2019/10/31
 */
interface MenuItem {
    
    val defId: Int
    
    val instr: Instruction
    val name: String
    val icon: Icon<*>
    
    val hasMenu: Boolean
    var status: MenuItemStatus
    
    companion object {
        operator fun invoke(
                defId: Int,
                instr: Instruction,
                name: String,
                icon: Icon<*>,
                hasMenu: Boolean,
                status: MenuItemStatus = MenuItemStatus.ENABLED
        ): MenuItem =
                StdMi(defId, instr, name, icon, hasMenu, status)
    }
}

abstract class AbstractMi(
        override val defId: Int,
        
        override val instr: Instruction,
        override val name: String,
        override val icon: Icon<*>,
        
        override val hasMenu: Boolean,
        override var status: MenuItemStatus = MenuItemStatus.ENABLED
) : MenuItem

class StdMi(
        defId: Int,
        
        instr: Instruction,
        name: String,
        icon: Icon<*>,
        
        hasMenu: Boolean,
        status: MenuItemStatus = MenuItemStatus.ENABLED
) : AbstractMi(defId, instr, name, icon, hasMenu, status)

enum class MenuItemStatus {
    ENABLED, DISABLED
}