package com.monkeydp.daios.dms.sdk.metadata.instruction

import com.monkeydp.daios.dms.sdk.metadata.instruction.action.Action
import com.monkeydp.daios.dms.sdk.metadata.instruction.target.Target
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * Instruction = Action + Target
 * @see
 * @author iPotato
 * @date 2019/10/25
 */
interface Instruction {
    val action: Action<*>
    val target: Target<*>
}