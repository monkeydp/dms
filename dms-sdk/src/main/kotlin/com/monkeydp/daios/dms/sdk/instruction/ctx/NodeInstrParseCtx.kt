package com.monkeydp.daios.dms.sdk.instruction.ctx

import com.fasterxml.jackson.annotation.JsonIgnore
import com.monkeydp.daios.dms.sdk.annot.NeedDatasource
import com.monkeydp.daios.dms.sdk.conn.Conn
import com.monkeydp.daios.dms.sdk.instruction.Instruction
import com.monkeydp.daios.dms.sdk.metadata.node.NodePath
import com.monkeydp.daios.dms.sdk.mocker.NodeJsonMocker.NODE_PATH
import com.monkeydp.daios.dms.sdk.useful.UserInput
import com.monkeydp.daios.dms.sdk.helper.IdHelper
import com.monkeydp.tools.ext.notNullSingleton
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.jetbrains.annotations.TestOnly
import kotlin.properties.Delegates

/**
 * @author iPotato
 * @date 2019/11/5
 */
@ApiModel
@NeedDatasource
class NodeInstrParseCtx(cpId: Long,
                        instr: Instruction,
                        userInput: UserInput = UserInput(),
                        @ApiModelProperty(required = true, example = NODE_PATH)
                        val nodePath: NodePath
) : AbstractInstrParseCtx(cpId, instr, userInput) {
    
    var conn: Conn<*> by Delegates.notNullSingleton()
        @JsonIgnore get
    
    @TestOnly
    constructor(
            conn: Conn<*>,
            instr: Instruction,
            userInput: UserInput = UserInput(),
            nodePath: NodePath
    ) : this(IdHelper.INVALID_ID, instr, userInput, nodePath) {
        this.conn = conn
    }
}