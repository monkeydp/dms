package com.monkeydp.daios.dms.sdk.metadata.node

import com.monkeydp.daios.dms.sdk.contract.Enumx

/**
 * @author iPotato
 * @date 2019/10/25
 */
interface NodeType<E> : Enumx<E>
        where E : NodeType<E>, E : Enum<E> {
}