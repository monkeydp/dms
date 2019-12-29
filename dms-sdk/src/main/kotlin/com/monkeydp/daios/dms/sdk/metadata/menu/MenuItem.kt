package com.monkeydp.daios.dms.sdk.metadata.menu

import com.monkeydp.daios.dms.sdk.instruction.Instruction
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import com.monkeydp.daios.dms.sdk.metadata.menu.MenuItemStatus.ENABLED

/**
 * @author iPotato
 * @date 2019/10/31
 */
interface MenuItem {
    
    val instr: Instruction
    val name: String
    val icon: Icon<*>
    
    val menuDefId: Int?
    val hasMenu: Boolean get() = menuDefId != null
    
    var status: MenuItemStatus
    
    companion object {
        operator fun invoke(
                instr: Instruction,
                name: String,
                icon: Icon<*>,
                menuDefId: Int?,
                status: MenuItemStatus = ENABLED
        ): MenuItem =
                StdMi(instr, name, icon, menuDefId, status)
    }
}

abstract class AbstractMi(
        override val instr: Instruction,
        override val name: String,
        override val icon: Icon<*>,
        
        override val menuDefId: Int?,
        override var status: MenuItemStatus = ENABLED
) : MenuItem

class StdMi(
        instr: Instruction,
        name: String,
        icon: Icon<*>,
        
        menuDefId: Int?,
        status: MenuItemStatus = ENABLED
) : AbstractMi(instr, name, icon, menuDefId, status)

enum class MenuItemStatus {
    ENABLED, DISABLED
}