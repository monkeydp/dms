package com.monkeydp.daios.dms.sdk.event

import com.monkeydp.tools.logger.getLogger

/**
 * @author iPotato
 * @date 2019/11/7
 */
object MockEventPublisher : AbstractEventPublisher() {
    private val log = getLogger()
    override fun publish(event: Event) {
        log.debug("Mock publishing $event.")
    }
}