package com.monkeydp.daios.dms.sdk.instruction

import com.fasterxml.jackson.annotation.JsonIgnore
import com.monkeydp.daios.dms.sdk.annot.NeedDatasource
import com.monkeydp.daios.dms.sdk.conn.Conn
import com.monkeydp.daios.dms.sdk.helper.IdHelper
import com.monkeydp.daios.dms.sdk.metadata.node.NodePath
import com.monkeydp.daios.dms.sdk.mocker.ConnJsonMocker
import com.monkeydp.daios.dms.sdk.mocker.InstrJsonMocker
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
@NeedDatasource
class InstrParsingCtx(
        @ApiModelProperty(required = true, example = ConnJsonMocker.CP_ID)
        val cpId: Long = IdHelper.INVALID_ID,
        @ApiModelProperty(required = true, example = InstrJsonMocker.INSTR)
        val instr: Instruction,
        @ApiModelProperty(required = true, example = InstrJsonMocker.NEW_TABLE_USER_INPUT)
        val userInput: UserInput = UserInput(),
        @ApiModelProperty(required = true, example = NODE_PATH)
        val nodePath: NodePath
) {
    
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