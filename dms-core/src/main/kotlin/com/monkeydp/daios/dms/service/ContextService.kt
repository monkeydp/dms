package com.monkeydp.daios.dms.service

import com.monkeydp.daios.dms.component.UserSession
import com.monkeydp.daios.dms.component.UserSession.Key.UI_CONTEXT_REPO
import com.monkeydp.daios.dms.sdk.ui.context.UiContextRepo
import com.monkeydp.daios.dms.sdk.ui.context.UiContextRepoBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author iPotato
 * @date 2019/12/30
 */
interface ContextService {
    fun saveRepo(repo: UiContextRepo)
    fun saveRepo(repoBuilderInit: UiContextRepoBuilder.() -> Unit) =
            saveRepo(UiContextRepo(repoBuilderInit))
}

@Service
internal class ContextServiceImpl @Autowired constructor(
        private val session: UserSession
) : ContextService {
    override fun saveRepo(repo: UiContextRepo) {
        session.setAttribute(UI_CONTEXT_REPO, repo)
    }
}