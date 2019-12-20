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
}

fun menuItem(
        instr: Instruction,
        name: String,
        icon: Icon<*>,
        hasSubmenu: Boolean,
        status: MenuItemStatus = MenuItemStatus.ENABLED
): MenuItem = StdMi(instr, name, icon, hasSubmenu, status)