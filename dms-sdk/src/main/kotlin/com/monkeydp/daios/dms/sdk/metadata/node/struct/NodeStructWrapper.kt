package com.monkeydp.daios.dms.sdk.metadata.node.struct

import com.fasterxml.jackson.databind.JsonNode

/**
 * @author iPotato
 * @date 2019/10/30
 */
interface NodeStructWrapper {
    val structure: JsonNode
}