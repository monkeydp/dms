package com.monkeydp.daios.dms.sdk.mocker

import com.monkeydp.daios.dms.sdk.ui.node.NodeDef
import com.monkeydp.daios.dms.sdk.ui.node.NodePath
import com.monkeydp.daios.dms.sdk.ui.node.NodePathRepo
import com.monkeydp.tools.ext.kotlin.matchOne
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf

/**
 * @author iPotato
 * @date 2019/12/30
 */
interface NodePathMocker {
    val current: NodePath
}

abstract class AbstractNodePathMocker : NodePathMocker {
    
    val currentHolder: ThreadLocal<NodePath> = ThreadLocal()
    override val current: NodePath get() = currentHolder.get()
    
    protected abstract val repo: NodePathRepo
    val map: Map<KClass<out NodeDef>, NodePath>
        get() =
            repo.getPaths().map {
                it.getLastNodeDef()::class to it
            }.toMap()
    
    inline fun <reified ND : NodeDef> switchNodePath() {
        val key = map.keys.matchOne { it.isSubclassOf(ND::class) }
        currentHolder.set(map.getValue(key))
    }
}