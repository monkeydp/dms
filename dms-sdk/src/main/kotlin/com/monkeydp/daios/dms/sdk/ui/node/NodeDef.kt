package com.monkeydp.daios.dms.sdk.ui.node

import com.monkeydp.daios.dms.sdk.instruction.target.GlobalTarget
import com.monkeydp.daios.dms.sdk.instruction.target.Target
import com.monkeydp.daios.dms.sdk.ui.icon.GlobalIcon
import com.monkeydp.daios.dms.sdk.ui.icon.Icon
import com.monkeydp.daios.dms.sdk.ui.menu.MenuDef
import com.monkeydp.tools.ext.kotlin.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.properties.Delegates

/**
 * @author iPotato
 * @date 2019/11/3
 */
interface NodeDef {
    
    val id: Int
    
    var target: Target<*>
    var name: String
    var icon: Icon<*>
    
    var parent: NodeDef?
    val children: List<NodeDef>
    
    var menuDef: MenuDef?
    
    /**
     * Add a child by "+"
     */
    operator fun NodeDef.unaryPlus()
    
    /**
     * Create a node by current node def
     */
    fun create(name: String? = null): Node
}

abstract class AbstractNd : NodeDef {
    
    companion object {
        private val idGenerator = AtomicInteger()
    }
    
    override val id = idGenerator.incrementAndGet()
    
    private val nd: NodeDef = this
    
    protected val targetName = javaClass.simpleName.camelCase2List().lastOf(1)
    
    override var target: Target<*> by Delegates.singleton(defaultTarget())
    override var name: String by Delegates.singleton("")
    override var icon: Icon<*> by Delegates.singleton(defaultIcon())
    
    override var parent: NodeDef? by Delegates.singletonOrNull({ null })
    private val _children = mutableListOf<NodeDef>()
    override val children
        get() = _children.toList()
    
    override var menuDef: MenuDef? by Delegates.singletonOrNull({ null })
    
    override operator fun NodeDef.unaryPlus() {
        this.parent = nd
        _children.add(this)
    }
    
    override fun create(name: String?) =
            Node(
                    defId = id,
                    target = target,
                    name = name ?: this.name,
                    icon = icon,
                    menuDefId = menuDef?.id
            )
    
    protected open fun defaultTarget() = GlobalTarget::class.valueOfOrNull(targetName)
    protected open fun defaultIcon(suffix: String = GlobalIcon.SUFFIX) =
            GlobalIcon::class.valueOfOrNull(GlobalIcon.appendSuffix(suffix)) ?: GlobalIcon.EMPTY_ICON
    
    override fun toString() = this.name
}