package com.monkeydp.daios.dms.sdk.metadata.menu.item

import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import com.monkeydp.daios.dms.sdk.metadata.instruction.Instruction
import com.monkeydp.daios.dms.sdk.metadata.menu.Menu

/**
 * @author iPotato
 * @date 2019/10/31
 */
interface MenuItem {
    val instr: Instruction
    val name: String
    val icon: Icon<*>
    var menu: Menu?
}