package com.monkeydp.daios.dms.sdk.ui.context

import com.monkeydp.daios.dms.sdk.conn.ConnProfile

/**
 * @author iPotato
 * @date 2019/12/30
 */
interface UiConnContext : UiContext {
    /**
     * @see ConnProfile.id
     */
    val cpId: Long
    
    companion object {
        operator fun invoke(cpId: Long): UiConnContext = StdUiConnContext(cpId)
    }
}

class StdUiConnContext(
        override val cpId: Long
) : UiConnContext