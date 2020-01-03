package com.monkeydp.daios.dms.sdk.ui.context

import com.monkeydp.daios.dms.sdk.context.NodeContext
import com.monkeydp.daios.dms.sdk.context.SelectedContext

/**
 * @author iPotato
 * @date 2019/12/30
 */
data class UiContextRepo(
        val connContext: UiConnContext,
        val nodeContext: NodeContext? = null,
        val selectedContext: SelectedContext? = null
)