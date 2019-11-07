package com.monkeydp.daios.dms.sdk.event

/**
 * @author iPotato
 * @date 2019/11/7
 */
interface EventPublisher {
    fun publish(event: Event)
    
    companion object {
        fun mock() = MockEventPublisher
    }
}