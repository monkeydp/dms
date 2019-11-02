package com.monkeydp.daios.dms.sdk.metadata.node

import com.monkeydp.daios.dms.sdk.entity.ConnProfile
import com.monkeydp.daios.dms.sdk.metadata.icon.GlobalIcon
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.GlobalTarget
import com.monkeydp.tools.ext.newInstanceX
import com.monkeydp.tools.ext.notNullSingleton
import org.jetbrains.annotations.TestOnly
import kotlin.properties.Delegates

/**
 * @author iPotato
 * @date 2019/11/2
 */
abstract class AbstractConnNode(
        cp: ConnProfile? = null,
        name: String? = null
) : ConnNode, AbstractNode() {
    override var cp by Delegates.notNullSingleton<ConnProfile>()
        protected set
    override val name by lazy { cp?.form?.connName ?: name ?: "" }
    override val target = GlobalTarget.CONN
    override val icon = GlobalIcon.CONN_ICON
    override val initKClass = StdConnNode::class
    
    init {
        if (cp != null) this.cp = cp
    }
    
    @TestOnly
    constructor(name: String) : this(cp = null, name = name)
    
    override fun create(cp: ConnProfile) = initKClass.java.newInstanceX(cp)
}