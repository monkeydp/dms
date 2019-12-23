package com.monkeydp.daios.dms.sdk.share.kodein

import com.monkeydp.daios.dms.sdk.config.SdkKodeinCompRepo
import com.monkeydp.daios.dms.sdk.event.EventPublisher
import com.monkeydp.daios.dms.sdk.share.conn.ConnContext
import com.monkeydp.daios.dms.sdk.share.request.RequestContext
import com.monkeydp.tools.ext.kodein.KodeinHelper.bindComps
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
    RequestContext.SINGLETON.also {
        bind<RequestContext>() with singleton { it }
        bind<ConnContext>() with provider { ConnContext(it.requestAttributes.attrs) }
    }
    bindComps(SdkKodeinCompRepo.comps)
}

val kodeinModules get() = arrayOf(sdkKodeinModule, dmsKodeinModule)