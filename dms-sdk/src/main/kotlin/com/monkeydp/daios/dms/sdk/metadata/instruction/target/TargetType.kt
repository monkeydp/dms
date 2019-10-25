package com.monkeydp.daios.dms.sdk.metadata.instruction.target

import com.monkeydp.daios.dms.sdk.contract.Enumx

/**
 * @author iPotato
 * @date 2019/10/25
 */
interface TargetType<E> : Enumx<E>
        where E : TargetType<E>, E : Enum<E>