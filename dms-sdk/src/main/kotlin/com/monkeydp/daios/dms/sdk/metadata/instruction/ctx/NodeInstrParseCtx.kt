package com.monkeydp.daios.dms.sdk.metadata.instruction.ctx

import com.monkeydp.daios.dms.sdk.conn.Conn
import com.monkeydp.daios.dms.sdk.metadata.instruction.Instruction
import com.monkeydp.daios.dms.sdk.metadata.node.NodePath
import com.monkeydp.daios.dms.sdk.useful.UserInput

/**
 * @author iPotato
 * @date 2019/11/5
 */
data class NodeInstrParseCtx(
        override val instr: Instruction,
        override val userInput: UserInput = UserInput(),
        val conn: Conn<*>,
        val nodePath: NodePath
) : AbstractInstrParseCtx()