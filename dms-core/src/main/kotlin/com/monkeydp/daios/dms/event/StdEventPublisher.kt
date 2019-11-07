package com.monkeydp.daios.dms.event

import com.monkeydp.daios.dms.sdk.event.AbstractEventPublisher
import com.monkeydp.daios.dms.sdk.event.Event
import com.monkeydp.tools.ext.getLogger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.support.AbstractApplicationContext
import org.springframework.stereotype.Component

/**
 * @author iPotato
 * @date 2019/11/7
 */
@Component
class StdEventPublisher : AbstractEventPublisher() {
    
    @Autowired
    private lateinit var applicationContext: AbstractApplicationContext
    
    companion object {
        val log = getLogger()
    }
    
    override fun publish(event: Event) {
        log.debug("Publish $event")
        applicationContext.publishEvent(event)
    }
}