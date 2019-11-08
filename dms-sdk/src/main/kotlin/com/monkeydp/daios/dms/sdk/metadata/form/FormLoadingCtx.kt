package com.monkeydp.daios.dms.sdk.metadata.form

import com.monkeydp.daios.dms.sdk.helper.IdHelper
import com.monkeydp.daios.dms.sdk.instruction.Instruction
import com.monkeydp.daios.dms.sdk.mocker.ConnJsonMocker
import com.monkeydp.daios.dms.sdk.mocker.InstrJsonMocker
import io.swagger.annotations.ApiModelProperty

/**
 * @author iPotato
 * @date 2019/11/8
 */
class FormLoadingCtx(
        @ApiModelProperty(required = true, example = ConnJsonMocker.CP_ID)
        val cpId: Long = IdHelper.INVALID_ID,
        @ApiModelProperty(required = true, example = InstrJsonMocker.INSTR)
        val instr: Instruction
)