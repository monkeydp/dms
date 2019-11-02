package com.monkeydp.daios.dms.sdk.metadata.node

import com.fasterxml.jackson.annotation.JsonIgnore
import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon.EMPTY_ICON
import com.monkeydp.daios.dms.sdk.metadata.icon.Icon
import com.monkeydp.daios.dms.sdk.metadata.menu.Menu
import com.monkeydp.tools.ext.*
import kotlin.properties.Delegates
import kotlin.reflect.KClass

/**
 * @author iPotato
 * @date 2019/10/25
 */
abstract class AbstractNode(
        name: String = ""
) : Node {
    override val structName by lazy @JsonIgnore {
        if (this.javaClass.kotlin.isAbstract) "<no name for abstract>"
        else this.javaClass.simpleName.camelCase2List().lastOf(1).toLowerCase()
    }
    override val name: String = name
    override val icon: Icon<*> = EMPTY_ICON
    @JsonIgnore
    override var parent: Node? = null
    override var children by Delegates.notNullSingleton<List<Node>>()
    @JsonIgnore
    override var menu: Menu? = null
    open val initKClass: KClass<out Node>? = null
    
    @Suppress("UNCHECKED_CAST")
    override fun <N : Node> create(name: String): N {
        if (initKClass == null) ierror("Cannot create node, undefined KClass for init!")
        return initKClass?.java?.newInstanceX(name) as N
    }
}