package com.monkeydp.daios.dms.sdk.metadata.node

import com.fasterxml.jackson.databind.JsonNode

/**
 * @author iPotato
 * @date 2019/10/30
 */
abstract class AbstractNodeStructWrapper(override val structure: JsonNode) : NodeStructWrapper