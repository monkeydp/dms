package com.monkeydp.daios.dms.sdk.ui.context

/**
 * @author iPotato
 * @date 2019/12/30
 */
interface UiContextRepo {
    val connContext: UiConnContext?
    val nodeContext: UiNodeContext?
    
    companion object {
        operator fun invoke(init: UiContextRepoBuilder.() -> Unit): UiContextRepo =
                StdUiContextRepo(UiContextRepoBuilder(init))
    }
}

class StdUiContextRepo(
        override val connContext: UiConnContext?,
        override val nodeContext: UiNodeContext?
) : UiContextRepo {
    constructor(builder: UiContextRepoBuilder) : this(
            builder.connContext,
            builder.nodeContext
    )
}

interface UiContextRepoBuilder {
    var connContext: UiConnContext?
    var nodeContext: UiNodeContext?
    
    companion object {
        operator fun invoke(init: UiContextRepoBuilder.() -> Unit): UiContextRepoBuilder =
                StdUiContextRepoBuilder().apply(init)
    }
}

private class StdUiContextRepoBuilder : UiContextRepoBuilder {
    override var connContext: UiConnContext? = null
    override var nodeContext: UiNodeContext? = null
}