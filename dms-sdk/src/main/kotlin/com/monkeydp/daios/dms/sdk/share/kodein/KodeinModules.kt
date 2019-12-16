package com.monkeydp.daios.dms.sdk.share.kodein

import com.monkeydp.daios.dms.sdk.event.EventPublisher
import com.monkeydp.daios.dms.sdk.instruction.action.Action
import com.monkeydp.daios.dms.sdk.instruction.action.GlobalAction
import com.monkeydp.daios.dms.sdk.share.conn.ConnContext
import com.monkeydp.daios.dms.sdk.share.conn.connContext
import com.monkeydp.daios.dms.sdk.share.request.RequestContext
import com.monkeydp.daios.dms.sdk.share.request.requestContext
import com.monkeydp.tools.ext.kotlin.notNullSingleton
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import kotlin.properties.Delegates
import kotlin.reflect.KClass

/**
 * @author iPotato
 * @date 2019/12/15
 */
private val mockDmsKodeinModule = Kodein.Module("mockDmsKodeinModule") {
    bind<EventPublisher>() with singleton { EventPublisher.mock() }
}

var dmsKodeinModule: Kodein.Module by Delegates.notNullSingleton(mockDmsKodeinModule)

val sdkKodeinModule = Kodein.Module("sdkKodeinModule") {
    requestContext.also {
        bind<RequestContext>() with singleton { it }
        bind<ConnContext>() with provider { connContext(it.requestAttributes.attrs) }
    }
    bind<KClass<out Action<*>>>() with singleton { GlobalAction::class }
}

val kodeinModules get() = arrayOf(dmsKodeinModule, sdkKodeinModule)