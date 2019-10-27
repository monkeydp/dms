package com.monkeydp.daios.dms.sdk.metadata.node

import com.fasterxml.jackson.annotation.JsonIgnore
import com.monkeydp.daios.dms.sdk.conn.Conn
import com.monkeydp.daios.dms.sdk.metadata.instruction.AbstractInstr
import com.monkeydp.daios.dms.sdk.metadata.instruction.action.Action
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.Target

/**
 * @author iPotato
 * @date 2019/10/25
 */
data class NodeLoadInstr(
        override val action: Action<*>,
        override val target: Target<*>,
        val cpId: Long,
        @JsonIgnore
        val conn: Conn,
        val nodePath: NodePath
) : AbstractInstr()