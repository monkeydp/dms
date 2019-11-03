package com.monkeydp.daios.dms.sdk.metadata.node.main

import com.monkeydp.daios.dms.sdk.entity.ConnProfile

/**
 * @author iPotato
 * @date 2019/10/27
 */
interface ConnNode : Node {
    val cp: ConnProfile
}