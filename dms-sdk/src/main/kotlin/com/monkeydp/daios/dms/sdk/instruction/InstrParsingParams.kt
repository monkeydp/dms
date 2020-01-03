package com.monkeydp.daios.dms.sdk.instruction

import com.monkeydp.daios.dms.sdk.mocker.InstrJsonMocker.NEW_TABLE_INSTR
import com.monkeydp.daios.dms.sdk.mocker.InstrJsonMocker.NEW_TABLE_USER_INPUT
import com.monkeydp.daios.dms.sdk.useful.UserInput
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @author iPotato
 * @date 2019/11/5
 */
@ApiModel
class InstrParsingParams(
        @ApiModelProperty(required = true, example = NEW_TABLE_INSTR)
        val instr: Instruction,
        @ApiModelProperty(required = true, example = NEW_TABLE_USER_INPUT)
        val userInput: UserInput = UserInput()
)