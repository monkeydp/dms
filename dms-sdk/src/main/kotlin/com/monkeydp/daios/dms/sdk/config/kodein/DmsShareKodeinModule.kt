package com.monkeydp.daios.dms.sdk.config.kodein

import com.monkeydp.daios.dms.sdk.event.EventPublisher
import com.monkeydp.tools.ext.kotlin.notNullSingleton
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import kotlin.properties.Delegates

/**
 * @author iPotato
 * @date 2019/12/15
 */
private val mockDmsShareKodeinModule = Kodein.Module("mockDmsShareKodeinModule") {
    bind<EventPublisher>() with singleton { EventPublisher.mock() }
}

var dmsShareKodeinModule: Kodein.Module by Delegates.notNullSingleton(mockDmsShareKodeinModule)