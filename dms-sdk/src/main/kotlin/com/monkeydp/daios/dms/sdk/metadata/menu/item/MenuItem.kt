package com.monkeydp.daios.dms.sdk.metadata.menu.item

import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import com.monkeydp.daios.dms.sdk.instruction.Instruction

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