package com.monkeydp.daios.dms.sdk.metadata.node

/**
 * @author iPotato
 * @date 2019/10/25
 */
abstract class AbstractNode(
        override val def: NodeDef,
        override val info: NodeInfo
) : Node