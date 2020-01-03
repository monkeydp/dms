package com.monkeydp.daios.dms.component

import com.monkeydp.daios.dms.sdk.context.ConnContext
import com.monkeydp.daios.dms.sdk.context.ContextRepo
import com.monkeydp.daios.dms.sdk.context.ContextRepoHolder
import com.monkeydp.daios.dms.sdk.ui.context.UiContextRepo
import com.monkeydp.daios.dms.service.contract.ConnService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * @author iPotato
 * @date 2019/11/24
 */
@Component
class ContextManager @Autowired constructor(
        private val session: UserSession,
        private val connService: ConnService
) {
    private val uiContextRepo: UiContextRepo? get() = session.getAttributeOrNull()
    private val contextRepo
        get() = uiContextRepo?.run {
            ContextRepo(
                    connContext = connContext.cpId.let { cpId ->
                        ConnContext(
                                cp = connService.findCp(cpId),
                                conn = connService.findActiveConnOrNull(cpId)
                        )
                    },
                    nodeContext = nodeContext,
                    selectedContext = selectedContext
            )
        }
    
    fun initContextHolder() {
        contextRepo?.apply(ContextRepoHolder::setContextRepo)
    }
    
    fun resetContextHolder() {
        ContextRepoHolder.resetContextRepo()
    }
}