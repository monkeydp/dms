package com.monkeydp.daios.dms.sdk.metadata.menu

import com.monkeydp.daios.dms.sdk.instruction.InstrHelper
import com.monkeydp.daios.dms.sdk.instruction.Instruction
import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon.EMPTY_ICON
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import java.util.concurrent.atomic.AtomicInteger

/**
 * @author iPotato
 * @date 2019/10/31
 */
interface MenuItemDef {
    
    val id: Int
    
    val instr: Instruction
    val name: String
    val icon: Icon<*>
    
    val menuDef: MenuDef?
    
    fun create(): MenuItem
    
    companion object {
        operator fun invoke(
                instr: Instruction,
                name: String? = null,
                icon: Icon<*>? = null,
                menuDef: MenuDef? = null
        ): MenuItemDef =
                StdMid(instr, name, icon, menuDef)
    }
}

abstract class AbstractMid(
        instr: Instruction? = null,
        name: String? = null,
        icon: Icon<*>? = null,
        override val menuDef: MenuDef? = null
) : MenuItemDef {
    
    companion object {
        private val idGenerator = AtomicInteger()
    }
    
    override val id = idGenerator.incrementAndGet()
    
    override val instr = instr ?: InstrHelper.getInstrByClassname(this)
    override val name = name ?: instr?.toString() ?: this.instr.toString()
    override val icon: Icon<*> = icon ?: EMPTY_ICON
    
    override fun create() =
            MenuItem(
                    defId = id,
                    instr = instr,
                    name = name,
                    icon = icon,
                    hasMenu = menuDef != null
            )
    
    override fun toString() = name
}

private class StdMid(
        override val instr: Instruction,
        name: String? = null,
        icon: Icon<*>? = null,
        menuDef: MenuDef? = null
) : AbstractMid(instr, name, icon, menuDef)