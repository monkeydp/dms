package com.monkeydp.daios.dms.config

import com.monkeydp.daios.dms.sdk.context.ContextRepoHolder
import com.monkeydp.daios.dms.sdk.event.EventPublisher
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * @author iPotato
 * @date 2019/12/31
 */
@Component
class DmsKodeinModule @Autowired constructor(
        private val eventPublisher: EventPublisher
) {
    fun get() =
            Kodein.Module("dmsKodeinModule") {
                bind<EventPublisher>() with singleton { eventPublisher }
                with(ContextRepoHolder) { bindAllContexts() }
            }
}