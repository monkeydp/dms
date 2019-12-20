package com.monkeydp.daios.dms.sdk.metadata.menu.item

import com.monkeydp.daios.dms.sdk.instruction.Instruction
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import com.monkeydp.daios.dms.sdk.metadata.menu.item.MenuItemStatus.ENABLED

/**
 * @author iPotato
 * @date 2019/11/4
 */
class StdMi(
        instr: Instruction,
        name: String,
        icon: Icon<*>,
        hasSubmenu: Boolean,
        status: MenuItemStatus = ENABLED
) : AbstractMi(instr, name, icon, hasSubmenu, status)