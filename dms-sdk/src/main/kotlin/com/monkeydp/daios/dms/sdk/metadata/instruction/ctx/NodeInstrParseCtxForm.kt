package com.monkeydp.daios.dms.sdk.metadata.instruction.ctx

import com.monkeydp.daios.dms.sdk.annot.NeedDatasource
import com.monkeydp.daios.dms.sdk.conn.Conn
import com.monkeydp.daios.dms.sdk.metadata.instruction.Instruction
import com.monkeydp.daios.dms.sdk.metadata.node.NodePath
import com.monkeydp.daios.dms.sdk.mocker.ConnJsonMocker.CP_ID
import com.monkeydp.daios.dms.sdk.mocker.InstrJsonMocker.INSTR
import com.monkeydp.daios.dms.sdk.mocker.InstrJsonMocker.NEW_TABLE_USER_INPUT
import com.monkeydp.daios.dms.sdk.mocker.NodeJsonMocker.NODE_PATH
import com.monkeydp.daios.dms.sdk.useful.UserInput
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @author iPotato
 * @date 2019/11/5
 */
@ApiModel
@NeedDatasource
data class NodeInstrParseCtxForm(
        @ApiModelProperty(required = true, example = CP_ID)
        override val cpId: Long,
        @ApiModelProperty(required = true, example = INSTR)
        override val instr: Instruction,
        @ApiModelProperty(required = true, example = NEW_TABLE_USER_INPUT)
        override val userInput: UserInput = UserInput(),
        @ApiModelProperty(required = true, example = NODE_PATH)
        val nodePath: NodePath
) : AbstractInstrParseCtxForm() {
    fun toInner(conn: Conn<*>) = NodeInstrParseCtx(instr, userInput, conn, nodePath)
}