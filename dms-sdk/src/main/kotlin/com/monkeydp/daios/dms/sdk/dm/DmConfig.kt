package com.monkeydp.daios.dms.sdk.dm

import com.monkeydp.daios.dms.sdk.event.EventPublisher
import com.monkeydp.tools.ext.java.mockFile
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import java.io.File

/**
 * @author iPotato
 * @date 2019/12/4
 */
data class DmConfig(
        /**
         * Dir where the dm is deployed
         */
        val deployDir: File,
        val kotlinModule: Kodein.Module,
        val isMock: Boolean = false
) {
    companion object {
        fun mock() = DmConfig(
                deployDir = mockFile(),
                kotlinModule = Kodein.Module("mockKodeinModule") {
                    bind<EventPublisher>() with singleton { EventPublisher.mock() }
                },
                isMock = true
        )
    }
}