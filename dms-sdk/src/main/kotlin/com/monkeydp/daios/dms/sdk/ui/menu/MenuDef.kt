package com.monkeydp.daios.dms.sdk.ui.menu

import com.monkeydp.daios.dms.sdk.instruction.Instruction
import com.monkeydp.tools.ext.kotlin.initInstance
import java.util.concurrent.atomic.AtomicInteger

/**
 * @author iPotato
 * @date 2019/11/4
 */
interface MenuDef {
    
    val id: Int
    val items: List<MenuItemDef>
    
    operator fun MenuItemDef.unaryPlus()
    operator fun Instruction.unaryPlus()
    fun create(): Menu
    
    companion object {
        operator fun invoke(init: MenuDef.() -> Unit): MenuDef = initInstance<StdMenuDef>(init)
    }
}

abstract class AbstractMenuDef : MenuDef {
    
    companion object {
        private val idGenerator = AtomicInteger()
    }
    
    override val id = idGenerator.incrementAndGet()
    
    private val _items = mutableListOf<MenuItemDef>()
    override val items get() = _items.toList()
    
    override operator fun MenuItemDef.unaryPlus() {
        _items.add(this)
    }
    
    override fun Instruction.unaryPlus() {
        also { _items.add(MenuItemDef(instr = it)) }
    }
    
    override fun create() = Menu(_items.map { it.create() }.toList())
}

private class StdMenuDef : AbstractMenuDef()