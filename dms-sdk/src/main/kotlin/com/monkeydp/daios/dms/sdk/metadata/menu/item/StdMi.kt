package com.monkeydp.daios.dms.sdk.metadata.menu.item

import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import com.monkeydp.daios.dms.sdk.metadata.instruction.Instruction

/**
 * @author iPotato
 * @date 2019/11/4
 */
class StdMi : AbstractMi {
    constructor(def: MenuItemDef) : super(def)
    constructor(instr: Instruction, name: String, icon: Icon<*>, hasSubmenu: Boolean, status: MenuItemStatus) :
            super(instr, name, icon, hasSubmenu, status)
}