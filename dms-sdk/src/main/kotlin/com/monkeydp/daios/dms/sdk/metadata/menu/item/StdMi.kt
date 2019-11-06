package com.monkeydp.daios.dms.sdk.metadata.menu.item

import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import com.monkeydp.daios.dms.sdk.instruction.Instruction
import com.monkeydp.daios.dms.sdk.metadata.menu.item.MenuItemStatus.ENABLED

/**
 * @author iPotato
 * @date 2019/11/4
 */
data class StdMi(
        override val instr: Instruction,
        override val name: String,
        override val icon: Icon<*>,
        override val hasSubmenu: Boolean,
        override var status: MenuItemStatus = ENABLED
) : MenuItem