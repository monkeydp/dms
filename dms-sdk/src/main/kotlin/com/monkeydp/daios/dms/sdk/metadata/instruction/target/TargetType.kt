package com.monkeydp.daios.dms.sdk.metadata.instruction.target

/**
 * @author iPotato
 * @date 2019/10/25
 */
interface TargetType<E>
        where E : TargetType<E>, E : Enum<E>