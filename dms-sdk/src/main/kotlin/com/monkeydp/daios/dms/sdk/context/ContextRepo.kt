package com.monkeydp.daios.dms.sdk.context

import com.monkeydp.tools.ext.kotlin.copyPropValuesFrom

/**
 * @author iPotato
 * @date 2019/12/31
 */
interface ContextRepo {
    val connContext: ConnContext?
    val nodeContext: NodeContext?
    
    companion object {
        operator fun invoke(builder: ContextRepoBuilder): ContextRepo =
                StdContextRepo(builder)
        
        operator fun invoke(init: ContextRepoBuilder.() -> Unit): ContextRepo =
                this(ContextRepoBuilder(init))
        
        fun copy(source: ContextRepo, init: ContextRepoBuilder.() -> Unit): ContextRepo =
                ContextRepoBuilder().run {
                    copyPropValuesFrom(source)
                    init()
                    ContextRepo(this)
                }
    }
}

class StdContextRepo(
        override val connContext: ConnContext?,
        override val nodeContext: NodeContext?
) : ContextRepo {
    constructor(builder: ContextRepoBuilder) : this(
            builder.connContext,
            builder.nodeContext
    )
}

interface ContextRepoBuilder {
    var connContext: ConnContext?
    var nodeContext: NodeContext?
    
    companion object {
        operator fun invoke(): ContextRepoBuilder = StdContextRepoBuilder()
        operator fun invoke(init: ContextRepoBuilder.() -> Unit): ContextRepoBuilder =
                StdContextRepoBuilder().apply(init)
    }
}

class StdContextRepoBuilder : ContextRepoBuilder {
    override var connContext: ConnContext? = null
    override var nodeContext: NodeContext? = null
}