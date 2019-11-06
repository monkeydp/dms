package com.monkeydp.daios.dms.sdk.instruction.ctx

import com.monkeydp.daios.dms.sdk.instruction.Instruction
import com.monkeydp.daios.dms.sdk.mocker.ConnJsonMocker
import com.monkeydp.daios.dms.sdk.mocker.InstrJsonMocker
import com.monkeydp.daios.dms.sdk.useful.UserInput
import com.monkeydp.daios.dms.sdk.helper.IdHelper
import io.swagger.annotations.ApiModelProperty

/**
 * @author iPotato
 * @date 2019/11/5
 */
abstract class AbstractInstrParseCtx(
        @ApiModelProperty(required = true, example = ConnJsonMocker.CP_ID)
        override val cpId: Long = IdHelper.INVALID_ID,
        @ApiModelProperty(required = true, example = InstrJsonMocker.INSTR)
        override val instr: Instruction,
        @ApiModelProperty(required = true, example = InstrJsonMocker.NEW_TABLE_USER_INPUT)
        override val userInput: UserInput = UserInput()
) : InstrParseCtx