package com.monkeydp.daios.dms.sdk.config

import com.monkeydp.daios.dms.sdk.event.EventPublisher
import com.monkeydp.daios.dms.sdk.share.conn.ConnContext
import com.monkeydp.daios.dms.sdk.share.request.RequestContextHolder
import com.monkeydp.tools.ext.kodein.KodeinHelper.bindComps
import com.monkeydp.tools.ext.kotlin.singleton
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import org.kodein.di.threadLocal
import kotlin.properties.Delegates

/**
 * @author iPotato
 * @date 2019/12/15
 */
private val mockDmsKodeinModule = Kodein.Module("mockDmsKodeinModule") {
    bind<EventPublisher>() with singleton { EventPublisher.mock() }
}

var dmsKodeinModule: Kodein.Module by Delegates.singleton(mockDmsKodeinModule)

val sdkKodeinModule = Kodein.Module("sdkKodeinModule") {
    bind<ConnContext>() with singleton(ref = threadLocal) {
        ConnContext(RequestContextHolder.requestAttributes.attrs)
    }
    bindComps(SdkKodeinCompRepo.comps)
}

val kodeinModules get() = arrayOf(sdkKodeinModule, dmsKodeinModule)