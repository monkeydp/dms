package com.monkeydp.daios.dms.sdk.metadata.menu.item

import com.monkeydp.daios.dms.sdk.instruction.Instruction
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon

/**
 * @author iPotato
 * @date 2019/10/31
 */
interface MenuItem {
    val instr: Instruction
    val name: String
    val icon: Icon<*>
    val hasSubmenu: Boolean
    var status: MenuItemStatus
    
    companion object {
        operator fun invoke(
                instr: Instruction,
                name: String,
                icon: Icon<*>,
                hasSubmenu: Boolean,
                status: MenuItemStatus = MenuItemStatus.ENABLED
        ): MenuItem = StdMi(instr, name, icon, hasSubmenu, status)
    }
}

abstract class AbstractMi(
        override val instr: Instruction,
        override val name: String,
        override val icon: Icon<*>,
        override val hasSubmenu: Boolean,
        override var status: MenuItemStatus = MenuItemStatus.ENABLED
) : MenuItem

class StdMi(
        instr: Instruction,
        name: String,
        icon: Icon<*>,
        hasSubmenu: Boolean,
        status: MenuItemStatus = MenuItemStatus.ENABLED
) : AbstractMi(instr, name, icon, hasSubmenu, status)

enum class MenuItemStatus {
    ENABLED, DISABLED
}