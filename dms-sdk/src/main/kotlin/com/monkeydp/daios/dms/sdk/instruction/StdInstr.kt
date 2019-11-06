package com.monkeydp.daios.dms.sdk.instruction

import com.monkeydp.daios.dms.sdk.instruction.action.Action
import com.monkeydp.daios.dms.sdk.instruction.target.Target

/**
 * @author iPotato
 * @date 2019/11/4
 */
class StdInstr(
        override val action: Action<*>,
        override val target: Target<*>
) : AbstractInstr()