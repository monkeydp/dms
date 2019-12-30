package com.monkeydp.daios.dms.sdk.api

import com.monkeydp.daios.dms.sdk.instruction.Instruction
import com.monkeydp.daios.dms.sdk.ui.form.Form

/**
 * @author iPotato
 * @date 2019/10/25
 */
interface FormApi : Api {
    fun loadFrom(instruction: Instruction): Form
}