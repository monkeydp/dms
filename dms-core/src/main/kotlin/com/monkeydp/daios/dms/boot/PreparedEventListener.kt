package com.monkeydp.daios.dms.boot

import org.springframework.boot.context.event.ApplicationPreparedEvent
import org.springframework.context.ApplicationListener

/**
 * @author iPotato
 * @date 2019/10/8
 */
class PreparedEventListener : ApplicationListener<ApplicationPreparedEvent> {
    override fun onApplicationEvent(event: ApplicationPreparedEvent) {
        PreparatoryWork.init()
    }
}