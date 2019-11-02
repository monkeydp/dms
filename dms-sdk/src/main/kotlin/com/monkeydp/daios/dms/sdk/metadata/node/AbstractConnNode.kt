package com.monkeydp.daios.dms.sdk.metadata.node

import com.monkeydp.daios.dms.sdk.entity.ConnProfile
import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.GlobalTarget
import com.monkeydp.tools.ext.notNullSingleton
import kotlin.properties.Delegates

/**
 * @author iPotato
 * @date 2019/11/2
 */
abstract class AbstractConnNode(
        def: ConnNode? = null,
        cp: ConnProfile? = null
) : ConnNode, AbstractNode(def) {
    override var cp by Delegates.notNullSingleton<ConnProfile>()
        protected set
    override val name by lazy { cp?.form?.connName ?: "" }
    override val target = GlobalTarget.CONN
    override val icon = GlobalIcon.CONN_ICON
    
    init {
        if (cp != null) this.cp = cp
    }
}