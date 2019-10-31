package com.monkeydp.daios.dms.sdk.metadata.menu

import com.monkeydp.daios.dms.sdk.metadata.instruction.Instruction

/**
 * @author iPotato
 * @date 2019/10/31
 */
interface MenuItemDef {
    val instr: Instruction
    val info: MenuItemInfo
    val menu: Menu?
}