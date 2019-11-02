package com.monkeydp.daios.dms.sdk.metadata.node

import com.monkeydp.daios.dms.sdk.entity.ConnProfile
import org.jetbrains.annotations.TestOnly

/**
 * @author iPotato
 * @date 2019/10/25
 */
class StdConnNode : ConnNode, AbstractConnNode {
    constructor(cp: ConnProfile) : super(cp)
    @TestOnly
    constructor(name: String) : super(cp = null, name = name)
}
