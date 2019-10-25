package com.monkeydp.daios.dms.sdk.metadata.instruction.action

/**
 * @author iPotato
 * @date 2019/10/25
 */
interface ActionType<E>
        where E : ActionType<E>, E : Enum<E>