package com.monkeydp.daios.dms.sdk.mocker

import com.monkeydp.daios.dms.sdk.context.ContextRepoHolder
import com.monkeydp.daios.dms.sdk.event.EventPublisher
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

/**
 * @author iPotato
 * @date 2019/12/30
 */
interface KodeinModulesMocker {
    companion object {
        val mockDmsKodeinModule
            get() = Kodein.Module("mockDmsKodeinModule") {
                bind<EventPublisher>() with singleton { EventPublisher.mock() }
                with(ContextRepoHolder) { bindAllContexts() }
            }
    }
}