package com.monkeydp.daios.dms.sdk.context

/**
 * @author iPotato
 * @date 2019/12/31
 */
data class ContextRepo(
        val connContext: ConnContext,
        val nodeContext: NodeContext? = null
)