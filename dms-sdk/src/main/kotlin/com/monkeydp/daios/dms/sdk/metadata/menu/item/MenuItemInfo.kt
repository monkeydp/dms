package com.monkeydp.daios.dms.sdk.metadata.menu.item

import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import com.monkeydp.daios.dms.sdk.metadata.instruction.Instruction

/**
 * @author iPotato
 * @date 2019/11/4
 */
data class MenuItemInfo(
        val instr: Instruction,
        val name: String,
        val icon: Icon<*>
)