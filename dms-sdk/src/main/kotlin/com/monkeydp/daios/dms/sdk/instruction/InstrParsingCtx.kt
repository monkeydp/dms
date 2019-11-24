package com.monkeydp.daios.dms.sdk.instruction

import com.fasterxml.jackson.annotation.JsonIgnore
import com.monkeydp.daios.dms.sdk.conn.Conn
import com.monkeydp.daios.dms.sdk.conn.HasCpId
import com.monkeydp.daios.dms.sdk.helper.IdHelper.INVALID_ID
import com.monkeydp.daios.dms.sdk.instruction.target.GlobalTarget.NONE
import com.monkeydp.daios.dms.sdk.instruction.target.Target
import com.monkeydp.daios.dms.sdk.metadata.node.NodePath
import com.monkeydp.daios.dms.sdk.mocker.ConnJsonMocker.CP_ID
import com.monkeydp.daios.dms.sdk.mocker.InstrJsonMocker.INSTR
import com.monkeydp.daios.dms.sdk.mocker.InstrJsonMocker.NEW_TABLE_USER_INPUT
import com.monkeydp.daios.dms.sdk.mocker.InstrJsonMocker.SELECTED
import com.monkeydp.daios.dms.sdk.mocker.NodeJsonMocker.NODE_PATH
import com.monkeydp.daios.dms.sdk.useful.UserInput
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
class InstrParsingCtx(
        @ApiModelProperty(required = true, example = CP_ID)
        override val cpId: Long,
        @ApiModelProperty(required = true, example = INSTR)
        val instr: Instruction,
        @ApiModelProperty(required = true, example = NEW_TABLE_USER_INPUT)
        val userInput: UserInput = UserInput(),
        @ApiModelProperty(required = true, example = NODE_PATH)
        val nodePath: NodePath,
        @ApiModelProperty(value = "user selected target", required = true, example = SELECTED)
        val selected: Target<*>
) : HasCpId {
    var conn: Conn<*> by Delegates.notNullSingleton()
        @JsonIgnore get
    
    @TestOnly
    constructor(
            conn: Conn<*>,
            instr: Instruction,
            userInput: UserInput = UserInput(),
            nodePath: NodePath,
            selected: Target<*> = NONE
    ) : this(INVALID_ID, instr, userInput, nodePath, selected) {
        this.conn = conn
    }
}