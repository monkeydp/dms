package com.monkeydp.daios.dms.sdk.metadata.node.def

/**
 * @author iPotato
 * @date 2019/10/28
 */
abstract class AbstractNodeDefFactory {
    protected open fun connNodeDef() = ConnNodeDef()
    protected open fun dbNodeDef() = DbNodeDef()
    protected open fun tableNodeDef() = TableNodeDef()
    
    protected open fun dbGroupNodeDef() = DbGroupNodeDef()
    protected open fun tableGroupNodeDef() = TableGroupNodeDef()
}