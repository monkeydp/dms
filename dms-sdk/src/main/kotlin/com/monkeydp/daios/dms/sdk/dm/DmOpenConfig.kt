package com.monkeydp.daios.dms.sdk.dm

import com.monkeydp.daios.dms.sdk.event.EventPublisher
import com.monkeydp.tools.ext.mockFile
import java.io.File

data class DmOpenConfig(
        /**
         * Dir where the dm is deployed
         */
        val deployDir: File,
        
        val eventPublisher: EventPublisher,
        
        val isMock: Boolean = false
) {
    companion object {
        fun mock() = DmOpenConfig(mockFile(), EventPublisher.mock(), true)
    }
}