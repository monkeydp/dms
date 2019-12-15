package com.monkeydp.daios.dms.sdk.share.kodein

import com.monkeydp.daios.dms.sdk.event.EventPublisher
import com.monkeydp.daios.dms.sdk.share.request.MyRequestContext
import com.monkeydp.daios.dms.sdk.share.request.RequestAttributes
import com.monkeydp.daios.dms.sdk.share.request.RequestContext
import com.monkeydp.tools.ext.kotlin.notNullSingleton
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import kotlin.properties.Delegates

/**
 * @author iPotato
 * @date 2019/12/15
 */
private val mockDmsKodeinModule = Kodein.Module("mockDmsKodeinModule") {
    bind<EventPublisher>() with singleton { EventPublisher.mock() }
}

var dmsKodeinModule: Kodein.Module by Delegates.notNullSingleton(mockDmsKodeinModule)

val sdkKodeinModule = Kodein.Module("sdkKodeinModule") {
    MyRequestContext.also {
        bind<RequestContext>() with singleton { it }
        bind<RequestAttributes>() with provider { it.requestAttributes }
    }
}

val kodeinModules get() = arrayOf(dmsKodeinModule, sdkKodeinModule)