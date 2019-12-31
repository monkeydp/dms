package com.monkeydp.daios.dms.sdk.context

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import org.kodein.di.threadLocal

/**
 * @author iPotato
 * @date 2019/12/31
 */
interface ContextRepoHolder {
    companion object {
        private val holder = ThreadLocal<ContextRepo?>()
        private val contextRepo: ContextRepo get() = holder.get()!!
        
        fun hasContextRepo() = holder.get() != null
        
        fun setContextRepo(contextRepo: ContextRepo) {
            holder.set(contextRepo)
        }
        
        fun setContextRepo(init: ContextRepoBuilder.() -> Unit) {
            setContextRepo(ContextRepo(init))
        }
        
        fun updateContextRepo(init: ContextRepoBuilder.() -> Unit) {
            ContextRepo.copy(contextRepo, init).also(::setContextRepo)
        }
        
        fun resetContextRepo() {
            holder.remove()
        }
        
        fun Kodein.Builder.bindAllContexts() {
            bind<ConnContext>() with singleton(ref = threadLocal) { contextRepo.connContext!! }
            bind<NodeContext>() with singleton(ref = threadLocal) { contextRepo.nodeContext!! }
        }
    }
}