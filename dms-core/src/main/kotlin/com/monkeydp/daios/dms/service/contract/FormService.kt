package com.monkeydp.daios.dms.service.contract

import com.monkeydp.daios.dms.sdk.instruction.Instruction
import com.monkeydp.daios.dms.sdk.ui.form.Form

/**
 * @author iPotato
 * @date 2019/11/1
 */
interface FormService {
    fun loadForm(instruction: Instruction): Form
}