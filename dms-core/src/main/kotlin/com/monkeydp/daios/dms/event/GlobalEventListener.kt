package com.monkeydp.daios.dms.event

import com.monkeydp.daios.dms.sdk.event.Event
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

/**
 * @author iPotato
 * @date 2019/11/7
 */
@Component
class GlobalEventListener {
    @EventListener
    fun handle(event: Event) {
        if (event.notifyUi) {
            // TODO Send message to ui
            println()
        }
    }
}