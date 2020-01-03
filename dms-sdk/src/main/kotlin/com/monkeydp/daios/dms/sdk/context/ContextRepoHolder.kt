package com.monkeydp.daios.dms.sdk.context

import com.monkeydp.tools.ext.main.ierror
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

/**
 * @author iPotato
 * @date 2019/12/31
 */
object ContextRepoHolder {
    private val holder = ThreadLocal<ContextRepo?>()
    val contextRepo: ContextRepo get() = holder.get() ?: ierror("Context repo must be set before use!")
    
    fun hasContextRepo() = holder.get() != null
    
    fun setContextRepo(contextRepo: ContextRepo) {
        holder.set(contextRepo)
    }
    
    fun setContextRepo(connContext: ConnContext) {
        setContextRepo(ContextRepo(connContext))
    }
    
    fun resetContextRepo() {
        holder.remove()
    }
    
    fun Kodein.Builder.bindAllContexts() {
        bind<ConnContext>() with provider { contextRepo.connContext }
        bind<NodeContext>() with provider { contextRepo.nodeContext!! }
        bind<SelectedContext>() with provider { contextRepo.selectedContext!! }
    }
}