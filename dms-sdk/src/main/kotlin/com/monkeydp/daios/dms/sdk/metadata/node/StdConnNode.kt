package com.monkeydp.daios.dms.sdk.metadata.node

import com.monkeydp.daios.dms.sdk.entity.ConnProfile

/**
 * @author iPotato
 * @date 2019/10/25
 */
class StdConnNode(def: ConnNode, cp: ConnProfile) : ConnNode, AbstractConnNode(def, cp)
